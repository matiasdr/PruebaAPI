package com.example.pruebaapi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class PostAdapter extends BaseAdapter {
    private List<Post> listPost;

    public PostAdapter(List<Post> listPost) {
        this.listPost = listPost;
    }

    @Override
    public int getCount() {
        return listPost.size();
    }

    @Override
    public Post getItem(int position) {
        return listPost.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listPost.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if(convertView==null){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post,null);
        }else{
            view = convertView;
        }

        Post post = this.getItem(position);

        TextView txtTitle = view.findViewById(R.id.txtTitle);
        TextView txtBody = view.findViewById(R.id.txtBody);

        txtTitle.setText(post.getTitle());
        txtBody.setText(post.getBody());


        return view;
    }
}
