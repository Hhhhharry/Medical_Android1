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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Order3_Activity extends AppCompatActivity {

    private String[] items = new String[]{"门诊一","门诊二"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order3);
        getSupportActionBar().hide();

        Intent intent = getIntent(); //用于激活它的意图对象：这里的intent获得的是上个Activity传递的intent
        Bundle bundle = intent.getExtras();
        String item = bundle.getString("selectedItem");

        String url = "";
        Map<String,String> map = new HashMap<>();
        map.put("item","");
        JSONObject data = new JSONObject(map);     //传值

        RequestQueue mqueu = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url,data,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        jsonObject.optString("data");    //这里要得到后台传来的门诊数和各个门诊的名字
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });



        ListView introlist = (ListView) findViewById(R.id.introduce_list3);
        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return 2;
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
                text.setText(items[position]);
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
                bundle.putString("selectedItem",items[position]);
                it.putExtras(bundle);
                startActivityForResult(it,0);
            }
        });
    }
}
