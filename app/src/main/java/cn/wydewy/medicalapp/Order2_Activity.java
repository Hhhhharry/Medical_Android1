package cn.wydewy.medicalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import static cn.wydewy.medicalapp.R.id.activity_order2;

public class Order2_Activity extends AppCompatActivity {
    private String[] items = new String[]{"儿科","内科","妇产科","血科","保健科","皮肤科","外科","眼科","儿童科","肾内科","妇产科","血科"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order2);
        getSupportActionBar().hide();

        String url = "";
        RequestQueue mqueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                                                                                    //得到后台数据
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });


        ListView introlist = (ListView) findViewById(R.id.introduce_list2);
        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return 12;
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
                TextView text = new TextView(Order2_Activity.this);
                text.setText(items[position]);
                text.setPadding(50,50,0,50);
                text.setTextColor(android.graphics.Color.rgb(0,0,0));
                text.setTextSize(15);
                return text;
            }
        };
        introlist.setAdapter(adapter);
        introlist.setOnItemClickListener(new OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(Order2_Activity.this,Order3_Activity.class);
                Bundle bundle = new Bundle();
                bundle.putString("selectedItem",items[position]);
                it.putExtras(bundle);
                startActivityForResult(it,0);
            }
        });

    }
}
