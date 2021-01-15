package com.sid.musicwiki.ui.genredetails;

import android.content.Context;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;
import androidx.fragment.app.Fragment;

import com.sid.musicwiki.data.genreapi.Genre;
import com.sid.musicwiki.data.genrewikiapi.GenreWikiResponse;
import com.sid.musicwiki.ui.genredetails.genrefrag.GenreDataFragment;
import com.sid.musicwiki.util.PreferenceHelper;
import com.sid.musicwiki.util.api.ApiInterface;
import com.sid.musicwiki.util.api.ApiUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sid.musicwiki.util.AppConstants.API_GENRE_WIKI_METHOD;
import static com.sid.musicwiki.util.AppConstants.API_KEY;

public class GenreDetailsViewModel {

    public final ObservableField<String> genreSummary;
    public final ObservableList<Fragment> fragmentList = new ObservableArrayList<>();
    private Context context;
    private ApiInterface apiInterface;
    private PreferenceHelper preferenceHelper;

    public GenreDetailsViewModel(Context context) {
        this.context = context;
        apiInterface = ApiUtil.provideRetrofit().create(ApiInterface.class);
        preferenceHelper = new PreferenceHelper(context);
        genreSummary = new ObservableField<>("");
    }

    public void setUpDataSource(Genre genre) {
        makeApiCallForGenreWikiData(genre);
        setFragmentList(genre.getName());
    }

    private void setFragmentList(String name) {
        fragmentList.clear();
        fragmentList.add(GenreDataFragment.newInstance("album", name));
        fragmentList.add(GenreDataFragment.newInstance("artist", name));
        fragmentList.add(GenreDataFragment.newInstance("track", name));
    }

    private void makeApiCallForGenreWikiData(Genre genre) {
        Call<GenreWikiResponse> genreCall = apiInterface.makeGetGenreWikiData("json", API_GENRE_WIKI_METHOD, API_KEY, genre.getName());
        genreCall.enqueue(new Callback<GenreWikiResponse>() {
            @Override
            public void onResponse(Call<GenreWikiResponse> call, Response<GenreWikiResponse> response) {
                if (response != null && response.isSuccessful() && response.body() != null) {
                    setBasicGenreData(response.body().getGenre());
                }
            }

            @Override
            public void onFailure(Call<GenreWikiResponse> call, Throwable t) {

            }
        });
    }

    private void setBasicGenreData(Genre genreData) {
        genreSummary.set(genreData.getWiki().getSummary());
    }
}
