package com.daelim.vct.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daelim.vct.R;
import com.daelim.vct.adapaters.ViewAdepter;
import com.daelim.vct.crawling.Crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class FragNews extends Fragment {
    private View view;

    RecyclerView newsList;
    Disposable newsCrawlDisposable;
    ViewAdepter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_news,container,false);

        init(view);

        crawlData();

        return view;
    }

    private void init(View view) {
        newsList = view.findViewById(R.id.newsList);
        adapter = new ViewAdepter(getContext());

        newsList.setAdapter(adapter);

        setRvView();

    }

    private void setRvView(){
        adapter.setOnItemClickListener((view1, position) -> {
            String url = adapter.getURl(position);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });

        newsList.setLayoutManager(new LinearLayoutManager(getContext()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
    }

    private void crawlData(){
        newsCrawlDisposable = Observable.interval(0L, 10L, TimeUnit.MINUTES)
                .subscribeOn(Schedulers.io())
                .map(notUsed -> Crawler.crawling())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(3)
                .subscribe(data -> {
                    adapter.updateData(data);
                    adapter.notifyDataSetChanged();
                },
                throwable -> {Toast.makeText(getContext(), throwable.getLocalizedMessage(),
                                    Toast.LENGTH_LONG).show();
                    Log.w( "crawlData: ", throwable.getLocalizedMessage());}
                );
    }
}





