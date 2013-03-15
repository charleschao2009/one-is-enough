package com.charles.util;
import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
	public List<Fragment> fragments = new ArrayList<Fragment>();
	public FragmentManager fm;

	public MyFragmentPagerAdapter(FragmentManager fm,
			List<Fragment> fragments) {
		super(fm);
		this.fragments = fragments;
	}

	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}

	@Override
	public Fragment getItem(int arg0) {
		  return fragments.get(arg0);
		}

	@Override
	public int getCount() {
		return fragments.size();
	}
}