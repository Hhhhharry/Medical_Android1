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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Register_Activity extends AppCompatActivity {

    private EditText name,account,phonenumber,password;


    private data da;
    boolean flag = false;
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
            String url = "http://192.168.1.140:8080/framework/customer/selectByCustomerName";
            flag = false;
            final String n = name.getText().toString();
            final String a = account.getText().toString();
            final String ph = phonenumber.getText().toString();
            final String pa = password.getText().toString();
            Map<String,String> map = new HashMap<>();
              map.put("account",account.getText().toString());
             JSONObject data1 = new JSONObject(map);
            //进行HTTP通信
            RequestQueue mqueue = Volley.newRequestQueue(this);
            System.out.println(data1.toString());
            JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, url, data1,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            System.out.println(jsonObject.toString());
                            if(jsonObject.optString("name").equals(n)&&jsonObject.optString("customerId").equals(a)&&jsonObject.optString("idCard").equals(ph)&&jsonObject.optString("password").equals(pa))
                            {
                                flag = true;
                                da.setLog(true);
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {

                }
            });
            mqueue.add(objectRequest);

            if (flag) {
                Intent it = new Intent(this, MainActivity.class);
                startActivity(it);
            }
            else {
                Toast toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
                 toast.setText("账号密码输入错误");
                 toast.show();
            }



    }


}

