package it.docdev.app.sqjoke;

import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class SQJoke extends AppCompatActivity{

    private boolean clicked = false;
    private boolean paused = false;
    private MediaPlayer player;
    private ImageButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqjoke);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        button = (ImageButton) findViewById(R.id.tasto);
        button.setBackgroundResource(R.drawable.play);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                riproduci();
            }
        });
        player = MediaPlayer.create(SQJoke.this,R.raw.sigla);
        Toast.makeText(getBaseContext(), "Have Fun :) !", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        super.onPause();
        if(clicked) {
            player.pause();
            if (!player.isPlaying()) button.setBackgroundResource(R.drawable.play);
        }
    }

    // this is the code from android "immersive mode"
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (hasFocus) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                getWindow().getDecorView()
                        .setSystemUiVisibility(
                                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        );
            }
        }
    }

    void riproduci(){

        if (!clicked) {
            clicked = true;
            player.start();
            button.setBackgroundResource(R.drawable.pause);
            paused = false;

        } else {
            if (!paused) {
                paused = true;
                player.pause();
                button.setBackgroundResource(R.drawable.play);
                clicked = false;
            }
        }

    }

}
