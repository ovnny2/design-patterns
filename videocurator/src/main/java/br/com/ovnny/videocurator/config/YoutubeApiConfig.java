package br.com.ovnny.videocurator.config;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

@Configuration
public class YoutubeApiConfig {

    @Bean
    public HttpTransport createHttpTransport() throws GeneralSecurityException, IOException {
        return GoogleNetHttpTransport.newTrustedTransport();
    }

    @Bean
    @Scope("prototype")
    public Credential createCredential(HttpTransport httpTransport) throws IOException {
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JacksonFactory.getDefaultInstance(),
                new FileReader("YOUR_CLIENT_SECRET.json"));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, JacksonFactory.getDefaultInstance(), clientSecrets,
                Arrays.asList("https://www.googleapis.com/auth/youtube.readonly"))
                .setDataStoreFactory(new FileDataStoreFactory(new File("tokens")))
                .build();

        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    }
}