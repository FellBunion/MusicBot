package com.Cooper.MusicBot.Audio;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTubeRequestInitializer;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public class MusicController {

    private static final String API_KEY = "YOUR_API_KEY_HERE";
    private static final String APPLICATION_NAME = "YOUR_APPLICATION_NAME_HERE";
    private static final long NUMBER_OF_VIDEOS_RETURNED = 25;

    private final YouTube youtube = YoutubeAPI.getYoutube();
    private final DefaultAudioPlayerManager Audio_MANAGER;

    private String query;

    public MusicController(String query) throws GeneralSecurityException, IOException {

        this.query = query;
        Audio_MANAGER = new DefaultAudioPlayerManager();
    }

    public void search() throws IOException {
        YouTube.Search.List search = youtube.search().list("id,snippet");

        search.setKey(API_KEY);
        search.setQ(query);
        search.setType("video");
        search.setFields("items(id/kind,id/videoId,snippet/title,snippet/description,snippet/thumbnails/default/url)");
        search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
        SearchListResponse searchResponse = search.execute();
        List<SearchResult> searchResultList = ((SearchListResponse) searchResponse).getItems();
        System.out.println("searchResultList: " + searchResultList);
    }

    //old method for creating a youtube object
      /*private YouTube getService() throws GeneralSecurityException, IOException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        return new YouTube.Builder(httpTransport, JSON_FACTORY, null)
                .setApplicationName("MusicBot")
                .build();
    }*/
}
