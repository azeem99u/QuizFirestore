package com.example.android.quizfirestore;



import android.annotation.SuppressLint;
import android.content.Context;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.card.MaterialCardView;


import java.util.ArrayList;

public class StartQuizFragment extends Fragment {
    String textOption1, textOption2, textOption3, textOption4;
    MaterialCardView cv_option1, cv_option2, cv_option3, cv_option4;
    String answer;
    ImageView close_fragment, moveBackWord, moveForWord;
    TextView question, option1, option2, option3, option4;
    Quiz quiz;
    private int index;
    private StartQuizFragmentListener mListener;

    public static ArrayList<Quiz> mCorrectAnsList;
    public static ArrayList<Quiz> mWrongAnsList;

    public StartQuizFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.fragment_start_quiz, container, false);
        initViews(inflate);
        return inflate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        close_fragment.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            QuizessActivity.cv_startQuiz.setVisibility(View.VISIBLE);
            mCorrectAnsList.clear();
            mWrongAnsList.clear();

        });

        moveForWord.setOnClickListener(view1 -> {

            if (!mCorrectAnsList.isEmpty()) {
                for (Quiz quiz1 : mCorrectAnsList) {
                    if (quiz.getQuiz_id().equals(quiz1.getQuiz_id())) {

                        mListener.onQuestionFragmentFinished(index, false, null);
                    }
                }
            }
            if (!mWrongAnsList.isEmpty()) {

                for (Quiz quiz1 : mWrongAnsList) {
                    if (quiz.getQuiz_id().equals(quiz1.getQuiz_id())) {
                        mListener.onQuestionFragmentFinished(index, false, null);
                    }
                }
            }


        });

        moveBackWord.setOnClickListener(view1 -> {

            int backStackEntryCount = requireActivity().getSupportFragmentManager().getBackStackEntryCount();
            if (backStackEntryCount > 1) {
                getActivity().getSupportFragmentManager().popBackStack();
            }


        });


        if (getArguments() != null) {

            if ((getArguments().getInt(QuizessActivity.ARRAY_LIST_INDEX)) != 0) {
                index = getArguments().getInt(QuizessActivity.ARRAY_LIST_INDEX);

            } else {
                index = getArguments().getInt("index");
            }


            quiz = getArguments().getParcelable(QuizessActivity.QUESTION);
            showQuestion(quiz);

            if ((getArguments().getParcelableArrayList(QuizessActivity.CORRECT_ANS_LIST_KEY)) != null) {

                mCorrectAnsList = getArguments().getParcelableArrayList(QuizessActivity.CORRECT_ANS_LIST_KEY);
                if (!mCorrectAnsList.isEmpty()) {
                    for (Quiz quiz1 : mCorrectAnsList) {

                        if (quiz.getQuiz_id().equals(quiz1.getQuiz_id()) && quiz1.getYourAnswer().equals(option1.getText().toString())) {
                            cv_option1.setCardBackgroundColor(Color.GREEN);
                            cv_option1.setClickable(false);
                            cv_option2.setClickable(false);
                            cv_option3.setClickable(false);
                            cv_option4.setClickable(false);
                            return;

                        }

                        if (quiz.getQuiz_id().equals(quiz1.getQuiz_id()) && quiz1.getYourAnswer().equals(option2.getText().toString())) {
                            cv_option2.setCardBackgroundColor(Color.GREEN);
                            cv_option1.setClickable(false);
                            cv_option2.setClickable(false);
                            cv_option3.setClickable(false);
                            cv_option4.setClickable(false);
                            return;
                        }


                        if (quiz.getQuiz_id().equals(quiz1.getQuiz_id()) && quiz1.getYourAnswer().equals(option3.getText().toString())) {
                            cv_option3.setCardBackgroundColor(Color.GREEN);
                            cv_option1.setClickable(false);
                            cv_option2.setClickable(false);
                            cv_option3.setClickable(false);
                            cv_option4.setClickable(false);
                            return;
                        }


                        if (quiz.getQuiz_id().equals(quiz1.getQuiz_id()) && quiz1.getYourAnswer().equals(option4.getText().toString())) {
                            cv_option4.setCardBackgroundColor(Color.GREEN);
                            cv_option1.setClickable(false);
                            cv_option2.setClickable(false);
                            cv_option3.setClickable(false);
                            cv_option4.setClickable(false);
                        }
                    }
                }


            }

            if ((getArguments().getParcelableArrayList(QuizessActivity.WRONG_ANS_LIST_KEY)) != null) {
                mWrongAnsList = getArguments().getParcelableArrayList(QuizessActivity.WRONG_ANS_LIST_KEY);
                if (!mWrongAnsList.isEmpty()) {
                    for (Quiz quiz1 : mWrongAnsList) {
                        if (quiz.getQuiz_id().equals(quiz1.getQuiz_id()) && quiz1.getYourAnswer().equals(option1.getText().toString())) {
                            cv_option1.setCardBackgroundColor(Color.RED);
                            cv_option1.setClickable(false);
                            cv_option2.setClickable(false);
                            cv_option3.setClickable(false);
                            cv_option4.setClickable(false);
                            return;

                        }

                        if (quiz.getQuiz_id().equals(quiz1.getQuiz_id()) && quiz1.getYourAnswer().equals(option2.getText().toString())) {
                            cv_option2.setCardBackgroundColor(Color.RED);
                            cv_option1.setClickable(false);
                            cv_option2.setClickable(false);
                            cv_option3.setClickable(false);
                            cv_option4.setClickable(false);
                            return;
                        }

                        if (quiz.getQuiz_id().equals(quiz1.getQuiz_id()) && quiz1.getYourAnswer().equals(option3.getText().toString())) {
                            cv_option3.setCardBackgroundColor(Color.RED);
                            cv_option1.setClickable(false);
                            cv_option2.setClickable(false);
                            cv_option3.setClickable(false);
                            cv_option4.setClickable(false);
                            return;
                        }

                        if (quiz.getQuiz_id().equals(quiz1.getQuiz_id()) && quiz1.getYourAnswer().equals(option4.getText().toString())) {
                            cv_option4.setCardBackgroundColor(Color.RED);
                            cv_option1.setClickable(false);
                            cv_option2.setClickable(false);
                            cv_option3.setClickable(false);
                            cv_option4.setClickable(false);
                        }

                    }
                }

            }
        }


    }


    @SuppressLint("ResourceAsColor")
    private void showQuestion(Quiz quiz) {
        String quiz_question = quiz.getQuiz_question();
        String option_1 = quiz.getOption_1();
        String option_2 = quiz.getOption_2();
        String option_3 = quiz.getOption_3();
        String option_4 = quiz.getOption_4();

        question.setText(quiz_question);
        option1.setText(option_1);
        option2.setText(option_2);
        option3.setText(option_3);
        option4.setText(option_4);
        answer = quiz.getAnswer();


        cv_option1.setOnClickListener(view -> {

            textOption1 = option1.getText().toString();
            if (answer != null) {
                if (textOption1.equals(answer)) {
                    cv_option1.setClickable(false);
                    cv_option2.setClickable(false);
                    cv_option3.setClickable(false);
                    cv_option4.setClickable(false);
                    mListener.onQuestionFragmentFinished(index, true, textOption1);
                    cv_option1.setCardBackgroundColor(Color.GREEN);

                } else {
                    cv_option1.setClickable(false);
                    cv_option2.setClickable(false);
                    cv_option3.setClickable(false);
                    cv_option4.setClickable(false);
                    mListener.onQuestionFragmentFinished(index, false, textOption1);
                    cv_option1.setCardBackgroundColor(Color.RED);
                }
            }
        });

        cv_option2.setOnClickListener(view -> {

            textOption2 = option2.getText().toString();
            if (answer != null) {
                if (textOption2.equals(answer)) {
                    cv_option1.setClickable(false);
                    cv_option2.setClickable(false);
                    cv_option3.setClickable(false);
                    cv_option4.setClickable(false);
                    mListener.onQuestionFragmentFinished(index, true, textOption2);
                    cv_option2.setCardBackgroundColor(Color.GREEN);

                } else {
                    cv_option1.setClickable(false);
                    cv_option2.setClickable(false);
                    cv_option3.setClickable(false);
                    cv_option4.setClickable(false);
                    mListener.onQuestionFragmentFinished(index, false, textOption2);
                    cv_option2.setCardBackgroundColor(Color.RED);
                }
            }
        });

        cv_option3.setOnClickListener(view -> {

            textOption3 = option3.getText().toString();
            if (answer != null) {
                if (textOption3.equals(answer)) {
                    cv_option1.setClickable(false);
                    cv_option2.setClickable(false);
                    cv_option3.setClickable(false);
                    cv_option4.setClickable(false);
                    mListener.onQuestionFragmentFinished(index, true, textOption3);
                    cv_option3.setCardBackgroundColor(Color.GREEN);

                } else {
                    cv_option1.setClickable(false);
                    cv_option2.setClickable(false);
                    cv_option3.setClickable(false);
                    cv_option4.setClickable(false);
                    mListener.onQuestionFragmentFinished(index, false, textOption3);
                    cv_option3.setCardBackgroundColor(Color.RED);
                }
            }
        });

        cv_option4.setOnClickListener(view -> {

            textOption4 = option4.getText().toString();
            if (answer != null) {
                if (textOption4.equals(answer)) {
                    cv_option1.setClickable(false);
                    cv_option2.setClickable(false);
                    cv_option3.setClickable(false);
                    cv_option4.setClickable(false);
                    mListener.onQuestionFragmentFinished(index, true, textOption4);
                    cv_option4.setCardBackgroundColor(Color.GREEN);

                } else {
                    cv_option1.setClickable(false);
                    cv_option2.setClickable(false);
                    cv_option3.setClickable(false);
                    cv_option4.setClickable(false);
                    mListener.onQuestionFragmentFinished(index, false, textOption4);
                    cv_option4.setCardBackgroundColor(Color.RED);
                }
            }
        });

    }




    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof StartQuizFragmentListener) {
            mListener = (StartQuizFragmentListener) context;
        }
    }


    private void initViews(View inflate) {
        close_fragment = inflate.findViewById(R.id.image_close_f);
        question = inflate.findViewById(R.id.question);
        moveForWord = inflate.findViewById(R.id.moveForwordImage);
        moveBackWord = inflate.findViewById(R.id.moveBackwordImage);
        option1 = inflate.findViewById(R.id.option1);
        option2 = inflate.findViewById(R.id.option2);
        option3 = inflate.findViewById(R.id.option3);
        option4 = inflate.findViewById(R.id.option4);
        cv_option1 = inflate.findViewById(R.id.cv_option1);
        cv_option2 = inflate.findViewById(R.id.cv_option2);
        cv_option3 = inflate.findViewById(R.id.cv_option3);
        cv_option4 = inflate.findViewById(R.id.cv_option4);
    }

    public interface StartQuizFragmentListener {
        void onQuestionFragmentFinished(int index, boolean isCorrect, String selectedTxt);
    }

}