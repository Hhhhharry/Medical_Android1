package cn.wydewy.medicalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class Confirm_Activity extends BaseBackActivity {


    private String outpatientname,userid,username,useridcard,userphone;
    private String doctorname,ampm,date,price,doctorid,releasenumid;
    private TextView doctortext,sectiontext,outpatienttext,ampmtext,datetext,pricetext,customertext,top_title;
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
        doctorid = bundle.getString(Constant.Doctor_Id);
        releasenumid = bundle.getString(Constant.Rleasenum_Id);
        price = bundle.getString(Constant.Order_Price);

        username = ((MedicalApplication)getApplication()).getCustomerName();

        doctortext = (TextView) findViewById(R.id.Confrim_Activity_doctor);
        outpatienttext = (TextView) findViewById(R.id.Confrim_Activity_outpatient);
        ampmtext = (TextView) findViewById(R.id.Confrim_Activity_ampm);
        datetext = (TextView) findViewById(R.id.Confrim_Activity_date);
        pricetext = (TextView) findViewById(R.id.Confrim_Activity_price);
        customertext = (TextView) findViewById(R.id.Confrim_Activity_person);
        top_title = (TextView) findViewById(R.id.top_title);

        doctortext.setText(doctorname);
        outpatienttext.setText(outpatientname);
        ampmtext.setText(ampm);
        datetext.setText(date);
        pricetext.setText(price);
        customertext.setText(username);
        top_title.setText("预约信息");
    }
    public void ConfirmOnClick(View view)
    {
        Map<String,String> map = new HashMap<>();
        map.put("customerId",((MedicalApplication)getApplication()).getCustomerId());
        map.put("doctorId",doctorid);
        map.put("releasenumid",releasenumid);
        map.put(Constant.OutPatient_Name,outpatientname);
        map.put(Constant.Order_Price,price);
        map.put(Constant.Order_Date,date);
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
        Toast toast = Toast.makeText(this,"挂号成功！",Toast.LENGTH_SHORT);
        toast.show();
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
