package cn.wydewy.medicalapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends BaseActivity implements OnClickListener {
    //private LinearLayout tabHospital,tabMy;
    private TextView btnhospital, btnmy;
    private Fragment_hospital fHospital;
    private Fragment_my fMy;
    private FragmentManager fManager;
    private ImageView Imghospital,Imgmy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        Imghospital = (ImageView) findViewById(R.id.Fh_img_hospital);
        Imgmy = (ImageView) findViewById(R.id.Fh_img_my);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_tab1:
                setSelect(0);
                break;
            case R.id.id_tab2:
                setSelect(1);
                break;
        }
    }

    private void setSelect(int i) {
        FragmentTransaction transaction = fManager.beginTransaction();
        setNormal();
        hideFragment(transaction);
        switch (i) {
            case 0:

                if (fHospital == null) {
                    fHospital = new Fragment_hospital();
                    transaction.add(R.id.id_content, fHospital);
                } else {
                    transaction.show(fHospital);
                }
                Imghospital.setBackgroundResource(R.mipmap.hospital_press);
                break;
            case 1:

                if (fMy == null) {
                    fMy = new Fragment_my();
                    transaction.add(R.id.id_content, fMy);
                } else {
                    transaction.show(fMy);
                }
                Imgmy.setBackgroundResource(R.mipmap.mypress);
                break;
        }
        transaction.commit();
    }

    private void setNormal() {
        Imghospital.setBackgroundResource(R.mipmap.hospital);
        Imgmy.setBackgroundResource(R.mipmap.my);
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (fHospital != null) {
            transaction.hide(fHospital);
        }
        if (fMy != null) {
            transaction.hide(fMy);
        }
    }

    public void MyOnClick(View view) {
        switch (view.getId()) {
            case R.id.my_yyjl:
                Intent it = new Intent(this, OrderRecord_Activity.class);
                startActivity(it);
                break;
            case R.id.User_detail:
                Intent it2 = new Intent(this, UserDetail_Activity.class);
                startActivity(it2);
                break;
        }
    }

    public void ChangeOnClick(View view) {
        switch (view.getId()) {
            case R.id.item1:
                Intent it = new Intent(this, Introduce_Activity.class);
                startActivity(it);
                break;

            case R.id.item2:
                Intent it2 = new Intent(this, Guide_Activity.class);
                startActivity(it2);
                break;
            case R.id.item3:
                Intent it3 = new Intent(this, Section_Activity.class);
                startActivity(it3);
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

    private long time = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (System.currentTimeMillis() - time < 2000) {
                    finish();
                } else {
                    Toast.makeText(this, "再点一次退出", Toast.LENGTH_SHORT).show();
                    time = System.currentTimeMillis();
                }
                break;
        }
        return false;
    }
}

