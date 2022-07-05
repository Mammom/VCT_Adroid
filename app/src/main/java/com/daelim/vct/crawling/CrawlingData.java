package com.daelim.vct.crawling;

import java.util.Map;

public class CrawlingData {
    private String title;
    private String article;
    private String pressName;
    private String pressThumb;
    private String url;
    private String imgURI;

    public CrawlingData(Map<String, String> newsCrawlData){
        this.title = newsCrawlData.get("title");
        this.article = newsCrawlData.get("article");
        this.pressName = newsCrawlData.get("pressName");
        this.pressThumb = newsCrawlData.get("pressThumb");
        this.url = newsCrawlData.get("url");
        this.imgURI = newsCrawlData.get("imgURI");
    }

    public String getTitle() {
        return title;
    }

    public String getArticle() {
        return article;
    }

    public String getPressName() {
        return pressName;
    }

    public String getPressThumb() {
        return pressThumb;
    }

    public String getUrl() {
        return url;
    }

    public String getImgURI() {
        return imgURI;
    }
}
