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
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.wydewy.medicalapp.model.Section;
import cn.wydewy.medicalapp.util.Constant;

public class Section_Activity extends BaseBackActivity {

    private List<Section> items = new ArrayList<>();
    private JSONArray json;
    private TextView top_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);
        getSupportActionBar().hide();


        top_title = (TextView) findViewById(R.id.top_title);
        top_title.setText("预约挂号");


        CustomRequest jsonObjectRequest = new CustomRequest(Constant.API_HOSPITAL_SECTION_LIST, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                        String itemsStr = null;
                        try {
                            itemsStr = jsonObject.get("datum").toString();
                            items = JSON.parseObject(itemsStr,new TypeReference<List<Section>>(){});
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                        finally {

                        }
                        setSection();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });
        MedicalApplication.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    private void setSection() {
        ListView introlist = (ListView) findViewById(R.id.section_list);
        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return items.size();
            }

            @Override
            public Section getItem(int position) {
                return items.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView text = new TextView(Section_Activity.this);
                JSONObject json1 = json.optJSONObject(position);
                text.setText(getItem(position).getSectionName());
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
                Intent it = new Intent(Section_Activity.this,Section2_Activity.class);
                Bundle bundle = new Bundle();
                bundle.putString("selectedItem", items.get(position).getSectionId().toString());
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
