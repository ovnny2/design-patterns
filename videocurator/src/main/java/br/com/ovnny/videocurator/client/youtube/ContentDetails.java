package br.com.ovnny.videocurator.client.youtube;

public class ContentDetails {
    public String videoId;
    public String videoPublishedAt;

    public String getVideoId() {
        return videoId;
    }

    public String getVideoPublishedAt() {
        return videoPublishedAt;
    }

    private void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    private void setVideoPublishedAt(String videoPublishedAt) {
        this.videoPublishedAt = videoPublishedAt;
    }
}