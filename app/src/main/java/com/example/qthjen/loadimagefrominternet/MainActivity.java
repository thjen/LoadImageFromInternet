package com.example.qthjen.loadimagefrominternet;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button bt;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt = (Button) findViewById(R.id.bt);
        iv = (ImageView) findViewById(R.id.iv);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AsyncTaskLoadImage().execute("https://s-media-cache-ak0.pinimg.com/736x/6a/98/d1/6a98d1d4bf37df099b6c74fdf5ee338f--iphone-wallpaper-dark-colors-galaxies-wallpaper.jpg");
            }
        });
    }

    private class AsyncTaskLoadImage extends AsyncTask<String, Void, Bitmap> {

        Bitmap image = null;

        @Override
        protected Bitmap doInBackground(String... strings) {

            try {
                URL url = new URL(strings[0]);
                /** lấy dữ liệu từ đường dẫn string **/
                InputStream inputStream = url.openConnection().getInputStream();
                /** chuyển dữ liệu sang bitmap **/
                image = BitmapFactory.decodeStream(inputStream);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return image;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            iv.setImageBitmap(image);
        }

    }



}
