package br.com.ovnny.videocurator.service;

import br.com.ovnny.videocurator.client.YoutubeClient;
import br.com.ovnny.videocurator.domain.PlaylistPreviewResponse;
import br.com.ovnny.videocurator.domain.video.State;
import br.com.ovnny.videocurator.domain.video.VideoSnippet;
import br.com.ovnny.videocurator.exception.PlaylistException;
import br.com.ovnny.videocurator.exception.PlaylistNotFoundException;
import br.com.ovnny.videocurator.repository.PlaylistRepository;
import br.com.ovnny.videocurator.utils.ParamExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VideoService {

    private final YoutubeClient youtubeClient;
    private final PlaylistRepository repository;

    @Autowired
    public VideoService(YoutubeClient youtubeClient, PlaylistRepository repository) {
        this.youtubeClient = youtubeClient;
        this.repository = repository;
    }

    @Transactional
    public PlaylistPreviewResponse createPlaylist(String url) {
        var playlistId = ParamExtractor.extractPlaylistId(url);

        Optional<PlaylistPreviewResponse> playlistView = repository.findById(playlistId);

        if (playlistView.isPresent()) throw
                new PlaylistException("A playlist já existe", HttpStatus.BAD_REQUEST);

        List<VideoSnippet> snippets = youtubeClient.fetchPlaylistItems(playlistId).getItems().stream()
                .map(item -> new VideoSnippet(item.getSnippet(), State.NOT_PROCESSED))
                .toList();

        PlaylistPreviewResponse newPlaylist = new PlaylistPreviewResponse(playlistId, snippets);
        repository.save(newPlaylist);

        return newPlaylist;
    }

    public PlaylistPreviewResponse getPlaylist(String id) {
        return repository.findById(id).orElseThrow(() ->
                new PlaylistNotFoundException("A playlist informada não existe", HttpStatus.NOT_FOUND)
        );
    }
}