package br.com.ovnny.videocurator.service;

import br.com.ovnny.videocurator.client.YoutubeClient;
import br.com.ovnny.videocurator.domain.playlist.PlaylistData;
import br.com.ovnny.videocurator.domain.playlist.PlaylistHeader;
import br.com.ovnny.videocurator.domain.video.State;
import br.com.ovnny.videocurator.domain.video.VideoSnippet;
import br.com.ovnny.videocurator.exception.PlaylistException;
import br.com.ovnny.videocurator.repository.PlaylistHeaderRepository;
import br.com.ovnny.videocurator.utils.ParamExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class VideoService {

    private final YoutubeClient youtubeClient;
    private final PlaylistHeaderRepository repository;

    @Autowired
    public VideoService(YoutubeClient youtubeClient, PlaylistHeaderRepository repository) {
        this.youtubeClient = youtubeClient;
        this.repository = repository;
    }

    @Transactional
    public PlaylistHeader createPlaylist(String url, String email) {
        var playlistId = ParamExtractor.extractPlaylistId(url);

        Optional<PlaylistHeader> playlistHeader = repository.findById(playlistId);

        if (playlistHeader.isPresent()) throw
                new PlaylistException("A playlist já existe", HttpStatus.BAD_REQUEST);

        List<VideoSnippet> snippets = youtubeClient.fetchPlaylistItems(playlistId).getItems().stream()
                .map(item -> new VideoSnippet(item.getSnippet(), State.NOT_PROCESSED))
                .toList();

        Map<String, PlaylistData> playlistData = new HashMap<>();

        for (VideoSnippet snippet: snippets) {
            playlistData.put(snippet.getId(), null);
        }

        PlaylistHeader newPlaylistHeader = new PlaylistHeader(
                playlistId, email, playlistData, LocalDateTime.now(), LocalDateTime.now()
        );

        return newPlaylistHeader;
    }
}