package com.sid.musicwiki.ui.genredetails.genrefrag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.sid.musicwiki.R;
import com.sid.musicwiki.databinding.FragmentGenreDataBinding;
import com.sid.musicwiki.ui.albuminfo.AlbumInfoActivity;

public class GenreDataFragment extends Fragment implements GenreDataItemAdapter.GenreDataItemAdapterListener {

    private static final String ARG_PARAM_1 = "ARG_PARAM_1";
    private static final String ARG_PARAM_2 = "ARG_PARAM_2";

    private FragmentGenreDataBinding fragmentBinding;
    private GenreDataViewModel viewModel;
    private String dataType;
    private String genreName;
    private GenreDataItemAdapter genreDataItemAdapter;

    public static GenreDataFragment newInstance(String dataType, String genreName) {
        GenreDataFragment fragment = new GenreDataFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_1, dataType);
        args.putString(ARG_PARAM_2, genreName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataType = getArguments().getString(ARG_PARAM_1);
        genreName = getArguments().getString(ARG_PARAM_2);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_genre_data, container, false);
        View view = fragmentBinding.getRoot();
        viewModel = new GenreDataViewModel(getContext());
        fragmentBinding.setViewModel(viewModel);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpDataSource(dataType, genreName);
        setUpView();
    }

    private void setUpView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        genreDataItemAdapter = new GenreDataItemAdapter(this, false, 0);
        fragmentBinding.fgdDataRv.setLayoutManager(gridLayoutManager);
        fragmentBinding.fgdDataRv.setAdapter(genreDataItemAdapter);
    }

    private void setUpDataSource(String dataType, String genreName) {
        viewModel.setUpDataSource(dataType, genreName);
    }


    @Override
    public void onGridItemClick(Object gridObject) {
        startActivity(AlbumInfoActivity.newIntent(getActivity(), gridObject));
    }
}
