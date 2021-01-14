package com.sid.musicwiki.ui.main;

import android.content.Context;
import android.util.Log;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.sid.musicwiki.data.genreapi.Genre;
import com.sid.musicwiki.data.genreapi.GenreResponse;
import com.sid.musicwiki.util.api.ApiInterface;
import com.sid.musicwiki.util.api.ApiUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sid.musicwiki.util.AppConstants.API_GENRE_METHOD;
import static com.sid.musicwiki.util.AppConstants.API_KEY;

public class MainViewModel {

    public final ObservableList<Genre> genreObservableList = new ObservableArrayList<>();
    public final ObservableField<String> welcome;
    public final ObservableField<String> genreHint;
    public final ObservableBoolean isAllGenreVisible;
    private Context context;
    private ApiInterface apiInterface;
    private List<Genre> genreList = new ArrayList<>();

    public MainViewModel(Context context) {
        this.context = context;
        apiInterface = ApiUtil.provideRetrofit().create(ApiInterface.class);
        welcome = new ObservableField<>("Welcome!");
        genreHint = new ObservableField<>("Choose a genre to Start with");
        isAllGenreVisible = new ObservableBoolean(false);
    }


    public void setUpDataSource() {
        makeApiCallForGenreList();
    }

    private void makeApiCallForGenreList() {
        Call<GenreResponse> genreResponseCall = apiInterface.makeGetGenreData("json", API_GENRE_METHOD, API_KEY);
        genreResponseCall.enqueue(new Callback<GenreResponse>() {
            @Override
            public void onResponse(Call<GenreResponse> call, Response<GenreResponse> response) {
                if (response != null && response.isSuccessful()) {
                    if (response.body() != null && response.body().getToptags() != null && response.body().getToptags().getGenreList() != null && response.body().getToptags().getGenreList().size() > 0) {
                        genreList = response.body().getToptags().getGenreList();
                        setGenreList(genreList);
                    } else {

                    }
                } else {

                }
            }

            @Override
            public void onFailure(Call<GenreResponse> call, Throwable t) {
                Log.d(MainViewModel.class.getSimpleName(), "fail");
            }
        });
    }

    private void setGenreList(List<Genre> genreList) {
        if (isAllGenreVisible.get()) {
            genreObservableList.clear();
            genreObservableList.addAll(new ArrayList<>(genreList));
        } else if (genreList.size() <= 10) {
            genreObservableList.clear();
            genreObservableList.addAll(new ArrayList<>(genreList));
        } else {
            genreObservableList.clear();
            genreObservableList.addAll(new ArrayList<>(genreList.subList(0, 9)));
        }
    }

    public void genreExpandClick() {
        isAllGenreVisible.set(!isAllGenreVisible.get());
        setGenreList(genreList);
    }
}
