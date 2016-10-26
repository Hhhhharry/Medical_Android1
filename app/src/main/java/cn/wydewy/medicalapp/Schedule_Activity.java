package cn.wydewy.medicalapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class Schedule_Activity extends AppCompatActivity {

    private GridView gridView;
    private MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        myAdapter = new MyAdapter();
        gridView = (GridView) findViewById(R.id.grid1);
        gridView.setAdapter(myAdapter);
    }

    private class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return 25;
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
            TextView textView = new TextView(Schedule_Activity.this);
            textView.setBackgroundResource(R.drawable.shape_label2);
            if(position<=4) {
                switch (position)
                {
                    case 0:
                        textView.setText("周一");
                        break;
                    case 1:
                        textView.setText("周二");
                        break;
                    case 2:
                        textView.setText("周三");
                        break;
                    case 3:
                        textView.setText("周四");
                        break;
                    case 4:
                        textView.setText("周五");
                        break;
                }

            }
            textView.setGravity(Gravity.CENTER);
            textView.setHeight(150);
            textView.setTextColor(android.graphics.Color.rgb(0,0,0));
            textView.setTextSize(10);
            return textView;
        }
    }
}
