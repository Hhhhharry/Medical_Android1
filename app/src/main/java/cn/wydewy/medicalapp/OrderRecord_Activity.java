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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.wydewy.medicalapp.util.Constant;

public class OrderRecord_Activity extends BaseBackActivity {

    private JSONArray json;
    private BaseAdapter adapter;
    private List<Map<String,String>> items = new ArrayList<>();
    private TextView tex,top_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_record);
        getSupportActionBar().hide();

       // text = (TextView) findViewById(R.id.Order_Record_Activity_nulltext);

        top_title = (TextView) findViewById(R.id.top_title);
        top_title.setText("预约记录");

        getDetail();
        setRecord();
    }
    private void getDetail() {

        Map<String,String> map = new HashMap<>();
        map.put(Constant.Customer_Id,((MedicalApplication)getApplication()).getCustomerId());
        CustomRequest jsonObjectRequest = new CustomRequest(Request.Method.POST,Constant.API_CUSTOMERID_RELEASELIST,map,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                        Log.d("json","****"+jsonObject.toString());
                        String itemsStr = null;
                        JSONArray am = jsonObject.optJSONArray("datum");
                        if (am!=null)
                        {
                            //text.invalidate();

                            for (int i = 0; i < am.length(); i++) {
                                JSONObject json = am.optJSONObject(i);
                                Map<String, String> map = new HashMap<>();
                                map.put(Constant.Order_Date, json.optString("appointdate").toString());
                                map.put(Constant.Order_AMPM, json.optString("ampm").toString());
                                map.put(Constant.Week, json.optString("week").toString());
                                map.put(Constant.OutPatient_Name, json.optString("outpatientname").toString());
                                map.put(Constant.Section_Name, json.optString("sectionname").toString());
                                map.put(Constant.Rleasenum_Id, json.optString("realseid".toString()));
                                items.add(map);
                            }
                            Log.d("items", items.toString());
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
    private void setRecord() {

        ListView introlist = (ListView) findViewById(R.id.orderrecord_list);

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
                    convertView = LayoutInflater.from(OrderRecord_Activity.this).inflate(
                            R.layout.orderrecord_list,null,true);
                    viewholder.date = (TextView) convertView.findViewById(R.id.Order_Record_Activity_list_date);
                    viewholder.week = (TextView) convertView.findViewById(R.id.Order_Record_Activity_list_week);
                    viewholder.ampm = (TextView) convertView.findViewById(R.id.Order_Record_Activity_list_ampm);
                    viewholder.outpatient = (TextView) convertView.findViewById(R.id.Order_Record_Activity_list_outpatient);
                    viewholder.section = (TextView) convertView.findViewById(R.id.Order_Record_Activity_list_section);
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
                Withdraw(position);
            }
        });
    }

    private void Withdraw(int position) {
        Map<String,String> map = new HashMap<>();
        map.put("releasenumId",items.get(position).get(Constant.Rleasenum_Id));
        CustomRequest jsonObjectRequest = new CustomRequest(Request.Method.POST,Constant.API_RELEASEID_WITHDRAW,map,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                        Log.d("json","****"+jsonObject.toString());
                        items.clear();
                        getDetail();
                        setRecord();
                        setToast();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });

        MedicalApplication.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    private void setToast() {
        Toast toast = Toast.makeText(this,"退号成功！！",Toast.LENGTH_SHORT);
        toast.show();
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
