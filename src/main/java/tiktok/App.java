package tiktok;

import result.CommentDataResult;
import result.VideoDataResult;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class App {
    public static void main(String[] args) throws InterruptedException, IOException {
        Collector collector = new Collector();
        Extractor extractor = new Extractor();

        VideoDataResult ladyGagaVideos = collector.collectVideoData("ladygaga");
        Set <String> extractIds = extractor.extractIds(ladyGagaVideos.getVideoData());
        List <String> urlsToFirstPageComments = extractor.generateUrlsToFirstCommentsPage(extractIds);

        System.out.println("Top 10 tags in videos descriptions: " + extractor.extractTopHashTags(ladyGagaVideos.getMessages(), 10));

        List <String> urlResponses = collector.collectUrlResponses(urlsToFirstPageComments);

        CommentDataResult commentDataResult = collector.groupCommentDataResults(urlResponses);
        List <String> commentsTextMessages = commentDataResult.getTextMessages();
        List<String> dates = commentDataResult.getDates();

        System.out.println("List of internet users comments related to analyzed videos: " + commentsTextMessages);
        System.out.printf("All comments were posted from %s till %s", extractor.getStartDate(dates), extractor.getEndDate(dates));



    }
}
