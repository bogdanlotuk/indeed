package org.indeed;

public class Job {
    private String _jobName;
    private String _jobUrl;
    private String _jobCompanyName;
    private String _jobPosition;
    private String _jobLocation;
    private String _jobText;


    public Job(String jobName, String jobCompanyName, String jobPosition, String jobLocation, String jobText,String jobUrl){
            _jobName = jobName;
            _jobCompanyName = jobCompanyName;
            _jobPosition = jobPosition;
            _jobLocation = jobLocation;
            _jobText = jobText;
            _jobUrl = jobUrl;
    }

    public String getJobName(){
        return _jobName;
    }
    public String getJobUrl(){ return _jobUrl; }
    public String getJobPosition(){ return _jobPosition; }
    public String getJobLocation(){ return _jobLocation; }
    public String getJobCompanyName(){ return _jobCompanyName; }
    public String getJobText(){ return _jobText; }

    public String toString(){
        return String.format("Job name: %s, company: %s, region: %s, location: %s, url: %", getJobName(), getJobCompanyName(), getJobPosition(), getJobLocation(), getJobText(), getJobUrl());
    }

}
