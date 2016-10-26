package cn.wydewy.medicalapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register_Activity extends AppCompatActivity {

    private EditText name,account,phonenumber,password;


    private data da;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        da = (data) getApplication();
        name = (EditText) findViewById(R.id.name);
        account = (EditText) findViewById(R.id.account);
        phonenumber = (EditText) findViewById(R.id.phone_number);
        password = (EditText) findViewById(R.id.password);

    }

    public void LoginOnClick(View view) {
            String h = account.getText().toString();
            if (h.equals("harry")) {
                Toast toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
                toast.setText(account.getText().toString());
                da.setLog(true);
                toast.show();
                Intent it = new Intent(this, MainActivity.class);
                startActivity(it);
            }
    }
}

