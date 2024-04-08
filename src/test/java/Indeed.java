import org.indeed.Page;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class Indeed {
    @Test public void testIndeed() throws FileNotFoundException {
        ArrayList<String> queryList = new ArrayList<>(Arrays.asList("autocad"));
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