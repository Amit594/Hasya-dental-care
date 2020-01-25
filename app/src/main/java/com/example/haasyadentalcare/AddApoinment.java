package com.example.haasyadentalcare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class AddApoinment extends AppCompatActivity {


    String H_fname,H_lname,H_gender,H_date,H_starttime,H_endtime,H_dentist,H_remarks;
    EditText fname,lname,gender,date,starttime,endtime,dentis,remarks;
    TextInputLayout textInputLayout_fname,textInputLayout_lname,textInputLayout_gender,textInputLayout_date,
            textInputLayout_starttime,textInputLayout_endtime,textInputLayout_dentis,textInputLayout_remarks;

    Button add;
    DatePickerDialog picker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_apoinment);

        fname=(EditText)findViewById(R.id.edit_fname);
        lname=(EditText)findViewById(R.id.edit_lname);
        gender=(EditText)findViewById(R.id.edit_gender);
        date=(EditText)findViewById(R.id.edit_date);
        starttime=(EditText)findViewById(R.id.edit_starttime);
        endtime=(EditText)findViewById(R.id.edit_endtime);
        dentis=(EditText)findViewById(R.id.edit_denties);
        remarks=(EditText)findViewById(R.id.edit_remarks);

        date.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                getdate();
                return false;
            }
        });

        gender.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                selectgender();
                return false;
            }
        });
        starttime.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gettime(starttime);
                return false;
            }
        });


        endtime.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                gettime(endtime);
                return false;
            }
        });
        textInputLayout_fname=(TextInputLayout)findViewById(R.id.textinput_fname);
        textInputLayout_lname=(TextInputLayout)findViewById(R.id.textinput_lname);
        textInputLayout_gender=(TextInputLayout)findViewById(R.id.textinput_gender);
        textInputLayout_date=(TextInputLayout)findViewById(R.id.textinput_date);
        textInputLayout_starttime=(TextInputLayout)findViewById(R.id.textinput_starttiem);
        textInputLayout_endtime=(TextInputLayout)findViewById(R.id.textinput_endtime);
        textInputLayout_dentis=(TextInputLayout)findViewById(R.id.textinput_denties);
        textInputLayout_remarks=(TextInputLayout)findViewById(R.id.textinput_remarks);

        add=(Button)findViewById(R.id.btn_add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                H_fname=fname.getText().toString();
                H_lname=lname.getText().toString();
                H_gender=gender.getText().toString();
                H_date=date.getText().toString();
                H_starttime=date.getText().toString();
                H_endtime=date.getText().toString();
                H_dentist=dentis.getText().toString();
                H_remarks=remarks.getText().toString();

                textInputLayout_fname.setErrorEnabled(false);
                textInputLayout_lname.setErrorEnabled(false);
                textInputLayout_gender.setErrorEnabled(false);
                textInputLayout_date.setErrorEnabled(false);
                textInputLayout_starttime.setErrorEnabled(false);
                textInputLayout_endtime.setErrorEnabled(false);
                textInputLayout_dentis.setErrorEnabled(false);
                textInputLayout_remarks.setErrorEnabled(false);

            }
        });






    }


    boolean checkempty()
    {
        boolean checkempty=true;

        if(TextUtils.isEmpty(H_fname))
        {
            textInputLayout_fname.setErrorEnabled(true);
            textInputLayout_fname.setError("Enter First Name");
            checkempty=false;
        }

        if(TextUtils.isEmpty(H_lname))
        {
            textInputLayout_lname.setErrorEnabled(true);
            textInputLayout_lname.setError("Enter Last name");
            checkempty=false;

        }
        if(TextUtils.isEmpty(H_gender)) {
            textInputLayout_gender.setErrorEnabled(true);
            textInputLayout_gender.setError("Selct gender");
            checkempty=false;
        }

        if(TextUtils.isEmpty(H_date))
        {
            textInputLayout_date.setErrorEnabled(true);
            textInputLayout_gender.setError("select date");
            checkempty=false;
        }
        if (TextUtils.isEmpty(H_starttime))
        {
            textInputLayout_starttime.setErrorEnabled(true);
            textInputLayout_starttime.setError("Set start time");
            checkempty=false;
        }

        if(TextUtils.isEmpty(H_endtime))
        {
            textInputLayout_endtime.setErrorEnabled(true);
            textInputLayout_endtime.setError("Set end time");
            checkempty=false;
        }

        if(TextUtils.isEmpty(H_dentist))
        {
            textInputLayout_dentis.setErrorEnabled(true);
            textInputLayout_dentis.setError("Enter dentis name");
            checkempty=false;
        }

        if(TextUtils.isEmpty(H_remarks))
        {
            textInputLayout_remarks.setErrorEnabled(true);
            textInputLayout_remarks.setError("Enter Remarks");
            checkempty=false;
        }
        return checkempty;
    }

    boolean validate()
    {

        boolean validation=true;


        if(!H_fname.matches("^[A-Za-z]+$"))
        {
            textInputLayout_fname.setErrorEnabled(true);
            textInputLayout_fname.setError("Enter valid First Name");
            validation=false;
        }
        if(!H_lname.matches("^[A-Za-z]+$"))
        {
            textInputLayout_lname.setErrorEnabled(true);
            textInputLayout_lname.setError("Enter valid Last Name");
            validation=false;
        }

        return validation;
    }


    void gettime(final EditText settime)
    {

        // TODO Auto-generated method stub
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;

        mTimePicker = new TimePickerDialog(AddApoinment.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                settime.setText( selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();

    }

    void getdate()
    {

        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        // date picker dialog
        picker = new DatePickerDialog(AddApoinment.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                    }
                }, year, month, day);
        picker.show();
    }


    void selectgender()
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(AddApoinment.this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.select_gender, null);
        builder.setView(dialogView);

        final RadioGroup radioGroup=(RadioGroup)dialogView.findViewById(R.id.sex);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                int selectid=radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton=(RadioButton)dialogView.findViewById(selectid);
                gender.setText(radioButton.getText());
                dialog.dismiss();




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
