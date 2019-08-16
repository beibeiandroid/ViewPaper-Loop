package com.example.myapplication;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

class ReadFragment extends Fragment {
    private TextView textView;
    private String tag;

    public static ReadFragment  getInstance(String string){
        ReadFragment fragment=new ReadFragment();
        Bundle bundle=new Bundle();
        bundle.putString("tag",string);
        fragment.setArguments(bundle);
        return  fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tag=getArguments().getString("tag");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        textView=new TextView(getContext());
        textView.setTextColor(getResources().getColor(android.R.color.white));
        textView.setGravity(Gravity.CENTER);

        textView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        textView.setText(tag);
        return textView;
    }

    public void refreshState(int position) {
        textView.getLayoutParams().height=500;
        textView.getLayoutParams().width=500;
        textView.setText(position+"");
    }
}
