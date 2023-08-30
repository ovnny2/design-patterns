package br.com.ovnny.videocurator.domain.videosnipet;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class PlaylistView {

    @Id
    public String playlistId;
    public List<VideoSnipet> playlist;

    public PlaylistView(String playlistId, List<VideoSnipet> videoSnipets) {
        this.playlistId = playlistId;
        this.playlist = videoSnipets;
    }

    public PlaylistView() {
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public List<VideoSnipet> getPlaylist() {
        return playlist;
    }
}