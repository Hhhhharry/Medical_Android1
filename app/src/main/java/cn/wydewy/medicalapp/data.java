package cn.wydewy.medicalapp;

import android.app.Application;

/**
 * Created by caosh on 2016/10/25.
 */

public class data extends Application {


    boolean IsLog;
    private String account;
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }


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
