package br.com.ovnny.videocurator.domain.playlist;

public class PlaylistItem {
    public String kind;
    public String etag;
    public String id;
    public Snipet snippet;

    public String getKind() {
        return kind;
    }

    public String getEtag() {
        return etag;
    }

    public String getId() {
        return id;
    }

    public Snipet getSnipet() {
        return snippet;
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

    private void setSnipet(Snipet snippet) {
        this.snippet = snippet;
    }
}