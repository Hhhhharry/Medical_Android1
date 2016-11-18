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

public class Register2_Activity extends AppCompatActivity {
    private EditText idcard,account,phonenumber,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        getSupportActionBar().hide();
        account = (EditText) findViewById(R.id.account1);
        idcard = (EditText) findViewById(R.id.idcard1);
        phonenumber = (EditText) findViewById(R.id.phone_number1);
        password = (EditText) findViewById(R.id.password1);
    }
    public void LoginOnClick(View view)
    {
        String urlhead = ((MedicalApplication)getApplication()).getUrlhead();
        String url = urlhead + "framework/customer/register";
        final String a = account.getText().toString();
        final String i = idcard.getText().toString();
        final String ph = phonenumber.getText().toString();
        final String pa = password.getText().toString();
        Map<String,String> map = new HashMap<>();
        map.put("account",a);
        map.put("idCard",i);
        map.put("phone",ph);
        map.put("password",pa);
        JSONObject data1 = new JSONObject(map);
        //进行HTTP通信
        RequestQueue mqueue = Volley.newRequestQueue(this);
        System.out.println(data1.toString());
        CustomRequest objectRequest = new CustomRequest(Request.Method.POST, url, map,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                        if(jsonObject.optBoolean("result"))
                        {
                            change();
                        }
                        else
                        {
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
    private void change() {
        Intent it = new Intent(this, Register_Activity.class);
        startActivity(it);
    }
    private void toast() {
        Toast toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        toast.setText("注册失败！！");
        toast.show();
    }
}
