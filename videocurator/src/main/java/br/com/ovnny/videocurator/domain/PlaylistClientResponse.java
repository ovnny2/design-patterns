package br.com.ovnny.videocurator.domain;

import br.com.ovnny.videocurator.domain.playlist.ContentDetails;
import br.com.ovnny.videocurator.domain.playlist.PlaylistItem;

import java.util.List;

public class PlaylistClientResponse {
    public String kind;
    public String etag;
    public String nextPageToken;
    public List<PlaylistItem> items;
    public ContentDetails contentDetails;

    public String getKind() {
        return kind;
    }

    public String getEtag() {
        return etag;
    }

    public String getNextPageToken() {
        return nextPageToken;
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