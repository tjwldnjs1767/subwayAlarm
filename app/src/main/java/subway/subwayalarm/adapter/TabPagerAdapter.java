package subway.subwayalarm.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import subway.subwayalarm.activity.fragment.AlarmListFragment;
import subway.subwayalarm.activity.fragment.SearchFragment;
import subway.subwayalarm.activity.fragment.SettingsFragment;


public class TabPagerAdapter extends FragmentStatePagerAdapter {
    private int tabCount;

    public TabPagerAdapter(FragmentManager fragmentManager, int tabCount) {
        super(fragmentManager);
        this.tabCount = tabCount;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                SearchFragment searchFragment = new SearchFragment();
                return searchFragment;

            case 1:
                AlarmListFragment alarmFragment = new AlarmListFragment();
                return alarmFragment;

            case 2:
                SettingsFragment settingsFragment = new SettingsFragment();
                return settingsFragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}