package cn.wydewy.medicalapp.model;

/**
 * Created by caosh on 2016/11/15.
 */
public class OutPatient {
    private String outpatientId;
    private String outpatientName;
    private String outpatientLoc;
    private String count;
    private String telephone;
    private String sectionId;
    private String sectionName;
    private String doctorName;

    public String getOutpatientId() {
        return outpatientId;
    }

    public void setOutpatientId(String outpatientId) {
        this.outpatientId = outpatientId;
    }

    public String getOutpatientName() {
        return outpatientName;
    }

    public void setOutpatientName(String outpatientName) {
        this.outpatientName = outpatientName;
    }

    public String getOutpatientLoc() {
        return outpatientLoc;
    }

    public void setOutpatientLoc(String outpatientLoc) {
        this.outpatientLoc = outpatientLoc;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }


}
