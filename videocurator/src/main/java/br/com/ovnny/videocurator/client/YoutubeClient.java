package br.com.ovnny.videocurator.client;

import br.com.ovnny.videocurator.domain.playlist.PlaylistClientResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

@Configuration
public class YoutubeClient {

    private final RestTemplate restTemplate;

    @Value("${clients.youtube.baseUrl}")
    private String GOOGLE_API_YOUTUBE_V3_BASE_URL;

    @Value("${clients.youtube.maxResults}")
    private String MAX_RESULTS;

    @Value("${clients.youtube.apiKey}")
    private String KEY_API;

    @Autowired
    public YoutubeClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PlaylistClientResponse getYoutubePlaylistItems(String playlistId) {
        HttpHeaders headers = new HttpHeaders();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(GOOGLE_API_YOUTUBE_V3_BASE_URL)
                .queryParam("part", "snippet,contentDetails")
                .queryParam("maxResults", MAX_RESULTS)
                .queryParam("playlistId", playlistId)
                .queryParam("key", KEY_API);

        String url = builder.toUriString();

        var rawResponse = restTemplate.exchange(url, HttpMethod.GET, createHttpEntity(headers),
                ParameterizedTypeReference.forType(PlaylistClientResponse.class)
        );

        return (PlaylistClientResponse) rawResponse.getBody();
    }

    private static HttpEntity<?> createHttpEntity(HttpHeaders headers) {
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return new HttpEntity<>(headers);
    }
}