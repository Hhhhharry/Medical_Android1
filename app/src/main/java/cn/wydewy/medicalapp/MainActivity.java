package cn.wydewy.medicalapp;


import android.os.Handler;
import android.provider.Settings;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.Button;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements OnClickListener {

    private ViewPager viewPager;
    private LinearLayout point_group;

    private final int[] images = {R.mipmap.p1, R.mipmap.p2, R.mipmap.p3};

    private ArrayList<ImageView> imageList;
    // 上一个页面的位置
    protected int lastPosition = 0;

    // 判断是否自动滚动viewPager
    private boolean isRunning = true;
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            // 执行滑动到下一个页面
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            if (isRunning) {
                // 在发一个handler延时
                handler.sendEmptyMessageDelayed(0, 5000);
            }
        }
    };

    //private LinearLayout tabHospital,tabMy;
    private Button btnhospital, btnmy;
    private Fragment_hospital fHospital;
    private Fragment_My fMy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
       // setContentView(R.layout.tab1);
        initView();
        initEvent();
        setSelect(0);


//        viewPager = (ViewPager) findViewById(R.id.viewPager);
//        point_group = (LinearLayout) findViewById(R.id.point_group);
//
//
//        // 初始化图片资源
//        imageList = new ArrayList<ImageView>();
//        for (int i : images) {
//            // 初始化图片资源
//            ImageView imageView = new ImageView(this);
//            imageView.setBackgroundResource(i);
//            imageList.add(imageView);
//
//        }
            // 添加指示小点
//            ImageView point = new ImageView(this);
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(100,
//                    15);
//            params.rightMargin = 20;
//            params.bottomMargin = 10;
//            point.setLayoutParams(params);
//            point.setBackgroundResource(R.mipmap.point_bg);
//            if (i == R.mipmap.p1) {
//                //默认聚焦在第一张
//                point.setBackgroundResource(R.mipmap.point_bg_focus);
//                point.setEnabled(true);
//            } else {
//                point.setEnabled(false);
//            }
//
//            point_group.addView(point);
 //       }


//        viewPager.setAdapter(new MyPageAdapter());
//        // 设置当前viewPager的位置
//        viewPager.setCurrentItem(Integer.MAX_VALUE / 2
//                - (Integer.MAX_VALUE / 2 % imageList.size()));
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//
//            @Override
//            public void onPageSelected(int position) {
//                // 页面切换后调用， position是新的页面位置
//
//                // 实现无限制循环播放
//                position %= imageList.size();
//
//
//                // 把当前点设置为true,将上一个点设为false；并设置point_group图标
//                point_group.getChildAt(position).setEnabled(true);
//               // point_group.getChildAt(position).setBackgroundResource(R.mipmap.point_bg_focus);//设置聚焦时的图标样式
//                point_group.getChildAt(lastPosition).setEnabled(false);
//                //point_group.getChildAt(lastPosition).setBackgroundResource(R.mipmap.point_bg);//上一张恢复原有图标
//                lastPosition = position;
//
//            }
//
//            @Override
//            public void onPageScrolled(int position, float positionOffset,
//                                       int positionOffsetPixels) {
//                // 页面正在滑动时间回调
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//                // 当pageView 状态发生改变的时候，回调
//
//            }
//        });
//        handler.sendEmptyMessageDelayed(0, 3000);


    }

    private void initEvent() {
        //tabHospital.setOnClickListener(this);
        //tabMy.setOnClickListener(this);
        btnhospital.setOnClickListener(this);
        btnmy.setOnClickListener(this);
    }

    private void initView() {
        // tabHospital = (LinearLayout) findViewById(R.id.id_tab1);
        //   tabMy = (LinearLayout) findViewById(R.id.id_tab2);
        btnhospital = (Button) findViewById(R.id.btn1);
        btnmy = (Button) findViewById(R.id.btn2);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                setSelect(0);
                break;
            case R.id.btn2:
                setSelect(1);
                break;
        }
    }

    private void setSelect(int i) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();  //开启一个事物
        hideFragment(transaction);
        switch (i) {
            case 0:
                if (fHospital == null) {
                    fHospital = new Fragment_hospital();
                    transaction.add(R.id.id_content, fHospital);
                } else {
                    transaction.show(fHospital);
                }
                break;
            case 1:
                if (fMy == null) {
                    fMy = new Fragment_My();
                    transaction.add(R.id.id_content, fMy);
                } else {
                    transaction.show(fMy);
                }
                break;
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (fHospital != null) {
            transaction.hide(fHospital);
        }
        if (fMy != null) {
            transaction.hide(fMy);
        }
    }

//    @Override
//    protected void onDestroy() {
//// 停止滚动
//        isRunning = false;
//        super.onDestroy();
//    }
//
//    private class MyPageAdapter extends PagerAdapter {
//        // 需要实现以下四个方法
//
//        @Override
//        public int getCount() {
//            // 获得页面的总数
//            return Integer.MAX_VALUE;
//        }
//
//        @Override
//        public boolean isViewFromObject(View view, Object object) {
//            // 判断view和Object对应是否有关联关系
//            if (view == object) {
//                return true;
//            } else {
//                return false;
//            }
//        }
//
//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            // 获得相应位置上的view； container view的容器，其实就是viewpage自身,
//            // position: viewpager上的位置
//            // 给container添加内容
//            container.addView(imageList.get(position % imageList.size()));
//
//            return imageList.get(position % imageList.size());
//        }
//
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            // 销毁对应位置上的Object
//            // super.destroyItem(container, position, object);
//            container.removeView((View) object);
//            object = null;
//        }
//    }
}

