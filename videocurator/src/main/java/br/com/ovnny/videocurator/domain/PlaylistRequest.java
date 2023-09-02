package br.com.ovnny.videocurator.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

@Validated
public class PlaylistRequest {

    @NotBlank @Size(max=128)
    @Pattern(regexp = "\\b[A-Za-z0-9._%+-]+@gmail\\.com\\b")
    String email;

    @NotBlank @Size(max=256)
    String playlistUrl;

    public PlaylistRequest(String email, String playlistUrl) {
        this.email = email;
        this.playlistUrl = playlistUrl;
    }

    public String getEmail() {
        return email;
    }

    public String getPlaylistUrl() {
        return playlistUrl;
    }
}