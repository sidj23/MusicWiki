package com.sid.musicwiki.util;

import android.content.Context;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sid.musicwiki.R;
import com.sid.musicwiki.data.genreapi.Genre;
import com.sid.musicwiki.ui.genredetails.GenreViewPagerAdapter;
import com.sid.musicwiki.ui.genredetails.genrefrag.GenreDataItemAdapter;
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

    @BindingAdapter({"imageUrl"})
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.ic_headphones)
                .error(R.drawable.ic_headphones);
        Glide.with(context).load(url).apply(options).into(imageView);
    }

    @BindingAdapter({"viewPager"})
    public static void setViewPagerData(ViewPager viewPager, List<Fragment> fragmentList) {
        GenreViewPagerAdapter adapter = (GenreViewPagerAdapter) viewPager.getAdapter();
        if (adapter != null) {
            adapter.clearItem();
            adapter.addItems(fragmentList);
        }
    }

    @BindingAdapter({"genreDataAdapter"})
    public static void setGenreDataAdapter(RecyclerView recyclerView, List<Object> objectList) {
        GenreDataItemAdapter adapter = (GenreDataItemAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(objectList);
        }
    }
}
