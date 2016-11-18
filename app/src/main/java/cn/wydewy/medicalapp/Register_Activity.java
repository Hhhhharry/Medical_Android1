package cn.wydewy.medicalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Register_Activity extends AppCompatActivity {

    private EditText idcard,account,phonenumber,password;


    private boolean flag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        account = (EditText) findViewById(R.id.account);
        idcard = (EditText) findViewById(R.id.idcard);
        phonenumber = (EditText) findViewById(R.id.phone_number);
        password = (EditText) findViewById(R.id.password);
    }

    public void LoginOnClick(View view) {
            String urlhead = ((MedicalApplication)getApplication()).getUrlhead();
            String url = urlhead + "framework/customer/loginVerifyByUserName";
            final String a = account.getText().toString();
            final String i = idcard.getText().toString();
            final String ph = phonenumber.getText().toString();
            final String pa = password.getText().toString();
            Map<String,String> map = new HashMap<>();
              map.put("account",a);
             JSONObject data1 = new JSONObject(map);
            //进行HTTP通信
            RequestQueue mqueue = Volley.newRequestQueue(this);
            System.out.println(data1.toString());
            CustomRequest objectRequest = new CustomRequest(Request.Method.POST, url, map,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            System.out.println(jsonObject.optJSONObject("datum").toString());
                            JSONObject json = jsonObject.optJSONObject("datum");
                            String i1 = json.optString("idcard").toString();
                            String ph1 = json.optString("phone").toString();
                            String pa1 = json.optString("password").toString();
                            System.out.println(pa);
                            if(i1.equals(i)&&ph1.equals(ph)&&pa1.equals(pa))
                            {
                                change();
                                ((MedicalApplication)getApplication()).setLog(true);
                                ((MedicalApplication)getApplication()).setAccount(a);    //存储已登录的用户名
                            }
                            else {
                                toast();
                            }
                        }

                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {

                }
            });
            mqueue.add(objectRequest);

    }
    public void LoginOnClick1(View view)
    {
        Intent it = new Intent(this, Register2_Activity.class);
        startActivity(it);
    }
    private void toast() {
        Toast toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        toast.setText("账号密码输入错误");
        toast.show();
    }

    private void change() {
        Intent it = new Intent(this, MainActivity.class);
        it.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(it);
        this.finish();
    }
}

