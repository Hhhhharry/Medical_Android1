package cn.wydewy.medicalapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Order2_Activity extends AppCompatActivity {
    private String[] items = new String[]{"儿科","内科","妇产科","血科","保健科","皮肤科","外科","眼科","儿童科","肾内科","妇产科","血科"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order2);
        getSupportActionBar().hide();
        ListView introlist = (ListView) findViewById(R.id.introduce_list2);
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
                TextView text = new TextView(Order2_Activity.this);
                text.setText(items[position]);
                text.setPadding(50,50,0,50);
                text.setTextColor(android.graphics.Color.rgb(0,0,0));
                text.setTextSize(15);
                return text;
            }
        };
        introlist.setAdapter(adapter);
    }
}
