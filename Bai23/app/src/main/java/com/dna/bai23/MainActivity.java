package com.dna.bai23;


import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static ListView lv1;
    List<News> mylist;
    Adapter myadapter;
    String URL= "https://vnexpress.net/rss/tin-moi-nhat.rss";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv1= findViewById(R.id.lvNews);
        mylist = new ArrayList<>();
        myadapter = new
                Adapter(MainActivity.this,mylist);
        lv1.setAdapter(myadapter);
        LoadExampleTask task = new LoadExampleTask();
        task.execute();
    }
    class LoadExampleTask extends AsyncTask<Void, Void, ArrayList<News>> {
        @Override
        protected ArrayList<News> doInBackground(Void... voids) {
            return new XMLParser().parseXML(URL);
        }

        private String extractImageUrl(String description) {
            try {
                return description.substring(description.indexOf("src=\"") + 5, description.indexOf("\"", description.indexOf("src=\"") + 5));
            } catch (Exception e) {
                return ""; // Fallback or default image URL
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            myadapter.clear();
        }

        @Override
        protected void onPostExecute(ArrayList<News> result) {
            super.onPostExecute(result);
            myadapter.addAll(result);
        }

    }
}