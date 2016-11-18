package cn.wydewy.medicalapp.model;

/**
 * Created by caosh on 2016/11/15.
 */
public class ScheduleModel {
    private String doctorid;
    private String doctorname;
    private ReleaseNum releaseNumList;

    public String getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(String doctorid) {
        this.doctorid = doctorid;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public void setDoctorname(String doctorname) {
        this.doctorname = doctorname;
    }

    public ReleaseNum getReleaseNumList() {
        return releaseNumList;
    }

    public void setReleaseNumList(ReleaseNum releaseNumList) {
        this.releaseNumList = releaseNumList;
    }


}
