package com.example.android.quizfirestore;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    public static final String ENGLISH_KEY = "english_key";
    public static final String PHYSIC_KEY = "physic_key";
    public static final String MATH_KEY = "math_key";

    CardView cv_english,cv_physics,cv_math;
    ChoseQuizTypeBottomSheet bottomSheet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        findViewById(R.id.fab).setOnClickListener(view -> {

            bottomSheet = new ChoseQuizTypeBottomSheet();
            bottomSheet.show(getSupportFragmentManager(), "choseQuizTypeBottomSheet");
//            startActivity(new Intent(this, AddQuizActivity.class));
        });

        cv_english.setOnClickListener(view -> {
            startActivity(new Intent(this, QuizessActivity.class).putExtra(ENGLISH_KEY,ChoseQuizTypeBottomSheet.ENGLISH_QUIZ_COLLECTION));
        });
        cv_physics.setOnClickListener(view -> {
            startActivity(new Intent(this, QuizessActivity.class).putExtra(PHYSIC_KEY,ChoseQuizTypeBottomSheet.PHYSICS_QUIZ_COLLECTION));

        });
        cv_math.setOnClickListener(view -> {
            startActivity(new Intent(this, QuizessActivity.class).putExtra(MATH_KEY,ChoseQuizTypeBottomSheet.MATH_QUIZ_COLLECTION));
        });
    }

    private void initViews() {
        cv_english = findViewById(R.id.cv_english);
        cv_physics = findViewById(R.id.cv_physics);
        cv_math = findViewById(R.id.cv_math);
    }









}