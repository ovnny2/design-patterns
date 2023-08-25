package br.com.ovnny.videocurator.domain.playlist;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class PlaylistClientResponse {
    public String kind;
    public String etag;
    public List<PlaylistItem> items;
    public ContentDetails contentDetails;

    public String getKind() {
        return kind;
    }

    public String getEtag() {
        return etag;
    }

    public List<PlaylistItem> getItems() {
        return items;
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

    private void setItems(List<PlaylistItem> items) {
        this.items = items;
    }

    private void setContentDetails(ContentDetails contentDetails) {
        this.contentDetails = contentDetails;
    }
}