package br.com.ovnny.videocurator.service;

import br.com.ovnny.videocurator.client.YoutubeClient;
import br.com.ovnny.videocurator.config.JsonStubLoader;
import br.com.ovnny.videocurator.domain.PlaylistClientResponse;
import br.com.ovnny.videocurator.repository.PlaylistRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
class VideoServiceTest {

    @Mock
    private YoutubeClient youtubeClient;

    @Mock
    private PlaylistRepository playlistRepository;

    @InjectMocks
    private VideoService videoService;

    @Autowired
    private final ObjectMapper mapper = new ObjectMapper().configure(JsonParser.Feature.IGNORE_UNDEFINED, true);

    @Test
    @DisplayName("Should return a PlaylistViewResponse given a valid youtube playlist url")
    void createPlaylist() throws IOException {

        var loader = new JsonStubLoader<>(mapper, PlaylistClientResponse.class);


        System.out.println(loader.load("stub/playlist-client-response.json"));

    }
}