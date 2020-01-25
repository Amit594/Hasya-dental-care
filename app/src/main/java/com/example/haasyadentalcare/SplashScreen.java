package com.example.haasyadentalcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;

public class SplashScreen extends AppCompatActivity {

    private ImageView img;

    TextSwitcher textSwitcher;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        img=(ImageView)findViewById(R.id.img_icon);
      textView=(TextView)findViewById(R.id.txt_main);
        final Animation animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadein);
        final Animation textanimation=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadeout);
//
        new CountDownTimer(2000, 2000) {

            public void onTick(long millisUntilFinished) {
                img.startAnimation(animFadeIn);

            }

            public void onFinish() {
                                new CountDownTimer(1000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        textView.setAnimation(textanimation);
                        textView.setText("Hasya dental care");
                    }

                    public void onFinish() {
//                //Toast.makeText(getApplicationContext(),"done",Toast.LENGTH_LONG).show();
                    Intent home=new Intent(getApplicationContext(),Home.class);
                    startActivity(home);
                    finish();
                    }
                }.start();

            }
        }.start();




    }
}
