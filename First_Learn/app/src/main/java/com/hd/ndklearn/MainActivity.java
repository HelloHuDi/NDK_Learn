package com.hd.ndklearn;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirstNdkClass firstNdkClass = new FirstNdkClass();
        String str = firstNdkClass.getCLanguageString();
        String add = String.valueOf(firstNdkClass.addAandB(2, 4));//6
        String mul = String.valueOf(firstNdkClass.mulAandB(3.1f, 4.2f));//7.3
        ((TextView) findViewById(R.id.tvNdk)).setText(str + "===" + add + "===" + mul);
    }
}

