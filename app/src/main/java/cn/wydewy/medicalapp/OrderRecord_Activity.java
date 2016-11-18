package cn.wydewy.medicalapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

public class OrderRecord_Activity extends AppCompatActivity {

    private JSONArray json;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_record);
        getSupportActionBar().hide();


        String url1 = ((MedicalApplication)getApplication()).getUrlhead();
        String url = url1 +"/framework/doctor/selectBySection";
        Map<String,String> map = new HashMap<>();
      //  map.put("MedicalApplication",item);                                   //传值 outpatientName

        setRecord();
//        RequestQueue mqueue = Volley.newRequestQueue(this);
//        CustomRequest jsonObjectRequest = new CustomRequest(Request.Method.POST,url,map,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject jsonObject) {
//                        json = jsonObject.optJSONArray("datum");
//                        setRecord();
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError volleyError) {
//
//                    }
//                });
//
//        mqueue.add(jsonObjectRequest);
    }

    private void setRecord() {

        ListView introlist = (ListView) findViewById(R.id.orderrecord_list);
        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return 20;
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

                LinearLayout line = new LinearLayout(OrderRecord_Activity.this);
                line.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout line1 = new LinearLayout(OrderRecord_Activity.this);
                line1.setOrientation(LinearLayout.VERTICAL);
                LinearLayout line11 = new LinearLayout(OrderRecord_Activity.this);
                line11.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout line12 = new LinearLayout(OrderRecord_Activity.this);
                line12.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout line13 = new LinearLayout(OrderRecord_Activity.this);
                line13.setOrientation(LinearLayout.HORIZONTAL);

                TextView texttime1 = new TextView(OrderRecord_Activity.this);
                texttime1.setText("预约时间:");
                texttime1.setPadding(20,20,0,20);
                texttime1.setTextColor(android.graphics.Color.rgb(0,0,0));
                texttime1.setTextSize(10);

                TextView texttime2 = new TextView(OrderRecord_Activity.this);
//                JSONObject json1 = json.optJSONObject(position);
                texttime2.setText("json1.optString.toString()");
                texttime2.setPadding(20,20,0,20);
                texttime2.setTextColor(android.graphics.Color.rgb(0,0,0));
                texttime2.setTextSize(10);
                line11.addView(texttime1);
                line11.addView(texttime2);

                TextView textdoctor1 = new TextView(OrderRecord_Activity.this);
                textdoctor1.setText("预约医生:");
                textdoctor1.setPadding(20,20,0,20);
                textdoctor1.setTextColor(android.graphics.Color.rgb(0,0,0));
                textdoctor1.setTextSize(10);

                TextView textdoctor2 = new TextView(OrderRecord_Activity.this);
                textdoctor2 .setText("json1.optString.toString()");
                textdoctor2 .setPadding(20,20,0,20);
                textdoctor2 .setTextColor(android.graphics.Color.rgb(0,0,0));
                textdoctor2 .setTextSize(10);
                line12.addView(textdoctor1);
                line12.addView(textdoctor2);

                TextView textsection1 = new TextView(OrderRecord_Activity.this);
                textsection1.setText("预约科室:");
                textsection1.setPadding(20,20,0,20);
                textsection1.setTextColor(android.graphics.Color.rgb(0,0,0));
                textsection1.setTextSize(10);

                TextView textsection2 = new TextView(OrderRecord_Activity.this);
                textsection2.setText("json1.optString.toString()");
                textsection2.setPadding(20,20,0,20);
                textsection2.setTextColor(android.graphics.Color.rgb(0,0,0));
                textsection2.setTextSize(10);
                line13.addView(textsection1);
                line13.addView(textsection2);

                TextView texttuihao = new TextView(OrderRecord_Activity.this);
                texttuihao.setText("退号");
                texttuihao.setPadding(400,100,0,100);
                texttuihao.setTextColor(android.graphics.Color.rgb(0,0,0));
                texttuihao.setTextSize(10);

                line1.addView(line11);
                line1.addView(line12);
                line1.addView(line13);


                line.addView(line1);
                line.addView(texttuihao);

                return line;
            }
        };
        introlist.setAdapter(adapter);
//        introlist.setOnItemClickListener(new AdapterView.OnItemClickListener()
//        {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent it = new Intent(Introduce2_Activity.this,Introduce3_Activity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("selectedItem",json.optJSONObject(position).optString("doctorid").toString());
//                it.putExtras(bundle);
//                startActivityForResult(it,0);
//            }
//        });
    }

}
