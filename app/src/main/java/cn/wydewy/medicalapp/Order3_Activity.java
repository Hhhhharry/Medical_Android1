package cn.wydewy.medicalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Order3_Activity extends AppCompatActivity {


    private JSONArray json;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order3);
        getSupportActionBar().hide();

        Intent intent = getIntent(); //用于激活它的意图对象：这里的intent获得的是上个Activity传递的intent
        Bundle bundle = intent.getExtras();
        String item = bundle.getString("selectedItem");

        String url = "http://192.168.1.132:8080/framework/outpatient/selectByExample";
        Map<String,String> map = new HashMap<>();
        map.put("data",item);                                   //传值 outpatientName
        RequestQueue mqueue = Volley.newRequestQueue(this);
        CustomRequest jsonObjectRequest = new CustomRequest(Request.Method.POST,url,map,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        json = jsonObject.optJSONArray("datum");        //这里要得到后台传来的门诊数和各个门诊的名字
                        System.out.println(json.toString());
                        setOutpatientName();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });

        mqueue.add(jsonObjectRequest);



    }

    private void setOutpatientName() {
        ListView introlist = (ListView) findViewById(R.id.introduce_list3);
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
                TextView text = new TextView(Order3_Activity.this);
                text.setText(json.optJSONObject(position).optString("outpatientname").toString());
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
                Intent it = new Intent(Order3_Activity.this,Confirm_Activity.class);
                Bundle bundle = new Bundle();
                bundle.putString("selectedItem",json.optJSONObject(position).optString("outpatientName").toString());
                it.putExtras(bundle);
                startActivityForResult(it,0);
            }
        });
    }
}
