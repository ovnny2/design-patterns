package br.com.ovnny.videocurator.service;

import br.com.ovnny.videocurator.client.YoutubeClient;
import br.com.ovnny.videocurator.domain.videosnipet.PlaylistView;
import br.com.ovnny.videocurator.domain.videosnipet.SnipetState;
import br.com.ovnny.videocurator.domain.videosnipet.VideoSnipet;
import br.com.ovnny.videocurator.repository.PlaylistRepository;
import br.com.ovnny.videocurator.utils.ParamExtractor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public PlaylistView createPlaylist(String playlistUrl) {
        var playlistId = ParamExtractor.extractPlaylistId(playlistUrl);
        Optional<PlaylistView> playlistView = repository.findById(playlistId);

        if (playlistView.isPresent()) {
            throw new RuntimeException();
        }

        List<VideoSnipet> snipets = youtubeClient.fetchYoutubePlaylistItems(playlistId).getItems().stream()
                .map(item -> new VideoSnipet(item.getSnipet(), SnipetState.NO_PROCESSED))
                .toList();

        PlaylistView newPlaylist = new PlaylistView(playlistId, snipets);
        repository.save(newPlaylist);

        return newPlaylist;
    }
}