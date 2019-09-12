package com.example.pruebaapi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PostService {
    String API_ROUTE = "/posts";

    @GET(API_ROUTE)
    Call<List<Post>> listarPost();

    @GET(API_ROUTE)
    Call<Post> getPostById(@Query("id") int idPost);

    @GET(API_ROUTE+"/{id}")
    Call<Post> getPostByIdPath(@Path("id") int idPost);
}
