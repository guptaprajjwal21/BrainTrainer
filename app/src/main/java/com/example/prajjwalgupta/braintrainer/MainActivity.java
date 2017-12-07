package com.example.prajjwalgupta.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btngo,btn1,btn2,btn3,btn4,playagain;
    TextView questextview,restv,scoretv,timertv;
    int locationOfCorrectAnswer;
  int numberofQues=0;
    RelativeLayout rl;
    int score=0;
    ArrayList<Integer> answers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btngo=(Button)findViewById(R.id.btngo);
        questextview=(TextView)findViewById(R.id.questextView);
        btn1=(Button)findViewById(R.id.btn1);
        btn2=(Button)findViewById(R.id.btn2);
        btn3=(Button)findViewById(R.id.btn3);
        btn4=(Button)findViewById(R.id.btn4);
        playagain=(Button)findViewById(R.id.btnpg);
        rl=(RelativeLayout)findViewById(R.id.rl);
        answers=new ArrayList<>();
        timertv=(TextView)findViewById(R.id.timerTextView);
        restv=(TextView)findViewById(R.id.resTextView);
        scoretv=(TextView)findViewById(R.id.scoreTextView);
        btngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btngo.setVisibility(View.INVISIBLE);
                rl.setVisibility(View.VISIBLE);

                playagain.setVisibility(View.INVISIBLE);



          generateQuestion();
        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long millisUntilFinished) {

                timertv.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {

                timertv.setText("0s");
                restv.setText("Your score is:"+Integer.toString(score)+"/"+Integer.toString(numberofQues));
                playagain.setVisibility(View.VISIBLE);
                playagain.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        score=0;
                        numberofQues=0;
                        scoretv.setText("0/0");
                        restv.setText("");
                        timertv.setText("30s");
                         playagain.setVisibility(View.INVISIBLE);

                        generateQuestion();
                        new CountDownTimer(30100,1000) {

                            @Override
                            public void onTick(long millisUntilFinished) {

                                timertv.setText(String.valueOf(millisUntilFinished / 1000) + "s");
                            }

                            @Override
                            public void onFinish() {
                                timertv.setText("0s");
                                restv.setText("Your score is:"+Integer.toString(score)+"/"+Integer.toString(numberofQues));

                                playagain.setVisibility(View.VISIBLE);

                            }


                        }.start();

                    }
                });


            }
        }.start();
            }
        });

    }
    public void choosenanswer(View view)
    {
      //  Log.i("Tag", (String) view.getTag()) ;

        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer)))
        {
          //  Log.i("correct","correct");
            score++;
             restv.setText("Correct!");



        }
        else
        {
            restv.setText("Wrong!");
        }

        numberofQues++;

        scoretv.setText(Integer.toString(score)+"/"+Integer.toString(numberofQues));
        generateQuestion();

    }

    public void generateQuestion()
    {
        Random rand=new Random();

        int a=rand.nextInt(21);
        int b=rand.nextInt(21);
        questextview.setText(Integer.toString(a)+ " + "+Integer.toString(b));

        answers.clear();


        locationOfCorrectAnswer=rand.nextInt(4);

        int incorrectans;

        for(int i=0;i<4;i++)
        {
            if(i==locationOfCorrectAnswer)
            {
                answers.add(a+b);
            }

            else
            {
                incorrectans=rand.nextInt(41);
                while(incorrectans == (a+b))
                {
                    incorrectans=rand.nextInt(41);
                }
                answers.add(incorrectans);
            }

        }


        btn1.setText(Integer.toString(answers.get(0)));
        btn2.setText(Integer.toString(answers.get(1)));
        btn3.setText(Integer.toString(answers.get(2)));
        btn4.setText(Integer.toString(answers.get(3)));


    }

}
