package subway.subwayalarm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.serchinfobysubwaynameservice.LineNumGetter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import subway.subwayalarm.adapter.TabPagerAdapter;
import subway.subwayalarm.R;


public class MainActivity extends AppCompatActivity {

    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;
    private TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = new Intent(this, LockScreenActivity/*TestActivity*/.class);
        startActivity(i);
        setTabLayout();
    }

    private void setTabLayout() {
        TabPagerAdapter pagerAdapter;
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.pager);

        tabLayout.addTab(tabLayout.newTab().setText("검색").setIcon(R.drawable.search_toolbar));
        tabLayout.addTab(tabLayout.newTab().setText("도착알람").setIcon(R.drawable.alarm_toolbar));
        tabLayout.addTab(tabLayout.newTab().setText("환경설정").setIcon(R.drawable.setting_toolbar));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (intervalTime >= 0 && FINISH_INTERVAL_TIME >= intervalTime) {
            super.onBackPressed();
        } else {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "한번 더 뒤로가기를 누르면 종료됩니다", Toast.LENGTH_SHORT).show();
        }
    }
}