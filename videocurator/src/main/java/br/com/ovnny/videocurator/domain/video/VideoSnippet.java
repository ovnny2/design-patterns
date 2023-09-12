package br.com.ovnny.videocurator.domain.video;

import br.com.ovnny.videocurator.domain.playlist.Snippet;
import br.com.ovnny.videocurator.domain.playlist.Thumbnails;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class VideoSnippet {

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

    @Size(max = 16)
    public String state;

    public int tries = 0;

    @NotBlank
    public String title;

    @NotBlank
    public String description;

    public Thumbnails thumbnails;


    @Deprecated
    public VideoSnippet() {
    }

    public VideoSnippet(
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

    public VideoSnippet(Snippet snippet, State state) {
        this.id = snippet.getResourceId().getVideoId();
        this.channelTitle = snippet.getChannelTitle();
        this.videoOwnerChannelTitle = snippet.getVideoOwnerChannelTitle();
        this.publishedAt = snippet.getPublishedAt();
        this.channelId = snippet.getChannelId();
        this.videoOwnerChannelId = snippet.getVideoOwnerChannelId();
        this.position = snippet.getPosition();
        this.state = String.valueOf(state);
        this.title = snippet.getTitle();
        this.description = snippet.getDescription();
        this.thumbnails = snippet.getThumbnails();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public String getVideoOwnerChannelTitle() {
        return videoOwnerChannelTitle;
    }

    public void setVideoOwnerChannelTitle(String videoOwnerChannelTitle) {
        this.videoOwnerChannelTitle = videoOwnerChannelTitle;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getVideoOwnerChannelId() {
        return videoOwnerChannelId;
    }

    public void setVideoOwnerChannelId(String videoOwnerChannelId) {
        this.videoOwnerChannelId = videoOwnerChannelId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getTries() {
        return tries;
    }

    public void setTries(int tries) {
        this.tries = tries;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Thumbnails getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(Thumbnails thumbnails) {
        this.thumbnails = thumbnails;
    }
}