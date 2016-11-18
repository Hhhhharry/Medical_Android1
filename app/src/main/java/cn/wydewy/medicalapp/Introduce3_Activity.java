package cn.wydewy.medicalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.wydewy.medicalapp.util.Constant;

public class Introduce3_Activity extends AppCompatActivity {
    private MedicalApplication da;
    private JSONObject json;
    private TextView title,name,doctorname,special,sex,section,degree,major,phone,ins,intro,certif;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce3);
        getSupportActionBar().hide();

        Intent intent = getIntent(); //用于激活它的意图对象：这里的intent获得的是上个Activity传递的intent
        Bundle bundle = intent.getExtras();
        String item = bundle.getString("selectedItem");

        title = (TextView) findViewById(R.id.titleDoctor);
        name = (TextView) findViewById(R.id.doctorname);
        doctorname = (TextView) findViewById(R.id.doctordoctorname);
        special = (TextView) findViewById(R.id.doctorspecial);
        sex = (TextView) findViewById(R.id.doctorsex);
        section = (TextView) findViewById(R.id.doctorsection);
        degree = (TextView) findViewById(R.id.doctordegree);
        major = (TextView) findViewById(R.id.doctormajor);
        phone = (TextView) findViewById(R.id.doctorphone);
        ins = (TextView) findViewById(R.id.doctorins);
        intro = (TextView) findViewById(R.id.doctorintro);
        certif = (TextView) findViewById(R.id.doctor1certif);


        Map<String,String> map = new HashMap<>();
        map.put("MedicalApplication",item);

        CustomRequest jsonObjectRequest = new CustomRequest(Request.Method.POST, Constant.API_DOCTOR_DETAIL,map,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject json12) {

                        System.out.println(json12.optJSONObject("datum").toString());
                        json = json12.optJSONObject("datum");
                        setDoctorInformation();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });

        MedicalApplication.getInstance().addToRequestQueue(jsonObjectRequest);
    }

   private void setDoctorInformation() {

        title.setText(json.optString("doctorname").toString());
        name.setText(json.optString("doctorname").toString());
        doctorname.setText(json.optString("doctorname").toString());
        special.setText(json.optString("special").toString());
        sex.setText(json.optString("sex").toString());
        section.setText(json.optString("section").toString());
        degree.setText(json.optString("degree").toString());
        major.setText(json.optString("marjor").toString());
        phone.setText(json.optString("telephone").toString());
        ins.setText(json.optString("graduinstitution").toString());
        intro.setText(json.optString("doctorintro").toString());
        certif.setText(json.optString("certificatehold").toString());

    }
}
