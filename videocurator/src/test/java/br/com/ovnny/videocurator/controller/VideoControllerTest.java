package br.com.ovnny.videocurator.controller;

import br.com.ovnny.videocurator.domain.PlaylistPreviewResponse;
import br.com.ovnny.videocurator.domain.PlaylistRequest;
import br.com.ovnny.videocurator.domain.playlist.Thumbnails;
import br.com.ovnny.videocurator.domain.video.VideoSnipet;
import br.com.ovnny.videocurator.service.VideoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VideoControllerTest {

    @Mock
    private VideoService videoService;

    @InjectMocks
    private VideoController videoController;

    @Test
    @DisplayName("should return a PlaylistPreviewResponse for any given valid playlist url")
    void testGetPlaylistItemsValidRequest() {
        var validRequest = new PlaylistRequest("test@gmail.com", anyString());

        var videoSnipet = new VideoSnipet(
                "123",
                "test",
                "Test Channel",
                "456",
                "999",
                "45vds",
                0,
                "NOT_PROCESSED",
                0,
                "Test Title",
                "Test Description",
                new Thumbnails()
        );

        var mockResponse = new PlaylistPreviewResponse(videoSnipet.id, List.of(videoSnipet));

        when(videoService.createPlaylist(validRequest.getPlaylistUrl())).thenReturn(mockResponse);

        var expectedResult = videoController.getPlaylistItems(validRequest);

        assertEquals(expectedResult.getBody(), mockResponse);
        assertEquals(expectedResult.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    @DisplayName("should return an ErrorMessage for any given invalid request entry")
    void testGetPlaylistItemsInvalidRequest() {

    }
}