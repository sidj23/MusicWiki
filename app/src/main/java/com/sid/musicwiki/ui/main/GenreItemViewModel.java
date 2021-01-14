package com.sid.musicwiki.ui.main;

import androidx.databinding.ObservableField;

import com.sid.musicwiki.data.genreapi.Genre;

public class GenreItemViewModel {

    public ObservableField<String> genreName;
    private GenreItemViewModelListener genreListener;
    private Genre genreData;

    public GenreItemViewModel(Genre genre, GenreItemViewModelListener listener) {
        this.genreData = genre;
        genreListener = listener;
        setGenreData();
    }

    private void setGenreData() {
        genreName = new ObservableField<>(genreData.getName());
    }

    public void onGenreClick() {
        if (genreListener != null)
            genreListener.onGenreItemClick(genreData);
    }

    public interface GenreItemViewModelListener {
        void onGenreItemClick(Genre genreData);
    }
}
