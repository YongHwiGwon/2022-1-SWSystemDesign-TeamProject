package com.discord.bot.commands;

import com.discord.bot.commands.admincommands.GuildsCommand;
import com.discord.bot.commands.admincommands.LogsCommand;
import com.discord.bot.commands.musiccommands.*;
import com.discord.bot.service.RestService;
import com.discord.bot.service.TrackService;
import com.discord.bot.service.audioplayer.PlayerManagerService;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommandManager extends ListenerAdapter {

    RestService restService;
    PlayerManagerService playerManagerService;
    ChannelValidation musicCommandUtils;
    TrackService trackService;
    private Map<String, ISlashCommand> commandsMap;

    public CommandManager(RestService restService, PlayerManagerService playerManagerService, TrackService trackService) {
        this.restService = restService;
        this.playerManagerService = playerManagerService;
        this.musicCommandUtils = new ChannelValidation();
        this.trackService = trackService;
        commandMapper();
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String commandName = event.getName();

        ISlashCommand command;
        if ((command = commandsMap.get(commandName)) != null) {
            command.execute(event);
        }
    }

    private void commandMapper() {
        commandsMap = new ConcurrentHashMap<>();
        //Admin Commands
        commandsMap.put("guilds", new GuildsCommand());
        commandsMap.put("logs", new LogsCommand());
        //Music Commands
        commandsMap.put("play", new PlayCommand(restService, playerManagerService, trackService, musicCommandUtils));
        commandsMap.put("skip", new SkipCommand(playerManagerService, musicCommandUtils));
        commandsMap.put("pause", new PauseCommand(playerManagerService, musicCommandUtils));
        commandsMap.put("resume", new ResumeCommand(playerManagerService, musicCommandUtils));
        commandsMap.put("leave", new LeaveCommand(playerManagerService, musicCommandUtils));
        commandsMap.put("queue", new QueueCommand(playerManagerService, musicCommandUtils));
        commandsMap.put("swap", new SwapCommand(playerManagerService, musicCommandUtils));
        commandsMap.put("shuffle", new ShuffleCommand(playerManagerService, musicCommandUtils));
        commandsMap.put("loop", new LoopCommand(playerManagerService, musicCommandUtils));
        commandsMap.put("mhelp", new MusicHelpCommand(musicCommandUtils));
    }
}
