package com.cabana.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cabana.stepprogressbar.Step;
import com.cabana.stepprogressbar.StepProgressBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StepProgressBar ProgressBar = (StepProgressBar) findViewById(R.id.StepProgressBar);

        ArrayList<Step> steps = new ArrayList<>();
        steps.add(new Step(10,"第一阶段"));
        steps.add(new Step(20,"第二阶段"));
        steps.add(new Step(30,"第三阶段"));

        ProgressBar.initData(steps,10);
        ProgressBar.setprogress(35);
    }
}
