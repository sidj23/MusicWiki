package com.sid.musicwiki.ui.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sid.musicwiki.base.BaseViewHolder;
import com.sid.musicwiki.data.genreapi.Genre;
import com.sid.musicwiki.databinding.ItemGenreBinding;

import java.util.ArrayList;
import java.util.List;

public class GenreListAdapter extends RecyclerView.Adapter<BaseViewHolder> {


    List<Genre> genreList = new ArrayList<>();

    GenreListAdapterListener listener;
    private Boolean isFromAlbumInfo;

    public GenreListAdapter(GenreListAdapterListener listener, Boolean isFromAlbumInfo) {
        this.listener = listener;
        this.isFromAlbumInfo = isFromAlbumInfo;
    }

    public void clearItems() {
        genreList.clear();
    }

    public void addItems(List<Genre> genres) {
        genreList.addAll(genres);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemGenreBinding binding = ItemGenreBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new GenreViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return genreList.size();
    }

    public interface GenreListAdapterListener {
        public void onGenreItemClick(Genre genreData);
    }

    private class GenreViewHolder extends BaseViewHolder implements GenreItemViewModel.GenreItemViewModelListener {

        ItemGenreBinding itemGenreBinding;

        GenreItemViewModel genreItemViewModel;

        public GenreViewHolder(ItemGenreBinding itemGenreBinding) {
            super(itemGenreBinding.getRoot());
            this.itemGenreBinding = itemGenreBinding;
        }

        @Override
        public void onBind(int position) {
            Genre genre = genreList.get(position);
            if (genre != null) {
                genreItemViewModel = new GenreItemViewModel(genre, this);
                itemGenreBinding.setViewModel(genreItemViewModel);
                if (isFromAlbumInfo)
                    itemGenreBinding.igHeadLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                itemGenreBinding.executePendingBindings();
            }
        }

        @Override
        public void onGenreItemClick(Genre genreData) {
            if (listener != null)
                listener.onGenreItemClick(genreData);
        }
    }


}
