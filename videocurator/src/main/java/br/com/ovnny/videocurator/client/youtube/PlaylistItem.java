package br.com.ovnny.videocurator.client.youtube;

public class PlaylistItem {
    public String kind;
    public String etag;
    public String id;
    public Snippet snippet;
    public ContentDetails contentDetails;

    public String getKind() {
        return kind;
    }

    public String getEtag() {
        return etag;
    }

    public String getId() {
        return id;
    }

    public Snippet getSnippet() {
        return snippet;
    }

    public ContentDetails getContentDetails() {
        return contentDetails;
    }

    private void setKind(String kind) {
        this.kind = kind;
    }

    private void setEtag(String etag) {
        this.etag = etag;
    }

    private void setId(String id) {
        this.id = id;
    }

    private void setSnippet(Snippet snippet) {
        this.snippet = snippet;
    }

    public void setContentDetails(ContentDetails contentDetails) {
        this.contentDetails = contentDetails;
    }
}