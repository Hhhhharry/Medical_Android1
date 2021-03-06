package cn.wydewy.medicalapp.util;

/**
 * Created by caosh on 2016/11/3.
 */

public class Constant {
    public static final String IS_LOG = "is_log";
    public static final String Section_ID = "sectionId";
    public static final String Section_Name = "sectionName";
    public static final String OutPatient_Id = "outpatienId";
    public static final String OutPatient_Name = "outpatienName";
    public static final String Doctor_Name = "doctorName";
    public static final String Order_Price = "orderprice";
    public static final String Order_AMPM = "orderampm";
    public static final String Order_Date = "orderdate";
    public static final String Doctor_Id = "doctorid";
    public static final String Week = "week";
    public static final String Customer_Id = "customerId";
    public static final String Rleasenum_Id = "releasenumid";
    public static final String Customer_Name = "customerName";

    public static final String HOST = "http://192.168.1.119:8080/";
    public static final String API_HOSPITAL_SECTION_LIST = HOST + "framework/section/selectAllSection";
    public static final String API_HOSPITAL_SECTION_DETAIL = HOST + "framework/section/searchSectionInfo";
    public static final String API_HOSPITAL_DETAIL = HOST + "framework/hospital/searchHosInfo";
    public static final String API_DOCTOR_DETAIL = HOST + "framework/doctor/searchDoctorInfo";
    public static final String API_SECTION_DOCTOR_LIST = HOST +"framework/doctor/selectBySection";

    public static final String API_Schedule_ReleaseNum = HOST + "framework/doctor/getDoctorReleaseNumDetailByOutpatientId";
    public static final String API_OUTPATIENT_LIST = HOST + "framework/outpatient/selectByExample";
    public static final String API_RELEASE_DETAIL = HOST +"framework/appointment/getAppointBaseInfoByDoctorId";
    public static final String API_RELEASE_INSERT = HOST +"framework/appointment/InsertAppointmentDetails";
    public static final String API_CUSTOMER_USERNAME = HOST +"framework/customer/loginVerifyByUserName";

    public static final String API_CUSTOMERID_RELEASELIST = HOST + "framework/appointment/getAppointmentDetailsByCustomerId";
    public static final String API_RELEASEID_WITHDRAW = HOST + "framework/appointment/withdrawlAppointmentByReleasenumId";
    public static final long ONCE_CLICK_TIME = 200;
}
