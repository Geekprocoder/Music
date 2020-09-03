package com.example.music;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.icu.text.Transliterator;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.FileObserver;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;

public class Player extends AppCompatActivity {
ImageButton next,previos,play,pause;
TextView textView;
SeekBar seekBar;
int   position;
MediaPlayer mediaPlayer;
ArrayList<File> allSong;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_player);
        play=findViewById(R.id.play);
        pause=findViewById(R.id.pause);
        next=findViewById(R.id.next);
        previos=findViewById(R.id.previous);
        textView=findViewById(R.id.textView);
        seekBar=findViewById(R.id.seekBar);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        allSong=(ArrayList) bundle.getParcelableArrayList("song");
        position=bundle.getInt("position",0);
    Uri uri=Uri.parse(allSong.get(position).toString());
        mediaPlayer=MediaPlayer.create(this,uri);
       // mediaPlayer.start();
    }
    public void pause(View view) {
        mediaPlayer.stop();
        pause.setVisibility(View.GONE);

        play.setVisibility(View.VISIBLE);
mediaPlayer.start();

    }
    public void play(View view) {
        play.setVisibility(View.GONE);
        mediaPlayer.start();
        pause.setVisibility(View.VISIBLE);



    }





    }
