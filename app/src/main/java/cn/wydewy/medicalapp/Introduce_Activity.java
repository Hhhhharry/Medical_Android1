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
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import cn.wydewy.medicalapp.util.Constant;


public class Introduce_Activity extends BaseBackActivity {

    private MedicalApplication da;
    private JSONObject json;
    private JSONArray jsonarray;
    private TextView Intro,top_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);
        getSupportActionBar().hide();

        Intro = (TextView) findViewById(R.id.introduce);
        top_title = (TextView) findViewById(R.id.top_title);

        top_title.setText("医院介绍");

        CustomRequest jsonObjectRequest = new CustomRequest(Constant.API_HOSPITAL_DETAIL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        json = jsonObject.optJSONObject("datum");
                        setHospitalDetial();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                 }
        });
        CustomRequest jsonObjectRequest1 = new CustomRequest(Constant.API_HOSPITAL_SECTION_LIST, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                        jsonarray = jsonObject.optJSONArray("datum");
                        setOutSection();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });
        MedicalApplication.getInstance().addToRequestQueue(jsonObjectRequest);
        MedicalApplication.getInstance().addToRequestQueue(jsonObjectRequest1);

    }

    private void setHospitalDetial() {

        Intro.setText(json.optString("hosintro").toString());

    }

    private void setOutSection() {
        ListView introlist = (ListView) findViewById(R.id.introduce2_list1);
        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return jsonarray.length();
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
                JSONObject json1 = jsonarray.optJSONObject(position);
                text.setText(json1.optString("sectionName").toString());
                text.setPadding(50, 50, 0, 50);
                text.setTextColor(android.graphics.Color.rgb(0, 0, 0));
                text.setTextSize(15);
                return text;
            }
        };
        introlist.setAdapter(adapter);
        introlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(Introduce_Activity.this, Introduce2_Activity.class);
                Bundle bundle = new Bundle();
                bundle.putString("selectedItem", jsonarray.optJSONObject(position).optString("sectionName").toString());
                it.putExtras(bundle);
                startActivityForResult(it, 0);
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
