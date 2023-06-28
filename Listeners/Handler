package com.Cooper.MusicBot.Listeners;

import com.Cooper.MusicBot.MusicPlayer.PlayerManager;
import com.Cooper.MusicBot.Youtube.YoutubeSearch;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Handler {

    Map<Integer,String[]> SEARCH_RESULTS;
    boolean QUERRY_IN_PROGRESS;

    User querryBy;

    public Handler()
    {
        reset();
        SEARCH_RESULTS = null;
    }
    public void collectSongs(MessageReceivedEvent event) {

        String querry = event.getMessage().getContentRaw();
        querry = querry.substring(6);
        YoutubeSearch music;
        HashMap<Integer,String[]> searchResults;
        try {
            music = new YoutubeSearch(querry);
            searchResults = music.getSearchResult();
            SEARCH_RESULTS = searchResults;
            String s = "";
            for (Integer x : searchResults.keySet()) {
                s = s + x + ": " + searchResults.get(x)[0] +  "\n";
            }
            event.getChannel().sendMessage(s).queue();
        }
        catch(GeneralSecurityException e){System.out.println("Security exception");}
        catch(IOException e){System.out.println("IO exception");}
        catch(Exception e) {System.out.println(e.getMessage());}

        QUERRY_IN_PROGRESS = true;
        querryBy = event.getAuthor();
    }

    public void choseSong(MessageReceivedEvent event)
    {

        if((querryBy.getName().equals(event.getAuthor().getName()) == false))
            return;

        String s = event.getMessage().getContentRaw();

        try {
            Integer x = Integer.parseInt(s);

            //This is where the song will be added to queue
            PlayerManager.get().play(event.getGuild(),"https://www.youtube.com/watch?v="+ SEARCH_RESULTS.get(x)[1]);
            event.getChannel().sendMessage("Queued: " + SEARCH_RESULTS.get(x)[0]).queue();
        }
        catch (NumberFormatException e)
        {
            System.out.println(e.getMessage());
            event.getChannel().sendMessage("That was not a number. Canceling...").queue();
        }
        catch(Exception e)
        {
            event.getChannel().sendMessage("Error").queue();
        }
        finally {
            reset();
        }

    }

    public void join(MessageReceivedEvent event)
    {
        event.getGuild().getAudioManager().openAudioConnection(event.getMember().getVoiceState().getChannel());
    }
    public void leave(MessageReceivedEvent event)
    {
        event.getGuild().getAudioManager().closeAudioConnection();
        reset();
    }

    public void reset()
    {
        QUERRY_IN_PROGRESS = false;
        querryBy = null;
    }

}

