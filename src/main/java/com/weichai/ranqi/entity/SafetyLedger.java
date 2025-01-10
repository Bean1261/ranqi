package com.weichai.ranqi.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "safety_ledger")
public class SafetyLedger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String inspectionType;
    private Date checkTime;
    private Date rectificationDeadline;
    private String hazardSeverity;
    private String issueCategory;
    private String subCategory;
    private String detailedCategory;
    private String department;
    private String hazardArea;
    private String personInCharge;
    private String subArea;
    private String detailedArea;
    private String issueObject;
    private String issueMode;
    private Integer lValue;
    private Integer eValue;
    private Integer cValue;
    private Integer dValue;

    @Lob
    private String beforeRectificationPhotos; // 保存图片路径，以逗号分隔

    @Lob
    private String issueDescription;
    private Boolean isFirstDiscovery;
    private String teamLeader;
    private Double deductionScore;
    private String inspector;

    @Lob
    private String afterRectificationPhotos; // 保存图片路径，以逗号分隔

    @Lob
    private String analysisAndMeasures;
    private Date rectificationFinishTime;
//
    private String assessmentPerson;

    public String getAssessmentPerson() {
        return assessmentPerson;
    }

    public void setAssessmentPerson(String assessmentPerson) {
        this.assessmentPerson = assessmentPerson;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInspectionType() {
        return inspectionType;
    }

    public void setInspectionType(String inspectionType) {
        this.inspectionType = inspectionType;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public Date getRectificationDeadline() {
        return rectificationDeadline;
    }

    public void setRectificationDeadline(Date rectificationDeadline) {
        this.rectificationDeadline = rectificationDeadline;
    }

    public String getHazardSeverity() {
        return hazardSeverity;
    }

    public void setHazardSeverity(String hazardSeverity) {
        this.hazardSeverity = hazardSeverity;
    }

    public String getIssueCategory() {
        return issueCategory;
    }

    public void setIssueCategory(String issueCategory) {
        this.issueCategory = issueCategory;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getDetailedCategory() {
        return detailedCategory;
    }

    public void setDetailedCategory(String detailedCategory) {
        this.detailedCategory = detailedCategory;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getHazardArea() {
        return hazardArea;
    }

    public void setHazardArea(String hazardArea) {
        this.hazardArea = hazardArea;
    }

    public String getPersonInCharge() {
        return personInCharge;
    }

    public void setPersonInCharge(String personInCharge) {
        this.personInCharge = personInCharge;
    }

    public String getSubArea() {
        return subArea;
    }

    public void setSubArea(String subArea) {
        this.subArea = subArea;
    }

    public String getDetailedArea() {
        return detailedArea;
    }

    public void setDetailedArea(String detailedArea) {
        this.detailedArea = detailedArea;
    }

    public String getIssueObject() {
        return issueObject;
    }

    public void setIssueObject(String issueObject) {
        this.issueObject = issueObject;
    }

    public String getIssueMode() {
        return issueMode;
    }

    public void setIssueMode(String issueMode) {
        this.issueMode = issueMode;
    }

    public Integer getlValue() {
        return lValue;
    }

    public void setlValue(Integer lValue) {
        this.lValue = lValue;
    }

    public Integer geteValue() {
        return eValue;
    }

    public void seteValue(Integer eValue) {
        this.eValue = eValue;
    }

    public Integer getcValue() {
        return cValue;
    }

    public void setcValue(Integer cValue) {
        this.cValue = cValue;
    }

    public Integer getdValue() {
        return dValue;
    }

    public void setdValue(Integer dValue) {
        this.dValue = dValue;
    }

    public String getBeforeRectificationPhotos() {
        return beforeRectificationPhotos;
    }

    public void setBeforeRectificationPhotos(String beforeRectificationPhotos) {
        this.beforeRectificationPhotos = beforeRectificationPhotos;
    }

    public String getIssueDescription() {
        return issueDescription;
    }

    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }

    public Boolean getFirstDiscovery() {
        return isFirstDiscovery;
    }

    public void setFirstDiscovery(Boolean firstDiscovery) {
        isFirstDiscovery = firstDiscovery;
    }

    public String getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(String teamLeader) {
        this.teamLeader = teamLeader;
    }

    public Double getDeductionScore() {
        return deductionScore;
    }

    public void setDeductionScore(Double deductionScore) {
        this.deductionScore = deductionScore;
    }

    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector;
    }

    public String getAfterRectificationPhotos() {
        return afterRectificationPhotos;
    }

    public void setAfterRectificationPhotos(String afterRectificationPhotos) {
        this.afterRectificationPhotos = afterRectificationPhotos;
    }

    public String getAnalysisAndMeasures() {
        return analysisAndMeasures;
    }

    public void setAnalysisAndMeasures(String analysisAndMeasures) {
        this.analysisAndMeasures = analysisAndMeasures;
    }

    public Date getRectificationFinishTime() {
        return rectificationFinishTime;
    }

    public void setRectificationFinishTime(Date rectificationFinishTime) {
        this.rectificationFinishTime = rectificationFinishTime;
    }

}
