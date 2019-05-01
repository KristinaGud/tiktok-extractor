package tiktok;

import java.util.*;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

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
                    id + "&count=48&cursor=0";
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
}
