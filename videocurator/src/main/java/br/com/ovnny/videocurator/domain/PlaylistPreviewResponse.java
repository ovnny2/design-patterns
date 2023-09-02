package br.com.ovnny.videocurator.domain;

import br.com.ovnny.videocurator.domain.video.VideoSnipet;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class PlaylistPreviewResponse {

    @Id
    public String playlistId;
    public List<VideoSnipet> playlist;

    public PlaylistPreviewResponse(String playlistId, List<VideoSnipet> videoSnipets) {
        this.playlistId = playlistId;
        this.playlist = videoSnipets;
    }

    @Deprecated
    public PlaylistPreviewResponse() {
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public List<VideoSnipet> getPlaylist() {
        return playlist;
    }
}