package com.example.evgeniy.week7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.t1)
    Button t1;
    @BindView(R.id.t2)
    Button t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        t1.setOnClickListener(view -> showT1());
        t2.setOnClickListener(view -> showT2());
    }

    private void showT1() {

    }

    private void showT2() {

    }

}
