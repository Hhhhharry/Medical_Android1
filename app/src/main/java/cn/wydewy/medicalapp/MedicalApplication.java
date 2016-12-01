package cn.wydewy.medicalapp;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import cn.wydewy.medicalapp.model.Customer;
import cn.wydewy.medicalapp.util.Constant;

/**
 * Created by caosh on 2016/10/25.
 */

public class MedicalApplication extends Application {


    private static MedicalApplication medicalApplication;

    private String urlhead = "http://192.168.1.102:8080/";
    private String account;

    private Customer customer;
    private RequestQueue mRequestQueue;



    private String CustomerId,CustomerName;

    public static MedicalApplication getMedicalApplication() {
        return medicalApplication;
    }

    public static void setMedicalApplication(MedicalApplication medicalApplication) {
        MedicalApplication.medicalApplication = medicalApplication;
    }

    private static final String TAG = MedicalApplication.class.getSimpleName();


    @Override
    public void onCreate(){
        super.onCreate();
        medicalApplication = this;
    }

    public RequestQueue getRequestQueue()
    {
        if(mRequestQueue == null)
        {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req,String tag)
    {
        req.setTag(TextUtils.isEmpty(tag)? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req)
    {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public Context getGlobalContext() {
        return getApplicationContext();
    }




    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }


    public String getUrlhead() {

        return urlhead;
    }
    public boolean isLog() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("medical", MODE_PRIVATE);
        return preferences.getBoolean(Constant.IS_LOG,false);
    }

    public void setLog(boolean log) {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("medical",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(Constant.IS_LOG,log);
        editor.commit();
    }
    public String getCustomerId() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("medical",MODE_PRIVATE);
        return preferences.getString(Constant.Customer_Id," ");
    }

    public void setCustomerId(String customerId) {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("medical",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Constant.Customer_Id,customerId);
        editor.commit();
    }
    public String getCustomerName() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("medical",MODE_PRIVATE);
        return preferences.getString(Constant.Customer_Name," ");
    }

    public void setCustomerName(String customerName) {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("medical",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Constant.Customer_Name,customerName);
        editor.commit();
    }
    public Customer getCustomer() {

        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public static final  MedicalApplication getInstance(){
        return  medicalApplication;
    }

}
