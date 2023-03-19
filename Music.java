package com.Cooper.MusicBot;

import com.Cooper.MusicBot.Listeners.EventListener;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;

public class Music {

    private final ShardManager SHARD_MANAGER;
    private final Dotenv config;

    public Music() throws LoginException {

        config = Dotenv.configure().load();
        DefaultShardManagerBuilder build = DefaultShardManagerBuilder.createDefault(config.get("TOKEN"));
        build.setStatus(OnlineStatus.ONLINE);
        build.setActivity(Activity.listening("your mom"));
        build.enableIntents(GatewayIntent.GUILD_MESSAGES,GatewayIntent.GUILD_MEMBERS,
                GatewayIntent.GUILD_MESSAGE_REACTIONS,GatewayIntent.GUILD_VOICE_STATES,
                GatewayIntent.GUILD_MESSAGE_TYPING,GatewayIntent.GUILD_EMOJIS_AND_STICKERS,
                GatewayIntent.GUILD_PRESENCES,GatewayIntent.GUILD_PRESENCES,
                GatewayIntent.MESSAGE_CONTENT);
        SHARD_MANAGER = build.build();
        SHARD_MANAGER.addEventListener(new EventListener());
    }

    public static void main(String[] args) throws LoginException {

        Music bot;
        try{
            bot = new Music();
        }
        catch(LoginException e){
            System.out.println("Failed to login invalid bot token");
        }


    }

    public ShardManager getSHARD_MANAGER() {return SHARD_MANAGER;}
}
