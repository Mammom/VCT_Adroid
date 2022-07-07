package com.daelim.vct.crawling;

import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Crawler {
    private final static String CRAWL_URL = "https://search.naver.com/search.naver?where=news&ie="+
            "utf8&sm=nws_hty&query=%EC%95%BC%EC%B1%84%EA%B0%80%EA%B2%A9";


    public static List<CrawlingData> crawling() throws IOException {
            Elements elementList = Observable.just(1)
                .subscribeOn(Schedulers.io())
                .map(notUsed -> Jsoup.connect(CRAWL_URL).get()
                        .select("div[class=\"news_wrap api_ani_send\"]"))
                .blockingFirst();

        Observable<Element> elementObservable = Observable.fromIterable(elementList)
                .take(5);


        ObservableSource<String> titles = elementObservable
                .map(element -> element.select("a[class=\"news_tit\"]")
                        .attr("title"));

        ObservableSource<String> articles = elementObservable
                .map(element -> element.select("a[class=\"api_txt_lines dsc_txt_wrap\"]")
                        .text());

        ObservableSource<String> pressNames = elementObservable
                .map(element -> element.select("a[class=\"info press\"]")
                        .text()
                        .replace("언론사 선정", ""));

        ObservableSource<String> pressThumbs = elementObservable
                .map(element -> element.select("img[class=\"thumb\"]")
                        .attr("src"));

        ObservableSource<String> newsURLs = elementObservable
                .map(element -> element.select("a[class=\"news_tit\"]")
                        .attr("href"));

        ObservableSource<String> imgURIs = elementObservable
                .map(element -> element.select("img[class=\"thumb api_get\"]")
                        .attr("src"));

        return Observable.zip(
                titles,
                articles,
                pressNames,
                pressThumbs,
                newsURLs,
                imgURIs,
                CrawlingData::new)
                .toList()
                .blockingGet();

    }
}

