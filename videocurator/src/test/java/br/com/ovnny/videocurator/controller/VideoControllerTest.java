package br.com.ovnny.videocurator.controller;

import br.com.ovnny.videocurator.domain.PlaylistPreviewResponse;
import br.com.ovnny.videocurator.domain.PlaylistRequest;
import br.com.ovnny.videocurator.client.youtube.Thumbnails;
import br.com.ovnny.videocurator.domain.video.VideoSnippet;
import br.com.ovnny.videocurator.service.VideoService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.net.URI;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VideoControllerTest {

    @Mock
    private VideoService videoService;

    @InjectMocks
    private VideoController videoController;

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    @DisplayName("should return a PlaylistPreviewResponse for any given valid playlist url")
    void testGetPlaylistItemsValidRequest() {
        var validRequest = new PlaylistRequest("test@gmail.com", anyString());

        var videoSnippet = new VideoSnippet(
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

        var mockResponse = new PlaylistPreviewResponse(videoSnippet.id, List.of(videoSnippet));

        when(videoService.createPlaylist(validRequest.getPlaylistUrl())).thenReturn(mockResponse);

        var expectedResult = videoController.createPlaylist(validRequest);

        assertEquals(mockResponse, expectedResult.getBody());
        assertEquals(HttpStatus.CREATED, expectedResult.getStatusCode());
        assertEquals(URI.create("/v1/playlists/" + videoSnippet.getId()), expectedResult.getHeaders().getLocation());
    }

    @Test
    @DisplayName("should return an ConstraintViolationImpl for any given invalid email")
    void testGetPlaylistItemsInvalidEmailFormat() {

        var invalidRequest = new PlaylistRequest("invalid email", "https://www.youtube.com/playlist?list=PLjAku6QgtOCcCHqGD5JJX-qBdYL6g8C0q");

        Set<ConstraintViolation<PlaylistRequest>> violations = validator.validate(invalidRequest);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
    }

    @Test
    @DisplayName("should return an ConstraintViolationImpl for any given invalid playlistUrl")
    void testGetPlaylistItemsInvalidPlaylistUrl() {

        var invalidRequest = new PlaylistRequest("example@gmail.com", "www.youtube.com/playlist?Invalid=PLjAku6QgtOCcCHqGD5JJX-qBdYL6g8C0q");

        Set<ConstraintViolation<PlaylistRequest>> violations = validator.validate(invalidRequest);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
    }

    @Test
    @DisplayName("should return an ConstraintViolationImpl for any given invalid PlaylistRequest")
    void testGetPlaylistItemsInvalidRequestParams() {

        var invalidRequest = new PlaylistRequest("invalidEmailFormat.com", null);

        Set<ConstraintViolation<PlaylistRequest>> violations = validator.validate(invalidRequest);

        assertFalse(violations.isEmpty());
        assertEquals(2, violations.size());
    }

    @Test
    @DisplayName("should return an ConstraintViolationImpl for any given invalid size params")
    void testGetPlaylistItemsInvalidRequestParamsSize() {
        var invalidEmailSize = "invalidEmailFormat.com65bytes....................................";
        var invalidPlaylistUrlSize = "invalidPlaylistUrlSize129bytes...................................................................................................";

        var invalidRequest = new PlaylistRequest(invalidEmailSize, invalidPlaylistUrlSize);

        Set<ConstraintViolation<PlaylistRequest>> violations = validator.validate(invalidRequest);

        assertFalse(violations.isEmpty());
        assertEquals(4, violations.size());
    }
}