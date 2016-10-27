package cn.wydewy.medicalapp;

import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class Introduce_Activity extends AppCompatActivity {

    private TextView Name,Intro;
    private String[] items = new String[]{"儿科","内科","妇产科","血科","保健科","皮肤科","外科","眼科","儿童科","肾内科","妇产科","血科"};
    private String Url = "http://192.168.1.108:8080/framework/hospital/insert";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);
        getSupportActionBar().hide();

        Name = (TextView) findViewById(R.id.name);
        Intro = (TextView) findViewById(R.id.introduce);

        RequestQueue mqueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        System.out.println(jsonObject.toString());
                        JSONAnalysis(jsonObject.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                 }
        });
        mqueue.add(jsonObjectRequest);


//        //连接测试
//        new Thread() {
//            public void run() {
//
//                try {
//
//            URL url = new URL(Url);
//            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//             /* optional request header */
//            urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
//
//             /* optional request header */
//            urlConnection.setRequestProperty("Accept", "application/json");
//
//            urlConnection.setRequestMethod("GET");//使用GET方法获取
//
//            int statusCode = urlConnection.getResponseCode();
//            System.out.println("~~~~~~~"+statusCode);
//            /* 200 represents HTTP OK */
//            if (statusCode == 200) {
//                InputStream inputStream = urlConnection.getInputStream();
//                String result = HttpUtils.readMyInputStream(inputStream);
//               //System.out.println("~~~~~~~"+inputStream.toString());
//                Message msg = new Message();
//                msg.obj = result;
//                JSONAnalysis(result);
//            }
//        } catch (Exception e) {
//
//                    e.printStackTrace();
//
//                }
//            }
//        }.start();



        ListView introlist = (ListView) findViewById(R.id.introduce_list);
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
                TextView text = new TextView(Introduce_Activity.this);
                text.setText(items[position]);
                text.setPadding(0,50,0,50);
                text.setTextColor(android.graphics.Color.rgb(0,0,0));
                text.setTextSize(15);
                return text;
            }
        };
        introlist.setAdapter(adapter);

    }
    protected void JSONAnalysis(String string) {
        JSONObject object = null;
        try {
            object = new JSONObject(string);

        /**
         * 在你获取的string这个JSON对象中，提取你所需要的信息。
         */
        JSONArray jsonArray = object.getJSONArray("datum");
        JSONObject jsonObj = jsonArray.getJSONObject(0);
        System.out.println("~~~~~~~"+jsonObj.toString());
        //JSONObject ObjectInfo = jsonObj.optJSONObject("datum");

        String name = jsonObj.optString("hosname");
        System.out.println("~~~~~~~"+name.toString());
        String introduce = jsonObj.optString("hosIntro");
            Name.setText(name);
            Intro.setText(introduce);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
