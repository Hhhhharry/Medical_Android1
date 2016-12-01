package cn.wydewy.medicalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.wydewy.medicalapp.model.Customer;
import cn.wydewy.medicalapp.model.Section;
import cn.wydewy.medicalapp.util.Constant;

public class Register_Activity extends BaseBackActivity {

    private EditText idcard,account,phonenumber,password;
    private Customer customer;
    private TextView top_title;
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
        top_title = (TextView) findViewById(R.id.top_title);

        top_title.setText("用户登录");
    }

    public void LoginOnClick(View view) {
            final String a = account.getText().toString();
            final String i = idcard.getText().toString();
            final String ph = phonenumber.getText().toString();
            final String pa = password.getText().toString();
            Map<String,String> map = new HashMap<>();
              map.put("account",a);

            //进行HTTP通信
            CustomRequest objectRequest = new CustomRequest(Request.Method.POST, Constant.API_CUSTOMER_USERNAME, map,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {

                            JSONObject json = jsonObject.optJSONObject("datum");
                            String Str = json.toString();
                            customer = JSON.parseObject(Str,new TypeReference<Customer>(){});
                            Log.d( "onResponse: ",customer.toString());
                            String i1 = json.optString("idcard").toString();
                            String ph1 = json.optString("phone").toString();
                            String pa1 = json.optString("password").toString();
                            System.out.println(pa);
                            if(i1.equals(i)&&ph1.equals(ph)&&pa1.equals(pa))
                            {
                                change();
                                ((MedicalApplication)getApplication()).setLog(true);
                                ((MedicalApplication)getApplication()).setAccount(a);    //存储已登录的用户名4
                                ((MedicalApplication)getApplication()).setCustomerId(customer.getCustomerId());
                                ((MedicalApplication)getApplication()).setCustomerName(customer.getCustomerName());

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
            MedicalApplication.getInstance().addToRequestQueue(objectRequest);

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

