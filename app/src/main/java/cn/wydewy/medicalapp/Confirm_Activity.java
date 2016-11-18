package cn.wydewy.medicalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.wydewy.medicalapp.util.Constant;

public class Confirm_Activity extends AppCompatActivity {


    private String outpatientname,userid;
    private String doctorname,ampm,date,price;
    private TextView doctortext,sectiontext,outpatienttext,ampmtext,datetext,pricetext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        doctorname = bundle.getString(Constant.Doctor_Name);
        outpatientname = bundle.getString(Constant.OutPatient_Name);
        ampm = bundle.getString(Constant.Order_AMPM);
        date = bundle.getString(Constant.Order_Date);
        price = bundle.getString(Constant.Order_Price);

        doctortext = (TextView) findViewById(R.id.Confrim_Activity_doctor);
        outpatienttext = (TextView) findViewById(R.id.Confrim_Activity_outpatient);
        ampmtext = (TextView) findViewById(R.id.Confrim_Activity_ampm);
        datetext = (TextView) findViewById(R.id.Confrim_Activity_date);
        pricetext = (TextView) findViewById(R.id.Confrim_Activity_price);

        doctortext.setText(doctorname);
        outpatienttext.setText(outpatientname);
        ampmtext.setText(ampm);
        datetext.setText(date);
        pricetext.setText(price);
    }
    public void ConfirmOnClick()
    {
        Map<String,String> map = new HashMap<>();
        map.put("userid",userid);
        CustomRequest objectRequest = new CustomRequest(Request.Method.POST, Constant.API_RELEASE_INSERT, map,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.d("TAG_response",jsonObject.toString());
                        Change();

                    }

                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.d("TAG-error", volleyError.toString());
                    }
                }
        );
        MedicalApplication.getInstance().addToRequestQueue(objectRequest);

    }

    private void Change() {
        if (true)
        {
            Intent it = new Intent(Confirm_Activity.this,MainActivity.class);
            startActivity(it);
        }
        Toast toast = Toast.makeText(this,"注册成功！",Toast.LENGTH_SHORT);
        toast.show();
    }

}
