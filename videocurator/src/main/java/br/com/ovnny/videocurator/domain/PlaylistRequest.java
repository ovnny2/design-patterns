package br.com.ovnny.videocurator.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

@Validated
public class PlaylistRequest {

    @NotBlank @Email @Size(max = 64)
    String email;

    @Pattern(
            regexp = "^(https?://)?(www\\.)?youtube\\.com/playlist\\?list=[A-Za-z0-9_-]+$",
            message = "A url é inválida.\n Exemplo: https://www.youtube.com/playlist?list={PlaylistId}"
    )
    @NotBlank @Size(max = 128)
    String playlistUrl;

    public PlaylistRequest(@Valid String email, @Valid String playlistUrl) {
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