package com.sub;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class api extends AppCompatActivity {

    String sourceText;
    TextView outputTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public String[] getSynonyms(){
        TextView sourceTextView = (TextView) findViewById(R.id.translateText);
        sourceText = sourceTextView.getText().toString();
        System.out.print("Megha Sai");
        String getURL = "https://dictionary.yandex.net/api/v1/dicservice.json/lookup?key=dict.1.1.20160204T023120Z.5ae2c9929c9b8d68.1b32f9aabf3e2f9645c75d8883e77d42de7a2f1a&text=" + sourceText +"&" + "lang=en-en";
        final String response1 = "";
        OkHttpClient client = new OkHttpClient(); try {

            Request request = new Request.Builder()
                    .url(getURL)
                    .build();

            client.newCall(request).enqueue(new Callback() {

                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.println(e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final JSONObject jsonResult;
                    final String result = response.body().string();

                    try {
                        jsonResult = new JSONObject(result);
                        JSONArray convertedTextArray = jsonResult.getJSONArray("text");
                        System.out.print(convertedTextArray+"this is the cshgfdhgad");
                        final String convertedText = convertedTextArray.get(0).toString();
                        Log.d("okHttp", jsonResult.toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });


        } catch (Exception ex) {
            outputTextView.setText(ex.getMessage());

        }



        return null;
    }


}
