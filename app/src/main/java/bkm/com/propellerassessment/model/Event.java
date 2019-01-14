package bkm.com.propellerassessment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Event implements Serializable {

    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("datetime")
    @Expose
    private String datetime;
    @SerializedName("medication")
    @Expose
    private String medication;
    @SerializedName("medicationtype")
    @Expose
    private String medicationtype;
    @SerializedName("id")
    @Expose
    private Integer id;
    private final static long serialVersionUID = -1917991896327640952L;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getMedicationtype() {
        return medicationtype;
    }

    public void setMedicationtype(String medicationtype) {
        this.medicationtype = medicationtype;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}