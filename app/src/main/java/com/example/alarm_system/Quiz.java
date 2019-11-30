package com.example.alarm_system;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class Quiz extends AppCompatActivity {

    public static Button ans1,ans2,ans3,ans4;
    public static TextView ques;

    Question Ques =new Question();

    private String answer ;
    private  int questionlength =Ques.myques.length;

    Random Ran;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Ran =new Random();

        updatequestion(Ran.nextInt(questionlength));

        ans1 =(Button)findViewById(R.id.answer1);
        ans2 =(Button)findViewById(R.id.answer2);
        ans3 =(Button)findViewById(R.id.answer3);
        ans4 =(Button)findViewById(R.id.answer4);

        ques =(TextView)findViewById(R.id.q);


        ans1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ans1.getText()==answer){
                    AlertReceiver.ring.stop();
                }
            }
        });

        ans2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = ans2.getText().toString();
                if(ans2.getText()==answer){
                    AlertReceiver.ring.stop();
                }
            }
        });
        ans3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(ans3.getText()==answer){
                    AlertReceiver.ring.stop();
                }
            }
        });
        ans4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ans4.getText()==answer){
                    AlertReceiver.ring.stop();
                }
            }
        });


    }

    public void updatequestion(int num){
        ans1 =(Button)findViewById(R.id.answer1);
        ans2 =(Button)findViewById(R.id.answer2);
        ans3 =(Button)findViewById(R.id.answer3);
        ans4 =(Button)findViewById(R.id.answer4);

        ques =(TextView)findViewById(R.id.q);

        ques.setText(Ques.getques(num));
        ans1.setText(Ques.choos1(num));
        ans2.setText(Ques.choos2(num));
        ans3.setText(Ques.choos3(num));
        ans4.setText(Ques.choos4(num));

        answer =Ques.coorectAnswer(num);
    }
}
