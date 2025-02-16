package com.polishealt.hospital_management_v2.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;


/** Patient entity, mapped to the 'patients' table. */
@Entity
@Table(name = "patients")
public class Patient {

    /** Patient's unique ID (PK, auto-generated). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Patient's first name (not null, max 50 chars). */
    @Column(nullable = false, length = 50)
    private String name;

    /** Patient's last name (not null, max 50 chars). */
    @Column(nullable = false, length = 50)
    private String lastName;

    /** Patient's birth date (not null). Consider Date/LocalDate. */
    @Column(nullable = false)
    private String birthDate;

    /** Department assigned to patient (M-to-1). Lazy loading. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    @JsonBackReference // Prevents JSON recursion.
    private Department department;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getBirthDate() { return birthDate; }
    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }
}