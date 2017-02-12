package com.dharmesh.task7;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by DHARMESH on 09-02-2017.
 */

public class Post_adapter extends ArrayAdapter<Post>{


    private Context context;
    int layResource;
    private ArrayList<Post> posts;
    private LayoutInflater inflater;
    private View view;

    public Post_adapter(Context context, int resource, ArrayList<Post> objects)
    {
        super(context, resource, objects);
        this.context=context;
        this.posts=objects;
        this.layResource = resource;

        inflater=LayoutInflater.from(context);

    }


    static class ViewHolder
    {
        TextView userId, id, title, description;
    }

    public View getView(int position, View contextView, ViewGroup viewGroup){
        ViewHolder holder;

        if(contextView == null){

            holder = new ViewHolder();
            contextView = LayoutInflater.from(context).inflate(layResource, viewGroup, false);
            holder.userId = (TextView)contextView.findViewById(R.id.tv_userid);
            holder.id = (TextView)contextView.findViewById(R.id.tv_id);
            holder.title = (TextView)contextView.findViewById(R.id.tv_title);
            holder.description = (TextView)contextView.findViewById(R.id.tv_body);
            contextView.setTag(holder);

        }
        else {
            holder = (ViewHolder)contextView.getTag();
        }

        Post p = posts.get(position);
        holder.userId.setText(String.valueOf(p.getUserid()));
        holder.id.setText(String.valueOf(p.getId()));
        holder.title.setText(p.getTitle());
        holder.description.setText(p.getDescription());

        return contextView;
    }

}
