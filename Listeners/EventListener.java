package com.Cooper.MusicBot.Listeners;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;

public class EventListener extends ListenerAdapter {
    Handler handler = new Handler();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if(event.getAuthor().isBot())
            return;
        String message = event.getMessage().getContentRaw();

        if(handler.QUERRY_IN_PROGRESS == true)
        {
            if(!message.startsWith("["))
                handler.choseSong(event);
        }

        if(message.startsWith("[play]"))
        {
            if(!(event.getMember().getVoiceState().getChannel() == null)) {
                event.getMember().getVoiceState().getChannel();
                AudioManager audioManager = event.getGuild().getAudioManager();
                audioManager.openAudioConnection(event.getMember().getVoiceState().getChannel());
            }
            else {
                event.getChannel().sendMessage("User not in voice channel").queue();
                return;
            }
            handler.collectSongs(event);
        }

        if(message.startsWith("[leave]"))
        {
            handler.leave(event);
        }

        if(message.startsWith("[join]"))
        {
            handler.join(event);
        }

    }
}

