package br.com.ovnny.videocurator.service;

import br.com.ovnny.videocurator.client.YoutubeClient;
import br.com.ovnny.videocurator.config.JsonStubLoader;
import br.com.ovnny.videocurator.client.PlaylistClientResponse;
import br.com.ovnny.videocurator.domain.PlaylistPreviewResponse;
import br.com.ovnny.videocurator.domain.video.State;
import br.com.ovnny.videocurator.domain.video.VideoSnippet;
import br.com.ovnny.videocurator.exception.PlaylistException;
import br.com.ovnny.videocurator.repository.PlaylistRepository;
import br.com.ovnny.videocurator.utils.ParamExtractor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VideoServiceTest {

    @Mock
    private YoutubeClient youtubeClient;

    @Mock
    private PlaylistRepository playlistRepository;

    @InjectMocks
    private VideoService videoService;

    @Autowired
    private static final ObjectMapper mapper = new ObjectMapper().configure(JsonParser.Feature.IGNORE_UNDEFINED, true);

    private static PlaylistClientResponse playlistClientResponseStub;

    @BeforeAll
    public static void buildStubsFromJsonFile() throws IOException {
        var loader = new JsonStubLoader<>(mapper, PlaylistClientResponse.class);
        playlistClientResponseStub = loader.load("stub/playlist-client-response.json");
    }

    @Test
    @DisplayName("Should return a PlaylistViewResponse given a valid youtube playlist url")
    void createPlaylist() {

        var playlistId = ParamExtractor
                .extractPlaylistId("https://www.youtube.com/playlist?list=PLjAku6QgtOCcCHqGD5JJX-qBdYL6g8C0q");

        var playlistItems = playlistClientResponseStub.getItems();

        List<VideoSnippet> videoSnippets = playlistItems.stream().map(item -> new VideoSnippet(item.getSnippet(), State.NOT_PROCESSED)).toList();

        PlaylistPreviewResponse expected = new PlaylistPreviewResponse(playlistId, videoSnippets);

        when(playlistRepository.findById(playlistId)).thenReturn(Optional.empty());
        when(youtubeClient.fetchPlaylistItems(playlistId)).thenReturn(playlistClientResponseStub);

        var result = videoService.createPlaylist(playlistId);

        assertEquals(expected.getPlaylistId(), result.getPlaylistId());
        assertEquals(result.getPlaylist().size(), expected.getPlaylist().size());
        assertEquals(result.getPlaylist().get(new Random().nextInt(0, videoSnippets.size())).getState(), String.valueOf(State.NOT_PROCESSED));
        assertTrue(result instanceof PlaylistPreviewResponse);
    }

    @Test
    @DisplayName("Should throw PlaylistException when the given playlist already exists")
    public void testCreatePlaylistWithExistingPlaylist() {
        String url = "https://www.youtube.com/playlist?list=PLjAku6QgtOCcCHqGD5JJX-qBdYL6g8C0q";
        String existingPlaylistId = "PLjAku6QgtOCcCHqGD5JJX-qBdYL6g8C0q";

        PlaylistClientResponse playlistStub = getPlaylistClientResponseStub();

        List<VideoSnippet> playlistPreviewResponseMock = playlistStub.getItems().stream()
                .map(item -> new VideoSnippet(item.getSnippet(), State.NOT_PROCESSED))
                .toList();

        when(playlistRepository.findById(existingPlaylistId))
                .thenReturn(Optional.of(new PlaylistPreviewResponse(existingPlaylistId, playlistPreviewResponseMock)));

        PlaylistException exception = assertThrows(PlaylistException.class, () -> videoService.createPlaylist(url));

        HttpStatus expectedHttpStatus = HttpStatus.BAD_REQUEST;

        assertEquals(expectedHttpStatus, exception.getStatus());

        Mockito.verify(playlistRepository, times(1)).findById(existingPlaylistId);
    }

    private static PlaylistClientResponse getPlaylistClientResponseStub() {
        return playlistClientResponseStub;
    }
}