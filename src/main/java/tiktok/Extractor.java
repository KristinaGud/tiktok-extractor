package tiktok;

import java.text.SimpleDateFormat;
import java.util.*;

import com.google.gson.Gson;
import json.CommentForm;
import json.CommentListData;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import result.Comment;
import result.CommentDataResult;
import result.NextPages;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Date;

import static java.util.stream.Collectors.*;

public class Extractor {
    Logger log = LoggerFactory.getLogger(Extractor.class);
    Gson gson = new Gson();

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

    public NextPages extractNextPagesInfo (String urlResponse) {
        CommentForm commentForm = gson.fromJson(urlResponse, CommentForm.class);
        NextPages nextPages = new NextPages();
            if (commentForm.statusCode==0) {
                boolean hasMore = commentForm.body.hasMore;
                nextPages.setStatus(hasMore);
                if (hasMore) {
                    nextPages.setTotal(commentForm.body.total);
                    nextPages.setUrl(commentForm.body.pageState.fullUrl);
                    nextPages.setNextPageCursor(commentForm.body.cursor);
                }
            }
        return nextPages;
    }

    public CommentDataResult extractCommentDataResults(List<String> urlResponses) {
        List<String> textMessages = new ArrayList <>();
        List<String> authors = new ArrayList <>();
        List<String> dates = new ArrayList <>();
        Map<String, Comment> comments = new HashMap <>();

        for (String response : urlResponses) {
            CommentForm commentForm = gson.fromJson(response, CommentForm.class);
            if (commentForm.statusCode==0) {

                for (CommentListData commentListData: commentForm.body.commentListData) {

                    String textMessage = commentListData.text;
                    textMessages.add(textMessage);

                    String author = commentListData.nickname;
                    authors.add(author);

                    String date = commentListData.createTimestamp;
                    dates.add(date);

                    String messageId = commentListData.id;

                    Comment comment = new Comment(textMessage, author, date);
                    comments.put(messageId, comment);
                }
            }
        }

        return new CommentDataResult(textMessages, authors, dates, comments);
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

    public String convertSingleDate(Long timeStamp) {
        String pattern = "dd MMM yyyy HH:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date(timeStamp * 1000));
    }

    public List <String> convertListDate(List<String> unixTimeStamps) {
        List<String> dates = new ArrayList <>();

        for (String date: unixTimeStamps) {
            dates.add(convertSingleDate(Long.parseLong(date)));
        }
        return dates;
    }

    public String getStartDate(List<String> unixTime) {
        Long startDate = unixTime.stream()
                .map(Long::valueOf)
                .min(Comparator.naturalOrder())
                .get();
        return convertSingleDate(startDate);
    }

    public String getEndDate(List<String> unixTime) {
        Long endDate = unixTime.stream()
                .map(Long::valueOf)
                .max(Comparator.naturalOrder())
                .get();
        return convertSingleDate(endDate);
    }
}
