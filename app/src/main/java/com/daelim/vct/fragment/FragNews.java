package com.daelim.vct.fragment;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.daelim.vct.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class FragNews extends Fragment {


    private View view;

    TextView tv_news;
    ImageView iv_weather;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_news,container,false);

        init(view);


        new WeatherAsynTask(tv_news).execute("https://www.google.com/search?q=%EC%95%BC%EC%B1%84+%EA%B0%80%EA%B2%A9&sxsrf=ALiCzsboTnq7v6IkiNF4OP8ha2fTM6yZ0Q:1656691247089&source=lnms&tbm=nws&sa=X&ved=2ahUKEwjO4qPqh9j4AhUO_GEKHQIjC8AQ_AUoAXoECAEQAw&biw=1418&bih=1319&dpr=1","div[class=\"mCBkyc y355M ynAwRc MBeuO nDgy9d\"]");

        return view;
    }

    public void init(View view) {
        tv_news = view.findViewById(R.id.tv_news);
        iv_weather = view.findViewById(R.id.iv_weather);
    }
}

class WeatherAsynTask extends AsyncTask<String,Void,String>{

    TextView textView;

    public WeatherAsynTask(TextView textView){

        this.textView = textView;

    }

    @Override
    protected String doInBackground(String... params) {

        String URL = params[0];
        String El = params[1];
        String result = "";

        try {
            Document document = Jsoup.connect(URL).get();
            Elements elements = document.select(El);

            for(Element element : elements){

                result = result+element.text()+"\n";

            }
            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s){
        super.onPostExecute(s);

        textView.setText(s);

    }

}





