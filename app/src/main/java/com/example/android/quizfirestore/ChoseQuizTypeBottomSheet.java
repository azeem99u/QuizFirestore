package com.example.android.quizfirestore;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class ChoseQuizTypeBottomSheet extends BottomSheetDialogFragment {


    public static final String ENGLISH_QUIZ_KEY = "english_quiz_key";
    public static final String PHYSICS_QUIZ_KEY = "physics_quiz_key";
    public static final String MATH_QUIZ_KEY = "math_quiz_key";
    public static final String PHYSICS_QUIZ_COLLECTION = "quiz_1/Gx3X2NLBnoLB37PPP234/physics";
    public static final String ENGLISH_QUIZ_COLLECTION = "quiz_1/Gx3X2NLBnoLB37PPPUJ2/english_quiz";
    public static final String MATH_QUIZ_COLLECTION = "quiz_1/Gx3X2NLBnoLB37PPP3422/math";
    TextView englishTv,physicsTv,mathTv;
    public ChoseQuizTypeBottomSheet() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_chose_quiz_type_bottom_sheet, container, false);
        englishTv = inflate.findViewById(R.id.correctListTextView);
        physicsTv = inflate.findViewById(R.id.wrongListTextView);
        mathTv = inflate.findViewById(R.id.mathTextView);
        return inflate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        englishTv.setOnClickListener(view1 -> {
            getActivity().startActivity(new Intent(getActivity(), AddQuizActivity.class).putExtra(ENGLISH_QUIZ_KEY, ENGLISH_QUIZ_COLLECTION));
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(16);
                    dismiss();
                }
            }).start();

        });
        physicsTv.setOnClickListener(view1 -> {
            getActivity().startActivity(new Intent(getActivity(), AddQuizActivity.class).putExtra(PHYSICS_QUIZ_KEY, PHYSICS_QUIZ_COLLECTION));
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(16);
                    dismiss();
                }
            }).start();

        });
        mathTv.setOnClickListener(view1 -> {
            getActivity().startActivity(new Intent(getActivity(), AddQuizActivity.class).putExtra(MATH_QUIZ_KEY, MATH_QUIZ_COLLECTION));
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(16);
                    dismiss();
                }
            }).start();
        });
    }
}