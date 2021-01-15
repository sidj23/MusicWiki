package com.sid.musicwiki.ui.genredetails;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class GenreViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragmentList = new ArrayList<>();
    private GenreViewPagerAdapterListener listener;

    public GenreViewPagerAdapter(@NonNull FragmentManager fm, int behavior, GenreViewPagerAdapterListener listener) {
        super(fm, behavior);
        this.listener = listener;
    }

    public void clearItem() {
        fragmentList.clear();
    }

    public void addItems(List<Fragment> fragments) {
        fragmentList.addAll(fragments);
        notifyDataSetChanged();
        if (listener != null)
            listener.setTabTitle();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public interface GenreViewPagerAdapterListener {
        void setTabTitle();
    }
}
