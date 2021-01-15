package com.sid.musicwiki.util;

public class AppConstants {
    public static final Integer CUSTOM_CONNECT_TIMEOUT = 40;                                       // seconds
    public static final Integer CUSTOM_READ_TIMEOUT = 40;

    public static final String BASE_URL = "https://ws.audioscrobbler.com/";

    public static final String API_KEY = "73395d602a78097839f658e3bd4dea31";

    public static final String PREF_NAME = "musik_wiki_preference";

    public static final String API_GENRE_METHOD = "tag.getTopTags";

    public static final String API_GENRE_WIKI_METHOD = "tag.getinfo";

    public static final String API_GENRE_ALBUM_METHOD = "tag.gettopalbums";
    public static final String API_GENRE_ARTIST_METHOD = "tag.gettopartists";
    public static final String API_GENRE_TRACK_METHOD = "tag.gettoptracks";
}