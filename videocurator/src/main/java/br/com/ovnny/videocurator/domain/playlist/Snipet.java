package br.com.ovnny.videocurator.domain.playlist;

public class Snipet {
    public String publishedAt;
    public String channelId;
    public String title;
    public String description;
    public Thumbnails thumbnails;
    public String channelTitle;
    public String playlistId;
    public int position;
    public ResourceId resourceId;
    public String videoOwnerChannelTitle;
    public String videoOwnerChannelId;

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getChannelId() {
        return channelId;
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

    public String getChannelTitle() {
        return channelTitle;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public int getPosition() {
        return position;
    }

    public ResourceId getResourceId() {
        return resourceId;
    }

    public String getVideoOwnerChannelTitle() {
        return videoOwnerChannelTitle;
    }

    public String getVideoOwnerChannelId() {
        return videoOwnerChannelId;
    }

    private void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    private void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    private void setThumbnails(Thumbnails thumbnails) {
        this.thumbnails = thumbnails;
    }

    private void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    private void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    private void setPosition(int position) {
        this.position = position;
    }

    private void setResourceId(ResourceId resourceId) {
        this.resourceId = resourceId;
    }

    private void setVideoOwnerChannelTitle(String videoOwnerChannelTitle) {
        this.videoOwnerChannelTitle = videoOwnerChannelTitle;
    }

    private void setVideoOwnerChannelId(String videoOwnerChannelId) {
        this.videoOwnerChannelId = videoOwnerChannelId;
    }
}