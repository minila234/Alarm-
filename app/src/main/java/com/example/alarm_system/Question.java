package com.example.alarm_system;

public class Question {
    public  String myques[]= {
            "Which element is a liquid at ordinary temperature?",
            "Which gas is found in soda water?",
            "What is sodium chloride?",
            "Which is called white poison?"
    };

    private  String chose[][]={
            {"Table salt","Sugar","Carbon dioxide","Mercury"},
            {"Table salt","Sugar","Carbon dioxide","Mercury"},
            {"Table salt","Sugar","Carbon dioxide","Mercury"},
            {"Table salt","Sugar","Carbon dioxide","Mercury"}
    };

    private  String correctans[]={
            "Mercury","Carbon dioxide","Table salt","Sugar"
    };

    public  String getques(int i){
        String ques=myques[i];
        return ques;
    }
    public  String choos1(int i){
        String choos1=chose[i][0];
        return choos1;
    }
    public  String choos2(int i){
        String choos2=chose[i][1];
        return choos2;
    }
    public  String choos3(int i){
        String choos3=chose[i][2];
        return choos3;
    }
    public  String choos4(int i){
        String choos4=chose[i][3];
        return choos4;
    }
    public String coorectAnswer(int i){
        String correct=correctans[i];
        return correct;
    }
}
