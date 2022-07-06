package com.daelim.vct.crawling;

public class CrawlingData {
    private final String title;
    private final String article;
    private final String pressName;
    private final String pressThumb;
    private final String url;
    private final String imgURI;

    public CrawlingData(String title,
                        String article,
                        String pressName,
                        String pressThumb,
                        String url,
                        String imgURI) {
        this.title = title;
        this.article = article;
        this.pressName = pressName;
        this.pressThumb = pressThumb;
        this.url = url;
        this.imgURI = imgURI;
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
