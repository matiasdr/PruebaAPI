package com.example.pruebaapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetalleActivity extends AppCompatActivity {
    private TextView txtidpost;
    private TextView txtiduser;
    private ImageView imageViewFoto;
    String RUTAIMAGEN="http://lorempixel.com/400/400/sports/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        Bundle extras = getIntent().getExtras();
        int postId = extras.getInt("KEY_ID");
        txtidpost = findViewById(R.id.textViewIdPost);
        txtiduser = findViewById(R.id.textViewUserID);
        imageViewFoto = findViewById(R.id.imageViewFoto);

        cargarDatos(postId);
    }

    private void cargarDatos(int postId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PostService postService = retrofit.create(PostService.class);
        Call<Post> call = postService.getPostByIdPath(postId);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Post post = response.body() ;

                txtidpost.setText(Integer.toString(post.getId()));
                txtiduser.setText(Integer.toString(post.getUserId()));
                Picasso.get().load(RUTAIMAGEN+post.getId()).into(imageViewFoto);

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }
}
