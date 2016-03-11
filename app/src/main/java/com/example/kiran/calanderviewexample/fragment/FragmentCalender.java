package com.example.kiran.calanderviewexample.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kiran.calanderviewexample.OnSwipeTouchListener;
import com.example.kiran.calanderviewexample.R;

import org.w3c.dom.Text;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Created by Kiran on 11-03-2016.
 */
public class FragmentCalender extends Fragment {
    TextView textView;
     Context context;
    private Calendar calendar;
    private int month, year;
    private GridCellAdapter adapter;
    private GridView calendarView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.custom_calender,container,false);
        context = getActivity().getApplicationContext();
        generateCalenderMonthYear();
        textView=(TextView)view.findViewById(R.id.tv_current_month);
        calendarView=(GridView)view.findViewById(R.id.calendar_grid);
        adapter=new GridCellAdapter(context,month,year);
        calendarView.setAdapter(adapter);
       calendarView.setOnTouchListener(swipListener);
        String MonthName=new DateFormatSymbols().getMonths()[month];
        textView.setText(MonthName+" "+String.valueOf(year));
//        textView.setOnTouchListener(swipListener);
        return view;

    }

    private void generateCalenderMonthYear() {
        calendar = Calendar.getInstance(Locale.getDefault());
        month = calendar.get(Calendar.MONTH) ;
        year = calendar.get(Calendar.YEAR);
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
        if(month<=0)
        {
            month=11;
            year--;
        }
        else
        {
            month--;
        }
        setGridCellAdapterToDate(month,year);

    }

    @Override
    public void onSwipeRight() {
        super.onSwipeRight();
        Toast.makeText(context,"swipe Right",Toast.LENGTH_SHORT).show();

        if(month>=11)
        {
            month=0;
            year++;
        }
        else
        {
            month++;
        }
        setGridCellAdapterToDate(month,year);
    }
};
    private void setGridCellAdapterToDate(int month, int year) {
        adapter=new GridCellAdapter(context, month,year);
//        calendar.set(year,month-1,calendar.get(Calendar.DAY_OF_MONTH));
        final String MONTHNAME = new DateFormatSymbols().getMonths()[month];
        textView.setText(MONTHNAME+" "+String.valueOf(year));
//        currentMonth.setText(DateFormat.format(dateTemplate,calendar.get(Calendar.DAY_OF_MONTH)));
        calendarView.setAdapter(adapter);


    }

    public class GridCellAdapter extends BaseAdapter {
        private static final String tag = "GridCellAdapter";
        private final Context _context;
        private final List<String> list;
        private static final int DAY_OFFSET = 1;
        private final String[] weekdays = new String[] { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
        private final String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
        private final int[] daysOfMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        private int daysInMonth;
        private int currentDayOfMonth;
        private int currentWeekDay;
        private TextView gridcell;
        // --Commented out by Inspection (11-03-2016 09:53):private TextView num_events_per_day;
        // --Commented out by Inspection (11-03-2016 09:53):private final HashMap<String, Integer> eventsPerMonthMap;
        private final SimpleDateFormat dateFormatter = new SimpleDateFormat( "dd-MMM-yyyy");
        // Days in Current Month
        public GridCellAdapter(Context context, int month, int year)
        { super();
            this._context = context;
            this.list = new ArrayList<String>();
            Log.d(tag, "==> Passed in Date FOR Month: " + month + " " + "Year: " + year);
            Calendar calendar = Calendar.getInstance();
            setCurrentDayOfMonth(calendar.get(Calendar.DAY_OF_MONTH));
            setCurrentWeekDay(calendar.get(Calendar.DAY_OF_WEEK));
            Log.d(tag, "New Calendar:= " + calendar.getTime().toString());
            Log.d(tag, "CurrentDayOfWeek :" + getCurrentWeekDay());
            Log.d(tag, "CurrentDayOfMonth :" + getCurrentDayOfMonth());
            // Print Month
            printMonth(month, year);
            // Find Number of Events
//            eventsPerMonthMap = findNumberOfEventsPerMonth(year, month);
        }
        private String getMonthAsString(int i)
        { return months[i]; }
        private String getWeekDayAsString(int i)
        { return weekdays[i]; }
        private int getNumberOfDaysOfMonth(int i)
        { return daysOfMonth[i]; }
        public String getItem(int position)
        { return list.get(position); }
        @Override
        public int getCount()
        { return list.size(); }
        /** * Prints Month * * @param mm * @param yy */
        private void printMonth(int mm, int yy)
        { Log.d(tag, "==> printMonth: mm: " + mm + " " + "yy: " + yy);
            int trailingSpaces = 0;
            int daysInPrevMonth = 0;
            int prevMonth = 0;
            int prevYear = 0;
            int nextMonth = 0;
            int nextYear = 0;
//            int currentMonth = mm - 1;
            int currentMonth=mm;
            String currentMonthName = getMonthAsString(currentMonth);
            daysInMonth = getNumberOfDaysOfMonth(currentMonth);
            Log.d(tag, "Current Month: " + " " + currentMonthName + " having " + daysInMonth + " days.");
            GregorianCalendar cal = new GregorianCalendar(yy, currentMonth, 1);
            Log.d(tag, "Gregorian Calendar:= " + cal.getTime().toString());
            if (currentMonth == 11)
            { prevMonth = currentMonth - 1;
                daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
                nextMonth = 0;
                prevYear = yy;
                nextYear = yy + 1;
                Log.d(tag, "*->PrevYear: " + prevYear + " PrevMonth:" + prevMonth + " NextMonth: " + nextMonth + " NextYear: " + nextYear);
            }
            else if (currentMonth == 0)
            {
                prevMonth = 11;
                prevYear = yy - 1;
                nextYear = yy;
                daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
                nextMonth = 1;
                Log.d(tag, "**--> PrevYear: " + prevYear + " PrevMonth:" + prevMonth + " NextMonth: " + nextMonth + " NextYear: " + nextYear);
            }
            else
            {
                prevMonth = currentMonth - 1;
                nextMonth = currentMonth + 1;
                nextYear = yy;
                prevYear = yy;
                daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
                Log.d(tag, "***---> PrevYear: " + prevYear + " PrevMonth:" + prevMonth + " NextMonth: " + nextMonth + " NextYear: " + nextYear);
            }
            int currentWeekDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
            trailingSpaces = currentWeekDay;
            Log.d(tag, "Week Day:" + currentWeekDay + " is " + getWeekDayAsString(currentWeekDay));
            Log.d(tag, "No. Trailing space to Add: " + trailingSpaces);
            Log.d(tag, "No. of Days in Previous Month: " + daysInPrevMonth);
            Log.e("leap_year","leap or not "+cal.isLeapYear(cal.get(Calendar.YEAR)));
            if (cal.isLeapYear(cal.get(Calendar.YEAR)))
                if (mm == 1)
                    ++daysInMonth;
                else if (mm == 2)
                    ++daysInPrevMonth;
            // Trailing Month days
            for (int i = 0; i < trailingSpaces; i++)
            {
                Log.d(tag, "PREV MONTH:= " + prevMonth + " => " + getMonthAsString(prevMonth) + " " + String.valueOf((daysInPrevMonth - trailingSpaces + DAY_OFFSET) + i));
                list.add(String .valueOf((daysInPrevMonth - trailingSpaces + DAY_OFFSET) + i) + "-GREY" + "-" + getMonthAsString(prevMonth) + "-" + prevYear);
            }
            // Current Month Days
            for (int i = 1; i <= daysInMonth; i++)
            {
                Log.d(currentMonthName, String.valueOf(i) + " " + getMonthAsString(currentMonth) + " " + yy);
                if (i == getCurrentDayOfMonth())
                {
                    list.add(String.valueOf(i) + "-BLUE" + "-" + getMonthAsString(currentMonth) + "-" + yy);
                }
                else
                {
                    list.add(String.valueOf(i) + "-WHITE" + "-" + getMonthAsString(currentMonth) + "-" + yy);
                }
            }
            // Leading Month days
            for (int i = 0; i < list.size() % 7; i++)
            {
                Log.d(tag, "NEXT MONTH:= " + getMonthAsString(nextMonth));
                list.add(String.valueOf(i + 1) + "-GREY" + "-" + getMonthAsString(nextMonth) + "-" + nextYear);
            }
        }
        /** * NOTE: YOU NEED TO IMPLEMENT THIS PART Given the YEAR, MONTH, retrieve
         * * ALL entries from a SQLite database for that month. Iterate over the
         * * List of All entries, and get the dateCreated, which is converted into * day.
         *  * * @param year * @param month * @return */
        private HashMap<String, Integer> findNumberOfEventsPerMonth(int year, int month)
        {
            HashMap<String, Integer> map = new HashMap<String, Integer>();
            return map;
        } @Override
        public long getItemId(int position)
        {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            View row = convertView;
            if (row == null)
            {
                LayoutInflater inflater = (LayoutInflater) _context .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.dates, parent, false);
            }
            // Get a reference to the Day gridcell
            gridcell = (TextView) row.findViewById(R.id.tv_dates);
//            gridcell.setOnClickListener(this);
            // ACCOUNT FOR SPACING
            Log.d(tag, "Current Day: " + getCurrentDayOfMonth());
            String[] day_color = list.get(position).split("-");
            String theday = day_color[0];
            String themonth = day_color[2];
            String theyear = day_color[3];
            /*if ((!eventsPerMonthMap.isEmpty()) && (eventsPerMonthMap != null))
            {
                if (eventsPerMonthMap.containsKey(theday))
                {
                    num_events_per_day = (TextView) row .findViewById(R.id.num_events_per_day);
                    Integer numEvents = (Integer) eventsPerMonthMap.get(theday);
                    num_events_per_day.setText(numEvents.toString());
                }
            }*/ // Set the Day GridCell
            gridcell.setText(theday);
            gridcell.setTag(theday + "-" + themonth + "-" + theyear);
            Log.d(tag, "Setting GridCell " + theday + "-" + themonth + "-" + theyear);
            if (day_color[1].equals("GREY"))
            {
                gridcell.setTextColor(getResources() .getColor(R.color.lightgray));
            }
            if (day_color[1].equals("WHITE")) {
                gridcell.setTextColor(getResources().getColor( R.color.lightgray02));
            }
            if (day_color[1].equals("BLUE"))
            { gridcell.setTextColor(getResources().getColor(R.color.orrange));
            }
            return row;
        }
        /*@Override
        public void onClick(View view)
        {
            String date_month_year = (String) view.getTag();
//            selectedDayMonthYearButton.setText("Selected: " + date_month_year);
            Log.e("Selected date", date_month_year);
            try {
                Date parsedDate = dateFormatter.parse(date_month_year);
                Log.d(tag, "Parsed Date: " + parsedDate.toString());
            }
             catch (ParseException e) {
                e.printStackTrace();
            }
        }
*/        public int getCurrentDayOfMonth() {
            return currentDayOfMonth;
        }
        private void setCurrentDayOfMonth(int currentDayOfMonth) {
            this.currentDayOfMonth = currentDayOfMonth;
        }
        public void setCurrentWeekDay(int currentWeekDay) {
            this.currentWeekDay = currentWeekDay;
        }
        public int getCurrentWeekDay() {
            return currentWeekDay;

        }
    }
}
