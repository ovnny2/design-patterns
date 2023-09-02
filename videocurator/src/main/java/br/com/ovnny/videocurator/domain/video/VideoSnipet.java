package br.com.ovnny.videocurator.domain.videosnipet;

import br.com.ovnny.videocurator.domain.playlist.Snipet;
import br.com.ovnny.videocurator.domain.playlist.Thumbnail;
import jakarta.validation.constraints.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class VideoSnipet {

    @NotBlank
    public String videoId;

    @NotBlank
    public String title;

    @NotBlank
    public String description;

    @NotBlank
    public String videoOwnerChannelTitle;

    @NotBlank @Past
    public String publishedAt;

    @NotBlank @Indexed
    public String channelId;

    @NotBlank
    public String videoOwnerChannelId;

    @NotBlank
    public String channelTitle;

    @NotNull
    public int position;

    @NotEmpty @Size(max = 4)
    public List<Thumbnail> thumbnails;

    public String state;

    public int tries = 0;

    @Deprecated
    public VideoSnipet() {
    }

    public VideoSnipet(Snipet snipet, State state) {
        this.videoId = snipet.getResourceId().getVideoId();
        this.title = snipet.getTitle();
        this.description = snipet.getDescription();
        this.videoOwnerChannelTitle = snipet.getVideoOwnerChannelTitle();
        this.publishedAt = snipet.getPublishedAt();
        this.position = snipet.getPosition();
        this.channelId = snipet.getChannelId();
        this.videoOwnerChannelId = snipet.getVideoOwnerChannelId();
        this.channelTitle = snipet.getChannelTitle();
        this.state = String.valueOf(state);
    }

    public String getVideoId() {
        return videoId;
    }

    public String getTitle() { return title; }

    public String getDescription() {
        return description;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public int getPosition() {
        return position;
    }

    public String getChannelId() {
        return channelId;
    }

    public String getVideoOwnerChannelId() {
        return videoOwnerChannelId;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public String getVideoOwnerChannelTitle() {
        return videoOwnerChannelTitle;
    }

    public List<Thumbnail> getThumbnails() {
        return thumbnails;
    }

    public String getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state.toString();
    }

    public int getTries() { return tries; }

    public void setTries(int tries) { this.tries = tries; }
}