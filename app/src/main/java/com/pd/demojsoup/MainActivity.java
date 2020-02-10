package com.pd.demojsoup;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Chapter> chapters;

    RecyclerView rcvNewChapter;
    Handler handler;
    Thread threadGetData;

    private final int THREAD_GET_DATA_COMPLETE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcvNewChapter = findViewById(R.id.rcvNewChapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        rcvNewChapter.setLayoutManager(gridLayoutManager);

        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case THREAD_GET_DATA_COMPLETE:
                        NewChapterAdapter newChapterAdapter = new NewChapterAdapter(MainActivity.this, chapters);
                        rcvNewChapter.setAdapter(newChapterAdapter);
                        break;
                }

            }
        };

        new Thread(this::getData).start();


    }

    void getData() {
        try {
            chapters = new ArrayList<>();
            Document document = Jsoup.connect("https://ln.hako.re/").get();
            Elements imgElements = document.select("div.container div.row main.row div.thumb-item-flow.col-4.col-1-4-m.col-2-l div.a6-ratio div.content");
            Elements chapterElements = document.select("div.container div.row main.row div.thumb-item-flow.col-4.col-1-4-m.col-2-l div.thumb-wrapper");
            Elements volElements = document.select("div.container div.row main.row div.thumb-item-flow.col-4.col-1-4-m.col-2-l div.thumb_attr.series-title");
            for (Element element : imgElements) {
                Chapter chapter = new Chapter();
                chapter.setImgChapter(element.attr("data-bg"));
                chapters.add(chapter);
            }
            Log.d("getData", "getData: " + imgElements.size());
            Message message = new Message();
            message.what = THREAD_GET_DATA_COMPLETE;
            handler.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
