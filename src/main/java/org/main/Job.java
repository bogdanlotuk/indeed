package org.main;

public class Job {
    private String _jobName;
    private String _jobUrl;
    private String _jobCompanyName;
    private String _jobRegion;
    private String _jobLocation;
    private String _jobText;


    public Job(String jobName, String jobCompanyName, String jobRegion, String jobLocation, String jobText,String jobUrl){
            _jobName = jobName;
            _jobCompanyName = jobCompanyName;
            _jobRegion = jobRegion;
            _jobLocation = jobLocation;
            _jobText = jobText;
            _jobUrl = jobUrl;
    }

    public String getJobName(){
        return _jobName;
    }
    public String getJobUrl(){ return _jobUrl; }
    public String getJobRegion(){ return _jobRegion; }
    public String getJobLocation(){ return _jobLocation; }
    public String getJobCompanyName(){ return _jobCompanyName; }
    public String getJobText(){ return _jobText; }

    public String toString(){
        return String.format("Job name: %s, company: %s, region: %s, location: %s, url: %", getJobName(), getJobCompanyName(), getJobRegion(), getJobLocation(), getJobText(), getJobUrl());
    }

}
