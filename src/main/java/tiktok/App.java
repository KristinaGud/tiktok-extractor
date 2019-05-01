package tiktok;

import result.VideoDataResult;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class App {
    public static void main(String[] args) throws InterruptedException, IOException {
        Collector collector = new Collector();
        Extractor extractor = new Extractor();

        VideoDataResult ladygaga = collector.collectVideoData("ladygaga");
        Set <String> extractIds = extractor.extractIds(ladygaga.getVideoData());
        List <String> urlsToFirstPageComments = extractor.generateUrlsToFirstCommentsPage(extractIds);

        System.out.println("Top 10 hashtags in video description: " + extractor.extractTopHashTags(ladygaga.getMessages(), 10));
        collector.collectCommentData(urlsToFirstPageComments).forEach(System.out::println);
    }
}
