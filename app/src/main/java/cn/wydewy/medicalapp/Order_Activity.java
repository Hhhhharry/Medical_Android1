package cn.wydewy.medicalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class Order_Activity extends AppCompatActivity implements OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        getSupportActionBar().hide();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.register:
                if(((MedicalApplication)getApplication()).isLog()){
                    //如果已经登录进行跳转
                    change();
                }else{
                    Intent it = new Intent(this, Register_Activity.class);
                    startActivity(it);
                }


                break;
            case R.id.order2:
                Intent it1 = new Intent(this, Order2_Activity.class);
                startActivity(it1);
                break;

        }
    }

    private void change() {
        Intent it = new Intent(this, MainActivity.class);
        it.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(it);
        this.finish();
    }
}
