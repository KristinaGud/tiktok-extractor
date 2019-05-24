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
        UrlGenerator urlGenerator = new UrlGenerator();

        VideoDataResult ladyGagaVideos = collector.collectVideoData("ladygaga");
        Set <String> extractIds = extractor.extractIds(ladyGagaVideos.getVideoData());
        List <String> urlsToFirstPageComments = urlGenerator.generateUrlsToFirstCommentsPage(extractIds);
        List <String> allLadyGagaTags = extractor.extractHashTags(ladyGagaVideos.getMessages());

        System.out.println("Num of unique tags used in video descriptions: " + extractor.countUniqueTags(allLadyGagaTags));
        System.out.println("Top 10 tags in videos descriptions: " + extractor.pickOutTopHashTags(allLadyGagaTags, 10));

        List <String> urlResponses = collector.collectUrlResponses(urlsToFirstPageComments);

        CommentDataResult commentDataResult = extractor.extractCommentDataResults(urlResponses);
        List <String> commentsTextMessages = commentDataResult.getTextMessages();
        List<String> dates = commentDataResult.getDates();

        System.out.println("List of internet users comments related to analyzed videos: " + commentsTextMessages);
        System.out.printf("All comments were posted from %s till %s", extractor.getStartDate(dates), extractor.getEndDate(dates));

    }
}
