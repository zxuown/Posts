package com.example.lesson08;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lesson08.models.Post;

public class SlideFragment extends Fragment {
    private TextView tvHeader;
    private TextView tvParagraph;
    private Slide slide;

    public SlideFragment(int resource, Slide slide) {
        super(resource);
        this.slide = slide;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvHeader = view.findViewById(R.id.tvHeader);
        tvParagraph = view.findViewById(R.id.tvParagraph);

        tvHeader.setText(slide.getHeader());
        tvParagraph.setText(slide.getParagraph());
        view.setBackgroundColor(slide.getColor());
        for (Post currentPost : MainActivity.currentPosts) {
            if (currentPost.getTitle().equals(slide.getHeader())) {
                MainActivity.displayUser(currentPost.getUserId());
                break;
            }
        }
        tvHeader.setOnClickListener(v -> {
            Toast.makeText(getContext(), slide.getHeader(), Toast.LENGTH_SHORT).show();
            UserDialog userDialog = new UserDialog();
            userDialog.show(getParentFragmentManager(), "contact");
        });
    }
}
