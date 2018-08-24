package com.example.tanmay.soundrecorder;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private class FragmentAdapter extends FragmentPagerAdapter{

        // An array containing the list of titles
        private String[] titles = getResources().getStringArray(R.array.fragment_titles);

        // Constructor matching super
        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        // Returns an instance of the required fragment
        @Override
        public Fragment getItem(int position) {

            switch (position){
                case 0:
                    //TODO Return a new Recording Fragment
                    break;
                case 1:
                    //TODO Return a new File Viewing Fragment
                    break;
            }

            return null;
        }

        @Override
        public int getCount() {
            return titles.length;
        }
    }

}
