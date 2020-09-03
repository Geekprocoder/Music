package com.example.music;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ListView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ListView listView;
String[] items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listview);
runTimePermission();
    }
    public void runTimePermission(){
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        disPlay();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                     permissionToken.continuePermissionRequest();
                    }
                }).check();

    }
    public ArrayList<File> findingSong(File file){
     ArrayList<File> arrayList=new ArrayList<>();
     File[] files=file.listFiles();
     for (File singleFile:files){
         if (singleFile.isDirectory()){
             arrayList.addAll(findingSong(singleFile));}


               if (singleFile.getName().endsWith(".mp3")||singleFile.getName().endsWith(".wan")||singleFile.getName().endsWith(".mp4")){
                   arrayList.add(singleFile);

     }}
     return arrayList;
    }
   public void  disPlay(){
        final ArrayList<File> mysong=findingSong(Environment.getExternalStorageDirectory());
        items=new String[mysong.size()];
        for (int i=0;i<mysong.size();i++){
items[i]=mysong.get(i).getName();
    }
       ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,items);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

                String songName=listView.getItemAtPosition(i).toString();
                Intent intent=new Intent(MainActivity.this,Player.class);
                intent.putExtra("song",mysong);
                intent.putExtra("songName",songName);
                intent.putExtra("position",i);
                startActivity(intent);
            }
        });
    }
}