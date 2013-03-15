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

public class FrontPageFragment extends Fragment {
    private TypeModel list;
    private TextView volume;
    private TextView author;
    private Handler handler;
    public FrontPageFragment(TypeModel list){
    	this.list = list;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.front_page, container, false);
        volume = (TextView) view.findViewById(R.id.volume);
        author = (TextView)view.findViewById(R.id.author);
    	volume.setText(list.getVolume());
    	author.setText(list.getAuthor());
        return view;
    }
}
