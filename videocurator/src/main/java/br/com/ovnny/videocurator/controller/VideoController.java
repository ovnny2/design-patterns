package br.com.ovnny.videocurator.controller;

import br.com.ovnny.videocurator.domain.PlaylistRequest;
import br.com.ovnny.videocurator.domain.PlaylistPreviewResponse;
import br.com.ovnny.videocurator.domain.playlist.PlaylistHeader;
import br.com.ovnny.videocurator.service.VideoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<PlaylistHeader> createPlaylist(@RequestBody @Valid PlaylistRequest request) {
        var response = (PlaylistHeader) service.createPlaylist(request.getPlaylistUrl(), request.getEmail());
        URI path = URI.create("/v1/playlists/" + response.getPlaylistId());

        return ResponseEntity.created(path).body(response);
    }
}