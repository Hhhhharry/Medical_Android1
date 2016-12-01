package cn.wydewy.medicalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import cn.wydewy.medicalapp.util.Constant;

public class Schedule_Activity extends BaseBackActivity {

    private GridView gridView1,gridView2;
    private ScheduleAdapter1 myAdapter1;
    private ScheduleAdapter2 myAdapter2;
    private String outpatientId;
    private int MaxHeight = 0;
    private Map<String,List<Map<String,String>>> items1 = new LinkedHashMap<>();
    private Map<String,List<Map<String,String>>> items2 = new LinkedHashMap<>();
    //private Map<String,List<Map<String,String>>> items = new LinkedHashMap<>();
    private JSONObject schedulearr;
    private String outpatientname;

    private Map<String,String> map = new TreeMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        outpatientId = bundle.getString("selectedItem");
        outpatientname = bundle.getString(Constant.OutPatient_Name);

        gridView1 = (GridView) findViewById(R.id.grid1);
        gridView2 = (GridView) findViewById(R.id.grid2);

        myAdapter1 = new ScheduleAdapter1();
        myAdapter2 = new ScheduleAdapter2();
        TextView title = (TextView) findViewById(R.id.top_title);
        title.setText(outpatientname);

        getFetureDate();
        GetSchedule();


    }

    private void setGrid1() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int px = dm.widthPixels;
        int length = px/5;
        int gridviewWidth =  7 * length;
        int itemWidth =  length ;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(gridviewWidth, LinearLayout.LayoutParams.MATCH_PARENT);
        gridView1.setLayoutParams(params); //设置GirdView布局参数,横向布局的关键
        gridView1.setColumnWidth(itemWidth);
        //gridView.setHorizontalSpacing(5);   //中间间隔
        gridView1.setStretchMode(GridView.NO_STRETCH);
        gridView1.setAdapter(myAdapter1);
        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(Schedule_Activity.this,Schedule2_Activity.class);
                Bundle bundle = new Bundle();
                bundle.putString(Constant.Doctor_Name,items1.get(WhichWeek(position%7)).get(position/7-1).get(Constant.Doctor_Name));
                bundle.putString(Constant.Week,items1.get(WhichWeek(position%7)).get(position/7-1).get(Constant.Week));
                bundle.putString(Constant.Doctor_Id,items1.get(WhichWeek(position%7)).get(position/7-1).get(Constant.Doctor_Id));
                it.putExtras(bundle);
                startActivityForResult(it,0);
            }
        });



    }


    private void setGrid2() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int px = dm.widthPixels;
        int length = px/5;
        int gridviewWidth =  7 * length;
        int itemWidth =  length ;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(gridviewWidth, LinearLayout.LayoutParams.MATCH_PARENT);
        gridView2.setLayoutParams(params); //设置GirdView布局参数,横向布局的关键
        gridView2.setColumnWidth(itemWidth);
        //gridView.setHorizontalSpacing(5);   //中间间隔
        gridView2.setStretchMode(GridView.NO_STRETCH);
        gridView2.setAdapter(myAdapter2);
        gridView2.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(Schedule_Activity.this,Schedule2_Activity.class);
                Bundle bundle = new Bundle();
                bundle.putString(Constant.Doctor_Name,items2.get(WhichWeek(position%7)).get(position/7-1).get(Constant.Doctor_Name));
                bundle.putString(Constant.Week,items2.get(WhichWeek(position%7)).get(position/7-1).get(Constant.Week));
                bundle.putString(Constant.Doctor_Id,items2.get(WhichWeek(position%7)).get(position/7-1).get(Constant.Doctor_Id));
                it.putExtras(bundle);
                startActivityForResult(it,0);
            }
        });



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
                            JSONArray  amarr = schedulearr.optJSONArray("上午");
                            Log.d("asasasasas",amarr.toString());
                            JSONObject AM = amarr.optJSONObject(0);

                            SetSingleDay("星期一","上午",AM);
                            SetSingleDay("星期二", "上午", AM);
                            SetSingleDay("星期三", "上午", AM);
                            SetSingleDay("星期四", "上午", AM);
                            SetSingleDay("星期五", "上午", AM);
                            SetSingleDay("星期六", "上午", AM);
                            SetSingleDay("星期日", "上午", AM);
                            amarr = schedulearr.optJSONArray("下午");
                            JSONObject PM = amarr.optJSONObject(0);

                            SetSingleDay("星期一","下午", PM);
                            SetSingleDay("星期二", "下午", PM);
                            SetSingleDay("星期三", "下午", PM);
                            SetSingleDay("星期四", "下午", PM);
                            SetSingleDay("星期五", "下午", PM);
                            SetSingleDay("星期六", "下午", PM);
                            SetSingleDay("星期日","下午", PM);

                            setGrid1();

                            setGrid2();

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
    private void SetSingleDay(String day, String list, JSONObject jsonObject) {
        String itemsStr = null;
        JSONArray mon = jsonObject.optJSONArray(day);
        List<Map<String,String>> ScheduleList = new ArrayList<>();

        if(mon != null)
        {
            for(int i = 0;i<mon.length();i++)
            {
                Map<String,String> map = new HashMap<>();
                map.put(Constant.Doctor_Name,mon.optJSONObject(i).optString("doctorname"));
                Log.d("TAG",mon.optJSONObject(i).toString());
                map.put(Constant.Doctor_Id,mon.optJSONObject(i).optString("doctorid"));
                map.put(Constant.Week,day);
                map.put("date",mon.optJSONObject(i).optString("pichead"));
                ScheduleList.add(map);
                Log.d("doctorname","doctorname"+ScheduleList.toString());
            }
            MaxHeight = Math.max(MaxHeight, mon.length());
            if(list=="上午")
            items1.put(day,ScheduleList);
            else
            items2.put(day, ScheduleList);

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
            case 5:

                return "星期六";

            case 6:
                return "星期日";

        }
        return null;
    }


    private class ScheduleAdapter1 extends BaseAdapter {



        @Override
        public int getCount() {
            return 7 * (MaxHeight + 1);
        }

        @Override
        public String getItem(int i) {

            int width = i % 7;
            int height = i / 7;
            Log.d("TAG", "width" + width + "Height" + height);
            if (height == 0) {
                String r;
                r = (WhichWeek(width))+ map.get(WhichWeek(width));  //把星期当doctoename储存
                return r;
            } else {
                List<Map<String,String>> its = items1.get(WhichWeek(width));
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
            Log.d("aaaaaaaaaaaaaaaaaaaaaaa",items1.toString());
            int width = position % 7;
            int height = position / 7;
            if (height == 0) {
                //把星期当doctoename储存
                textView.setBackgroundResource(R.drawable.shape_label_lightgreen);
                textView.setTextColor(android.graphics.Color.rgb(255, 255, 255));

            } else {
                List<Map<String,String>> its = items1.get(WhichWeek(width));
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
            textView.setTextSize(13);
            return textView;
        }

        @Override
        public boolean isEnabled(int i) {

            int width = i % 7;
            int height = i / 7;
            Log.d("TAG", "width" + width + "Height" + height);
            if (height == 0) {
                //把星期当doctoename储存
                return false;
            } else {
                List<Map<String,String>> its = items1.get(WhichWeek(width));
                if (height <= its.size()) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    private class ScheduleAdapter2 extends BaseAdapter {



        @Override
        public int getCount() {
            return 7 * (MaxHeight + 1);
        }

        @Override
        public String getItem(int i) {

            int width = i % 7;
            int height = i / 7;
            Log.d("TAG", "width" + width + "Height" + height);
            if (height == 0) {
                String r;
                r = (WhichWeek(width))+ map.get(WhichWeek(width));  //把星期当doctoename储存
                return r;
            } else {
                List<Map<String,String>> its = items2.get(WhichWeek(width));
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
            Log.d("aaaaaaaaaaaaaaaaaaaaaaa",items2.toString());
            int width = position % 7;
            int height = position / 7;
            Log.d("TAG", "width" + width + "Height" + height);
            if (height == 0) {
                //把星期当doctoename储存
                textView.setBackgroundResource(R.drawable.shape_label_lightgreen);
                textView.setTextColor(android.graphics.Color.rgb(255, 255, 255));

            } else {
                List<Map<String,String>> its = items2.get(WhichWeek(width));
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
            textView.setTextSize(13);
            return textView;
        }

        @Override
        public boolean isEnabled(int i) {

            int width = i % 7;
            int height = i / 7;
            Log.d("TAG", "width" + width + "Height" + height);
            if (height == 0) {
                //把星期当doctoename储存
                return false;
            } else {
                List<Map<String,String>> its = items2.get(WhichWeek(width));
                if (height <= its.size()) {
                    return true;
                } else {
                    return false;
                }
            }
        }
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

    public  Map<String, String> getFetureDate() {


        for(int past=0; past<7; past++){
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
            Date today = calendar.getTime();

            String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
            Calendar cal = Calendar.getInstance();
            cal.setTime(today);
            int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
            if (w < 0)
                w = 0;

            SimpleDateFormat format = new SimpleDateFormat("MM/dd");
            String result = format.format(today);
            map.put(weekDays[w], result);
        }
        return map;
    }


}

