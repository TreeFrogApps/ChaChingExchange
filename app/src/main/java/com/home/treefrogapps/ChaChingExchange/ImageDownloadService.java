package com.home.treefrogapps.ChaChingExchange;


import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageDownloadService extends IntentService {

    public static final String IMAGE_DOWNLOAD_COMPLETE = "IMAGE_DOWNLOAD_COMPLETE";

    public ImageDownloadService() {
        super(ImageDownloadService.class.getName());
    }

    public ImageDownloadService(String name) {
        super(name);
    }


    @Override
    protected void onHandleIntent(Intent intent) {

        Log.e("SERVICE", "STARTED");

        String passedURL = intent.getStringExtra("url");
        String timeScale = intent.getStringExtra("timescale");
        downloadImage(passedURL, timeScale);

        Log.e("SERVICE", "STOPPED");

        Intent broadcastIntent = new Intent(IMAGE_DOWNLOAD_COMPLETE);
        ImageDownloadService.this.sendBroadcast(broadcastIntent);




    }

    private void downloadImage(String passedURL, String timeScale) {

        String graphImage = "downloaded_graph_single.png";

        try {

            FileOutputStream fileOutputStream = new FileOutputStream(new File(this.getFilesDir(), graphImage));

            OkHttpClient httpClient = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(HttpUrl.parse(passedURL))
                    .build();

            Response response = httpClient.newCall(request).execute();

            InputStream inputStream = response.body().byteStream();

            byte[] buffer = new byte[1024];
            int bufferLength = 0;

            while ((bufferLength = inputStream.read(buffer)) > 0){
                fileOutputStream.write(buffer, 0, bufferLength);
            }

            inputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
