package com.example.android.quizfirestore;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddQuizActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private CollectionReference quiz_1;
    TextView quizNameTxt;
    ProgressBar progressBar;
    private TextInputLayout tTLQuizQuestion, tILOption1, tILOption2, tILOption3, tILOption4;
    private EditText et_QuizQuestion, et_Option1, et_Option2, et_Option3, et_Option4;
    String[] selectAnswer = {"Answer [ A ]", "Answer [ B ]", "Answer [ C ]", "Answer [ D ]"};
    Spinner spin;
    String answer;
    String quiz_question;
    String option_1;
    String option_2;
    String option_3;
    String option_4;



    private boolean mFieldTextHasChanged= false;
    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            mFieldTextHasChanged = true;
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quiz);
        initViews();


        findViewById(R.id.btnSave).setOnClickListener(this::insertQuiz);


        if (getIntent() != null) {

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            if (getIntent().hasExtra(ChoseQuizTypeBottomSheet.ENGLISH_QUIZ_KEY)) {
                quizNameTxt.setText("Add New ( English ) Quiz");
                String englishCollection = getIntent().getStringExtra(ChoseQuizTypeBottomSheet.ENGLISH_QUIZ_KEY);
                quiz_1 = db.collection(englishCollection);
            }
            if (getIntent().hasExtra(ChoseQuizTypeBottomSheet.PHYSICS_QUIZ_KEY)) {
                quizNameTxt.setText("Add New ( Physic ) Quiz");
                String englishCollection = getIntent().getStringExtra(ChoseQuizTypeBottomSheet.PHYSICS_QUIZ_KEY);
                quiz_1 = db.collection(englishCollection);
            }
            if (getIntent().hasExtra(ChoseQuizTypeBottomSheet.MATH_QUIZ_KEY)) {
                quizNameTxt.setText("Add New ( Math ) Quiz");
                String englishCollection = getIntent().getStringExtra(ChoseQuizTypeBottomSheet.MATH_QUIZ_KEY);
                quiz_1 = db.collection(englishCollection);
            }

        }


        spin.setOnItemSelectedListener(AddQuizActivity.this);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, selectAnswer);

        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);

    }



    private void insertQuiz(View view) {

        if (!validateQuestionText() | !validateOption1Text() | !validateOption2Text() | !validateOption3Text() | !validateOption4Text()) {
            return;
        }

        showProgressBar();
        mFieldTextHasChanged = false;
        switch (answer) {
            case "Answer [ A ]":
                answer = "A.  " + option_1;
                break;
            case "Answer [ B ]":
                answer = "B.  " + option_2;
                break;
            case "Answer [ C ]":
                answer = "C.  " + option_3;
                break;
            case "Answer [ D ]":
                answer = "D.  " + option_4;
                break;
        }

        Quiz quiz = new Quiz("Q.  " + quiz_question, "A.  " + option_1, "B.  " + option_2, "C.  " + option_3, "D.  " + option_4, answer);
        quiz_1.document().set(quiz).addOnCompleteListener(new OnCompleteListener<Void>() {
            ConstraintLayout constraintLayout = findViewById(R.id.constraintLayoutAddQuiz);

            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    hideProgressBar();
                    Snackbar.make(constraintLayout, "Quiz Successfully Added", Snackbar.LENGTH_SHORT).show();
                } else if (task.getException() != null) {
                    hideProgressBar();
                    ConstraintLayout constraintLayout = findViewById(R.id.constraintLayoutAddQuiz);
                    Snackbar.make(constraintLayout, "Error", Snackbar.LENGTH_SHORT).show();

                }
            }
        });


    }



    private boolean validateQuestionText() {


        quiz_question = et_QuizQuestion.getText().toString().trim();

        if (quiz_question.isEmpty()) {
            tTLQuizQuestion.setError("Question is required. Can't be empty.");
            return false;
        } else {
            tTLQuizQuestion.setError(null);
            return true;
        }
    }

    private boolean validateOption1Text() {

        option_1 = et_Option1.getText().toString().trim();

        if (option_1.isEmpty()) {
            tILOption1.setError("Option 1 is required. Can't be empty.");
            return false;
        } else {
            tILOption1.setError(null);
            return true;
        }
    }

    private boolean validateOption2Text() {


        option_2 = et_Option2.getText().toString().trim();

        if (option_2.isEmpty()) {
            tILOption2.setError("Option 2 is required. Can't be empty.");
            return false;
        } else {
            tILOption2.setError(null);
            return true;
        }
    }

    private boolean validateOption3Text() {

        option_3 = et_Option3.getText().toString().trim();


        if (option_3.isEmpty()) {
            tILOption3.setError("Option 3 is required. Can't be empty.");
            return false;
        } else {
            tILOption3.setError(null);
            return true;
        }
    }

    private boolean validateOption4Text() {

        option_4 = et_Option4.getText().toString().trim();

        if (option_4.isEmpty()) {
            tILOption4.setError("Option 4 is required. Can't be empty.");
            return false;
        } else {
            tILOption4.setError(null);
            return true;
        }
    }


    @SuppressLint("ClickableViewAccessibility")
    private void initViews() {
        quizNameTxt = findViewById(R.id.quizName);
        tTLQuizQuestion = findViewById(R.id.tIL_quiz_question);
        tILOption1 = findViewById(R.id.tIL_option_1);
        tILOption2 = findViewById(R.id.tIL_option_2);
        tILOption3 = findViewById(R.id.tIL_option_3);
        tILOption4 = findViewById(R.id.tIL_option_4);
        spin = (Spinner) findViewById(R.id.spinner2);
        progressBar = findViewById(R.id.progressBar2);

        et_QuizQuestion = findViewById(R.id.et_quiz_question);
        et_Option1 = findViewById(R.id.et_option_1);
        et_Option2 = findViewById(R.id.et_option_2);
        et_Option3 = findViewById(R.id.et_option_3);
        et_Option4 = findViewById(R.id.et_option_4);

        et_QuizQuestion.setOnTouchListener(onTouchListener);
        et_Option1.setOnTouchListener(onTouchListener);
        et_Option2.setOnTouchListener(onTouchListener);
        et_Option3.setOnTouchListener(onTouchListener);
        et_Option4.setOnTouchListener(onTouchListener);

    }

    private void showProgressBar() {
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        answer = selectAnswer[i];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    void discardAndKeepEditingAlertDialog(DialogInterface.OnClickListener discardButtonClickListener){

        AlertDialog.Builder  builder = new AlertDialog.Builder(this);
        builder.setMessage("Discard your changes and quit editing?");
        builder.setPositiveButton("Discard", (DialogInterface.OnClickListener) discardButtonClickListener);
        builder.setNegativeButton("Keep Editing", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (dialogInterface != null){
                    dialogInterface.dismiss();
                }
            }
        });
        builder.create();
        builder.show();
    }

    @Override
    public void onBackPressed() {
        if (!mFieldTextHasChanged){
            super.onBackPressed();
            return;
        }
        DialogInterface.OnClickListener onDiscardClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        };
        discardAndKeepEditingAlertDialog((DialogInterface.OnClickListener) onDiscardClickListener);
    }
}