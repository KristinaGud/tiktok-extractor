package tiktok;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static tiktok.DateFormatter.convertListDate;

public class DateFormatterTest {

    @Test
    public void dateFormatConverted() {
        List<String> unixTime = new ArrayList<>();
        unixTime.add("1533633600");
        unixTime.add("1553897340");
        unixTime.add("1556907526");

        List<String> formatedDate = new ArrayList <>();
        formatedDate.add("07 Aug 2018 11:20");
        formatedDate.add("29 Mar 2019 23:09");
        formatedDate.add("03 May 2019 20:18");

        Assert.assertEquals(formatedDate, convertListDate(unixTime));
    }
}
