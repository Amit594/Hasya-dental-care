package com.example.haasyadentalcare.ui.Services;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.haasyadentalcare.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<Service> mdata;
    Context context;

    public Adapter(List<Service> mdata, Context context) {
        this.mdata = mdata;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.service_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(mdata.get(position).getImage());
        holder.text.setText(mdata.get(position).getTitle());
        holder. itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewdata=new Intent(v.getContext(),ServiceView.class);
                v.getContext().startActivity(viewdata);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView imageView;
        TextView text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView= (CircleImageView) itemView.findViewById(R.id.service_icon);
            text=(TextView)itemView.findViewById(R.id.text_title);




        }
    }

}
