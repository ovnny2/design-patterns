package br.com.ovnny.videocurator.domain;

import br.com.ovnny.videocurator.domain.video.VideoSnippet;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class PlaylistPreview {

    @Id
    public String playlistId;

    public List<VideoSnippet> videoSnippetList;

    public PlaylistPreview(String playlistId) {
        this.playlistId = playlistId;
    }
}