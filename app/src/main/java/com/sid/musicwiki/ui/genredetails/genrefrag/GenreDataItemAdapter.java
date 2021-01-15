package com.sid.musicwiki.ui.genredetails.genrefrag;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sid.musicwiki.base.BaseViewHolder;
import com.sid.musicwiki.databinding.ItemGridInfoBinding;

import java.util.ArrayList;
import java.util.List;

public class GenreDataItemAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<Object> objectList = new ArrayList<>();
    private GenreDataItemAdapterListener adapterListener;
    private Boolean isFromAlbumInfo;
    private int screenWidth;

    public GenreDataItemAdapter(GenreDataItemAdapterListener adapterListener, Boolean isFromAlbumInfo, int width) {
        this.adapterListener = adapterListener;
        this.isFromAlbumInfo = isFromAlbumInfo;
        screenWidth = width;
    }

    public void clearItems() {
        objectList.clear();
    }

    public void addItems(List<Object> objectList) {
        this.objectList.addAll(objectList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemGridInfoBinding gridInfoBinding = ItemGridInfoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new GenreDataItemViewHolder(gridInfoBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return objectList.size();
    }

    public interface GenreDataItemAdapterListener {
        void onGridItemClick(Object gridObject);
    }

    private class GenreDataItemViewHolder extends BaseViewHolder implements GridItemViewModel.GridItemViewModelListener {

        private ItemGridInfoBinding itemGridInfoBinding;
        private GridItemViewModel gridItemViewModel;

        public GenreDataItemViewHolder(ItemGridInfoBinding gridInfoBinding) {
            super(gridInfoBinding.getRoot());
            itemGridInfoBinding = gridInfoBinding;
        }

        @Override
        public void onBind(int position) {
            Object object = objectList.get(position);
            if (object != null) {
                gridItemViewModel = new GridItemViewModel(object, this);
                itemGridInfoBinding.setViewModel(gridItemViewModel);
                if (isFromAlbumInfo)
                    itemGridInfoBinding.igiSquareRl.setLayoutParams(new LinearLayout.LayoutParams(screenWidth / 2, screenWidth / 2));
                itemGridInfoBinding.executePendingBindings();
            }
        }

        @Override
        public void onGridItemClick(Object gridObject) {
            adapterListener.onGridItemClick(gridObject);
        }
    }
}
