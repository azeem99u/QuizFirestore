package com.example.android.quizfirestore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AnswerListAdapter extends RecyclerView.Adapter<AnswerListAdapter.MyViewHolder> {

    private ArrayList<Quiz> answerList;

    public AnswerListAdapter(ArrayList<Quiz> answerList) {
        this.answerList = answerList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_ans_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Quiz quiz = answerList.get(position);
        holder.questionTxt.setText(quiz.getQuiz_question());
    }

    @Override
    public int getItemCount() {
        return answerList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView questionTxt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            questionTxt = itemView.findViewById(R.id.questionTextView);
        }
    }
}
