package com.example.haasyadentalcare.ui.AboutUs;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.haasyadentalcare.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;

import static com.google.android.youtube.player.YouTubePlayer.FULLSCREEN_FLAG_ALWAYS_FULLSCREEN_IN_LANDSCAPE;
import static com.google.android.youtube.player.YouTubePlayer.FULLSCREEN_FLAG_CONTROL_ORIENTATION;
import static com.google.android.youtube.player.YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT;

public class About extends Fragment  {

    public static final String apikey="AIzaSyCoozv23m4giBXpQpfXiJnI7VPt-FNj-tk";
    private YouTubePlayer YPlayer;

    Button playvdo;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        View root= inflater.inflate(R.layout.about_fragment, container, false);

        YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.youtube_fragment, youTubePlayerFragment).commit();

//        playvdo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
                youTubePlayerFragment.initialize(apikey, new YouTubePlayer.OnInitializedListener() {

                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {

                        if (!wasRestored) {
                            YPlayer = player;

                            YPlayer.setFullscreen(false);
                            YPlayer.setShowFullscreenButton(false);
                            YPlayer.setFullscreenControlFlags(FULLSCREEN_FLAG_CUSTOM_LAYOUT);


                            YPlayer.loadVideo("vVpHeJ95irE");
                            YPlayer.play();
                        }

                    }

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider arg0, YouTubeInitializationResult arg1) {
                        // TODO Auto-generated method stub

                    }
                });






        return root;
    }





    void setYPlayer()
    {



    }




}
