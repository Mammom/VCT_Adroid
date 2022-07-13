package com.daelim.vct;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import java.util.Random;

public class TitleActivity extends AppCompatActivity {

    ImageView imageView;

    int[] img = {R.drawable.title_imgvct1,R.drawable.title_imgvct2,R.drawable.title_imgvct3,R.drawable.title_imgvct4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        imageView = (ImageView)findViewById(R.id.TitleImageView);

        Random ram = new Random();
        int num = ram.nextInt(img.length);

        imageView.setBackgroundResource(img[num]);

        moveMain(1);
    }

    private void moveMain(int sec) {
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent intent = new Intent(getApplicationContext(), LoginpageActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000 * sec); // sec초 정도 딜레이를 준 후 시작
    }
}