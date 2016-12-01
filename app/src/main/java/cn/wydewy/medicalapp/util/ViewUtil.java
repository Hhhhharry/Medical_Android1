package cn.wydewy.medicalapp.util;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by weiyideweiyi8 on 2015/10/26.
 */
public class ViewUtil {

    private static long lastClickTime ;

    public static final void measureView(View view) {
        ViewGroup.LayoutParams p = view.getLayoutParams();
        if (p == null) {
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int width = ViewGroup.getChildMeasureSpec(0, 0, p.width);
        int height;
        int tempHeight = p.height;
        if (tempHeight > 0) {
            height = View.MeasureSpec.makeMeasureSpec(tempHeight,
                    View.MeasureSpec.EXACTLY);
        } else {
            height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        }
        view.measure(width, height);
    }

    public static boolean isFastDoubleClick(){
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if(0<timeD&&timeD<Constant.ONCE_CLICK_TIME){
            return true;
        }
        lastClickTime = time;
        return false;
    }


}
