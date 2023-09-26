package org.main;

public class Constants {
    public final static String BASE_URL = "https://ca.indeed.com/";
    public final static String WHAT_INPUT = "//input[@id='text-input-what']";
    public final static String WHERE_INPUT = "//input[@id='text-input-where']";
    public final static String BUTTON_FIND_JOBS = "//button[contains(text(), 'Find jobs')]";
    public final static String LIST_JOB_LINK = "//div[@id='mosaic-provider-jobcards']//td[@class='resultContent']//a";
    public final static String JOB_TITLE_ELEMENT = "//h1[contains(@class, 'jobsearch-JobInfoHeader-title')]/span";
    public final static String JOB_COMPANY_ELEMENT = "//div[@data-testid='inlineHeader-companyName']//a";
    public final static String JOB_LOCATION_ELEMENT = "//div[@data-testid='job-location']";
    public final static String JOB_TEXT_ELEMENT = "//div[@id='jobDescriptionText']";
    public final static String NEXT_PAGE_ELEMENT = "//a[@data-testid='pagination-page-next']";
    public final static String SELECT_POSTED_ELEMENT = "//button[@id='filter-dateposted']";
    public final static String MODAL_CLOSE_BUTTON = "//div[@aria-modal='true']//button[@aria-label='close']";
    public final static Job FIRST_LINE = new Job("JOB_NAME", "JOB_COMPANY_NAME", "JOB_REGION", "JOB_LOCATION", "JOB_TEXT", "JOB_URL");

}
