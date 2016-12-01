package cn.wydewy.medicalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.wydewy.medicalapp.util.Constant;

public class Introduce2_Activity extends BaseBackActivity {

    private MedicalApplication da;
    private JSONArray json;
    private TextView title;
    private String item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce2);
        getSupportActionBar().hide();

        Intent intent = getIntent(); //用于激活它的意图对象：这里的intent获得的是上个Activity传递的intent
        Bundle bundle = intent.getExtras();
        item = bundle.getString("selectedItem");
        title = (TextView) findViewById(R.id.top_title);


        Map<String,String> map = new HashMap<>();
        map.put("MedicalApplication",item);                                   //传值 outpatientName



        CustomRequest jsonObjectRequest = new CustomRequest(Request.Method.POST, Constant.API_SECTION_DOCTOR_LIST,map,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        json = jsonObject.optJSONArray("datum");
                        Log.d("datum",json.toString());
                        setDoctor();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });

        MedicalApplication.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    private void setDoctor() {
        title.setText(item);
        ListView introlist = (ListView) findViewById(R.id.introduce2_list2);
        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return json.length();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView text = new TextView(Introduce2_Activity.this);
                JSONObject json1 = json.optJSONObject(position);
                text.setText(json1.optString("doctorname").toString());
                text.setPadding(50,50,0,50);
                text.setTextColor(android.graphics.Color.rgb(0,0,0));
                text.setTextSize(15);
                return text;
            }
        };
        introlist.setAdapter(adapter);
        introlist.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(Introduce2_Activity.this,Introduce3_Activity.class);
                Bundle bundle = new Bundle();
                bundle.putString("selectedItem",json.optJSONObject(position).optString("doctorid").toString());
                it.putExtras(bundle);
                startActivityForResult(it,0);
            }
        });
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

