package cn.wydewy.medicalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class UserDetail_Activity extends BaseBackActivity {


    private TextView top_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        getSupportActionBar().hide();
        top_title = (TextView) findViewById(R.id.top_title);
        top_title.setText("个人信息");
    }
    public void MyOnClick(View view)
    {
            Intent it = new Intent(this,User_detail_Change.class);
            startActivity(it);
    }

    public void BackonClick(View view) {
        finish();
    }
    public void FristonClick(View view) {
        Intent it = new Intent(this, MainActivity.class);
        it.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(it);
        this.finish();
    }

}
