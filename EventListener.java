package com.Cooper.MusicBot.Listeners;

import com.Cooper.MusicBot.Audio.MusicController;
import com.google.api.services.youtube.YouTube;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.IOException;

public class EventListener extends ListenerAdapter {



    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if(event.getAuthor().isBot())
            return;
        String message = event.getMessage().getContentRaw();

        if(message.startsWith("[play] "))
        {
            event.getChannel().sendMessage("request recived").queue();
            try {MusicController music = new MusicController("Hi");}
            catch(Exception e){System.out.println("It Broke");}
        }
    }
}
