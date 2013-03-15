package com.charles.one;

import java.util.ArrayList;
import java.util.List;

import com.charles.model.QuestionModel;

import android.os.AsyncTask;
import android.support.v4.view.ViewPager;

public class AsyncLoadInfo extends AsyncTask<String,Void,Integer>{

	private List<QuestionModel> list = new ArrayList<QuestionModel>();
	private ViewPager vPager;
	public AsyncLoadInfo(ViewPager vPager){
		this.vPager = vPager;
	}
	
	@Override
	protected Integer doInBackground(String... params) {
		QuestionModel question_item;
		List<String> question_item_content = new ArrayList<String>();
		for (int i = 0; i < 30; i++) {
			question_item_content.add(params[0]);
		}
		for (int i = 0; i < 4; i++) {
			question_item = new QuestionModel();
			question_item.setQuesiton_title(i + "----看足球当职业的人，你们是不是特别幸福");
			question_item.setQuestion_content(question_item_content);
			question_item.setAnswer_content(question_item_content);
			question_item.setAnswer_title(i + "----网友答看足球当职业的人");
			list.add(question_item);
		}
		return null;
	}
	
}
