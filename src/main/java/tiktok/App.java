package tiktok;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import result.AppResult;
import result.CommentDataResult;
import result.VideoDataResult;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class App {
    static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    static final Gson GSON = new Gson();

    public static void main(String[] args) throws InterruptedException, IOException {
        App app = new App();
        AppResult VideosResult = app.showResults(args[0]);
        LOGGER.info(parseResultToJson(VideosResult));
    }

    public AppResult showResults(String keyword) throws InterruptedException, IOException {
        Collector collector = new Collector();
        Extractor extractor = new Extractor();
        UrlGenerator urlGenerator = new UrlGenerator();

        VideoDataResult analysedVideos = collector.collectVideoData(keyword);
        Integer numFoundVideos = analysedVideos.getVideoData().size();
        Set <String> extractIds = extractor.extractIds(analysedVideos.getVideoData());
        List <String> urlsToFirstPageComments = urlGenerator.generateUrlsToFirstCommentsPage(extractIds);
        List <String> allTags = extractor.extractHashTags(analysedVideos.getMessages());
        long uniqueTags = extractor.countUniqueTags(allTags);
        Map <String, Long> topTags = extractor.pickOutTopHashTags(allTags, 10);
        List <String> urlResponses = collector.collectUrlResponses(urlsToFirstPageComments);
        CommentDataResult commentDataResult = extractor.extractCommentDataResults(urlResponses);
        List <String> commentsTextMessages = commentDataResult.getTextMessages();
        List<String> dates = commentDataResult.getDates();

        return new AppResult(extractor.getStartDate(dates), extractor.getEndDate(dates), numFoundVideos, topTags, uniqueTags, commentsTextMessages);
    }

    public static String parseResultToJson(AppResult appResult) {
        return GSON.toJson(appResult);
    }
}
