package com.example.haasyadentalcare;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haasyadentalcare.Room.DatabaseClient;
import com.example.haasyadentalcare.Room.PatientData;
import com.example.haasyadentalcare.Room.PatientOperations;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddPatient extends AppCompatActivity {

    CircleImageView p_image;
    EditText fname,mname,lname,gender,email,contact;
    Button add;
    TextView selectimage;
    TextInputLayout textInputLayout_fname,textInputLayout_mname,textInputLayout_lname,textInputLayout_gender,textInputLayout_email,textInputLayout_contact;
    String H_fname,H_mname,H_lname,H_gender,H_email,H_contact;
    private static int RESULT_LOAD_IMAGE = 1;
    PatientOperations patientOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);





        fname=(EditText)findViewById(R.id.edit_fname);
        mname=(EditText)findViewById(R.id.edit_mname);
        lname=(EditText)findViewById(R.id.edit_lname);
        gender=(EditText)findViewById(R.id.edit_gender);
        email=(EditText)findViewById(R.id.edit_email);
        contact=(EditText)findViewById(R.id.edit_contact);
        selectimage=(TextView) findViewById(R.id.txt_select);
        add=(Button)findViewById(R.id.btn_add);
        p_image=(CircleImageView) findViewById(R.id.img_patients);




        contact.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String prefix = "+91 ";

                int count = (s == null) ? 0 : s.toString().length();
                if (count < prefix.length()) {

                    contact.setText(prefix);

                    /*
                     * This line ensure when you do a rapid delete (by holding down the
                     * backspace button), the caret does not end up in the middle of the
                     * prefix.
                     */
                    int selectionIndex = Math.max(count + 1, prefix.length());

                    contact.setSelection(selectionIndex);
                }
            }
        });
        //contact.setCompoundDrawablePadding(code.length()*10);
        selectimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
        textInputLayout_fname=(TextInputLayout)findViewById(R.id.textinput_fname);
        textInputLayout_mname=(TextInputLayout)findViewById(R.id.textinput_mname);
        textInputLayout_lname=(TextInputLayout)findViewById(R.id.textinput_lname);
        textInputLayout_gender=(TextInputLayout)findViewById(R.id.textinput_gender);
        textInputLayout_email=(TextInputLayout)findViewById(R.id.textinput_email);
        textInputLayout_contact=(TextInputLayout)findViewById(R.id.textinput_contact);

        textInputLayout_gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        gender.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                setGender();
                return false;
            }
        });

    gender.setOnFocusChangeListener(new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if(!hasFocus)
            {

            }
            else
            {

                setGender();
            }
        }
    });



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                H_fname=fname.getText().toString();
                H_mname=mname.getText().toString();
                H_lname=lname.getText().toString();
                H_gender=gender.getText().toString();
                H_email=email.getText().toString();
                H_contact=contact.getText().toString();

                textInputLayout_fname.setErrorEnabled(false);
                textInputLayout_mname.setErrorEnabled(false);
                textInputLayout_lname.setErrorEnabled(false);
                textInputLayout_gender.setErrorEnabled(false);
                textInputLayout_email.setErrorEnabled(false);
                textInputLayout_contact.setErrorEnabled(false);
                checkempty();

                if (checkempty())
                {
                    //Toast.makeText(getApplicationContext(),"check empty true",Toast.LENGTH_LONG).show();
                    validation();
                    if (validation())
                    {
                        Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG).show();
                        savedata();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"validation false",Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"please fill all the fields",Toast.LENGTH_LONG).show();
                }
            }
        });







    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImageURI = data.getData();

            Picasso.with(getApplicationContext()).load(selectedImageURI).resize(120,120).centerCrop().into(p_image);

        }
    }







    boolean checkempty()
    {
        boolean checkempty=true;
            if (TextUtils.isEmpty(H_fname))
            {
                textInputLayout_fname.setErrorEnabled(true);
                textInputLayout_fname.setError("Enter First Name");
                checkempty=false;
            }
            if(TextUtils.isEmpty(H_mname))
            {
                textInputLayout_mname.setErrorEnabled(true);
                textInputLayout_mname.setError("Enter Middle Name");
                checkempty=false;
            }
            if(TextUtils.isEmpty(H_lname))
            {
                textInputLayout_lname.setErrorEnabled(true);
                textInputLayout_lname.setError("Enter Last Name");
                checkempty=false;
            }
            if(TextUtils.isEmpty(H_gender))
            {
                textInputLayout_gender.setErrorEnabled(true);
                textInputLayout_gender.setError("Select Gender");
                checkempty=false;
            }
            if(TextUtils.isEmpty(H_email))
            {
                textInputLayout_email.setErrorEnabled(true);
                textInputLayout_email.setError("Enter Email");
                checkempty=false;
            }
            if (TextUtils.isEmpty(H_contact))
            {
                textInputLayout_contact.setErrorEnabled(true);
                textInputLayout_contact.setError("Enter contact");
                checkempty=false;
            }

            return checkempty;
    }

    boolean validation()
    {
        boolean validation=true;

        if(!H_fname.matches("^[A-Za-z]+$"))
        {
            textInputLayout_fname.setErrorEnabled(true);
            textInputLayout_fname.setError("Enter valid First Name");
            validation=false;
        }
        if(!H_mname.matches("^[A-Za-z]+$"))
        {
            textInputLayout_mname.setErrorEnabled(true);
            textInputLayout_mname.setError("Enter valid Middle Name");
            validation=false;
        }
        if(!H_lname.matches("^[A-Za-z]+$"))
        {
            textInputLayout_lname.setErrorEnabled(true);
            textInputLayout_lname.setError("Enter vali Last Name");
            validation=false;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(H_email).matches())
        {
            textInputLayout_email.setErrorEnabled(true);
            textInputLayout_email.setError("Enter valid Email");
            validation=false;
        }
        if(!Patterns.PHONE.matcher(H_contact).matches())
        {
            textInputLayout_contact.setErrorEnabled(true);
            textInputLayout_contact.setError("Enter valid contact");
            validation=false;
        }

        return validation;
    }

    void setGender()
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(AddPatient.this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.select_gender, null);
        builder.setView(dialogView);

        final RadioGroup radioGroup=(RadioGroup)dialogView.findViewById(R.id.sex);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                int selectid=radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton=(RadioButton)dialogView.findViewById(selectid);
                gender.setText(radioButton.getText());

               email.requestFocus();


            }
        })
                .setNegativeButton("cencel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        email.requestFocus();
                    }
                });

        builder.show();
    }
    void savedata()
    {

        Bitmap bitmap = ((BitmapDrawable) p_image.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        final byte[] imageInByte = baos.toByteArray();

        class save extends AsyncTask
        {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }


            @Override
            protected Object doInBackground(Object[] objects) {

                PatientData patientData=new PatientData();
                patientData.setImage(imageInByte);
                patientData.setFname(H_fname);
                patientData.setMname(H_mname);
                patientData.setLname(H_lname);
                patientData.setGender(H_gender);
                patientData.setEmail(H_email);
                patientData.setContact(H_contact);
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().patientDao().insert(patientData);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
                Intent home=new Intent(getApplicationContext(),Home.class);
                startActivity(home);
                finish();

            }

        }
        save st=new save();
        st.execute();


    }
}
