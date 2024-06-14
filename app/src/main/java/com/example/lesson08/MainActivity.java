package com.example.lesson08;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.lesson08.Api.Api;
import com.example.lesson08.models.Post;
import com.example.lesson08.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private SlideAdapter adapter;
    private List<Slide> slides;
    private Api api;
    public static User currentUser;
    public static List<Post> currentPosts;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewPager);
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        findViewById(R.id.firstSlide).setOnClickListener(v ->{
            viewPager.setCurrentItem(0);
        });
        findViewById(R.id.lastSlide).setOnClickListener(v ->{
            viewPager.setCurrentItem(Objects.requireNonNull(viewPager
                    .getAdapter()).getItemCount());
        });
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sharedPreferences.edit().putInt("position", position).apply();
                Toast.makeText(MainActivity.this,
                        "Slide changed to: " + position,
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });
        displayAllPosts();
    }

    private void displayAllPosts() {
        Call<List<Post>> call = NetworkService.getInstance().getApi().getAllPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post> posts = response.body();
                Random random = new Random();
                assert posts != null;
                slides = new ArrayList<>();
                posts.forEach(post -> {
                    int randomColor = Color.rgb(
                            random.nextInt(256),
                            random.nextInt(256),
                            random.nextInt(256));
                    slides.add(new Slide(post.getTitle(), post.getBody(), randomColor));
                });
                adapter = new SlideAdapter(MainActivity.this, slides);
                viewPager.setAdapter(adapter);
                viewPager.setPageTransformer(new ZoomOutPageTransformer());
                viewPager.setCurrentItem(sharedPreferences.getInt("position", 0));
                currentPosts = posts;
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                // Handle failure
            }
        });
    }

    public static void displayUser(int id) {
        Call<User> call = NetworkService.getInstance().getApi().getUserById(id);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                currentUser = response.body();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // Handle failure
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }
}
