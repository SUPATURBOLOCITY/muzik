package example.com.muzik.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import example.com.musico.R;
import example.com.muzik.utils.MusicData;
import example.com.muzik.data.MusicItem;
import example.com.muzik.adapter.MusicAdapter;

public class AlbumsFragment extends Fragment {

    private RecyclerView recyclerView;

    public AlbumsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        //get all songs
        ArrayList<MusicItem> musicItems = MusicData.getMusicItemsList(getContext());
        MusicAdapter musicAdapters = new MusicAdapter(getContext(), musicItems, getTag(), getTag());
        recyclerView.setAdapter(musicAdapters);
    }

    /**
     * update recyclerview grid column number depending on the current orientation
     * @param newConfig reference to changed configuration
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int orientation = newConfig.orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        }
    }
}
