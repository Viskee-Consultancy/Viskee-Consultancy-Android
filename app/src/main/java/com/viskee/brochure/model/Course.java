package com.viskee.brochure.model;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Course implements Serializable {

    public Course() {

    }

    private GroupEnum group;
    private String vetCode;
    private String cricosCode;
    private String department;
    private String name;
    private int duration;
    private int durationMin;
    private int durationMax;
    private String durationDetail;
    private String offshoreTuition;
    private String onshoreTuition;
    private String location;
    private List<String> locationList = new ArrayList<>();
    private int unpaidPlacement;
    private String completeServicePeriods;

    public GroupEnum getGroup() {
        return group;
    }

    public void setGroup(GroupEnum group) {
        this.group = group;
    }

    public String getVetCode() {
        return vetCode;
    }

    public void setVetCode(String vetCode) {
        this.vetCode = vetCode;
    }

    public String getCricosCode() {
        return cricosCode;
    }

    public void setCricosCode(String cricosCode) {
        this.cricosCode = cricosCode;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDurationMin() {
        return durationMin;
    }

    public void setDurationMin(int durationMin) {
        this.durationMin = durationMin;
    }

    public int getDurationMax() {
        return durationMax;
    }

    public void setDurationMax(int durationMax) {
        this.durationMax = durationMax;
    }

    public String getDurationDetail() {
        return durationDetail;
    }

    public void setDurationDetail(String durationDetail) {
        this.durationDetail = durationDetail;
    }

    public String getOffshoreTuition() {
        return offshoreTuition;
    }

    public void setOffshoreTuition(String offshoreTuition) {
        this.offshoreTuition = offshoreTuition;
    }

    public String getOnshoreTuition() {
        return onshoreTuition;
    }

    public void setOnshoreTuition(String onshoreTuition) {
        this.onshoreTuition = onshoreTuition;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getLocationList() {
        if (locationList.isEmpty() && StringUtils.isNotEmpty(location)) {
            locationList = Arrays.asList(location.split("/"));
        }
        return locationList;
    }

    public void setLocationList(List<String> locationList) {
        this.locationList = locationList;
    }

    public int getUnpaidPlacement() {
        return unpaidPlacement;
    }

    public void setUnpaidPlacement(int unpaidPlacement) {
        this.unpaidPlacement = unpaidPlacement;
    }

    public String getCompleteServicePeriods() {
        return completeServicePeriods;
    }

    public void setCompleteServicePeriods(String completeServicePeriods) {
        this.completeServicePeriods = completeServicePeriods;
    }

    public String getDurationString() {
        if (duration != 0) {
            return String.valueOf(duration);
        } else if (durationMin != 0 && durationMax != 0) {
            return durationMin + " - " + durationMax;
        } else {
            return "";
        }
    }

    @Override
    public String toString() {
        return "Course{" +
                "vetCode='" + vetCode + '\'' +
                ", department='" + department + '\'' +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                ", durationDetail='" + durationDetail + '\'' +
                ", offshoreTuition=" + offshoreTuition +
                ", onshoreTuition=" + onshoreTuition +
                ", location=" + locationList +
                ", unpaidPlacement=" + unpaidPlacement +
                ", completeServicePeriods='" + completeServicePeriods + '\'' +
                '}';
    }
}
