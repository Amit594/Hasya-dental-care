package com.example.haasyadentalcare.ui.home;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.haasyadentalcare.AddApoinment;
import com.example.haasyadentalcare.AddPatient;
import com.example.haasyadentalcare.PatientList;
import com.example.haasyadentalcare.R;
import com.example.haasyadentalcare.Room.DatabaseClient;
import com.example.haasyadentalcare.Room.PatientData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    TextView celender,totalpatients;
    ImageView appointment,viewappointment,upacomingappointment,patient,viewpatientlist;
    List<PatientData> patientDataList;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        appointment=(ImageView)root.findViewById(R.id.img_addapointment);
        patient=(ImageView)root.findViewById(R.id.img_addpatiets);
        totalpatients=(TextView)root.findViewById(R.id.txt_patientcount);
        viewappointment=(ImageView)root.findViewById(R.id.img_viewapointment);
        upacomingappointment=(ImageView)root.findViewById(R.id.img_upcoming);


        appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select();
            }
        });




        patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addpatient=new Intent(getActivity(), AddPatient.class);
                startActivity(addpatient);
            }
        });

        viewpatientlist=(ImageView)root.findViewById(R.id.img_viewpatients);
        viewpatientlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewlist=new Intent(getActivity(), PatientList.class);
                viewlist.putExtra("Massage","ForView");
                startActivity(viewlist);
            }
        });



        //totalpatients.setText(patientDataList.size());

        settext();
        celender=(TextView) root.findViewById(R.id.txt_celender);
        celender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long startMillis = 0;
                Uri.Builder builder = CalendarContract.CONTENT_URI.buildUpon();
                builder.appendPath("time");
                ContentUris.appendId(builder, startMillis);
                Intent intent = new Intent(Intent.ACTION_VIEW)
                        .setData(builder.build());
                startActivity(intent);
            }
        });
        return root;
    }

    void settext()
    {
        class GetpatientList extends AsyncTask<Void,Void, List<PatientData>>
        {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @SuppressLint("WrongThread")
            @Override
            protected List<PatientData> doInBackground(Void... voids) {

                List<PatientData> patientDataList= DatabaseClient.getInstance(getActivity()).getAppDatabase().patientDao().getAll();
                String size= String.valueOf(patientDataList.size());
                totalpatients.setText(size);
                return patientDataList;



            }
            @Override
            protected void onPostExecute(List<PatientData> patientData) {
                super.onPostExecute(patientData);
            }
        }
        GetpatientList pt=new GetpatientList();
        pt.execute();
    }

    void select()
    {
        final AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity(),R.style.AlertDialog);
        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.dialog_appointment, null);
        builder.setView(dialogView);

        final RadioGroup radioGroup=(RadioGroup)dialogView.findViewById(R.id.appointment);

        builder.setPositiveButton("confirm", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                int selectid=radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton=(RadioButton)dialogView.findViewById(selectid);
                radioButton.getText();

                if (radioButton.getText().equals("Existing Patient"))
                {
                    Intent patientlist=new Intent(getActivity(),PatientList.class);
                    patientlist.putExtra("Massage","ForAppoinment");
                    startActivity(patientlist);
                }
                else if(radioButton.getText().equals("New Patient"))
                {
                    Toast.makeText(getContext(),"New",Toast.LENGTH_LONG).show();

                    Intent appoinment=new Intent(getActivity(), AddApoinment.class);
                    startActivity(appoinment);
                }


            }
        })
                .setNegativeButton("cencel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();

                    }
                });



        builder.show();


    }

}
