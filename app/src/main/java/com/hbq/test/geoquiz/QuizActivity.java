package com.hbq.test.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends Activity {
    private ImageButton mTrueButton;
    private ImageButton mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPreButton;
    private Button mCheatButton;
    private boolean mCheater;
    private static final String KEY_CURRENTINDEX = "currentindex";

    private TextView mQuenstionTextView;
    private TrueFalse[] mQuenstionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_oceans,true),
            new TrueFalse(R.string.quenston_mideast,false),
            new TrueFalse(R.string.quenstion_africa,false),
            new TrueFalse(R.string.quenstion_americas,true),
            new TrueFalse(R.string.quenstion_asia,true)
    };

    private int mCurrentIndex=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState!=null)
        {
            mCurrentIndex = savedInstanceState.getInt(KEY_CURRENTINDEX,0);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mQuenstionTextView = (TextView) findViewById(R.id.quenstion_text);
        mTrueButton = (ImageButton) findViewById(R.id.true_button);
        mFalseButton = (ImageButton) findViewById(R.id.false_button);
        mNextButton = (ImageButton)findViewById(R.id.next_button);
        mPreButton = (ImageButton)findViewById(R.id.pre_button);
        mCheatButton = (Button) findViewById(R.id.buttoncheat);


        mQuenstionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateQuenstion();
            }
        });
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                      checkAnswer(true);
            }
        });
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                       checkAnswer(false);
            }
        });
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex+1)%mQuenstionBank.length;
                mCheater = false;
                updateQuenstion();
            }
        });

        mPreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentIndex==0)
                {
                    int quenstion = mQuenstionBank[mQuenstionBank.length-1].getQuestion();
                    mQuenstionTextView.setText(quenstion);//通过
                    mQuenstionTextView.setText(quenstion);//通过
                    mCurrentIndex = mQuenstionBank.length-1;
                }
                else {
                    mCurrentIndex = (mCurrentIndex-1)%mQuenstionBank.length;
                    int quenstion = mQuenstionBank[mCurrentIndex].getQuestion();


                    mQuenstionTextView.setText(quenstion);//通过
                }


            }
        });

        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(QuizActivity.this,CheatActivity.class);
                boolean answerIsTrue = mQuenstionBank[mCurrentIndex].isTrueQuenstion();
                i.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE,answerIsTrue);
                startActivityForResult(i,0);
            }
        });
        updateQuenstion();
    }

    private void updateQuenstion(){

        int quenstion = mQuenstionBank[mCurrentIndex].getQuestion();
        mQuenstionTextView.setText(quenstion);//通过
    }
    private  void checkAnswer(boolean userPressed)
    {
        boolean answerOfQuenstion = mQuenstionBank[mCurrentIndex].isTrueQuenstion();
        int messageResId = 0;
        if(mCheater){
            messageResId = R.string.judgment_toast;
        }
        else {
            if(userPressed==answerOfQuenstion){
                messageResId =R.string.correct_tost;
            }
            else {
                messageResId = R.string.incorrect_tost;
            }
        }

        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_CURRENTINDEX,mCurrentIndex);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data==null) return;
        mCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOW,false);
    }
}
