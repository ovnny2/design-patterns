package br.com.ovnny.videocurator.repository;

import br.com.ovnny.videocurator.domain.video.VideoSnippet;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VideoSnippetRepository extends MongoRepository<VideoSnippet, String> {
}