package com.sid.musicwiki.ui.albuminfo;

import android.content.Context;
import android.util.Log;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.sid.musicwiki.data.albumapi.AlbumData;
import com.sid.musicwiki.data.albumapi.AlbumInfoData;
import com.sid.musicwiki.data.albumapi.AlbumInfoResponse;
import com.sid.musicwiki.data.artistapi.ArtistData;
import com.sid.musicwiki.data.artistapi.ArtistInfoData;
import com.sid.musicwiki.data.artistapi.ArtistInfoResponse;
import com.sid.musicwiki.data.genreapi.Genre;
import com.sid.musicwiki.data.genrewikiapi.GenreAllResponse;
import com.sid.musicwiki.util.api.ApiInterface;
import com.sid.musicwiki.util.api.ApiUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sid.musicwiki.util.AppConstants.API_ALBUM_INFO_METHOD;
import static com.sid.musicwiki.util.AppConstants.API_ARTISTS_INFO_METHOD;
import static com.sid.musicwiki.util.AppConstants.API_ARTISTS_TOP_ALBUM_METHOD;
import static com.sid.musicwiki.util.AppConstants.API_ARTISTS_TOP_TRACK_METHOD;
import static com.sid.musicwiki.util.AppConstants.API_KEY;

public class AlbumInfoViewModel {

    public final ObservableBoolean isArtistDataVisible;
    public final ObservableField<String> title;
    public final ObservableField<String> artistName;
    public final ObservableField<String> followerData;
    public final ObservableField<String> followerHint;
    public final ObservableField<String> playCountData;
    public final ObservableField<String> playCountHint;
    public final ObservableList<Genre> genreObservableList = new ObservableArrayList<>();
    public final ObservableField<String> imageUrl;
    public final ObservableField<String> description;
    public final ObservableField<String> topAlbumHint;
    public final ObservableField<String> topTrackHint;
    public final ObservableList<Object> albumObservableList = new ObservableArrayList<>();
    public final ObservableList<Object> trackObservableList = new ObservableArrayList<>();
    public final ObservableBoolean isTopTrackDataVisible;
    public final ObservableBoolean isTopAlbumDataVisible;

    private Context context;
    private ApiInterface apiInterface;


    public AlbumInfoViewModel(Context context) {
        this.context = context;
        apiInterface = ApiUtil.provideRetrofit().create(ApiInterface.class);
        isArtistDataVisible = new ObservableBoolean(false);
        title = new ObservableField<>("");
        artistName = new ObservableField<>("");
        followerData = new ObservableField<>("");
        followerHint = new ObservableField<>("Followers");
        playCountData = new ObservableField<>("");
        playCountHint = new ObservableField<>("Play Counts");
        imageUrl = new ObservableField<>("");
        description = new ObservableField<>("");

        topAlbumHint = new ObservableField<>("Top Albums");
        topTrackHint = new ObservableField<>("Top Tracks");

        isTopTrackDataVisible = new ObservableBoolean(false);
        isTopAlbumDataVisible = new ObservableBoolean(false);
    }

    public void setUpDataSource(AlbumData albumData, ArtistData artistData) {
        if (albumData != null) {
            isArtistDataVisible.set(false);
            title.set(albumData.getName());
            artistName.set(albumData.getArtistData().getName());
            imageUrl.set(albumData.getImage().get(1).getText());
            makeApiCallForAlbumData(title.get(), artistName.get());
        } else if (artistData != null) {
            isArtistDataVisible.set(true);
            title.set(artistData.getName());
            imageUrl.set(artistData.getImage().get(1).getText());
            makeApiCallForArtistData(title.get());
            makeApiCallForTopTracks(title.get());
            makeApiCallForTopAlbum(title.get());
        }
    }

    private void makeApiCallForTopAlbum(String artist) {
        Call<GenreAllResponse> albumResponseCall = apiInterface.makeGetArtistTopAlbumTracks("json", API_ARTISTS_TOP_ALBUM_METHOD, API_KEY, artist);
        albumResponseCall.enqueue(new Callback<GenreAllResponse>() {
            @Override
            public void onResponse(Call<GenreAllResponse> call, Response<GenreAllResponse> response) {
                if (response != null && response.isSuccessful() && response.body() != null && response.body().getTopAlbumResponse() != null && response.body().getTopAlbumResponse().getAlbumDataList() != null) {
                    setAlbumObservableList(new ArrayList<>(response.body().getTopAlbumResponse().getAlbumDataList()));
                }
            }

            @Override
            public void onFailure(Call<GenreAllResponse> call, Throwable t) {

            }
        });
    }

    private void setAlbumObservableList(List<Object> albumData) {
        if (albumData != null && albumData.size() > 0) {
            albumObservableList.clear();
            albumObservableList.addAll(albumData);
            isTopAlbumDataVisible.set(true);
        } else {
            isTopAlbumDataVisible.set(false);
        }

    }

    private void setTrackObservableList(List<Object> trackData) {
        if (trackData != null && trackData.size() > 0) {
            trackObservableList.clear();
            trackObservableList.addAll(trackData);
            isTopTrackDataVisible.set(true);
        } else {
            isTopTrackDataVisible.set(false);
        }


    }

    private void makeApiCallForTopTracks(String artist) {
        Call<GenreAllResponse> trackResponseCall = apiInterface.makeGetArtistTopAlbumTracks("json", API_ARTISTS_TOP_TRACK_METHOD, API_KEY, artist);
        trackResponseCall.enqueue(new Callback<GenreAllResponse>() {
            @Override
            public void onResponse(Call<GenreAllResponse> call, Response<GenreAllResponse> response) {
                if (response != null && response.isSuccessful() && response.body() != null && response.body().getTopTrackResponse() != null && response.body().getTopTrackResponse().getTrackData() != null) {
                    setTrackObservableList(new ArrayList<>(response.body().getTopTrackResponse().getTrackData()));
                }
            }

            @Override
            public void onFailure(Call<GenreAllResponse> call, Throwable t) {

            }
        });
    }

    private void makeApiCallForArtistData(String artist) {
        Call<ArtistInfoResponse> callArtistInfo = apiInterface.makeGetArtistInfoData("json", API_ARTISTS_INFO_METHOD, API_KEY, artist);
        callArtistInfo.enqueue(new Callback<ArtistInfoResponse>() {
            @Override
            public void onResponse(Call<ArtistInfoResponse> call, Response<ArtistInfoResponse> response) {
                if (response != null && response.isSuccessful() && response.body() != null && response.body().getArtistInfoData() != null) {
                    setArtistDescriptionAndGenreData(response.body().getArtistInfoData());
                }
            }

            @Override
            public void onFailure(Call<ArtistInfoResponse> call, Throwable t) {

            }
        });
    }

    private void setArtistDescriptionAndGenreData(ArtistInfoData artistInfoData) {
        if (artistInfoData.getWiki() != null) {
            description.set(artistInfoData.getWiki().getSummary());
        }

        if (artistInfoData.getTagData() != null && artistInfoData.getTagData().getGenreList() != null && artistInfoData.getTagData().getGenreList().size() > 0) {
            genreObservableList.clear();
            genreObservableList.addAll(new ArrayList<>(artistInfoData.getTagData().getGenreList()));
        }

        if (artistInfoData.getArtistStats() != null) {
            playCountData.set(artistInfoData.getArtistStats().getPlayCount());
            followerData.set(artistInfoData.getArtistStats().getListeners());
        }
    }

    private void makeApiCallForAlbumData(String album, String artist) {
        Call<AlbumInfoResponse> callAlbumInfo = apiInterface.makeGetAlbumInfoData("json", API_ALBUM_INFO_METHOD, API_KEY, artist, album);
        callAlbumInfo.enqueue(new Callback<AlbumInfoResponse>() {
            @Override
            public void onResponse(Call<AlbumInfoResponse> call, Response<AlbumInfoResponse> response) {
                if (response != null && response.isSuccessful() && response.body() != null && response.body().getAlbumData() != null) {
                    setAlbumDescriptionAndGenreData(response.body().getAlbumData());
                }
            }

            @Override
            public void onFailure(Call<AlbumInfoResponse> call, Throwable t) {
                Log.d(AlbumInfoViewModel.class.getSimpleName(), "Error: " + t.getMessage());
            }
        });
    }

    private void setAlbumDescriptionAndGenreData(AlbumInfoData albumData) {
        if (albumData.getWiki() != null) {
            description.set(albumData.getWiki().getSummary());
        }

        if (albumData.getTagData() != null && albumData.getTagData().getGenreList() != null && albumData.getTagData().getGenreList().size() > 0) {
            genreObservableList.clear();
            genreObservableList.addAll(new ArrayList<>(albumData.getTagData().getGenreList()));
        }
    }
}
