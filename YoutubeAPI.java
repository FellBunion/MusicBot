package com.Cooper.MusicBot.Audio;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTubeRequest;
import com.google.api.services.youtube.YouTubeRequestInitializer;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class YoutubeAPI {

    private static final String APPLICATION_NAME = "YOUR_APPLICATION_NAME_HERE";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static YouTube youtube;

    private static Dotenv config = Dotenv.configure().load();

    public static YouTube getYoutube() throws GeneralSecurityException, IOException {
        if (youtube == null) {
            youtube = new YouTube.Builder(GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, null)
                    .setApplicationName(APPLICATION_NAME)
                    .setYouTubeRequestInitializer(new YouTubeRequestInitializer() {
                        public void initialize(YouTubeRequest<?> request) throws IOException {
                            // Set the API key for the request
                            request.setKey(config.get("APIKEY"));
                        }
                    })
                    .build();
        }
        return youtube;
    }
}
