package com.example.android.quizfirestore;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.firestore.Exclude;

public class Quiz implements Parcelable {
    private String quiz_id = "";
    private String quiz_question;
    private String option_1;
    private String option_2;
    private String option_3;
    private String option_4;
    private String answer;
    private String yourAnswer;

    public Quiz(String quiz_question, String option_1, String option_2, String option_3, String option_4, String answer) {
        this.quiz_question = quiz_question;
        this.option_1 = option_1;
        this.option_2 = option_2;
        this.option_3 = option_3;
        this.option_4 = option_4;
        this.answer = answer;
    }

    public Quiz() {}

    @Exclude
    public String getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(String quiz_id) {
        this.quiz_id = quiz_id;
    }

    public String getQuiz_question() {
        return quiz_question;
    }

    public void setQuiz_question(String quiz_question) {
        this.quiz_question = quiz_question;
    }

    public String getOption_1() {
        return option_1;
    }

    public void setOption_1(String option_1) {
        this.option_1 = option_1;
    }

    public String getOption_2() {
        return option_2;
    }

    public void setOption_2(String option_2) {
        this.option_2 = option_2;
    }

    public String getOption_3() {
        return option_3;
    }

    public void setOption_3(String option_3) {
        this.option_3 = option_3;
    }

    public String getOption_4() {
        return option_4;
    }

    public void setOption_4(String option_4) {
        this.option_4 = option_4;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getYourAnswer() {
        return yourAnswer;
    }

    public void setYourAnswer(String yourAnswer) {
        this.yourAnswer = yourAnswer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.quiz_id);
        dest.writeString(this.quiz_question);
        dest.writeString(this.option_1);
        dest.writeString(this.option_2);
        dest.writeString(this.option_3);
        dest.writeString(this.option_4);
        dest.writeString(this.answer);
        dest.writeString(this.yourAnswer);
    }

    public void readFromParcel(Parcel source) {
        this.quiz_id = source.readString();
        this.quiz_question = source.readString();
        this.option_1 = source.readString();
        this.option_2 = source.readString();
        this.option_3 = source.readString();
        this.option_4 = source.readString();
        this.answer = source.readString();
        this.yourAnswer = source.readString();
    }

    protected Quiz(Parcel in) {
        this.quiz_id = in.readString();
        this.quiz_question = in.readString();
        this.option_1 = in.readString();
        this.option_2 = in.readString();
        this.option_3 = in.readString();
        this.option_4 = in.readString();
        this.answer = in.readString();
        this.yourAnswer = in.readString();
    }

    public static final Parcelable.Creator<Quiz> CREATOR = new Parcelable.Creator<Quiz>() {
        @Override
        public Quiz createFromParcel(Parcel source) {
            return new Quiz(source);
        }

        @Override
        public Quiz[] newArray(int size) {
            return new Quiz[size];
        }
    };
}
