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
import android.widget.Toast;

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

public class Schedule2_Activity extends BaseBackActivity {

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

        TextView title = (TextView) findViewById(R.id.top_title);
        title.setText(doctorname);


        initListview();

        getDetail();

    }
    private void getDetail() {

        Map<String,String> map = new HashMap<>();
        map.put("doctorId",doctorid);
        map.put("week",week);
        Log.d("json","****"+map.toString());
        CustomRequest jsonObjectRequest = new CustomRequest(Request.Method.POST,Constant.API_RELEASE_DETAIL,map,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                        Log.d("json","****"+jsonObject.toString());
                        String itemsStr = null;
                        JSONObject jsonObject1 = jsonObject.optJSONObject("datum");
                        JSONArray am = jsonObject1.optJSONArray("上午");
                        JSONArray pm = jsonObject1.optJSONArray("下午");
                        for (int i = 0;i<am.length();i++)
                        {
                            JSONObject json = am.optJSONObject(i);
                            Map<String,String> map = new HashMap<>();
                            map.put(Constant.Order_Date,json.optString("date").toString());
                            map.put(Constant.Order_AMPM,json.optString("ampm").toString());
                            map.put(Constant.Week,json.optString("week").toString());
                            map.put(Constant.OutPatient_Name,json.optString("outpatientName").toString());
                            map.put(Constant.Section_Name,json.optString("sectionName").toString());
                            map.put(Constant.Rleasenum_Id,json.optString("releasenumid").toString());
                            map.put(Constant.Doctor_Id,json.optString("doctorId").toString());
                            map.put(Constant.Order_Price,json.optString("price").toString());
                            map.put(Constant.Doctor_Name,doctorname);
                            items.add(map);
                        }
                        for (int i = 0;i<pm.length();i++)
                        {
                            JSONObject json = pm.optJSONObject(i);
                            Map<String,String> map = new HashMap<>();
                            map.put(Constant.Order_Date,json.optString("date").toString());
                            map.put(Constant.Order_AMPM,json.optString("ampm").toString());
                            map.put(Constant.Week,json.optString("week").toString());
                            map.put(Constant.OutPatient_Name,json.optString("outpatientName").toString());
                            map.put(Constant.Section_Name,json.optString("sectionName").toString());
                            map.put(Constant.Rleasenum_Id,json.optString("releasenumid").toString());
                            map.put(Constant.Doctor_Id,json.optString("doctorId").toString());
                            map.put(Constant.Order_Price,json.optString("price").toString());
                            map.put(Constant.Doctor_Name,doctorname);
                            items.add(map);
                        }
                        Log.d("items",items.toString());
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

    public void setChange(int position)
    {
        if(((MedicalApplication)getApplication()).isLog()) {
            Intent it = new Intent(Schedule2_Activity.this, Confirm_Activity.class);
            Bundle bundle = new Bundle();
            bundle.putString(Constant.Section_Name, items.get(position).get(Constant.Section_Name).toString());
            bundle.putString(Constant.OutPatient_Name, items.get(position).get(Constant.OutPatient_Name).toString());
            bundle.putString(Constant.Order_AMPM, items.get(position).get(Constant.Order_AMPM).toString());
            bundle.putString(Constant.Order_Date, items.get(position).get(Constant.Order_Date).toString());
            bundle.putString(Constant.Week, items.get(position).get(Constant.Week).toString());
            bundle.putString(Constant.Doctor_Id, items.get(position).get(Constant.Doctor_Id).toString());
            bundle.putString(Constant.Rleasenum_Id, items.get(position).get(Constant.Rleasenum_Id).toString());
            bundle.putString(Constant.Order_Price, items.get(position).get(Constant.Order_Price).toString());
            bundle.putString(Constant.Doctor_Name, items.get(position).get(Constant.Doctor_Name).toString());
            it.putExtras(bundle);
            startActivityForResult(it, 0);
        }
        else
        {

            Toast toast = Toast.makeText(this,"请登录！！",Toast.LENGTH_LONG);
            toast.show();
            Intent it1 = new Intent(Schedule2_Activity.this,Register_Activity.class);
            startActivity(it1);
        }

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
                viewholder.date.setText(items.get(position).get(Constant.Order_Date).toString());
                viewholder.week.setText(items.get(position).get(Constant.Week).toString());
                viewholder.ampm.setText(items.get(position).get(Constant.Order_AMPM).toString());
                viewholder.outpatient.setText(items.get(position).get(Constant.OutPatient_Name).toString());
                viewholder.section.setText(items.get(position).get(Constant.Section_Name).toString());
                return convertView;
            }

            final class ViewHolder {
                TextView date,week,ampm,outpatient,section;
            }
        };
        introlist.setAdapter(adapter);
        introlist.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setChange(position);
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
