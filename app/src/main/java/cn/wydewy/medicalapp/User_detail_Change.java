package cn.wydewy.medicalapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class User_detail_Change extends BaseBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail_change);
        getSupportActionBar().hide();
    }
}
