package br.com.ovnny.videocurator.client;

import br.com.ovnny.videocurator.client.youtube.PageInfo;
import br.com.ovnny.videocurator.client.youtube.PlaylistItem;

import java.util.List;

public class PlaylistClientResponse {
    public String kind;
    public String etag;
    public String nextPageToken;
    public List<PlaylistItem> items;
    public PageInfo pageInfo;

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

    public PageInfo getPageInfo() {
        return pageInfo;
    }
}