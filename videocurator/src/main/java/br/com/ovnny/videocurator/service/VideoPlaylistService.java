package br.com.ovnny.videocurator.service;

import br.com.ovnny.videocurator.client.YoutubeClient;
import br.com.ovnny.videocurator.utils.ParamExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoPlaylistService {

    YoutubeClient youtubeClient;

    @Autowired
    public VideoPlaylistService(YoutubeClient youtubeClient) {
        this.youtubeClient = youtubeClient;
    }

    public Object fetchPlaylistItems(String playlistUrl) {
        var playlistId = ParamExtractor.extractVideoId(playlistUrl);

        return youtubeClient.getYoutubePlaylistItems(playlistId);
    }
}