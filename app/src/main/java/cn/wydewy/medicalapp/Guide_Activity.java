package cn.wydewy.medicalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class Guide_Activity extends BaseBackActivity {

    private JSONObject json;
    private TextView website,bus,phone,top_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        getSupportActionBar().hide();

        website = (TextView) findViewById(R.id.website);
        bus = (TextView) findViewById(R.id.bus);
        phone = (TextView) findViewById(R.id.hospital_phone);
        top_title = (TextView) findViewById(R.id.top_title);

        top_title.setText("医院导航");

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
