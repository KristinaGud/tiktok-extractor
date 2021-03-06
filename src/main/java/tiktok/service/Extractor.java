package tiktok.service;

import java.util.*;

import com.google.gson.Gson;
import tiktok.json.CommentForm;
import tiktok.json.CommentListData;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import tiktok.result.Comment;
import tiktok.result.CommentDataResult;
import tiktok.result.NextPages;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.*;
import static tiktok.service.DateFormatter.convertSingleDate;

public class Extractor {
    private final Logger LOGGER = LoggerFactory.getLogger(Extractor.class);

    private Gson GSON;

    public Extractor(Gson GSON) {
        this.GSON = GSON;
    }

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

        LOGGER.info("found unique ids: " + ids.size());

        return ids;
    }

    public NextPages extractNextPagesInfo (String urlResponse) {
        CommentForm commentForm = GSON.fromJson(urlResponse, CommentForm.class);
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
            CommentForm commentForm = GSON.fromJson(response, CommentForm.class);
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
    
    public List <String> extractHashTags(List<String> messages) {
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

        return tags;
    }

    public Map<String, Long> pickOutTopHashTags(List<String> tags, int tagsNum) {

        Map<String, Long> topTags = tags.stream().collect(groupingBy(Function.identity(), counting()))
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(entry -> -entry.getValue()))
                .limit(tagsNum)
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));

        return topTags;
    }

    public long countUniqueTags (List<String> tags) {
        return tags.stream().distinct().count();
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
