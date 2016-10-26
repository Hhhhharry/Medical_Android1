package cn.wydewy.medicalapp;

import android.app.Application;

/**
 * Created by caosh on 2016/10/25.
 */

public class data extends Application {


    boolean IsLog;
    public boolean isLog() {
        return IsLog;
    }

    public void setLog(boolean log) {
        IsLog = log;
    }
    @Override
    public void onCreate(){
        IsLog = false;
        super.onCreate();
    }

}
