package com.example.android.quizfirestore;

import android.os.Bundle;
import android.os.Parcelable;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;


public class ChoseReviewListBottomSheet extends BottomSheetDialogFragment {
    ArrayList<Quiz> correctAnsList;
    ArrayList<Quiz> wrongAnsList;
    TextView wrongAnsTxt,correctAnsTxt;


    public ChoseReviewListBottomSheet() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chose_review_list_bottom_sheet, container, false);
        correctAnsTxt= view.findViewById(R.id.correctListTextView);
        wrongAnsTxt= view.findViewById(R.id.wrongListTextView);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        correctAnsTxt.setOnClickListener(view1 -> {
            AnswerListFragment fragment = new AnswerListFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(QuizessActivity.CORRECT_ANS_LIST_KEY,correctAnsList);
            fragment.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).add(R.id.fragmentContainerView,fragment).commit();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(100);
                    dismiss();
                }
            }).start();
        });
        wrongAnsTxt.setOnClickListener(view1 -> {
            AnswerListFragment fragment = new AnswerListFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(QuizessActivity.WRONG_ANS_LIST_KEY,wrongAnsList);
            fragment.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).add(R.id.fragmentContainerView,fragment).commit();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(100);
                    dismiss();
                }
            }).start();


        });



        if (getArguments() != null) {

            if ((getArguments().getParcelableArrayList(QuizessActivity.CORRECT_ANS_LIST_KEY)) != null) {

                correctAnsList = getArguments().getParcelableArrayList(QuizessActivity.CORRECT_ANS_LIST_KEY);

            }

            if ((getArguments().getParcelableArrayList(QuizessActivity.WRONG_ANS_LIST_KEY)) != null) {

                wrongAnsList = getArguments().getParcelableArrayList(QuizessActivity.WRONG_ANS_LIST_KEY);
            }

        }
    }
}