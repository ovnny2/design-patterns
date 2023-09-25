package br.com.ovnny.videocurator.repository;

import br.com.ovnny.videocurator.domain.playlist.PlaylistHeader;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlaylistHeaderRepository extends MongoRepository<PlaylistHeader, String> {
}