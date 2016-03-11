package com.example.kiran.calanderviewexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.text.DateFormatSymbols;
public class MainActivity extends AppCompatActivity {
    CalendarView calendarView;
    TextView tvdatePrint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendarView=(CalendarView)findViewById(R.id.cal_date);
        tvdatePrint=(TextView)findViewById(R.id.tv_print_selected_date);
        Calendar c=Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMMMMMM-yyyy");
        String formattedDate = df.format(c.getTime());
        tvdatePrint.setText(formattedDate.toString());
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                String date=""+String.valueOf(year)+"-"+getMonthForInt(month)+"-"+String.valueOf(dayOfMonth);
                tvdatePrint.setText(date);
            }
        });
    }
    public String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11 ) {
            month = months[num];
        }
        return month.substring(0,3);
    }
}
