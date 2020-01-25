package com.example.haasyadentalcare.ui.Review;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.haasyadentalcare.R;

import java.util.ArrayList;

public class ReviewFragment extends Fragment {

    RecyclerView recyclerView;
    ReviewAdapter adapter;
    ArrayList<Review> mdata=new ArrayList<>();
    Review review;
    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

    int[] images={R.drawable.testi01};
    String[] name={"James Fernando","Andrew Atkinson","Amanda DOE","Martin Johnson","Andrew Atkinson","James Fernando"};


    String[] occupation={"Manager of Racer"," Life Manager","Manager of Racer","Founder of Goosilo","Manager of Racer","Life Manager"};



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_review, container, false);

        recyclerView=(RecyclerView) root.findViewById(R.id.review_list);

        for(int i=0;i<=4;i++)
        {
            review =new Review();
            review.setImage(R.drawable.testi01+i);
            review.setTitle(name[i]);
            review.setOccupation(occupation[i]);
            mdata.add(review);
        }

          adapter=new ReviewAdapter(mdata,getContext());
          recyclerView.setLayoutManager(layoutManager);
          recyclerView.setAdapter(adapter);
          return root;
    }
}