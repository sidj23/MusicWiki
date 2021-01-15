package com.sid.musicwiki.ui.genredetails.genrefrag;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import com.sid.musicwiki.data.albumapi.AlbumData;
import com.sid.musicwiki.data.artistapi.ArtistData;
import com.sid.musicwiki.data.tracksapi.TrackData;

public class GridItemViewModel {

    public ObservableField<String> title;
    public ObservableField<String> artistName;
    public ObservableField<String> imageUrl;
    public ObservableBoolean isArtistVisible;

    private GridItemViewModelListener listener;
    private Object gridObject;

    public GridItemViewModel(Object object, GridItemViewModelListener listener) {
        this.gridObject = object;
        this.listener = listener;
        setGridData(object);
    }

    private void setGridData(Object object) {
        if (object instanceof AlbumData) {
            AlbumData albumData = (AlbumData) object;
            imageUrl = new ObservableField<>(albumData.getImage() != null && albumData.getImage().size() > 0 ? albumData.getImage().get(0).getText() : "");
            title = new ObservableField<>(albumData.getName());
            artistName = new ObservableField<>(albumData.getArtistData().getName());
            isArtistVisible = new ObservableBoolean(true);
        } else if (object instanceof ArtistData) {
            ArtistData artistData = (ArtistData) object;
            imageUrl = new ObservableField<>(artistData.getImage() != null && artistData.getImage().size() > 0 ? artistData.getImage().get(0).getText() : "");
            title = new ObservableField<>(artistData.getName());
            artistName = new ObservableField<>("");
            isArtistVisible = new ObservableBoolean(false);
        } else if (object instanceof TrackData) {
            TrackData trackData = (TrackData) object;
            imageUrl = new ObservableField<>(trackData.getImage() != null && trackData.getImage().size() > 0 ? trackData.getImage().get(0).getText() : "");
            title = new ObservableField<>(trackData.getName());
            artistName = new ObservableField<>(trackData.getArtistData().getName());
            isArtistVisible = new ObservableBoolean(true);
        }
    }


    public void onGridItemClick() {
        if (listener != null)
            listener.onGridItemClick(gridObject);
    }

    public interface GridItemViewModelListener {
        void onGridItemClick(Object gridObject);
    }

}
