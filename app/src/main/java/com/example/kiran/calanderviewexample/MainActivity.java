package com.example.kiran.calanderviewexample;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.kiran.calanderviewexample.fragment.FragmentCalender;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentCalling();
    }
    private void fragmentCalling() {
        FragmentManager fManger=getSupportFragmentManager();
        FragmentTransaction fTransaction=fManger.beginTransaction();
        fTransaction.replace(R.id.fragment_custom_calender,new FragmentCalender());
        fTransaction.commit();
    }

}
