package com.placementpro.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "placement_drives")
public class PlacementDrive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;

    private String jobRole;

    private String location;

    private String packageOffered;

    private String eligibility;

    private String driveDate;

    private String applicationDeadline;

    private String skillsRequired;


    // =========================================
    // GETTERS AND SETTERS
    // =========================================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public String getPackageOffered() {
        return packageOffered;
    }

    public void setPackageOffered(String packageOffered) {
        this.packageOffered = packageOffered;
    }


    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }


    public String getDriveDate() {
        return driveDate;
    }

    public void setDriveDate(String driveDate) {
        this.driveDate = driveDate;
    }


    public String getApplicationDeadline() {
        return applicationDeadline;
    }

    public void setApplicationDeadline(
            String applicationDeadline) {

        this.applicationDeadline =
                applicationDeadline;
    }


    public String getSkillsRequired() {
        return skillsRequired;
    }

    public void setSkillsRequired(
            String skillsRequired) {

        this.skillsRequired =
                skillsRequired;
    }

}