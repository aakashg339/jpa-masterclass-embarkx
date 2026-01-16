package com.demo.healthcare.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class PrescriptionId implements Serializable {
    
    private Long doctorId;
    
    private Long patientId;

    public PrescriptionId() {
    }

    public PrescriptionId(Long doctorId, Long patientId) {
        this.doctorId = doctorId;
        this.patientId = patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        
        if(!(o instanceof PrescriptionId))
            return false;
        
        PrescriptionId that = (PrescriptionId) o;

        return Objects.equals(doctorId, that.doctorId) &&
            Objects.equals(patientId, that.patientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctorId, patientId);
    }
    
}
