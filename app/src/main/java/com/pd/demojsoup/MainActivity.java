package com.pd.demojsoup;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    RecyclerView rcvNewChapter;
    Handler handler;
    Thread threadGetData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcvNewChapter = findViewById(R.id.rcvNewChapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        rcvNewChapter.setLayoutManager(gridLayoutManager);

        NewChapterAdapter newChapterAdapter = new NewChapterAdapter(this);
        rcvNewChapter.setAdapter(newChapterAdapter);

        new Thread(this::getData).start();

    }

    void getData(){
        try {
            Document document= Jsoup.connect("https://ln.hako.re/").get();
            Elements elements=document.select("div.container div.row main.row div.thumb-item-flow.col-4.col-1-4-m.col-2-l");
            Log.d("getData", "getData: "+elements.size());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
