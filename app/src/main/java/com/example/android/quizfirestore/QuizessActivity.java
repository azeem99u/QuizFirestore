package com.example.android.quizfirestore;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class QuizessActivity extends AppCompatActivity implements StartQuizFragment.StartQuizFragmentListener {

    public static final String QUESTION = "Question";
    public static final String ARRAY_LIST_INDEX = "index";
    public static final String CORRECT_ANS_LIST_KEY = "correct_ans_list";
    public static final String WRONG_ANS_LIST_KEY = "wrong_ans_list";
    ArrayList<Quiz> quizList = new ArrayList<>();
    public static CardView cv_startQuiz;
    ProgressBar progressBar;
    Fragment fragment;

    public ArrayList<Quiz> correctAnsList = new ArrayList<>();
    public ArrayList<Quiz> wrongAnsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizess);
        progressBar = findViewById(R.id.progressBar);
        hideProgressBar();
        cv_startQuiz = findViewById(R.id.start_quiz);
        cv_startQuiz.setVisibility(View.GONE);
        FragmentContainerView fragmentContainerView;
        fragmentContainerView = findViewById(R.id.fragmentContainerView);

        correctAnsList.clear();
        wrongAnsList.clear();

        if (getIntent() != null) {
            if (getIntent().hasExtra(MainActivity.ENGLISH_KEY)) {
                String stringExtra = getIntent().getStringExtra(MainActivity.ENGLISH_KEY);
                showProgressBar();
                getResultFromCollection(stringExtra);

            } else if (getIntent().hasExtra(MainActivity.PHYSIC_KEY)) {
                String stringExtra = getIntent().getStringExtra(MainActivity.PHYSIC_KEY);
                showProgressBar();
                getResultFromCollection(stringExtra);

            } else if (getIntent().hasExtra(MainActivity.MATH_KEY)) {
                showProgressBar();
                String stringExtra = getIntent().getStringExtra(MainActivity.MATH_KEY);
                getResultFromCollection(stringExtra);
            }
        }


        cv_startQuiz.setOnClickListener(view -> {

            fragment = new StartQuizFragment();
            Bundle bundle = new Bundle();
            Quiz quiz = quizList.get(0);
            bundle.putParcelableArrayList(CORRECT_ANS_LIST_KEY, correctAnsList);
            bundle.putParcelableArrayList(WRONG_ANS_LIST_KEY, wrongAnsList);
            bundle.putInt("index", 0);
            bundle.putParcelable(QUESTION, quiz);
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainerView, fragment, "firstFragment").addToBackStack(null).commit();
            cv_startQuiz.setVisibility(View.GONE);
        });

    }

    private void getResultFromCollection(String stringExtra) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference collection = db.collection(stringExtra);
        collection.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Toast.makeText(QuizessActivity.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (value.isEmpty()) {
                    hideProgressBar();
                    cv_startQuiz.setVisibility(View.VISIBLE);
                    cv_startQuiz.setClickable(false);
                    TextView isQuizReady = findViewById(R.id.quiz_start);
                    isQuizReady.setText("Right Now Quiz not available! ");
                    return;
                }
                for (DocumentSnapshot snapshot : value.getDocuments()) {
                    Quiz quiz = snapshot.toObject(Quiz.class);
                    String id = snapshot.getId();
                    quiz.setQuiz_id(id);
                    quizList.add(quiz);
                }
                if (!quizList.isEmpty()) {
                    hideProgressBar();
                    cv_startQuiz.setVisibility(View.VISIBLE);
                    Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
                    if (fragment != null) {
                        cv_startQuiz.setVisibility(View.GONE);
                    }
                }

            }
        });
    }


    void showProgressBar() {
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.VISIBLE);
    }

    void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onQuestionFragmentFinished(final int index, boolean isCorrect, String selectedTxt) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                if (selectedTxt != null) {
                    SystemClock.sleep(270);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            int fIndex = index;
                            if (fIndex <= quizList.size() - 1) {
                                if (isCorrect) {
                                    Quiz quiz = quizList.get(fIndex);
                                    quiz.setYourAnswer(selectedTxt);
                                    correctAnsList.add(quiz);
                                } else {
                                    Quiz quiz = quizList.get(fIndex);
                                    quiz.setYourAnswer(selectedTxt);
                                    wrongAnsList.add(quiz);
                                }
                                fIndex = fIndex + 1;
                            }
                            if (fIndex <= quizList.size() - 1) {

                                fragment = new StartQuizFragment();
                                Bundle bundle = new Bundle();
                                Quiz quiz = quizList.get(fIndex);
                                bundle.putParcelableArrayList(CORRECT_ANS_LIST_KEY, correctAnsList);
                                bundle.putParcelableArrayList(WRONG_ANS_LIST_KEY, wrongAnsList);
                                bundle.putInt(ARRAY_LIST_INDEX, fIndex);
                                bundle.putParcelable(QUESTION, quiz);

                                fragment.setArguments(bundle);
                                getSupportFragmentManager().beginTransaction().
                                        setCustomAnimations(
                                                R.anim.slide_in_right,
                                                R.anim.slide_out_left,
                                                android.R.anim.slide_in_left,
                                                android.R.anim.slide_out_right
                                        )
                                        .replace(R.id.fragmentContainerView, fragment)
                                        .addToBackStack(null)
                                        .commit();
                            } else {

                                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

                                CongratsFragment congratsFragment = new CongratsFragment();
                                Bundle bundle = new Bundle();
                                bundle.putParcelableArrayList(CORRECT_ANS_LIST_KEY, correctAnsList);
                                bundle.putParcelableArrayList(WRONG_ANS_LIST_KEY, wrongAnsList);


                                congratsFragment.setArguments(bundle);
                                getSupportFragmentManager().beginTransaction().
                                        setCustomAnimations(
                                                R.anim.slide_in_right,
                                                R.anim.slide_out_left
                                        )
                                        .add(R.id.fragmentContainerView, congratsFragment, "congratsFragment").addToBackStack(null)
                                        .commit();
                            }
                        }
                    });
                } else if (selectedTxt == null) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            int fIndex = index;
                            fIndex = fIndex + 1;
                            fragment = new StartQuizFragment();
                            Bundle bundle = new Bundle();
                            Quiz quiz = quizList.get(fIndex);
                            bundle.putParcelableArrayList(CORRECT_ANS_LIST_KEY, correctAnsList);
                            bundle.putParcelableArrayList(WRONG_ANS_LIST_KEY, wrongAnsList);
                            bundle.putInt(ARRAY_LIST_INDEX, fIndex);
                            bundle.putParcelable(QUESTION, quiz);
                            fragment.setArguments(bundle);
                            getSupportFragmentManager().beginTransaction().
                                    setCustomAnimations(
                                            R.anim.slide_in_right,
                                            R.anim.slide_out_left,
                                            android.R.anim.slide_in_left,
                                            android.R.anim.slide_out_right
                                    )
                                    .replace(R.id.fragmentContainerView, fragment)
                                    .addToBackStack(null)
                                    .commit();
                        }
                    });

                }

            }
        }).start();
    }


    @Override
    public void onBackPressed() {

        Fragment fragment1 = getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        if (!(getSupportFragmentManager().getBackStackEntryCount() == 0)) {
            if ((getSupportFragmentManager().getBackStackEntryCount() == 1) && fragment1 == getSupportFragmentManager().findFragmentByTag("firstFragment")) {
                showDialog();
            } else if ((getSupportFragmentManager().getBackStackEntryCount() == 1) && fragment1 == getSupportFragmentManager().findFragmentByTag("congratsFragment")) {
                getSupportFragmentManager().popBackStack();
                QuizessActivity.cv_startQuiz.setVisibility(View.VISIBLE);
                correctAnsList.clear();
                wrongAnsList.clear();
            } else {
                getSupportFragmentManager().popBackStack();
            }
        } else {
            super.onBackPressed();
        }
    }

    public void showDialog() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Do you want to close Quiz?");

        builder.setCancelable(false);

        builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (dialogInterface != null) {
                    dialogInterface.dismiss();
                }
            }
        });

        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                getSupportFragmentManager().popBackStack();
                QuizessActivity.cv_startQuiz.setVisibility(View.VISIBLE);
                correctAnsList.clear();
                wrongAnsList.clear();
            }
        });

        builder.show();

    }


}