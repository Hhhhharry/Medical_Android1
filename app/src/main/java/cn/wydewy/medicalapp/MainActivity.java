package cn.wydewy.medicalapp;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements OnClickListener {
    //private LinearLayout tabHospital,tabMy;
    private TextView btnhospital, btnmy;
    private Fragment_hospital fHospital;
    private Fragment_my fMy;
    private FragmentManager fManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        initView();
        initEvent();
        fManager = getSupportFragmentManager();
        setSelect(0);
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
}

