package org.main;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static org.main.Constants.*;

public class Page {
    public static String jobName;
    public static String locationName;
    public static String selectDays;
    public ArrayList<String> linksFromList = new ArrayList<>();
    public static ArrayList<Job> jobsWithUrl = new ArrayList<>();
    public static SelenideElement whatInput = $x(WHAT_INPUT);
    public static SelenideElement whereInput = $x(WHERE_INPUT);
    public static SelenideElement buttonFindJobs = $x(BUTTON_FIND_JOBS);
    public static SelenideElement nextPageElement = $x(NEXT_PAGE_ELEMENT);
    public static SelenideElement modalCloseButton = $x(MODAL_CLOSE_BUTTON);
    public static SelenideElement selectPostedElement = $x(SELECT_POSTED_ELEMENT);
    public String jobCompanyName;
    public String jobLocation;
    public String jobText;


    public void setValues(){
        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        Configuration.browserSize = "1366x768";

        open(BASE_URL);
        sleep(5000);
        whatInput.click();
        whatInput.sendKeys(jobName);
        whereInput.click();
        if($x(CLEAR_LOCATION).exists()){
            $x(CLEAR_LOCATION).click();
        }
        whereInput.sendKeys(locationName);

        sleep(5000);
        buttonFindJobs.click();
        sleep(5000);
    }

    public void closeModal(){
        if(modalCloseButton.isDisplayed()){
            modalCloseButton.click();
        }
    }

    public void getLinksFromLists(){
        while(($$x(LIST_JOB_LINK).size() > 0) || modalCloseButton.isDisplayed()) {
            closeModal();

            List<SelenideElement> itemsFromList = $$x(LIST_JOB_LINK);
            for (SelenideElement item : itemsFromList) {
                item.hover();
                sleep(1000);
                String link = item.attr("href").replaceAll(",","");
                linksFromList.add(link);
            }

            if(nextPageElement.isDisplayed()) {
                nextPageElement.click();
            } else {
                break;
            }
            sleep(10000);
        }
    }

    public void collectInfoFromTheLink(){
        for(String link : linksFromList){
            open(link);
            closeModal();

            SelenideElement jobTitleElement = $x(JOB_TITLE_ELEMENT);
            String jobTitleName= jobTitleElement.text().replaceAll(","," / ").replaceAll("\\s+"," ");
            if($x(JOB_COMPANY_ELEMENT).exists()){
                SelenideElement jobCompanyNameElement = $x(JOB_COMPANY_ELEMENT);
                jobCompanyName = jobCompanyNameElement.text().replaceAll(","," / ").replaceAll("\\s+"," ");
            }
            if($x(JOB_LOCATION_ELEMENT).exists()){
                SelenideElement jobLocationElement = $x(JOB_LOCATION_ELEMENT);
                jobLocation = jobLocationElement.text().replaceAll(","," ").replaceAll("\\s+"," ");
            }
            if($x(JOB_TEXT_ELEMENT).exists()){
                SelenideElement jobTextElement = $x(JOB_TEXT_ELEMENT);
                jobText = jobTextElement.text().replaceAll(","," ").replaceAll("\\s+"," ");
            }
            Job job = new Job(jobTitleName, jobCompanyName, Page.jobName, jobLocation, jobText, link);
            jobsWithUrl.add(job);
            System.out.println(jobTitleName);
            sleep(2000);
        }
    }

    public void selectDatePosted(){
        selectPostedElement.click();
        sleep(1000);
        SelenideElement selectPostedDays = $x("//ul[@id='filter-dateposted-menu']/li/a[contains(text(), '" + selectDays + "')]");
        selectPostedDays.click();
        sleep(1000);
    }

    public void closeBrowser(){
        Selenide.closeWindow();
    }

    public void writeCsvFile() throws FileNotFoundException {
        jobsWithUrl.add(0, FIRST_LINE);
        Date date = new Date();
        Format formatter = new SimpleDateFormat("yyyy-MM-dd__HH-mm-ss");
        String dateString = formatter.format(date);

        File csvFile = new File("results/" + dateString + "__" + locationName.replaceAll(", ", "_") + ".csv");
        PrintWriter out = new PrintWriter(csvFile);

        for(Job job : jobsWithUrl){
            out.printf("%s, %s, %s, %s, %s, %s\n", job.getJobName(), job.getJobCompanyName(), job.getJobPosition(), job.getJobLocation(), job.getJobText(), job.getJobUrl());
        }

        out.close();
    }

}
