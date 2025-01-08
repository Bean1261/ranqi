package com.weichai.ranqi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

public class Ledger {

    private Long id;
    private String inspectionType;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate rectificationDeadline;

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
    private Double lValue;
    private Double eValue;
    private Double cValue;
    private Double dValue;
    private List<String> beforeRectificationPhoto;
    private String issueDescription;
    private Boolean isFirstDiscovery;
    private String teamLeader;
    private Double deductionScore;
    private String inspector;
    private List<String> afterRectificationPhoto;
    private String analysisAndMeasures;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate rectificationFinishTime;
    // JSON 字符串字段
    private String beforeRectificationPhotoJson; // 用于存储 JSON 字符串
    private String afterRectificationPhotoJson; // 用于存储 JSON 字符串
    private String assessmentPerson;
    private Double assessmentAmount;
    public String getBeforeRectificationPhotoJson() {
        return beforeRectificationPhotoJson;
    }

    public void setBeforeRectificationPhotoJson(String beforeRectificationPhotoJson) {
        this.beforeRectificationPhotoJson = beforeRectificationPhotoJson;
    }

    public String getAfterRectificationPhotoJson() {
        return afterRectificationPhotoJson;
    }

    public void setAfterRectificationPhotoJson(String afterRectificationPhotoJson) {
        this.afterRectificationPhotoJson = afterRectificationPhotoJson;
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

    public LocalDate getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(LocalDate checkTime) {
        this.checkTime = checkTime;
    }

    public LocalDate getRectificationDeadline() {
        return rectificationDeadline;
    }

    public void setRectificationDeadline(LocalDate rectificationDeadline) {
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

    public Double getlValue() {
        return lValue;
    }

    public void setlValue(Double lValue) {
        this.lValue = lValue;
    }

    public Double geteValue() {
        return eValue;
    }

    public void seteValue(Double eValue) {
        this.eValue = eValue;
    }

    public Double getcValue() {
        return cValue;
    }

    public void setcValue(Double cValue) {
        this.cValue = cValue;
    }

    public Double getdValue() {
        return dValue;
    }

    public void setdValue(Double dValue) {
        this.dValue = dValue;
    }

    public List<String> getBeforeRectificationPhoto() {
        return beforeRectificationPhoto;
    }

    public void setBeforeRectificationPhoto(List<String> beforeRectificationPhoto) {
        this.beforeRectificationPhoto = beforeRectificationPhoto;
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

    public List<String> getAfterRectificationPhoto() {
        return afterRectificationPhoto;
    }

    public void setAfterRectificationPhoto(List<String> afterRectificationPhoto) {
        this.afterRectificationPhoto = afterRectificationPhoto;
    }

    public String getAnalysisAndMeasures() {
        return analysisAndMeasures;
    }

    public void setAnalysisAndMeasures(String analysisAndMeasures) {
        this.analysisAndMeasures = analysisAndMeasures;
    }

    public LocalDate getRectificationFinishTime() {
        return rectificationFinishTime;
    }

    public void setRectificationFinishTime(LocalDate rectificationFinishTime) {
        this.rectificationFinishTime = rectificationFinishTime;
    }

    public String getAssessmentPerson() {
        return assessmentPerson;
    }

    public void setAssessmentPerson(String assessmentPerson) {
        this.assessmentPerson = assessmentPerson;
    }

    public Double getAssessmentAmount() {
        return assessmentAmount;
    }

    public void setAssessmentAmount(Double assessmentAmount) {
        this.assessmentAmount = assessmentAmount;
    }


// Getters and Setters
}
