package com.example.multipurposecalci;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class AgeCalci extends AppCompatActivity {
    Button selectDate,selectDate2;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int day;
    Date d1=new Date(2001-1900,7,4);
    Date d2=new Date();
    Calendar calendar;
    TextView nextbirthday,years,md,byears,bmonths,bweeks,bdays,bhours,bmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_calci);
        nextbirthday = findViewById(R.id.next);
        years = findViewById(R.id.years);
        md = findViewById(R.id.month);
        byears=findViewById(R.id.byears);
        bmonths=findViewById(R.id.bmonths);
        bweeks=findViewById(R.id.bweeks);
        bdays=findViewById(R.id.bdays);
        bhours=findViewById(R.id.bhours);
        bmin=findViewById(R.id.bmin);

        selectDate = findViewById(R.id.btnDate);

        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(AgeCalci.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                d1=new Date(year-1900, month, day);
                                selectDate.setText(day+"/"+(month+1)+"/"+year);
                                calculateAge();
                                nextBirthday();
                            }
                        }, year, month, day);
                datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
                datePickerDialog.show();
            }
        });

        selectDate2 = findViewById(R.id.btnDate2);

        selectDate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(AgeCalci.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                d2=new Date(year-1900,month,day);
                                selectDate2.setText(day+"/"+(month+1)+"/"+year);
                                calculateAge();
                                nextBirthday();
                            }
                        }, year, month, day);
                datePickerDialog.getDatePicker().setMinDate(d1.getTime());
                datePickerDialog.show();
            }
        });
    }

    public void nextBirthday() {
        int currentDay = d2.getDate();
        int currentMonth = d2.getMonth()+1;
        int currentYear = d2.getYear();

        Calendar current = Calendar.getInstance();
        current.set(currentYear, currentMonth, currentDay);

        int birthDay = d1.getDate();
        int birthMonth = d1.getMonth()+1;
        int birthYear = d1.getYear();

        Calendar birthday = Calendar.getInstance();
        birthday.set(birthYear, birthMonth, birthDay);

        long difference = birthday.getTimeInMillis() - current.getTimeInMillis();

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(difference);

        nextbirthday.setText(cal.get(Calendar.MONTH) +" months "+ (cal.get(Calendar.DAY_OF_MONTH) - 1) +" days");
    }

    public void calculateAge() {
        int currentDay = d2.getDate();
        int currentMonth = d2.getMonth()+1;
        int currentYear = d2.getYear();

        int birthDay = d1.getDate();
        int birthMonth = d1.getMonth()+1;
        int birthYear = d1.getYear();

        int[] month = {31, 28, 31, 30, 31, 30, 31,
                31, 30, 31, 30, 31};

        if (birthDay > currentDay) {
            currentDay = currentDay + month[birthMonth - 1];
            currentMonth = currentMonth - 1;
        }

        if (birthMonth > currentMonth) {
            currentYear = currentYear - 1;
            currentMonth = currentMonth + 12;
        }

        int cdate = currentDay - birthDay;
        int cmonth = currentMonth - birthMonth;
        int cyear = currentYear - birthYear;

        Toast.makeText(this, "Calculating", Toast.LENGTH_SHORT).show();

        md.setText(cmonth+" months "+cdate+" days");
        years.setText(cyear+" years");
        byears.setText("Years\n"+cyear);
            int tmonths=cyear*12+cmonth;
            bmonths.setText("Months\n"+tmonths);
            int tdays=cyear*365;
            for(int i=birthYear;i<currentYear;i++)
                if(isLeap(i))
                   tdays++;
            for(int i=0;i<cmonth;i++){
                tdays+=month[i];
            }
            tdays+=cdate;
            int tweeks=tdays/7;
            bweeks.setText("Weeks\n"+tweeks);
            bdays.setText("Days\n"+tdays);
            bhours.setText("Hours\n"+tdays*24);
            bmin.setText(("Minutes\n"+tdays*24l*60l));

    }

    public boolean isLeap(int year) {
        boolean leap = false;
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                leap = year % 400 == 0;
            }
            else
                leap = true;
        }
        else
            leap = false;
        return leap;
    }
}