package br.com.ovnny.videocurator.domain;

import br.com.ovnny.videocurator.domain.video.VideoSnippet;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class PlaylistPreviewResponse {

    @Id
    public String playlistId;
    public List<VideoSnippet> playlist;

    public PlaylistPreviewResponse(String playlistId, List<VideoSnippet> videoSnippets) {
        this.playlistId = playlistId;
        this.playlist = videoSnippets;
    }

    @Deprecated
    public PlaylistPreviewResponse() {
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public List<VideoSnippet> getPlaylist() {
        return playlist;
    }
}