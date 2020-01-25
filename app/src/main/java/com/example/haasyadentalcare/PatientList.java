package com.example.haasyadentalcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.example.haasyadentalcare.Adapters.PatientListAdapter;
import com.example.haasyadentalcare.Room.DatabaseClient;
import com.example.haasyadentalcare.Room.PatientData;

import java.util.List;

public class PatientList extends AppCompatActivity {

    RecyclerView patientlist;
    PatientListAdapter patientListAdapter;
    String mykey;
    Intent receivedIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);

        patientlist=(RecyclerView)findViewById(R.id.recyclerView_patientlist);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        patientlist.setLayoutManager(mLayoutManager);
         receivedIntent= getIntent();
        mykey=receivedIntent.getStringExtra("Massage");

       // Toast.makeText(getApplicationContext(),mykey,Toast.LENGTH_LONG).show();
        getpatientList();
    }


    private void getpatientList()
    {
        class GetpatientList extends AsyncTask<Void,Void, List<PatientData>>
        {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected List<PatientData> doInBackground(Void... voids) {

                List<PatientData> patientDataList= DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().patientDao().getAll();

                return patientDataList;
            }
            @Override
            protected void onPostExecute(List<PatientData> patientData) {

                super.onPostExecute(patientData);
                patientListAdapter=new PatientListAdapter(patientData,getApplicationContext(),mykey);
                patientlist.setAdapter(patientListAdapter);
            }
        }
        GetpatientList pt=new GetpatientList();
        pt.execute();
    }
}
