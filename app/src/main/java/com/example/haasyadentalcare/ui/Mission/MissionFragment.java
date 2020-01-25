package com.example.haasyadentalcare.ui.Mission;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.haasyadentalcare.R;

import java.util.ArrayList;

public class MissionFragment extends Fragment {

    private ShareViewModel shareViewModel;
    GridView gridView;
    ArrayList itemlist=new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shareViewModel =
                ViewModelProviders.of(this).get(ShareViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mission, container, false);

        gridView=(GridView)root.findViewById(R.id.grideview);
        itemlist.add(new Item("James Fernando","Manager of Racer",R.drawable.testi01));
        itemlist.add(new Item("Andrew Atkinson","Life Manager",R.drawable.testi02));
        itemlist.add(new Item("James Fernando","Manager of Racer",R.drawable.testi01));
        itemlist.add(new Item("Andrew Atkinson","Life Manager",R.drawable.testi02));
        itemlist.add(new Item("James Fernando","Manager of Racer",R.drawable.testi01));
        itemlist.add(new Item("Andrew Atkinson","Life Manager",R.drawable.testi02));

        MyAdapter myAdapter=new MyAdapter(getContext(),R.layout.grideview_item,itemlist);
        gridView.setAdapter(myAdapter);
        return root;
    }
}