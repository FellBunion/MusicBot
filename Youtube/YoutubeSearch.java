package com.Cooper.MusicBot.Youtube;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.List;

public class YoutubeSearch {

    private static Dotenv config = Dotenv.configure().load();
    private static final String API_KEY = config.get("APIKEY");
    private static final String APPLICATION_NAME = "MusicBot";
    private static final long NUMBER_OF_VIDEOS_RETURNED = 5;

    private final YouTube youtube = YoutubeAPI.getYoutube();
    private final AudioPlayerManager AUDIO_MANAGER;

    private final HashMap<Integer,String[]> searchResult;

    private String query;

    public YoutubeSearch(String query) throws GeneralSecurityException, IOException {

        this.query = query;
        AUDIO_MANAGER = new DefaultAudioPlayerManager();
        searchResult = search();
    }

    public HashMap<Integer,String[]> search() throws IOException {
        YouTube.Search.List search = youtube.search().list(List.of("id","snippet"));
        search.setKey(API_KEY);
        search.setQ(query);
        search.setType(List.of("video"));
        search.setFields("items(id/kind,id/videoId,snippet/title,snippet/description,snippet/thumbnails/default/url)");
        search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
        SearchListResponse searchResponse = search.execute();
        List<SearchResult> searchResultList = ((SearchListResponse) searchResponse).getItems();
        HashMap<Integer,String[]> map = new HashMap<>();
        int x = 1;
        for (SearchResult r : searchResultList) {
            String[] s = {r.getSnippet().getTitle(),r.getId().getVideoId()};
            map.put(x,s);
            x++;
        }
        return map;
    }

    public HashMap<Integer,String[]> getSearchResult() {return searchResult;}
}
