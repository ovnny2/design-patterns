package br.com.ovnny.videocurator.domain.video;

import br.com.ovnny.videocurator.domain.playlist.Snipet;
import br.com.ovnny.videocurator.domain.playlist.Thumbnails;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class VideoSnipet {

    @Id
    public String id;

    @NotBlank
    public String channelTitle;

    @NotBlank
    public String videoOwnerChannelTitle;

    @NotBlank
    @Past
    public String publishedAt;

    @NotBlank
    @Indexed
    public String channelId;

    @NotBlank
    public String videoOwnerChannelId;

    @NotNull
    public int position;

    @Size(max = 32)
    public String state;

    public int tries = 0;

    @NotBlank
    public String title;

    @NotBlank
    public String description;

    public Thumbnails thumbnails;


    @Deprecated
    public VideoSnipet() {
    }

    public VideoSnipet(
            String id,
            String channelTitle,
            String videoOwnerChannelTitle,
            String publishedAt,
            String channelId,
            String videoOwnerChannelId,
            int position,
            String state,
            int tries,
            String title,
            String description,
            Thumbnails thumbnails
    ) {
        this.id = id;
        this.channelTitle = channelTitle;
        this.videoOwnerChannelTitle = videoOwnerChannelTitle;
        this.publishedAt = publishedAt;
        this.channelId = channelId;
        this.videoOwnerChannelId = videoOwnerChannelId;
        this.position = position;
        this.state = state;
        this.tries = tries;
        this.title = title;
        this.description = description;
        this.thumbnails = thumbnails;
    }

    public VideoSnipet(Snipet snipet, State state) {
        this.id = snipet.getResourceId().getVideoId();
        this.channelTitle = snipet.getChannelTitle();
        this.videoOwnerChannelTitle = snipet.getVideoOwnerChannelTitle();
        this.publishedAt = snipet.getPublishedAt();
        this.channelId = snipet.getChannelId();
        this.videoOwnerChannelId = snipet.getVideoOwnerChannelId();
        this.position = snipet.getPosition();
        this.state = String.valueOf(state);
        this.title = snipet.getTitle();
        this.description = snipet.getDescription();
        this.thumbnails = snipet.getThumbnails();
    }

    public String getId() {
        return id;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public String getVideoOwnerChannelTitle() {
        return videoOwnerChannelTitle;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getChannelId() {
        return channelId;
    }

    public String getVideoOwnerChannelId() {
        return videoOwnerChannelId;
    }

    public int getPosition() {
        return position;
    }

    public String getState() {
        return state;
    }

    public int getTries() {
        return tries;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Thumbnails getThumbnails() {
        return thumbnails;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setTries(int tries) {
        this.tries = tries;
    }
}