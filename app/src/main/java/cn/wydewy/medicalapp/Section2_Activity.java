package cn.wydewy.medicalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

public class Section2_Activity extends BaseBackActivity {

    private JSONObject json;
    private TextView title,detail,post,location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section2);
        getSupportActionBar().hide();

        Intent intent = getIntent(); //用于激活它的意图对象：这里的intent获得的是上个Activity传递的intent
        Bundle bundle = intent.getExtras();
        String item = bundle.getString("selectedItem");

        location = (TextView) findViewById(R.id.section2_location);
        title = (TextView) findViewById(R.id.top_title);
        post = (TextView) findViewById(R.id.section2_youbian);
        detail = (TextView) findViewById(R.id.section2_detail);

        Map<String,String> map = new HashMap<>();
        map.put("MedicalApplication",item);
        RequestQueue mqueue = Volley.newRequestQueue(this);
        CustomRequest jsonObjectRequest = new CustomRequest(Request.Method.POST, Constant.API_HOSPITAL_SECTION_DETAIL,map,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject json12) {

                        System.out.println(json12.optJSONObject("datum").toString());
                        json = json12.optJSONObject("datum");
                        setSectionInformation();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });

        mqueue.add(jsonObjectRequest);
    }

    private void setSectionInformation() {

        title.setText(json.optString("sectionname").toString());
        detail.setText(json.optString("sectionintro").toString());
        post.setText(json.optString("sectioncode").toString());
        location.setText(json.optString("sectionloc").toString());
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
