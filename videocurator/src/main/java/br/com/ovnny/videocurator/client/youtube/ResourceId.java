package br.com.ovnny.videocurator.client.youtube;

public class ResourceId {

    public String kind;
    public String videoId;

    public String getKind() {
        return kind;
    }

    public String getVideoId() {
        return videoId;
    }

    private void setKind(String kind) {
        this.kind = kind;
    }

    private void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}