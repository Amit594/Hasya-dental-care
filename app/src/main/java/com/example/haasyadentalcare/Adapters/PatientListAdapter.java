package com.example.haasyadentalcare.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.haasyadentalcare.R;
import com.example.haasyadentalcare.Room.PatientData;


import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PatientListAdapter extends RecyclerView.Adapter<PatientListAdapter.ViewHolder> {


    List<PatientData> mdata;
    Context context;
    String mykey;



    public PatientListAdapter(List<PatientData> mdata, Context context,String mykey) {
        this.mdata = mdata;
        this.context = context;
        this.mykey=mykey;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //holder.imageView.setImageResource();
        holder.title.setText(mdata.get(position).getFname()+" "+mdata.get(position).getLname());
        Bitmap bitmap = BitmapFactory.decodeByteArray(mdata.get(position).getImage(), 0, mdata.get(position).getImage().length);
        holder.imageView.setImageBitmap(bitmap);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),mykey,Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView imageView;
        TextView title,description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=(CircleImageView)itemView.findViewById(R.id.img_user);
            title=(TextView)itemView.findViewById(R.id.txt_title);
            description=(TextView)itemView.findViewById(R.id.txt_description);
        }
    }
}
