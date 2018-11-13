package com.braintrainer.aswin.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button playAgainButton;
    TextView timer,question,score;
    TextView resultText;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int a,b,locationOfCorrectAnswers;
    int scoreObtained=0,totalNumberOfQuestions=0;
    ConstraintLayout goLayout;
    ConstraintLayout startGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goButton = findViewById(R.id.goButton);
        startGame = findViewById(R.id.startOfGame);

        goLayout = findViewById(R.id.goLayout);
        goLayout.setVisibility(View.VISIBLE);
        startGame.setVisibility(View.INVISIBLE);

    }

    public void nextQuestion()
    {
        Random rand = new Random();

        a = rand.nextInt(41);
        b = rand.nextInt(41);
        locationOfCorrectAnswers = rand.nextInt(4);
        answers.clear();

        question.setText(Integer.toString(a)+"+"+Integer.toString(b));

        for(int i=0;i<4;i++)
        {
            if(i==locationOfCorrectAnswers)
                answers.add(a+b);
            else
            {
                int wrongNumber = rand.nextInt(41);
                while(wrongNumber==a+b)
                    wrongNumber = rand.nextInt(41);
                answers.add(wrongNumber);
            }
        }

        button1.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));
    }

    public void startGame(View view)
    {
        startGame.setVisibility(View.VISIBLE);
        goButton.setVisibility(View.INVISIBLE);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        playAgainButton = findViewById(R.id.playAgainButton);
        timer = findViewById(R.id.timerTextView);
        question = findViewById(R.id.questionTextView);
        score = findViewById(R.id.scoreTextView);
        resultText = findViewById(R.id.resultTextView);
        resultText.setText(" ");
        nextQuestion();
        setTimer();
    }

    public void onClickButton(View view)
    {
        String tag = view.getTag().toString();
        int intTag = Integer.parseInt(tag);
        resultText.setVisibility(View.VISIBLE);
        if(intTag==locationOfCorrectAnswers)
        {
            resultText.setText("Correct :)");
            scoreObtained++;
            totalNumberOfQuestions++;
        }
        else
        {
            resultText.setText("Wrong :(");
            totalNumberOfQuestions++;
        }

        score.setText(Integer.toString(scoreObtained)+" / "+Integer.toString(totalNumberOfQuestions));
        nextQuestion();

    }

    public void setTimer()
    {
        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf((millisUntilFinished/1000)+"s"));
            }

            @Override
            public void onFinish() {
                timer.setText(String.valueOf(0)+"s");
                resultText.setText("Done !!");
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void playAgain(View view)
    {
        scoreObtained = 0;
        totalNumberOfQuestions = 0;
        resultText.setVisibility(View.INVISIBLE);
        timer.setText("30s");
        score.setText(Integer.toString(scoreObtained)+" / "+Integer.toString(totalNumberOfQuestions));
        playAgainButton.setVisibility(View.INVISIBLE);
        goButton.setVisibility(View.VISIBLE);
        startGame.setVisibility(View.INVISIBLE);
    }
}
