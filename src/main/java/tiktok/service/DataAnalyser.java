package tiktok.service;

import tiktok.result.CommentDataResult;
import tiktok.result.TikTokAnalyzedDataResult;
import tiktok.result.VideoDataResult;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DataAnalyser {

    private Collector collector;
    private Extractor extractor;
    private UrlGenerator urlGenerator;

    public DataAnalyser(Collector collector, Extractor extractor, UrlGenerator urlGenerator) {
        this.collector = collector;
        this.extractor = extractor;
        this.urlGenerator = urlGenerator;
    }

    public TikTokAnalyzedDataResult showResults(String keyword) throws InterruptedException, IOException {
        VideoDataResult analysedVideos = collector.collectVideoData(keyword);
        Integer numFoundVideos = analysedVideos.getVideoData().size();
        Set<String> extractIds = extractor.extractIds(analysedVideos.getVideoData());
        List<String> urlsToFirstPageComments = urlGenerator.generateUrlsToFirstCommentsPage(extractIds);

        List <String> allTags = extractor.extractHashTags(analysedVideos.getMessages());
        long uniqueTags = extractor.countUniqueTags(allTags);
        Map<String, Long> topTags = extractor.pickOutTopHashTags(allTags, 10);

        List <String> urlResponses = collector.collectUrlResponses(urlsToFirstPageComments);
        CommentDataResult commentDataResult = extractor.extractCommentDataResults(urlResponses);
        List <String> commentsTextMessages = commentDataResult.getTextMessages();
        List<String> dates = commentDataResult.getDates();

        return new TikTokAnalyzedDataResult(extractor.getStartDate(dates), extractor.getEndDate(dates), numFoundVideos, topTags, uniqueTags, commentsTextMessages);
    }
}
