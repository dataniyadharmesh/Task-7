package com.dharmesh.task7;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;
import java.lang.StringBuffer;
import java.lang.StringBuilder;

public class MainActivity extends AppCompatActivity {
    private ListView ls;
    private Post_adapter ad;
    private List<Post> list_items = new ArrayList<Post>();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        jsonAdapter();

    }

   public void jsonAdapter() {
    ArrayList<Post> postArrayList = new ArrayList<Post>();
        ls = (ListView) findViewById(R.id.ls_items);
        ad = new Post_adapter(this, R.layout.listed_items, postArrayList);
        ls.setAdapter(ad);
        HttpURLConnection connection;
        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/posts");
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer bf = new StringBuffer();
                String line = "";

                while ((reader.readLine() != null)) {
                    line = reader.readLine();
                    bf.append(line);
                }

                String jsonData = bf.toString();


                try {

                    JSONArray rootArray = new JSONArray(jsonData);

                    for (int i = 0; i < rootArray.length(); i++) {
                        JSONObject postObject = rootArray.getJSONObject(i);
                        int id = postObject.getInt("id");
                        int userid = postObject.getInt("userId");
                        String title = postObject.getString("title");
                        String description = postObject.getString("body");
                        Post p = new Post();
                        p.setUserid(userid);
                        p.setId(id);
                        p.setTitle(title);
                        p.setDescription(description);
                        postArrayList.add(p);


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }


}