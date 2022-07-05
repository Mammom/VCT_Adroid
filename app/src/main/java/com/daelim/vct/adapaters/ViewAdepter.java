package com.daelim.vct.adapaters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.daelim.vct.R;
import com.daelim.vct.crawling.CrawlingData;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class ViewAdepter extends RecyclerView.Adapter<ViewAdepter.ListItemHolder> {
    private List<CrawlingData> crawlDataList;
    private Context context;


    public ViewAdepter(Context context) {
        this.crawlDataList = new ArrayList<>();
        this.context = context;
    }

    public void addData(List<CrawlingData> dataList){
        crawlDataList.addAll(dataList);
    }

    @NonNull
    @Override
    public ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem, parent, false);
        return new ListItemHolder(view);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ListItemHolder holder, int position) {
        CrawlingData data = crawlDataList.get(position);

        holder.title.setText(data.getTitle());
        holder.article.setText(data.getArticle());
        holder.pressName.setText(data.getPressName());

        Glide.with(context).load(data.getImgURI()).into(holder.image);
        Glide.with(context).load(data.getPressThumb()).into(holder.pressThumb);
    }

    @Override
    public int getItemCount() {
        return crawlDataList.size();
    }


    protected class ListItemHolder extends RecyclerView.ViewHolder {
        ImageView image, pressThumb;
        TextView title, article, pressName;

        public ListItemHolder(@NonNull View view) {
            super(view);
            image = view.findViewById(R.id.imageView);
            title = view.findViewById(R.id.title);
            article = view.findViewById(R.id.article);
            pressName = view.findViewById(R.id.pressName);
            pressThumb = view.findViewById(R.id.pressThumb);
        }
    }
}
