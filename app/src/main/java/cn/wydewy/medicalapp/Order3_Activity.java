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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.wydewy.medicalapp.model.OutPatient;
import cn.wydewy.medicalapp.model.Section;
import cn.wydewy.medicalapp.util.Constant;

public class Order3_Activity extends AppCompatActivity {



    private List<OutPatient> items = new ArrayList<>();
    private BaseAdapter adapter;
    private String item;
    private String sectionname;
   // private JSONArray json;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order3);
        getSupportActionBar().hide();

        Intent intent = getIntent(); //用于激活它的意图对象：这里的intent获得的是上个Activity传递的intent
        Bundle bundle = intent.getExtras();

        item = bundle.getString(Constant.Section_ID);
        sectionname = bundle.getString(Constant.Section_Name);

        TextView title = (TextView) findViewById(R.id.Order3_Activity_Title);
        title.setText(sectionname);

        initListview();

        setOutpatientName();




    }

    private void initListview() {

        ListView introlist = (ListView) findViewById(R.id.introduce_list3);
        adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return items.size();
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
                text.setText(items.get(position).getOutpatientName());
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
                Intent it = new Intent(Order3_Activity.this,Schedule_Activity.class);
                Bundle bundle = new Bundle();
                bundle.putString("selectedItem",items.get(position).getOutpatientId().toString());
             //   bundle.putString(Constant.Section_Name,sectionname);
                bundle.putString(Constant.OutPatient_Name,items.get(position).getOutpatientName().toString());
                it.putExtras(bundle);
                startActivityForResult(it,0);
            }
        });
    }

    private void setOutpatientName() {


        Map<String,String> map = new HashMap<>();
        map.put("data",item);                                   //传值 outpatientName
        CustomRequest jsonObjectRequest = new CustomRequest(Request.Method.POST,Constant.API_OUTPATIENT_LIST,map,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                        Log.d("response",jsonObject.toString());
                        String itemsStr = null;
                        try {
                            itemsStr = jsonObject.get("datum").toString();
                            items = JSON.parseObject(itemsStr,new TypeReference<List<OutPatient>>(){});
                            adapter.notifyDataSetChanged();

                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                        finally {

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });

        MedicalApplication.getInstance().addToRequestQueue(jsonObjectRequest);

    }
}
