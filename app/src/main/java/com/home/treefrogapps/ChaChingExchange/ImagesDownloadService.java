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
import java.util.ArrayList;

public class ImagesDownloadService extends IntentService {

    public static final String IMAGES_DOWNLOAD_COMPLETE = "IMAGES_DOWNLOAD_COMPLETE";

    public ImagesDownloadService() {
        super(ImagesDownloadService.class.getName());
    }

    public ImagesDownloadService(String name) {
        super(name);
    }


    @Override
    protected void onHandleIntent(Intent intent) {

        Log.e("SERVICE", "STARTED");

        ArrayList<String> myURLS = intent.getStringArrayListExtra("urls");

        downloadImages(myURLS);

        Log.e("SERVICE", "STOPPED");

        Intent broadcastIntent = new Intent(IMAGES_DOWNLOAD_COMPLETE);
        ImagesDownloadService.this.sendBroadcast(broadcastIntent);




    }

    private void downloadImages(ArrayList<String> myURLS) {

        for (int i = 0; i < myURLS.size(); i++){

            String graphImage = "downloaded_graph_0" + String.valueOf(i) + ".png";

            try {

                FileOutputStream fileOutputStream = new FileOutputStream(new File(this.getFilesDir(), graphImage));

                OkHttpClient httpClient = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(HttpUrl.parse(myURLS.get(i)))
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
}
