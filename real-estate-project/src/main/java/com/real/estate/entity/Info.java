package com.real.estate.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Info {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer infoId;
    private int projectCompleted;
    private int satisfiedClient;
    private int percentSuccessRate;
    private int noOfExperience;
    
    public Info() {
    }
    
    public Info(Integer infoId, int projectCompleted, int satisfiedClient, int percentSuccessRate, int noOfExperience) {
        this.infoId = infoId;
        this.projectCompleted = projectCompleted;
        this.satisfiedClient = satisfiedClient;
        this.percentSuccessRate = percentSuccessRate;
        this.noOfExperience = noOfExperience;
    }
    
    public Integer getInfoId() {
        return infoId;
    }
    
    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }
    
    public int getProjectCompleted() {
        return projectCompleted;
    }
    
    public void setProjectCompleted(int projectCompleted) {
        this.projectCompleted = projectCompleted;
    }
    
    public int getSatisfiedClient() {
        return satisfiedClient;
    }
    
    public void setSatisfiedClient(int satisfiedClient) {
        this.satisfiedClient = satisfiedClient;
    }
    
    public int getPercentSuccessRate() {
        return percentSuccessRate;
    }
    
    public void setPercentSuccessRate(int percentSuccessRate) {
        this.percentSuccessRate = percentSuccessRate;
    }
    
    public int getNoOfExperience() {
        return noOfExperience;
    }
    
    public void setNoOfExperience(int noOfExperience) {
        this.noOfExperience = noOfExperience;
    }
    
    @Override
    public String toString() {
        return "Info{" +
                "infoId=" + infoId +
                ", projectCompleted=" + projectCompleted +
                ", satisfiedClient=" + satisfiedClient +
                ", percentSuccessRate=" + percentSuccessRate +
                ", noOfExperience=" + noOfExperience +
                '}';
    }
}
