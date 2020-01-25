package com.example.haasyadentalcare.ui.Services;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.haasyadentalcare.R;

import java.util.ArrayList;

public class ServicesFragment extends Fragment {


    RecyclerView recyclerView;
    Adapter adapter;
    private ServicesViewModel servicesViewModel;
    ArrayList<Service> data=new ArrayList<>();
    Service service;
    LinearLayoutManager layoutManager
            = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

    int[] images={R.drawable.ic_info};
    String[] title={"SINGLE SITTING...","COSMETIC DENTISTRY","CROWNS AND BRIDGES",
                    "DENTAL IMPLANTS","COMPLETE DENTURES","ORAL AND MAXILLOFA",
                    "WISDOM TOOTH","INLAYS AND ONLAYS","GENERAL TREATMENTS"};
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        servicesViewModel = ViewModelProviders.of(this).get(ServicesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_services, container, false);

        recyclerView=(RecyclerView)root.findViewById(R.id.service_list);

        for (int i=0;i<8;i++)
        {
            service=new Service();
            service.setImage(R.drawable.ic_info);
            service.setTitle(title[i]);

            data.add(service);
        }
        adapter=new Adapter(data,getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        return root;
    }
}