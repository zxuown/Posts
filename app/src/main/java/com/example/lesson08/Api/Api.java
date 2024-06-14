package com.example.lesson08.Api;

import com.example.lesson08.models.Post;
import com.example.lesson08.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {
    @GET("/posts")
    Call<List<Post>> getAllPosts();

    @GET("/users/{id}")
    Call<User> getUserById(@Path("id") int id);


}
