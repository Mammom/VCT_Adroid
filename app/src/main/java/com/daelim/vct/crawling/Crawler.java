package com.daelim.vct.crawling;

import android.os.Build;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Crawler {
    private final static String CRAWL_URL = "https://search.naver.com/search.naver?where=news&ie="+
            "utf8&sm=nws_hty&query=%EC%95%BC%EC%B1%84%EA%B0%80%EA%B2%A9";

    public static List<CrawlingData> crawling() throws IOException {
        return Observable.fromIterable(Jsoup.connect(CRAWL_URL).get()
                        .select("div[class=\"news_wrap api_ani_send\"]"))
                .subscribeOn(Schedulers.computation())
                .map(element -> {
                    String title = element.select("a[class=\"news_tit\"]").attr("title");
                    String article = element.select("a[class=\"api_txt_lines dsc_txt_wrap\"]").text();
                    String pressName = element.select("a[class=\"info press\"]").text().replace("언론사 선정", "");
                    String pressThumb = element.select("img[class=\"thumb\"]").attr("src");
                    String newsURL = element.select("a[class=\"news_tit\"]").attr("href");
                    String imgURI = element.select("img[class=\"thumb api_get\"]").attr("src");

                    Map<String, String> args = new HashMap<>(6);
                    args.put("title", title);
                    args.put("article", article);
                    args.put("pressName", pressName);
                    args.put("pressThumb", pressThumb);
                    args.put("newsURL", newsURL);
                    args.put("imgURI", imgURI);

                   return new CrawlingData(args);
                })
                .take(5)
                .toList()
                .blockingGet();


    }
}

