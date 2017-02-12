package com.dharmesh.task7;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView ls;
    private Post_adapter ad;
    private List<Post> list_items = new ArrayList<Post>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        jsonAdapter();
    }
    public void jsonAdapter()
    {
        ArrayList<Post> postArrayList = new ArrayList<Post>();
        ls = (ListView)findViewById(R.id.ls_items);
        ad = new Post_adapter(this,R.layout.listed_items,postArrayList);
        ls.setAdapter(ad);

        try {

            JSONArray rootArray = new JSONArray();

            for (int i = 0; i < rootArray.length() ; i++) {
                JSONObject postObject = rootArray.getJSONObject(i);
                int id =postObject.getInt("id");
                int userid =postObject.getInt("userId");
                String title =postObject.getString("title");
                String description =postObject.getString("body");
                Post p = new Post();
                p.setUserid(userid);
                p.setId(id);
                p.setTitle(title);
                p.setDescription(description);
                postArrayList.add(p);



            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

    }
}
