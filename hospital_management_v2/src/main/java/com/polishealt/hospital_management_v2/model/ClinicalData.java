package com.polishealt.hospital_management_v2.model;

import jakarta.persistence.*;

@Entity
@Table(name = "clinical_data")
public class ClinicalData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "clinicalRecord", columnDefinition = "TEXT", nullable = false)
    private String clinicalRecord;

    @ManyToOne
    @JoinColumn(name = "admission_id", nullable = false)
    private AdmissionState admissionState;

    // Getters dhe Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getClinicalRecord() { return clinicalRecord; }
    public void setClinicalRecord(String clinicalRecord) { this.clinicalRecord = clinicalRecord; }

    public AdmissionState getAdmissionState() { return admissionState; }
    public void setAdmissionState(AdmissionState admissionState) { this.admissionState = admissionState; }
}