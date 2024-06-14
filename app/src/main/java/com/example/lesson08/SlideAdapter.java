package com.example.lesson08;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class SlideAdapter extends FragmentStateAdapter {
    private List<Slide> slides;
    public SlideAdapter(@NonNull FragmentActivity fragmentActivity, List<Slide> slides) {
        super(fragmentActivity);
        this.slides = slides;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int resource = position % 2 == 0
                ? R.layout.fragment_slide_top
                : R.layout.fragment_slide_bottom;
        return new SlideFragment(resource, slides.get(position));
    }

    @Override
    public int getItemCount() {
        return slides.size();
    }
}
