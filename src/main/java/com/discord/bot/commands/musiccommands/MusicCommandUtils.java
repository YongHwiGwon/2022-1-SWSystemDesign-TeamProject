package com.discord.bot.commands.musiccommands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class MusicCommandUtils {
    public boolean isBotAndUserInSameChannel(SlashCommandInteractionEvent event) {
        if (!isBotInVoiceChannel(event)) {
            return false;
        }
        if (!isUserInVoiceChannel(event)) {
            return false;
        }
        return isUserInVoiceChannel(event) == isUserInVoiceChannel(event);
    }

    public boolean isBotInVoiceChannel(SlashCommandInteractionEvent event) {
        return event.getGuild().getSelfMember().getVoiceState().inAudioChannel();
    }

    public boolean isUserInVoiceChannel(SlashCommandInteractionEvent event) {
        return event.getMember().getVoiceState().inAudioChannel();
    }
}
