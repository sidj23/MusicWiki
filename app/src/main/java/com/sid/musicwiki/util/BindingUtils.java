package com.sid.musicwiki.util;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.sid.musicwiki.data.genreapi.Genre;
import com.sid.musicwiki.ui.main.GenreListAdapter;

import java.util.List;

public class BindingUtils {

    @BindingAdapter({"genreAdapter"})
    public static void genreAdapterList(RecyclerView recyclerView, List<Genre> genreList) {
        GenreListAdapter adapter = (GenreListAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(genreList);
        }
    }
}
