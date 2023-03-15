package com.example.android.quizfirestore;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class CongratsFragment extends Fragment {

    TextView correctAnsTxt, wrongAnsTxt;
    Button btnShareScore, btnReviewList,btnHome;
    ArrayList<Quiz> correctAnsList;
    ArrayList<Quiz> wrongAnsList;
    public CongratsFragment() {
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnReviewList.setOnClickListener(view1 -> {

            ChoseReviewListBottomSheet choseReviewListBottomSheet = new ChoseReviewListBottomSheet();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(QuizessActivity.CORRECT_ANS_LIST_KEY,correctAnsList);
            bundle.putParcelableArrayList(QuizessActivity.WRONG_ANS_LIST_KEY,wrongAnsList);
            choseReviewListBottomSheet.setArguments(bundle);
            choseReviewListBottomSheet.show(getActivity().getSupportFragmentManager(), "choseReviewListBottomSheet");

        });
        if (getArguments() != null) {

            if ((getArguments().getParcelableArrayList(QuizessActivity.CORRECT_ANS_LIST_KEY)) != null) {

                correctAnsList = getArguments().getParcelableArrayList(QuizessActivity.CORRECT_ANS_LIST_KEY);
                int totalCorrectAns = correctAnsList.size();
                correctAnsTxt.setText("" + totalCorrectAns);
            } else {
                correctAnsTxt.setText("0");
            }


            if ((getArguments().getParcelableArrayList(QuizessActivity.WRONG_ANS_LIST_KEY)) != null) {

                wrongAnsList = getArguments().getParcelableArrayList(QuizessActivity.WRONG_ANS_LIST_KEY);
                int totalWrongAns = wrongAnsList.size();
                wrongAnsTxt.setText("" + totalWrongAns);

            } else {
                wrongAnsTxt.setText("0");
            }
        }

        btnHome.setOnClickListener(view1 -> {
            getActivity().finish();
        });

    }


/*
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventMessage event) {
        boolean backPressed = event.isBackPressed();
        if (backPressed) {
            if (requireActivity().getSupportFragmentManager().findFragmentByTag("congratsFragment").equals(this)) {
                requireActivity().getSupportFragmentManager().popBackStack();
                QuizessActivity.cv_startQuiz.setVisibility(View.VISIBLE);
                QuizessActivity.correctAnsList.clear();
                QuizessActivity.wrongAnsList.clear();
            }
        }
    }
*/


    private void intiViews(View inflate) {
        correctAnsTxt = inflate.findViewById(R.id.correctAnsTxt);
        wrongAnsTxt = inflate.findViewById(R.id.wrongAnsTxt);
        btnHome = inflate.findViewById(R.id.btnHome);
        btnReviewList = inflate.findViewById(R.id.btnReviewList);
        btnShareScore = inflate.findViewById(R.id.btnShareScore);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_congrate, container, false);
        intiViews(inflate);
        return inflate;
    }

}