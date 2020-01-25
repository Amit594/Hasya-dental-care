package com.example.haasyadentalcare.ui.Review;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.haasyadentalcare.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder>{

    List<Review> mdata;
    Context context;

    public ReviewAdapter(List<Review> mdata, Context context) {
        this.mdata = mdata;
        this.context = context;
    }

    @NonNull
    @Override
    public ReviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.ViewHolder holder, final int position) {
            holder.imageView.setImageResource(mdata.get(position).getImage());
            holder.name.setText(mdata.get(position).getTitle());
            holder.occupation.setText("-"+mdata.get(position).getOccupation());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent view=new Intent(v.getContext(), ReviewView.class);
                    view.putExtra("name",mdata.get(position).getTitle());
                    view.putExtra("occu",mdata.get(position).getOccupation());
                    v.getContext().startActivity(view);
                }
            });

    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView imageView;
        TextView name,occupation;
        
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=(CircleImageView)itemView.findViewById(R.id.image_r);
            name=(TextView)itemView.findViewById(R.id.txt_title);
            occupation=(TextView)itemView.findViewById(R.id.txt_occupation);


        }
    }
}
