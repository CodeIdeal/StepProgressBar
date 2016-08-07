package com.cabana.stepprogressbar;


/**
 * Created by work on 2016-5-27.
 */
public class Step {

    private int mLimit;
    private String node;

    public Step(int mLimit, String node) {
        this.setmLimit(mLimit);
        this.setNode(node);
    }

    public int getmLimit() {
        return mLimit;
    }

    public void setmLimit(int mLimit) {
        this.mLimit = mLimit;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }
}