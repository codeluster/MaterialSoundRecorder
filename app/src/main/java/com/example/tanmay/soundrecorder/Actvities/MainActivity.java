package com.example.tanmay.soundrecorder.Actvities;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tanmay.soundrecorder.Fragments.PreviousRecordingsFragment;
import com.example.tanmay.soundrecorder.Fragments.RecordFragment;
import com.example.tanmay.soundrecorder.R;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize View variables
        viewPager = (ViewPager) findViewById(R.id.fragments_view_pager);

        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set FragmentPagerAdapter on the ViewPager
        viewPager.setAdapter(adapter);
    }

    private class SectionsPagerAdapter extends FragmentPagerAdapter{

        // An array containing the list of titles
        private String[] titles = getResources().getStringArray(R.array.fragment_titles);

        // Constructor matching super
        private SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        // Returns an instance of the required fragment
        @Override
        public Fragment getItem(int position) {

            switch (position){
                case 0:
                    return new RecordFragment();
                case 1:
                    return new PreviousRecordingsFragment();
            }

            return null;
        }

/*        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }*/

        @Override
        public int getCount() {
            return titles.length;
        }
    }

}
