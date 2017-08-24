package com.hbq.test.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/3/4.
 */
public class CheatActivity extends Activity {
    private boolean mAnswerIsTrue ;
    public static final String EXTRA_ANSWER_IS_TRUE = "com.hbq.test.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOW = "com.hbq.test.geoquiz.answer_show";
    private TextView mAnswerTextView ;
    private Button mButtonShowAnswer;
    private boolean  mSaveCheatRecord=false;
    private static final String CHEATRECORD="com.hbq.test.geoquiz.cheatrecord";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);
        mAnswerTextView=(TextView) findViewById(R.id.textview_answer);
        mButtonShowAnswer = (Button) findViewById(R.id.button_showanswer);

        if (savedInstanceState!=null)
        {
            mSaveCheatRecord = savedInstanceState.getBoolean(CHEATRECORD,true);
            mAnswerIsTrue = mSaveCheatRecord;
        }

        mButtonShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAnswerIsTrue){
                    mAnswerTextView.setText(R.string.true_button);
                }
                else {
                    mAnswerTextView.setText(R.string.False_button);
                }
                setAnswerShowResult(true);
            }
        });
    }

    private void setAnswerShowResult(boolean isAnswerShow){
        Intent data = new Intent();
        mSaveCheatRecord = isAnswerShow;
        data.putExtra(EXTRA_ANSWER_SHOW,isAnswerShow);
        setResult(RESULT_OK,data);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(CHEATRECORD,mSaveCheatRecord);
    }
}
