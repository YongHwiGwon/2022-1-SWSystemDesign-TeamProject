package com.discord.bot.service;

import com.discord.bot.BotApplication;
import com.discord.bot.entity.pojo.MusicPojo;
import com.discord.bot.loader.*;
import org.junit.Before;
import org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class MusicLoaderTest {

    @Mock
    RestService restService;

    MusicLoaderManager musicLoaderManager;

    @Before
    public void setUp() throws Exception {
        String[] args = new String[0];
        SpringApplication.run(BotApplication.class, args);

        openMocks(this);
        musicLoaderManager = new MusicLoaderManager();

    }

    /**
     * Purpose: Verify that the correct loader is generated for the given URL
     * Input: URLs
     *  String(Youtube Single Track) : https://www.youtube.com/watch?v=em0MknB6wFo
     *  String(Youtube Playlist) : https://www.youtube.com/watch?v=A2VpR8HahKc&list=PLSdoVPM5WnndSQEXRz704yQkKwx76GvPV
     *  String(Spotify Single Track) : https://open.spotify.com/track/2Foc5Q5nqNiosCNqttzHof
     *  String(Spotify Playlist) : https://open.spotify.com/playlist/6Ot4aTDQpU5MpMikXiVzls
     *  String(Music Name) : viva la dida
     * Expected:
     *  YoutubeMusicLoader Object
     *  YoutubeMusicLoader Object
     *  SpotifyMusicLoader Object
     *  SpotifyMusicLoader Object
     *  YoutubeMusicLoader Object
     */
    @Test
    public void MusicLoaderFactoryTest() {
        MusicLoader youtubeSingleLoader = MusicLoaderFactory.createMusicLoader("https://www.youtube.com/watch?v=em0MknB6wFo");
        MusicLoader youtubeListLoader = MusicLoaderFactory.createMusicLoader("https://www.youtube.com/watch?v=A2VpR8HahKc&list=PLSdoVPM5WnndSQEXRz704yQkKwx76GvPV");
        MusicLoader spotifySingleLoader = MusicLoaderFactory.createMusicLoader("https://open.spotify.com/track/2Foc5Q5nqNiosCNqttzHof");
        MusicLoader spotifyListLoader = MusicLoaderFactory.createMusicLoader("https://open.spotify.com/playlist/6Ot4aTDQpU5MpMikXiVzls");
        MusicLoader musicNameLoader = MusicLoaderFactory.createMusicLoader("viva la dida");
        assertTrue(youtubeSingleLoader instanceof YoutubeMusicLoader);
        assertTrue(youtubeListLoader instanceof YoutubeMusicLoader);
        assertTrue(spotifySingleLoader instanceof SpotifyMusicLoader);
        assertTrue(spotifyListLoader instanceof SpotifyMusicLoader);
        assertTrue(musicNameLoader instanceof YoutubeMusicLoader);

    }
}
