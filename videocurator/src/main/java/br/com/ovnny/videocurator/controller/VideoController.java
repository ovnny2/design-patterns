package br.com.ovnny.videocurator.controller;

import br.com.ovnny.videocurator.domain.PlaylistRequest;
import br.com.ovnny.videocurator.domain.PlaylistPreviewResponse;
import br.com.ovnny.videocurator.service.VideoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/v1")
public class VideoController {

    final private VideoService service;

    @Autowired
    public VideoController(VideoService service) {
        this.service = service;
    }

    @PostMapping("/playlists")
    public ResponseEntity<PlaylistPreviewResponse> getPlaylistItems(@RequestBody @Valid PlaylistRequest request) {
        var response = (PlaylistPreviewResponse) service.createPlaylist(request.getPlaylistUrl());
        URI path = URI.create("/v1/playlists/" + response.playlistId);

        return ResponseEntity.created(path).body(response);
    }
}