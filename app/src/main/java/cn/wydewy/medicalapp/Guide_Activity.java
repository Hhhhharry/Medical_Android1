package cn.wydewy.medicalapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class Guide_Activity extends AppCompatActivity {

    private JSONObject json;
    private TextView website,bus,phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        getSupportActionBar().hide();

        website = (TextView) findViewById(R.id.website);
        bus = (TextView) findViewById(R.id.bus);
        phone = (TextView) findViewById(R.id.hospital_phone);

        String url = ((MedicalApplication)getApplication()).getUrlhead();
        String url1 = url + "framework/hospital/searchHosInfo";

        RequestQueue mqueue = Volley.newRequestQueue(this);
        CustomRequest jsonObjectRequest = new CustomRequest(url1, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        json = jsonObject.optJSONObject("datum");
                        website.setText(json.optString("hosurl").toString());
                        bus.setText(json.optString("hostraffic").toString());
                        phone.setText(json.optString("hostel").toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });
        mqueue.add(jsonObjectRequest);
    }
}
