package br.com.ovnny.videocurator.repository;

import br.com.ovnny.videocurator.domain.videosnipet.PlaylistView;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlaylistRepository extends MongoRepository<PlaylistView, String> {
}