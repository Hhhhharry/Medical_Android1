package cn.wydewy.medicalapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Guide_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        getSupportActionBar().hide();
    }
}
