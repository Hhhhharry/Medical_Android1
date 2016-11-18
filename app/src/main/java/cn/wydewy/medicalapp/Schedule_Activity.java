package cn.wydewy.medicalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.wydewy.medicalapp.model.ReleaseNum;
import cn.wydewy.medicalapp.model.ScheduleModel;
import cn.wydewy.medicalapp.util.Constant;

public class Schedule_Activity extends AppCompatActivity {

    private GridView gridView;
    private ScheduleAdapter myAdapter;
    private String outpatientId;
    private RequestQueue mqueue;
    private int MaxHeight = 0;
    private Map<String,List<Map<String,String>>> items = new LinkedHashMap<>();
    private JSONObject schedulearr;
    private String schedulelist;
    private String outpatientname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        outpatientId = bundle.getString("selectedItem");
        outpatientname = bundle.getString(Constant.OutPatient_Name);

        gridView = (GridView) findViewById(R.id.grid1);
        myAdapter = new ScheduleAdapter();
        gridView.setAdapter(myAdapter);

        TextView title = (TextView) findViewById(R.id.Schedule_Activity_Title);
        title.setText(outpatientname);

        GetSchedule();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(Schedule_Activity.this,Schedule2_Activity.class);
                Bundle bundle = new Bundle();

                bundle.putString(Constant.Doctor_Name,items.get(WhichWeek(position%5)).get(position/5-1).get(Constant.Doctor_Name));
                bundle.putString(Constant.Week,items.get(WhichWeek(position%5)).get(position/5-1).get(Constant.Week));
             //   bundle.putString(Constant.Order_Price,items.get(WhichWeek(position%5)).get(position/5-1).get(Constant.Order_Price));
             //   bundle.putString(Constant.Order_Date,items.get(WhichWeek(position%5)).get(position/5-1).get(Constant.Order_Date));
               // bundle.putString(Constant.Order_AMPM,items.get(WhichWeek(position%5)).get(position/5-1).get(Constant.Order_AMPM));
              //  bundle.putString(Constant.OutPatient_Name,outpatientname);

                it.putExtras(bundle);
                startActivityForResult(it,0);
            }
        });


        //getData();
    }


    private void GetSchedule() {
        Map<String,String> map = new HashMap<>();
        map.put("outpatientId",outpatientId);
        CustomRequest objectRequest = new CustomRequest(Request.Method.POST, Constant.API_Schedule_ReleaseNum, map,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.d("TAG_response",jsonObject.toString());

                        try
                        {

                            schedulearr = jsonObject.optJSONObject("datum");

                            SetSingleDay("星期一");
                            SetSingleDay("星期二");
                            SetSingleDay("星期三");
                            SetSingleDay("星期四");
                            SetSingleDay("星期五");
                            myAdapter.notifyDataSetChanged();
                            Log.d("TAG","items"+items.toString());

                        }
                       finally {


                        }


                    }


                },
                   new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                        Log.d("TAG-error", volleyError.toString());
                    }
                    }
                );
        MedicalApplication.getInstance().addToRequestQueue(objectRequest);
    }
    private void SetSingleDay(String day) {
        String itemsStr = null;
        JSONArray mon = schedulearr.optJSONArray(day);
        List<Map<String,String>> ScheduleList = new ArrayList<>();



        if(mon != null)
        {
           // try{
            for(int i = 0;i<mon.length();i++)
            {
                Map<String,String> map = new HashMap<>();
                map.put(Constant.Doctor_Name,mon.optJSONObject(i).optString("doctorname"));
                Log.d("TAG",mon.optJSONObject(i).toString());
                map.put(Constant.Doctor_Id,mon.optJSONObject(i).optString("doctorid"));
                map.put(Constant.Week,mon.optJSONObject(i).optString("week"));
               // map.put(Constant.Order_Price,release.optString("price").toString());
              //  map.put(Constant.Order_Date,release.optString("date").toString());
               // map.put(Constant.Order_AMPM,release.optString("ampm").toString());
                ScheduleList.add(map);
                Log.d("doctorname","doctorname"+ScheduleList.toString());
//            }
//            }
//            catch (JSONException e)
//            {
//                e.printStackTrace();
            }
            MaxHeight = Math.max(MaxHeight, mon.length());
            items.put(day, ScheduleList);

        }
    }
    private String WhichWeek(int i) {
        switch (i) {
            case 0:
                return "星期一";

            case 1:
                return "星期二";

            case 2:
                return "星期三";

            case 3:
                return "星期四";

            case 4:
                return "星期五";

        }
        return null;
    }

    private class ScheduleAdapter extends BaseAdapter {



        @Override
        public int getCount() {
            return 5 * (MaxHeight + 1);
        }

        @Override
        public String getItem(int i) {

            int width = i % 5;
            int height = i / 5;
            Log.d("TAG", "width" + width + "Height" + height);
            if (height == 0) {
                String r;
                r = (WhichWeek(width));  //把星期当doctoename储存
                return r;
            } else {
                List<Map<String,String>> its = items.get(WhichWeek(width));
                if (height <= its.size()) {
                    return its.get(height - 1).get(Constant.Doctor_Name);
                } else {
                    String r;
                    r = " ";
                    return r;
                }
            }

        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView = new TextView(Schedule_Activity.this);

            int width = position % 5;
            int height = position / 5;
            Log.d("TAG", "width" + width + "Height" + height);
            if (height == 0) {
                //把星期当doctoename储存
                textView.setBackgroundResource(R.drawable.shape_label_lightgreen);
                textView.setTextColor(android.graphics.Color.rgb(255, 255, 255));

            } else {
                List<Map<String,String>> its = items.get(WhichWeek(width));
                if (height <= its.size()) {
                    textView.setBackgroundResource(R.drawable.shape_label2);
                    textView.setTextColor(android.graphics.Color.rgb(0, 0, 0));
                } else {

                    textView.setBackgroundResource(R.drawable.shape_label2);
                }
            }
            //textView.setBackgroundColor(android.graphics.Color.rgb(0, 0, 0));
            textView.setText(getItem(position));
            textView.setGravity(Gravity.CENTER);
            textView.setHeight(150);

            textView.setTextSize(10);
            return textView;
        }

        @Override
        public boolean isEnabled(int i) {

            int width = i % 5;
            int height = i / 5;
            Log.d("TAG", "width" + width + "Height" + height);
            if (height == 0) {
                  //把星期当doctoename储存
                return false;
            } else {
                List<Map<String,String>> its = items.get(WhichWeek(width));
                if (height <= its.size()) {
                    return true;
                } else {

                    return false;
                }
            }
        }
    }
}

