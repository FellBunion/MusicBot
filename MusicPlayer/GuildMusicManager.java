package com.Cooper.MusicBot.MusicPlayer;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;

public class GuildMusicManager {

    private TrackScheduler trackScheduler;
    private AudioFowarder fowarder;

    public GuildMusicManager(AudioPlayerManager manager)
    {
        AudioPlayer player = manager.createPlayer();
        trackScheduler = new TrackScheduler(player);
        player.addListener(trackScheduler);
        fowarder = new AudioFowarder(player);
    }

    public TrackScheduler getTrackScheduler() {
        return trackScheduler;
    }

    public AudioFowarder getFowarder() {
        return fowarder;
    }
}
