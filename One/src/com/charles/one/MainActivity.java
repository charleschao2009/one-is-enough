package com.charles.one;

import com.charles.model.Tip;
import com.charles.util.Common;
import android.app.ActivityGroup;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class MainActivity extends ActivityGroup implements
		OnCheckedChangeListener {
	private LocalActivityManager mActivityManager = null;
	private LinearLayout change_content;
	private RadioGroup mainTab;

	private ImageView btn_fav;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_fav = (ImageView) findViewById(R.id.btn_fav);
		mActivityManager = getLocalActivityManager();
		change_content = (LinearLayout) findViewById(R.id.change_content);
		mainTab = (RadioGroup) findViewById(R.id.main_tab);
		
		mainTab.setOnCheckedChangeListener(this);
		change_content.addView(
				mActivityManager.startActivity("",
						new Intent(this, FrontPageActivity.class))
						.getDecorView(), LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.FILL_PARENT);
		
		btn_fav.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				new Tip(MainActivity.this,Common.fav_item).show();
//				Intent intent = new Intent();
//				intent.setClass(MainActivity.this, Share.class);
//				startActivity(intent); // 目标应用选择对话框的标题
			}
		});
	}

	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.btn_content:
			btn_fav.setVisibility(View.VISIBLE);
			change_content.removeAllViews();
			change_content.addView(
					mActivityManager.startActivity("",
							new Intent(this, ContentActivity.class))
							.getDecorView(),
					LinearLayout.LayoutParams.FILL_PARENT,
					LinearLayout.LayoutParams.FILL_PARENT);
			break;
		case R.id.btn_front_page:
			btn_fav.setVisibility(View.VISIBLE);
			change_content.removeAllViews();
			change_content.addView(
					mActivityManager.startActivity("",
							new Intent(this, FrontPageActivity.class))
							.getDecorView(),
					LinearLayout.LayoutParams.FILL_PARENT,
					LinearLayout.LayoutParams.FILL_PARENT);
			break;
		case R.id.btn_question:
			btn_fav.setVisibility(View.VISIBLE);
			change_content.removeAllViews();
			change_content.addView(
					mActivityManager.startActivity("",
							new Intent(this, QuestionActivity.class))
							.getDecorView(),
					LinearLayout.LayoutParams.FILL_PARENT,
					LinearLayout.LayoutParams.FILL_PARENT);
			break;
		case R.id.btn_fav:

			btn_fav.setVisibility(View.GONE);
			change_content.removeAllViews();
			change_content
					.addView(
							mActivityManager.startActivity("",
									new Intent(this, FavActivity.class))
									.getDecorView(),
							LinearLayout.LayoutParams.FILL_PARENT,
							LinearLayout.LayoutParams.FILL_PARENT);
			break;
		case R.id.btn_more:

			btn_fav.setVisibility(View.GONE);
			change_content.removeAllViews();
			change_content
					.addView(
							mActivityManager.startActivity("",
									new Intent(this, MoreActivity.class))
									.getDecorView(),
							LinearLayout.LayoutParams.FILL_PARENT,
							LinearLayout.LayoutParams.FILL_PARENT);
			break;
		}
		
		
	}

}
