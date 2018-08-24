package com.example.tanmay.soundrecorder.Actvities;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.astuetz.PagerSlidingTabStrip;
import com.example.tanmay.soundrecorder.Fragments.RecordFragment;
import com.example.tanmay.soundrecorder.Main2Activity;
import com.example.tanmay.soundrecorder.R;

public class MainActivity extends AppCompatActivity {

    private PagerSlidingTabStrip tabStrip;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize View variables
        tabStrip = findViewById(R.id.fragments_tabs_strip);
        viewPager = (ViewPager) findViewById(R.id.fragments_view_pager);

        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());

        startActivity(new Intent(MainActivity.this, Main2Activity.class));

        // Set FragmentPagerAdapter on the ViewPager
      /*  viewPager.setAdapter(adapter);*/
        // Set the ViewPager as the target for the PagerSlidingTabStrip
//        tabStrip.setViewPager(viewPager);

    }

    private class FragmentAdapter extends FragmentPagerAdapter{

        // An array containing the list of titles
        private String[] titles = getResources().getStringArray(R.array.fragment_titles);

        // Constructor matching super
        private FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        // Returns an instance of the required fragment
        @Override
        public Fragment getItem(int position) {

            switch (position){
                case 0:
                    return RecordFragment.newInstance();
                case 1:
                    //TODO Return a new File Viewing Fragment
            }

            return null;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public int getCount() {
            return titles.length;
        }
    }

}
