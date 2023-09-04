package br.com.ovnny.videocurator.service;

import br.com.ovnny.videocurator.client.YoutubeClient;
import br.com.ovnny.videocurator.domain.PlaylistClientResponse;
import br.com.ovnny.videocurator.domain.PlaylistPreviewResponse;
import br.com.ovnny.videocurator.domain.playlist.*;
import br.com.ovnny.videocurator.domain.video.State;
import br.com.ovnny.videocurator.domain.video.VideoSnipet;
import br.com.ovnny.videocurator.repository.PlaylistRepository;
import br.com.ovnny.videocurator.utils.ParamExtractor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VideoServiceTest {

    @Mock
    private YoutubeClient youtubeClient;

    @Mock
    private PlaylistRepository playlistRepository;

    @InjectMocks
    private VideoService videoService;

    @Test
    @DisplayName("Should return a PlaylistViewResponse given a valid youtube playlist url")
    void createPlaylist() {

        var playlistUrl = "https://www.youtube.com/playlist?list=PLjAku6QgtOCcCHqGD5JJX-qBdYL6g8C0q";
        var playlistId = ParamExtractor.extractPlaylistId(playlistUrl);



//        var clientResponse =
//
//        List<VideoSnipet> snipets = clientResponse.getItems().stream()
//                .map(item -> new VideoSnipet(item.getSnipet(), State.NOT_PROCESSED))
//                .toList();
//
//        PlaylistPreviewResponse playlistPreview = new PlaylistPreviewResponse(playlistId, snipets);
//
//        when(youtubeClient.fetchPlaylistItems(playlistId)).thenReturn(clientResponse);
//
//        when(playlistRepository.findById(playlistId)).thenReturn(Optional.empty());
//
//        var response = videoService.createPlaylist(playlistUrl);
//
//        assertEquals(playlistPreview.getPlaylistId(), response.getPlaylistId());
//        assertEquals(response.getPlaylist().size(), playlistPreview.getPlaylist().size());
//        assertInstanceOf(PlaylistPreviewResponse.class, response);
//        verify(playlistRepository, times(1)).findById(playlistId);
//
    }
}