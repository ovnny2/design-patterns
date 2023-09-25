package br.com.ovnny.videocurator.service;

import br.com.ovnny.videocurator.client.YoutubeClient;
import br.com.ovnny.videocurator.domain.playlist.PlaylistData;
import br.com.ovnny.videocurator.domain.playlist.PlaylistHeader;
import br.com.ovnny.videocurator.domain.video.State;
import br.com.ovnny.videocurator.domain.video.VideoSnippet;
import br.com.ovnny.videocurator.exception.PlaylistException;
import br.com.ovnny.videocurator.repository.PlaylistHeaderRepository;
import br.com.ovnny.videocurator.repository.VideoSnippetRepository;
import br.com.ovnny.videocurator.utils.ParamExtractor;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
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
    private final VideoSnippetRepository videoSnippetRepository;
    private final PlaylistHeaderRepository playlistHeaderRepository;

    public VideoService(
            YoutubeClient youtubeClient,
            VideoSnippetRepository videoSnippetRepository,
            PlaylistHeaderRepository playlistHeaderRepository
    ) {
        this.youtubeClient = youtubeClient;
        this.videoSnippetRepository = videoSnippetRepository;
        this.playlistHeaderRepository = playlistHeaderRepository;
    }

    @Transactional
    public PlaylistHeader createPlaylist(String url, String email) {
        var playlistId = ParamExtractor.extractPlaylistId(url);

        Optional<PlaylistHeader> playlistHeader = playlistHeaderRepository.findById(playlistId);

        if (playlistHeader.isPresent()) throw
                new PlaylistException("A playlist já existe", HttpStatus.BAD_REQUEST);

        List<VideoSnippet> snippets = youtubeClient.fetchPlaylistItems(playlistId).getItems().stream()
                .map(item -> new VideoSnippet(item.getSnippet(), State.NOT_PROCESSED))
                .toList();

        Map<String, PlaylistData> playlistData = new HashMap<>();

        for (VideoSnippet snippet : snippets) {
            playlistData.put(snippet.getId(), null);
        }

        PlaylistHeader newPlaylistHeader = new PlaylistHeader(
                playlistId, email, playlistData, LocalDateTime.now(), LocalDateTime.now()
        );

        saveVideoSnippetsAndFetchTranscriptions(snippets);

        return newPlaylistHeader;
    }

    @Async
    public void saveVideoSnippetsAndFetchTranscriptions(List<VideoSnippet> videoSnippets) {
        videoSnippetRepository.saveAll(videoSnippets);
    }

    public VideoSnippet getSnippetById(String id) {
        return videoSnippetRepository.findById(id)
                .orElseThrow( () -> new PlaylistException("", HttpStatus.BAD_REQUEST)
        );
    }
}