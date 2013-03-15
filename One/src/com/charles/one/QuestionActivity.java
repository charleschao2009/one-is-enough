package com.charles.one;

import java.util.ArrayList;
import java.util.List;

import com.charles.model.QuestionModel;
import com.charles.model.TypeModel;
import com.charles.util.Common;
import com.charles.util.ContentFragment;
import com.charles.util.MyFragmentPagerAdapter;
import com.charles.util.FrontPageFragment;
import com.charles.util.QuestionFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

public class QuestionActivity extends FragmentActivity implements OnPageChangeListener{
	private ViewPager vPager;
	private List<QuestionModel> list = new ArrayList<QuestionModel>();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.front);

		vPager = (ViewPager) findViewById(R.id.vPager);
		QuestionModel question_item;
		List<String> question_item_content = new ArrayList<String>();

		for (int i = 0; i < 30; i++) {
			question_item_content.add(getResources().getString(R.string.hints));
		}
		for (int i = 0; i < 4; i++) {
			question_item = new QuestionModel();
			question_item.setQuesiton_title(i + "----看足球当职业的人，你们是不是特别幸福");
			question_item.setQuestion_content(question_item_content);
			question_item.setAnswer_content(question_item_content);
			question_item.setAnswer_title(i + "----网友答看足球当职业的人");
			list.add(question_item);
		}
		Common.fav_item= list.get(0).getQuesiton_title();
		vPager.setOnPageChangeListener(this);
		InitFragment(list);

	}

	private void InitFragment(List<QuestionModel> lists) {
		List<Fragment> fragmentsList = new ArrayList<Fragment>();
		for (int pages = 0; pages < lists.size(); pages++) {
			QuestionFragment fragment = new QuestionFragment(lists.get(pages),
					QuestionActivity.this);
			fragmentsList.add(fragment);
		}
		MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(
				getSupportFragmentManager(), fragmentsList);
		adapter.notifyDataSetChanged();
		vPager.setAdapter(adapter);
		vPager.setCurrentItem(0);
	}

	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	public void onPageSelected(int location) {
		Common.fav_item= list.get(location).getQuesiton_title();
	}
}
