package com.cabana.stepprogressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by work on 2016-5-25.
 */
public class StepProgressBar extends LinearLayout {

    private Context mContext;
    private ArrayList<Step> mSteps;
    private boolean mToggle = false; //宽度按权重自适应开关，默认关闭按阶段平分

    public StepProgressBar(Context context) {
        super(context);
    }

    public StepProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        setOrientation(HORIZONTAL);
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.StepProgressBar, 0, 0);
        boolean b = a.hasValue(R.styleable.StepProgressBar_self_adaptation);
        if(b){
            mToggle = a.getBoolean(R.styleable.StepProgressBar_self_adaptation,false);
        }
    }

    public void initData(ArrayList<Step> steps) {
        initData(steps,14);
    }

    public void initData(ArrayList<Step> steps,int textViewSize) {
        mSteps = steps;
        for (int i = 0; i < steps.size(); i++) {
            if (i == 0) {
                addProgressBar(R.layout.item_start_step_progress_bar, steps.get(i),textViewSize);
                continue;
            }
            if (i == steps.size() - 1) {
                addProgressBar(R.layout.item_end_step_progress_bar, steps.get(i),textViewSize);
                continue;
            }
            addProgressBar(R.layout.item_middle_step_progress_bar, steps.get(i),textViewSize);
        }
    }

    private void addProgressBar(int layout, Step rule, int textViewSize) {
        View ProgressBar = View.inflate(mContext, layout, null);
        ProgressBar progressBar = (ProgressBar) ProgressBar.findViewById(R.id.pb_progressbar);
        TextView tvNode = (TextView) ProgressBar.findViewById(R.id.tv_node);
        progressBar.setMax(rule.getmLimit());
        tvNode.setText(rule.getNode());
        tvNode.setTextSize(textViewSize);

        LayoutParams params = new LayoutParams(0, LayoutParams.WRAP_CONTENT);

        if (mToggle) {
            params.weight = rule.getmLimit();
        } else {
            params.weight = 1;
        }

        addView(ProgressBar, params);
    }

    public void setprogress(int progress) {
        for (int i = 0; i < mSteps.size(); i++) {
            int container = 0;
            for (int j = 0; j <= i; j++) {
                container += mSteps.get(j).getmLimit();
            }
            if (container >= progress) {
                int subProgress = 0;
                for (int k = 0; k <= i; k++) {
                    View UsedChild = getChildAt(k);
                    ProgressBar usedProgressbar = (ProgressBar) UsedChild.findViewById(R.id.pb_progressbar);
                    if (k == i) {
                        usedProgressbar.setProgress(progress - subProgress);
                        for (int l = k + 1; l < mSteps.size(); l++) {
                            View UnusedChild = getChildAt(l);
                            ProgressBar UnusedProgressbar = (ProgressBar) UnusedChild.findViewById(R.id.pb_progressbar);
                            UnusedProgressbar.setProgress(0);
                        }
                        return;
                    }
                    int max = usedProgressbar.getMax();
                    usedProgressbar.setProgress(max);
                    subProgress += max;
                }
            }
        }
    }

    public void setWidthSelfAdaptation(boolean toggle) {
        mToggle = toggle;
    }

    public String getCurrentStep(int CurProgress) {
        String curPrice = null;
        for (int i = 0; i < mSteps.size(); i++) {
            int SubProgress = 0;
            for (int j = 0; j < i; j++) {
                SubProgress += mSteps.get(j).getmLimit();
                if (SubProgress >= CurProgress)
                    curPrice = mSteps.get(j).getNode();
            }
        }
        return curPrice;
    }


}
