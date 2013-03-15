package com.charles.util;
import com.charles.model.QuestionModel;
import com.charles.one.R;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class QuestionFragment extends Fragment {
    private QuestionModel list;
    private TextView textview;
    private TextView question_title;
    private TextView answer_title;
    private LinearLayout question_list;
    private LinearLayout answer_list;
    private Handler handler;
    private Context context;
    public QuestionFragment(QuestionModel list,Context context){
    	this.list = list;
    	this.context = context;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.question, container, false);
        question_list = (LinearLayout)view.findViewById(R.id.question_item);
        question_title = (TextView)view.findViewById(R.id.question_title);
        answer_list= (LinearLayout)view.findViewById(R.id.answer_item);
        question_title.setText(list.getQuesiton_title());
        answer_title = (TextView)view.findViewById(R.id.answer_title);
        answer_title.setText(list.getAnswer_title());
        for(int i=0;i<list.getQuestion_content().size();i++){
        	textview = new TextView(context);
        	textview.setText(list.getQuestion_content().get(i));
        	question_list.addView(textview);
        }
        for(int j=0;j<list.getAnswer_content().size();j++){
        	textview = new TextView(context);
        	textview.setText(list.getAnswer_content().get(j));
        	answer_list.addView(textview);
        }
        return view;
    }
}
