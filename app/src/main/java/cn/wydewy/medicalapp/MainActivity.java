package cn.wydewy.medicalapp;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import static cn.wydewy.medicalapp.R.id.date;


public class MainActivity extends AppCompatActivity implements OnClickListener {
    //private LinearLayout tabHospital,tabMy;
    private TextView btnhospital, btnmy;
    private Fragment_hospital fHospital;
    private Fragment_my fMy;
    private FragmentManager fManager;
    private data da;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        initView();
        initEvent();
        fManager = getSupportFragmentManager();

        da = (data) getApplication();
        if(!da.isLog())
            setSelect(0);
        else
            setSelect(1);

    }
    private void initEvent() {
        btnhospital.setOnClickListener(this);
        btnmy.setOnClickListener(this);
    }

    private void initView() {
        btnhospital = (TextView) findViewById(R.id.btn1);
        btnmy = (TextView) findViewById(R.id.btn2);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                setSelect(0);
                break;
            case R.id.btn2:
                setSelect(1);
                break;
        }
    }

    private void setSelect(int i) {
        FragmentTransaction transaction = fManager.beginTransaction();
        hideFragment(transaction);
        switch (i) {
            case 0:
                if (fHospital == null) {
                    fHospital = new Fragment_hospital();
                    transaction.add(R.id.id_content, fHospital);
                } else {
                    transaction.show(fHospital);
                }
                break;
            case 1:
                if (fMy == null) {
                    fMy = new Fragment_my();
                    transaction.add(R.id.id_content, fMy);
                } else {
                    transaction.show(fMy);
                }
                break;
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (fHospital != null) {
            transaction.hide(fHospital);
        }
        if (fMy != null) {
            transaction.hide(fMy);
        }
    }
    public void ChangeOnClick(View view)
    {
        switch (view.getId())
        {
            case R.id.item1:
                Intent it = new Intent(this, Introduce_Activity.class);
                startActivity(it);
                break;

            case R.id.item2:
                Intent it2 = new Intent(this, Guide_Activity.class);
                startActivity(it2);
                break;
            case R.id.item4:
                Intent it4 = new Intent(this, Schedule_Activity.class);
                startActivity(it4);
                break;
            case R.id.item5:
                Intent it5 = new Intent(this, Order_Activity.class);
                startActivity(it5);
                break;
        }
    }

}

