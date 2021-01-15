package com.sid.musicwiki.ui.genredetails.genrefrag;

import android.content.Context;
import android.util.Log;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import com.sid.musicwiki.data.genrewikiapi.GenreAllResponse;
import com.sid.musicwiki.util.api.ApiInterface;
import com.sid.musicwiki.util.api.ApiUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sid.musicwiki.util.AppConstants.API_GENRE_ALBUM_METHOD;
import static com.sid.musicwiki.util.AppConstants.API_GENRE_ARTIST_METHOD;
import static com.sid.musicwiki.util.AppConstants.API_GENRE_TRACK_METHOD;
import static com.sid.musicwiki.util.AppConstants.API_KEY;

public class GenreDataViewModel {
    public final ObservableList<Object> objectObservableList = new ObservableArrayList<>();
    private Context context;
    private String dataType;
    private ApiInterface apiInterface;

    public GenreDataViewModel(Context context) {
        this.context = context;
        apiInterface = ApiUtil.provideRetrofit().create(ApiInterface.class);
    }

    public void setUpDataSource(String dataType, String genreName) {
        this.dataType = dataType;
        if (dataType.equals("album")) {
            makeApiCallForAlbumData(genreName);
        } else if (dataType.equals("artist")) {
            makeApiCallForArtistData(genreName);
        } else if (dataType.equals("track")) {
            makeApiCallForTrackData(genreName);
        }
    }

    private void makeApiCallForTrackData(String genreName) {
        Call<GenreAllResponse> genreAllResponseCall = apiInterface.makeGetGenreAllData("json", API_GENRE_TRACK_METHOD, API_KEY, genreName);
        genreAllResponseCall.enqueue(new Callback<GenreAllResponse>() {
            @Override
            public void onResponse(Call<GenreAllResponse> call, Response<GenreAllResponse> response) {
                if (response != null && response.isSuccessful() && response.body() != null && response.body().getTrackResponse() != null && response.body().getTrackResponse().getTrackData() != null) {
                    setObjectObservableList(new ArrayList<>(response.body().getTrackResponse().getTrackData()));
                }
            }

            @Override
            public void onFailure(Call<GenreAllResponse> call, Throwable t) {
                Log.d(GenreDataViewModel.class.getSimpleName(), "error: " + t.getMessage());
            }
        });

    }

    private void makeApiCallForArtistData(String genreName) {
        Call<GenreAllResponse> genreAllResponseCall = apiInterface.makeGetGenreAllData("json", API_GENRE_ARTIST_METHOD, API_KEY, genreName);
        genreAllResponseCall.enqueue(new Callback<GenreAllResponse>() {
            @Override
            public void onResponse(Call<GenreAllResponse> call, Response<GenreAllResponse> response) {
                if (response != null && response.isSuccessful() && response.body() != null && response.body().getArtistResponse() != null && response.body().getArtistResponse().getArtistDataList() != null) {
                    setObjectObservableList(new ArrayList<>(response.body().getArtistResponse().getArtistDataList()));
                }
            }

            @Override
            public void onFailure(Call<GenreAllResponse> call, Throwable t) {
                Log.d(GenreDataViewModel.class.getSimpleName(), "error: " + t.getMessage());
            }
        });
    }

    private void makeApiCallForAlbumData(String genreName) {
        Call<GenreAllResponse> genreAllResponseCall = apiInterface.makeGetGenreAllData("json", API_GENRE_ALBUM_METHOD, API_KEY, genreName);
        genreAllResponseCall.enqueue(new Callback<GenreAllResponse>() {
            @Override
            public void onResponse(Call<GenreAllResponse> call, Response<GenreAllResponse> response) {
                if (response != null && response.isSuccessful() && response.body() != null && response.body().getAlbumResponse() != null && response.body().getAlbumResponse().getAlbumDataList() != null) {
                    setObjectObservableList(new ArrayList<>(response.body().getAlbumResponse().getAlbumDataList()));
                }
            }

            @Override
            public void onFailure(Call<GenreAllResponse> call, Throwable t) {
                Log.d(GenreDataViewModel.class.getSimpleName(), "error: " + t.getMessage());
            }
        });
    }

    private void setObjectObservableList(List<Object> objectList) {
        objectObservableList.clear();
        objectObservableList.addAll(objectList);
    }


}
