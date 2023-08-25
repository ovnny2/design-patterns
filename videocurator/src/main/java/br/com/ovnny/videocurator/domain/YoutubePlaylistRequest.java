package br.com.ovnny.videocurator.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

@Validated
public class YoutubePlaylistRequest {

    @NotBlank @Size(min=16 , max=128)
    @Pattern(regexp = "\\b[A-Za-z0-9._%+-]+@gmail\\.com\\b")
    String email;

    @NotNull @Size(min=8, max=32)
    String playlistUrl;

    public YoutubePlaylistRequest(String email, String playlistUrl) {
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