package com.charles.util;

import java.util.ArrayList;
import java.util.List;

import com.charles.model.TypeModel;
import com.charles.one.R;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ContentFragment extends Fragment {
    private TypeModel list;
    private TextView volume;
    private TextView author;
    public ContentFragment(TypeModel list){
    	this.list = list;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content, container, false);
        volume = (TextView) view.findViewById(R.id.content_time);
        author = (TextView)view.findViewById(R.id.contentTitle);
    	volume.setText(list.getVolume());
    	author.setText(list.getAuthor());
    	
        return view;
    }
}
