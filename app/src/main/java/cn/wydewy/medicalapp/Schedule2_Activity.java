package cn.wydewy.medicalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.wydewy.medicalapp.model.OutPatient;
import cn.wydewy.medicalapp.util.Constant;

public class Schedule2_Activity extends AppCompatActivity {

    private String doctorname;
    private String doctorid;
    private String week;
    private BaseAdapter adapter;
    private List<Map<String,String>> items = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule2);
        getSupportActionBar().hide();

        Intent intent = getIntent(); //用于激活它的意图对象：这里的intent获得的是上个Activity传递的intent
        Bundle bundle = intent.getExtras();

        doctorname = bundle.getString(Constant.Doctor_Name);
        doctorid = bundle.getString(Constant.Doctor_Id);
        week = bundle.getString(Constant.Week);

        TextView title = (TextView) findViewById(R.id.Schedule2_Activity_Title);
        title.setText(doctorname);

        initListview();

        getDetail();

    }
    private void getDetail() {

        Map<String,String> map = new HashMap<>();
        map.put("doctorId",doctorid);
        map.put("week",week);
        CustomRequest jsonObjectRequest = new CustomRequest(Request.Method.POST,Constant.API_RELEASE_DETAIL,map,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                        Log.d("response",jsonObject.toString());
                        String itemsStr = null;
                        JSONArray arr = jsonObject.optJSONArray("datum");
                        for (int i = 0;i<arr.length();i++)
                        {
                            JSONObject json = arr.optJSONObject(i);
                            Map<String,String> map = new HashMap<>();
                            map.put(Constant.Order_Date,json.optString("date").toString());
                            map.put(Constant.Order_AMPM,json.optString("ampm").toString());
                            map.put(Constant.Week,json.optString("week").toString());
                            map.put(Constant.OutPatient_Name,json.optString("outpatientName").toString());
                            map.put(Constant.Section_Name,json.optString("sectionName").toString());
                            items.add(map);
                        }
                        adapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });

        MedicalApplication.getInstance().addToRequestQueue(jsonObjectRequest);
    }


    private void initListview () {
        ListView introlist = (ListView) findViewById(R.id.Schedule2_Activity_list);
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

                ViewHolder viewholder;
                if(convertView == null)
                {
                    viewholder = new ViewHolder();
                    convertView = LayoutInflater.from(Schedule2_Activity.this).inflate(
                            R.layout.schedule2_list,null,true);
                    viewholder.date = (TextView) convertView.findViewById(R.id.Schedule2_Activity_list_date);
                    viewholder.week = (TextView) convertView.findViewById(R.id.Schedule2_Activity_list_week);
                    viewholder.ampm = (TextView) convertView.findViewById(R.id.Schedule2_Activity_list_ampm);
                    viewholder.outpatient = (TextView) convertView.findViewById(R.id.Schedule2_Activity_list_outpatient);
                    viewholder.section = (TextView) convertView.findViewById(R.id.Schedule2_Activity_list_section);
                    convertView.setTag(viewholder);
                }
                else {
                    viewholder = (ViewHolder) convertView.getTag();
                }
                viewholder.date.setText(items.get(position).get("date").toString());
                viewholder.week.setText(items.get(position).get("week").toString());
                viewholder.ampm.setText(items.get(position).get("ampm").toString());
                viewholder.outpatient.setText(items.get(position).get("outpatientName").toString());
                viewholder.section.setText(items.get(position).get("sectionName").toString());
                return convertView;
            }

            final class ViewHolder {
                TextView date,week,ampm,outpatient,section;
            }
        };
        introlist.setAdapter(adapter);
//        introlist.setOnItemClickListener(new AdapterView.OnItemClickListener()
//        {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent it = new Intent(Order3_Activity.this,Schedule_Activity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("selectedItem",items.get(position).getOutpatientId().toString());
//                //   bundle.putString(Constant.Section_Name,sectionname);
//                bundle.putString(Constant.OutPatient_Name,items.get(position).getOutpatientName().toString());
//                it.putExtras(bundle);
//                startActivityForResult(it,0);
//            }
//        });
    }


}
