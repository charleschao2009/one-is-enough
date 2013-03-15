package com.charles.one;

import java.util.ArrayList;
import java.util.List;

import com.charles.model.TypeModel;
import com.charles.util.Common;
import com.charles.util.ContentFragment;
import com.charles.util.MyFragmentPagerAdapter;
import com.charles.util.FrontPageFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

public class ContentActivity extends FragmentActivity implements OnPageChangeListener{
	private ViewPager vPager;
	private List<TypeModel> list = new ArrayList<TypeModel>();
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.front);
		vPager = (ViewPager)findViewById(R.id.vPager);
		TypeModel model;
		for(int i=0;i<10;i++){
			model = new TypeModel("张丹丹"+i,"赵超"+i);
			list.add(model);
		}
		InitFragment(list);
		Common.fav_item= list.get(0).getAuthor();
		vPager.setOnPageChangeListener(this);
	}
	private void InitFragment(List<TypeModel> lists) {
		List<Fragment> fragmentsList = new ArrayList<Fragment>();
		for (int pages = 0; pages < lists.size(); pages++) {
			ContentFragment fragment = new ContentFragment(lists.get(pages));
			fragmentsList.add(fragment);
		}
		MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(
				getSupportFragmentManager(), fragmentsList);
		adapter.notifyDataSetChanged();
		vPager.setAdapter(adapter);
		vPager.setCurrentItem(0);
	}
	public void onPageScrollStateChanged(int arg0) {
		
	}
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		
	}
	public void onPageSelected(int location) {
		Common.fav_item= list.get(location).getAuthor();
	}
}
