import org.main.*;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class Tests  {
    @Test public void test1() throws FileNotFoundException {
        ArrayList<String> queryList = new ArrayList<>(Arrays.asList("autocad", "it support technician"));
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