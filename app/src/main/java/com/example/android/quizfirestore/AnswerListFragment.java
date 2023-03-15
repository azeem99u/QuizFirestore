package com.example.android.quizfirestore;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class AnswerListFragment extends Fragment {

    private ArrayList<Quiz> correctAnsList;
    private ArrayList<Quiz> wrongAnsList;
    private RecyclerView recyclerView;
    private AnswerListAdapter answerListAdapter;
    TextView answerTitle = null;
    private ImageView backBtn = null;
    public AnswerListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View inflate = inflater.inflate(R.layout.fragment_answer_list, container, false);
        recyclerView = inflate.findViewById(R.id.recyclerView);
        answerTitle = inflate.findViewById(R.id.ansTitle);
        backBtn = inflate.findViewById(R.id.backbuttonImage);
        return inflate;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        backBtn.setOnClickListener(view1 -> {
            getActivity().getSupportFragmentManager().popBackStack();
        });
        if (getArguments() != null) {

            if ((getArguments().getParcelableArrayList(QuizessActivity.CORRECT_ANS_LIST_KEY)) != null) {
                correctAnsList = getArguments().getParcelableArrayList(QuizessActivity.CORRECT_ANS_LIST_KEY);
                answerTitle.setText("Correct Answers");
                answerTitle.setTextColor(Color.GREEN);
                showData(correctAnsList);

            }
            if ((getArguments().getParcelableArrayList(QuizessActivity.WRONG_ANS_LIST_KEY)) != null) {

                wrongAnsList = getArguments().getParcelableArrayList(QuizessActivity.WRONG_ANS_LIST_KEY);
                answerTitle.setText("Wrong Answers");
                answerTitle.setTextColor(Color.RED);
                showData(wrongAnsList);
            }

        }
    }

    private void showData(ArrayList<Quiz> correctAnsList) {
        answerListAdapter = new AnswerListAdapter(correctAnsList);
        recyclerView.setAdapter(answerListAdapter);
        answerListAdapter.notifyDataSetChanged();
    }
}