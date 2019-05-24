package tiktok;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateFormatter {
    public static String convertSingleDate(Long timeStamp) {
        String pattern = "dd MMM yyyy HH:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date(timeStamp * 1000));
    }

    public static List<String> convertListDate(List<String> unixTimeStamps) {
        List<String> dates = new ArrayList<>();

        for (String date: unixTimeStamps) {
            dates.add(convertSingleDate(Long.parseLong(date)));
        }
        return dates;
    }
}
