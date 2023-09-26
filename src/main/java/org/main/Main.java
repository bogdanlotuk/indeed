package org.main;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> queryList = new ArrayList<>(Arrays.asList("T2V", "T2H"));
        Page page = new Page();
        Page.locationName = "Calgary, AB";
        Page.selectDays = "Last 3 days";
        for(String query : queryList) {
            Page.jobName = query;
            page.setValues();
            page.selectDatePosted();
            page.getLinksFromLists();
            page.collectInfoFromTheLink();
            page.closeBrowser();
        }
        page.writeCsvFile();
    }
}