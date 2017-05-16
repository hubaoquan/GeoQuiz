package com.hbq.test.geoquiz;

/**
 * Created by Administrator on 2017/3/4.
 */
public class TrueFalse {
    private  int  mQuestion;
    private boolean mTrueQuenstion;

    public TrueFalse(int mQuestion, boolean mTrueQuenstion) {
        this.mQuestion = mQuestion;
        this.mTrueQuenstion = mTrueQuenstion;
    }

    public void setQuestion(int question) {
        mQuestion = question;
    }

    public void setTrueQuenstion(boolean trueQuenstion) {
        mTrueQuenstion = trueQuenstion;
    }

    public int getQuestion() {
        return mQuestion;
    }

    public boolean isTrueQuenstion() {
        return mTrueQuenstion;
    }
}
