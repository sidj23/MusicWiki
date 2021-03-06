package com.sid.musicwiki.util.api;


import com.sid.musicwiki.data.albumapi.AlbumInfoResponse;
import com.sid.musicwiki.data.artistapi.ArtistInfoResponse;
import com.sid.musicwiki.data.genreapi.GenreResponse;
import com.sid.musicwiki.data.genrewikiapi.GenreAllResponse;
import com.sid.musicwiki.data.genrewikiapi.GenreWikiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiInterface {

    @Headers("Content-Type: application/json")
    @GET("2.0/")
    Call<GenreResponse> makeGetGenreData(
            @Query("format") String format,
            @Query("method") String method,
            @Query("api_key") String apiKey
    );

    @Headers("Content-Type: application/json")
    @GET("2.0/")
    Call<GenreWikiResponse> makeGetGenreWikiData(
            @Query("format") String format,
            @Query("method") String method,
            @Query("api_key") String apiKey,
            @Query("tag") String tag
    );

    @Headers("Content-Type: application/json")
    @GET("2.0/")
    Call<GenreAllResponse> makeGetGenreAllData(
            @Query("format") String format,
            @Query("method") String method,
            @Query("api_key") String apiKey,
            @Query("tag") String tag
    );

    @Headers("Content-Type: application/json")
    @GET("2.0/")
    Call<AlbumInfoResponse> makeGetAlbumInfoData(
            @Query("format") String format,
            @Query("method") String method,
            @Query("api_key") String apiKey,
            @Query("artist") String artist,
            @Query("album") String album

    );

    @Headers("Content-Type: application/json")
    @GET("2.0/")
    Call<ArtistInfoResponse> makeGetArtistInfoData(
            @Query("format") String format,
            @Query("method") String method,
            @Query("api_key") String apiKey,
            @Query("artist") String artist
    );

    @Headers("Content-Type: application/json")
    @GET("2.0/")
    Call<GenreAllResponse> makeGetArtistTopAlbumTracks(
            @Query("format") String format,
            @Query("method") String method,
            @Query("api_key") String apiKey,
            @Query("artist") String artist
    );
}
