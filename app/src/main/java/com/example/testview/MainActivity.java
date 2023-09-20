package com.example.testview;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.animation.AnimationUtils;
import android.widget.Scroller;

import com.example.testview.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewPager2 viewPager;

    TabLayout tabLayout;

    List<PageModel> pageModels = new ArrayList<>();

    {
        pageModels.add(new PageModel(R.layout.sample_color, R.string.title_draw_color,
                R.layout.practice_color));
        pageModels.add(new PageModel(R.layout.sample_circle, R.string.title_draw_circle,
                R.layout.practice_circle));
        pageModels.add(new PageModel(R.layout.sample_rect, R.string.title_draw_rect,
                R.layout.practice_rect));
        pageModels.add(new PageModel(R.layout.sample_point, R.string.title_draw_point,
                R.layout.practice_point));
        pageModels.add(new PageModel(R.layout.sample_oval, R.string.title_draw_oval,
                R.layout.practice_oval));
        pageModels.add(new PageModel(R.layout.sample_line, R.string.title_draw_line,
                R.layout.practice_line));
        pageModels.add(new PageModel(R.layout.sample_round_rect, R.string.title_draw_round_rect,
                R.layout.practice_round_rect));
        pageModels.add(new PageModel(R.layout.sample_arc, R.string.title_draw_arc,
                R.layout.practice_arc));
        pageModels.add(new PageModel(R.layout.sample_path, R.string.title_draw_path,
                R.layout.practice_path));
        pageModels.add(new PageModel(R.layout.sample_histogram, R.string.title_draw_histogram,
                R.layout.practice_histogram));
        pageModels.add(new PageModel(R.layout.sample_pie_chart, R.string.title_draw_pie_chart,
                R.layout.practice_pie_chart));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewPager = (ViewPager2) findViewById(R.id.view_pager);

        viewPager.setAdapter(new FragmentStateAdapter(getSupportFragmentManager(),getLifecycle()) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                PageModel pageModel = pageModels.get(position);
                return PageFragment.newInstance(pageModel.sampleLayoutSrc,pageModel.practiceLayoutSrc);
            }

            @Override
            public int getItemCount() {
                return pageModels.size();
            }

        });

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout,viewPager,(tab, position) -> {
            PageModel pageModel = pageModels.get(position);
            tab.setText(getString(pageModel.titleSrc));
        }).attach();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    public static class PageModel{
        @LayoutRes int sampleLayoutSrc;
        @StringRes int titleSrc;
        @LayoutRes int practiceLayoutSrc;

        public PageModel(@LayoutRes int sampleLayoutSrc,@StringRes int titleSrc,@LayoutRes int practiceLayoutSrc) {

            this.sampleLayoutSrc = sampleLayoutSrc;

            this.titleSrc = titleSrc;

            this.practiceLayoutSrc = practiceLayoutSrc;
        }
    }
}