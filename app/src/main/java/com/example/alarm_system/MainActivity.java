package com.example.alarm_system;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{

    DatabaseHelper db;
    int notificationId = 1;
   // TextView mTextView;
    ListView listview;
    Spinner toneList;
    public static  String Iterm="";
    static int toneID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        db =new DatabaseHelper(this);
        listview =(ListView)findViewById(R.id.listview);
        toneList = (Spinner) findViewById(R.id.spinner);
        Button clear =(Button)findViewById(R.id.clear);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        Button buttonCancelAlarm = findViewById(R.id.button_cancel);
        buttonCancelAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAlarm();
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listview.setAdapter(null);
            }
        });

        ArrayList<String> tones = new ArrayList<String>();
        tones.add("Alarm1");
        tones.add("Alarm2");
        tones.add("Alarm3");
        tones.add("Alarm4");

        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, tones);
        toneList.setAdapter(arrayAdapter);

        toneList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                if(position==1){
                    toneID = 4;
                }else if(position==2){
                    toneID = 1;
                }else if(position==3){
                    toneID = 2;
                }else if(position==0){
                    toneID =4;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onTimeSet (TimePicker timePicker,int hourOfDay, int minute){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);

        updateTimeText(c);
        startAlarm(c);
    }


    private void updateTimeText(Calendar c) {
        String timeText = "Alarm set for: ";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        AddData(timeText);
        //mTextView.setText(timeText);
        //////////////show the list view

        ArrayList<String> theList = new ArrayList<>();
        Cursor data = db.getListContents();
        if(data.getCount() == 0){
            Toast.makeText(this, "There are no contents in this list!",Toast.LENGTH_LONG).show();
        }else {
            while (data.moveToNext()) {
                theList.add(data.getString(1));// clo 1
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
                listview.setAdapter(listAdapter);
                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        EditText editText = findViewById(R.id.editText);
                        Calendar c = Calendar.getInstance();
                        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                        Intent intent = new Intent(MainActivity.this, AlertReceiver.class);
                        intent.putExtra("notificationId", notificationId);
                        intent.putExtra("todo", editText.getText().toString());
                        intent.putExtra("toneid",toneID);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 1, intent, 0);

                        if (c.before(Calendar.getInstance())) {
                            c.add(Calendar.DATE, 1);
                        }

                        alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);

                    }

                });
            }
        }
       /* Intent i= new Intent(this,Quiz.class);
        startActivity(i);*/
    }

    private void startAlarm(Calendar c) {

        EditText editText = findViewById(R.id.editText);
        //Spinner sp= findViewById(R.id.spinner);



        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        intent.putExtra("notificationId", notificationId);
        intent.putExtra("todo", editText.getText().toString());
        intent.putExtra("toneid",toneID);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }

        alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);



    }

    private void cancelAlarm() {

      //AlertReceiver.ring.stop();
      Intent i= new Intent(this,Quiz.class);
      startActivity(i);

    }

    public void AddData(String newEntry) {

        boolean insertData = db.addData(newEntry);

        if(insertData==true){
            Toast.makeText(this, "Successfully enter time", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Something went wrong :(.", Toast.LENGTH_LONG).show();
        }
    }


}
