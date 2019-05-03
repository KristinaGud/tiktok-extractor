package tiktok;

import java.text.SimpleDateFormat;
import java.util.*;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Date;

import static java.util.stream.Collectors.*;

public class Extractor {
    Logger log = LoggerFactory.getLogger(Extractor.class);

    public Set<String> extractIds (List<String> videoData) {
        Set<String > ids = new HashSet <>();

        for (String text: videoData) {
            String patternString = "Video Id: (\\d+)";
            Pattern pattern = Pattern.compile(patternString);
            Matcher matcher = pattern.matcher(text);
            if (matcher.find()) {
                ids.add(matcher.group(1));
            }
        }

        log.info("found unique ids: " + ids.size());

        return ids;
    }

    public List<String> generateUrlsToFirstCommentsPage(Set<String> ids) {

        List<String> urlsToComments = new ArrayList <>();
        for (String id: ids) {
            String firstPage = "https://www.tiktok.com/share/item/comment/list?id=" +
                    id + "&count=48&cursor=0&_signature=cJfYMBAfLGFyOs.2PI.XMXCX2C";
            urlsToComments.add(firstPage);
        }

        return urlsToComments;
    }

    public Map<String, Long> extractTopHashTags(List<String> messages, int tagsNum) {
        String [] allText = (Arrays.stream(messages.toString()
                .split("#"))
                .skip(1)
                .toArray(String[]::new));

        List<String> tags = new ArrayList <>();

        for (String text: allText) {
            String stringPattern = "(\\w+)\\s?,?.*";
            Pattern pattern = Pattern.compile(stringPattern);
            Matcher matcher = pattern.matcher(text);

            while (matcher.find()) {
                tags.add(matcher.group(1));
            }
        }

        Map<String, Long> topTags = tags.stream().collect(groupingBy(Function.identity(), counting()))
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(entry -> -entry.getValue()))
                .limit(tagsNum)
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));

        log.info(topTags.entrySet().toString());

        return topTags;
    }

    public String formatDate (Long timeStamp) {
        String pattern = "dd MMM yyyy HH:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date(timeStamp * 1000));
    }

    public List <String> convertDateFormat(List<String> unixTimeStamps) {
        List<String> dates = new ArrayList <>();

        for (String date: unixTimeStamps) {
            dates.add(formatDate(Long.parseLong(date)));
        }

        return dates;
    }

    public String getStartDate(List<String> unixTime) {
        Long startDate = unixTime.stream()
                .map(Long::valueOf)
                .min(Comparator.naturalOrder())
                .get();
        return formatDate(startDate);
    }

    public String getEndDate(List<String> unixTime) {
        Long endDate = unixTime.stream()
                .map(Long::valueOf)
                .max(Comparator.naturalOrder())
                .get();
        return formatDate(endDate);

    }
}
