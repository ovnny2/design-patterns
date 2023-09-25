package br.com.ovnny.videocurator.domain.playlist;

import java.time.LocalDateTime;
import java.util.Map;

public class PlaylistHeader {
    final String playlistId;
    final String userEmail;
    public Map<String, PlaylistData> playlistData;
    final LocalDateTime createdAt;
    public LocalDateTime updatedAt;

    public PlaylistHeader(
            String playlistId,
            String userEmail,
            Map<String, PlaylistData> playlistData,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.playlistId = playlistId;
        this.userEmail = userEmail;
        this.playlistData = playlistData;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public Map<String, PlaylistData> getPlaylistData() {
        return playlistData;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setPlaylistData(Map<String, PlaylistData> playlistData) {
        this.playlistData = playlistData;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}