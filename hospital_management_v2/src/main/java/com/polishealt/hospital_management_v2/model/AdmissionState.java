package com.polishealt.hospital_management_v2.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "admissions")
public class AdmissionState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "enteringDate", nullable = false)
    private LocalDateTime enteringDate = LocalDateTime.now();

    @Column(name = "exitingDate")
    private LocalDateTime exitingDate;

    @Column(name = "cause", columnDefinition = "TEXT")
    private String cause;

    @Column(name = "reason", columnDefinition = "TEXT")
    private String reason;

    @Column(name = "discharge", nullable = false)
    private boolean discharge = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
    
    // ✅ 🔹 Shto lidhjen me departamentin
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    @JsonBackReference  // 👈 Kjo e ndalon ciklin rekursiv
    private Department department;

    // Getters dhe Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getEnteringDate() { return enteringDate; }
    public void setEnteringDate(LocalDateTime enteringDate) { this.enteringDate = enteringDate; }

    public LocalDateTime getExitingDate() { return exitingDate; }
    public void setExitingDate(LocalDateTime exitingDate) { this.exitingDate = exitingDate; }

    public String getCause() { return cause; }
    public void setCause(String cause) { this.cause = cause; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public boolean isDischarge() { return discharge; }
    public void setDischarge(boolean discharge) { this.discharge = discharge; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    // ✅ Getter dhe Setter për `department`
    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }
}
