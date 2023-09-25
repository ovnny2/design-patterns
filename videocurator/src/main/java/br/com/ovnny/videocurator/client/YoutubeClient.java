package br.com.ovnny.videocurator.client;

import br.com.ovnny.videocurator.exception.PlaylistClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

@Configuration
public class YoutubeClient {

    @Value("${clients.youtube.baseUrl}")
    private String YOUTUBE_V3API_BASE_URL;

    @Value("${clients.youtube.maxResults}")
    private String MAX_RESULTS;

    @Value("${clients.youtube.key}")
    private String API_KEY;

    private final RestTemplate restTemplate;

    @Autowired
    public YoutubeClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PlaylistClientResponse fetchPlaylistItems(String playlistId) {
        final UriComponentsBuilder url = buildClientRequest(playlistId, YOUTUBE_V3API_BASE_URL);
        HttpHeaders headers = new HttpHeaders();

        try {
            var rawResponse = restTemplate.exchange(url.toUriString(), HttpMethod.GET, createHttpEntity(headers),
                    ParameterizedTypeReference.forType(PlaylistClientResponse.class));
            return (PlaylistClientResponse) rawResponse.getBody();

        } catch (HttpServerErrorException | HttpClientErrorException exception) {
            throw new PlaylistClientException("Não é possível processar uma playlist privada.", exception.getStatusCode());
        }
    }

    private UriComponentsBuilder buildClientRequest(String id, String baseUrl) {
        return UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("part", "snippet,contentDetails")
                .queryParam("maxResults", MAX_RESULTS)
                .queryParam("playlistId", id)
                .queryParam("key", API_KEY);
    }

    private static HttpEntity<?> createHttpEntity(HttpHeaders headers) {
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return new HttpEntity<>(headers);
    }
}