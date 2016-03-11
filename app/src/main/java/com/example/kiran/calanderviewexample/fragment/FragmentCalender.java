package com.example.kiran.calanderviewexample.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kiran.calanderviewexample.OnSwipeTouchListener;
import com.example.kiran.calanderviewexample.R;

import org.w3c.dom.Text;

/**
 * Created by Kiran on 11-03-2016.
 */
public class FragmentCalender extends Fragment {
    TextView textView;
     Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.custom_calender,container,false);
        textView=(TextView)view.findViewById(R.id.tv_current_month);
        context = getActivity().getApplicationContext();
        Toast.makeText(getActivity().getApplicationContext(),""+textView.getText(),Toast.LENGTH_SHORT).show();
        textView.setOnTouchListener(swipListener);
        textView.setOnClickListener(clickListener);
        return view;

    }
    View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(context,"slkdjlsdfjlasdkjf",Toast.LENGTH_SHORT).show();
        }
    };
OnSwipeTouchListener swipListener=new OnSwipeTouchListener(context){
    @Override
    public void onSwipeLeft() {
        super.onSwipeLeft();
        Toast.makeText(context,"swipe Left",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSwipeRight() {
        super.onSwipeRight();
        Toast.makeText(context,"swipe Right",Toast.LENGTH_SHORT).show();
    }
};
}
