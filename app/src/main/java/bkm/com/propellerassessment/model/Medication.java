package bkm.com.propellerassessment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Medication implements Serializable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("medicationtype")
    @Expose
    private String medicationtype;
    private final static long serialVersionUID = 2561405792025566590L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMedicationtype() {
        return medicationtype;
    }

    public void setMedicationtype(String medicationtype) {
        this.medicationtype = medicationtype;
    }

}