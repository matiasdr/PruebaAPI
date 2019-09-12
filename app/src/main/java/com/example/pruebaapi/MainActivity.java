package com.example.pruebaapi;


import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private List<Post> postList;
    private PostAdapter adapter;
    private ListView listView;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        postList = new ArrayList<Post>();

        // obtener los datos
        getPosts();

        adapter = new PostAdapter(postList);

        listView = findViewById(R.id.listViewPost);

        listView.setOnItemClickListener(this);

        listView.setAdapter(adapter);




    }

    private void getPosts(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PostService postService = retrofit.create(PostService.class);
        Call<List<Post>> call = postService.listarPost();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                for(Post post : response.body()) {
                    postList.add(post);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });


    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Post post = postList.get(position);

        // String mensaje = "El id del Usuario es :" + post.getUserId() +" y del Post es: " + post.getId();
        Toast.makeText(this, "HOLAAAAAAAAAA", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, DetalleActivity.class);
        intent.putExtra("KEY_ID", post.getId());
        startActivity(intent);
    }
}
