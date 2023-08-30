package br.com.ovnny.videocurator.controller;

import br.com.ovnny.videocurator.domain.PlaylistRequest;
import br.com.ovnny.videocurator.domain.videosnipet.PlaylistView;
import br.com.ovnny.videocurator.service.VideoService;
import br.com.ovnny.videocurator.utils.ParamExtractor;
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

    VideoService service;

    @Autowired
    public VideoController(VideoService service) {
        this.service = service;
    }

    @PostMapping("/playlists")
    public ResponseEntity<?> getPlaylistItems(@Valid @RequestBody PlaylistRequest request) {
        var response = (PlaylistView) service.createPlaylist(request.getPlaylistUrl());
        var resource = ParamExtractor.extractPlaylistId(request.getPlaylistUrl());
        URI path = URI.create("/v1/playlists/" + resource);

        return ResponseEntity.created(path).body(response);
    }
}