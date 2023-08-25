package br.com.ovnny.videocurator.controller;

import br.com.ovnny.videocurator.domain.playlist.PlaylistClientResponse;
import br.com.ovnny.videocurator.service.VideoPlaylistService;
import br.com.ovnny.videocurator.domain.YoutubePlaylistRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class YoutubeController {

    VideoPlaylistService service;

    @Autowired
    public YoutubeController(VideoPlaylistService service) {
        this.service = service;
    }

    @GetMapping("/youtubePlaylists")
    public ResponseEntity<?> getPlaylistItems(@RequestBody YoutubePlaylistRequest request) {
        var response = (PlaylistClientResponse) service.fetchPlaylistItems(request.getPlaylistUrl());

        return ResponseEntity.ok(response);
    }
}