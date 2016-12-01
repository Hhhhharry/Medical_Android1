package cn.wydewy.medicalapp;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class Fragment_hospital extends Fragment {

    private ViewPager viewPager;
    private LinearLayout point_group;
    //private TextView image_desc;
    private final int[] images = {R.mipmap.p1, R.mipmap.p2, R.mipmap.p3,
            R.mipmap.p2, R.mipmap.p3};
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



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hospital, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        point_group = (LinearLayout) view.findViewById(R.id.point_group);

        // 初始化图片资源
        imageList = new ArrayList<ImageView>();
        View pointView;
        for (int i : images) {
            // 初始化图片资源
            ImageView imageView = new ImageView(getActivity());//
            imageView.setBackgroundResource(i);
            imageList.add(imageView);

            pointView = new View(getActivity());
            pointView.setBackgroundResource(R.drawable.selector_bg);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(25,25);
            if (i == R.mipmap.p1) {
                //默认聚焦在第一张
                pointView.setBackgroundResource(R.drawable.selector_bg);
                pointView.setEnabled(true);
            } else {
                pointView.setEnabled(false);
                params.leftMargin = 15;
            }
            point_group.addView(pointView,params);

        }


        viewPager.setAdapter(new MyPageAdapter());
        // 设置当前viewPager的位置
        viewPager.setCurrentItem(Integer.MAX_VALUE / 2
                - (Integer.MAX_VALUE / 2 % imageList.size()));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // 页面切换后调用， position是新的页面位置

                // 实现无限制循环播放
                position %= imageList.size();

                //			image_desc.setText(imageDescriptions[position]);

                // 把当前点设置为true,将上一个点设为false；并设置point_group图标
                point_group.getChildAt(position).setEnabled(true);

                //point_group.getChildAt(position).setBackgroundResource(R.mipmap.point_bg_focus);//设置聚焦时的图标样式
                point_group.getChildAt(lastPosition).setEnabled(false);

                //point_group.getChildAt(lastPosition).setBackgroundResource(R.mipmap.point_bg);//上一张恢复原有图标
                lastPosition = position;

            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                // 页面正在滑动时间回调

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // 当pageView 状态发生改变的时候，回调

            }
        });
        handler.sendEmptyMessageDelayed(0, 3000);
        return view;
    }

    @Override
    public void onDestroy() {
// 停止滚动
        isRunning = false;
        super.onDestroy();
    }

    private class MyPageAdapter extends PagerAdapter {
        // 需要实现以下四个方法

        @Override
        public int getCount() {
            // 获得页面的总数
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            // 判断view和Object对应是否有关联关系
            if (view == object) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // 获得相应位置上的view； container view的容器，其实就是viewpage自身,
            // position: viewpager上的位置
            // 给container添加内容
            container.addView(imageList.get(position % imageList.size()));

            return imageList.get(position % imageList.size());
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // 销毁对应位置上的Object
            // super.destroyItem(container, position, object);
            container.removeView((View) object);
            object = null;
        }

    }
}
