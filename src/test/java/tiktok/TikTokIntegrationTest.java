package tiktok;

import org.junit.Assert;
import org.junit.Test;
import result.CommentDataResult;
import result.NextPages;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TikTokIntegrationTest {
    UrlGenerator urlGenerator = new UrlGenerator();
    Collector collector = new Collector();
    Extractor extractor = new Extractor();

    @Test
    public void extractingCommentsFromNextPages() throws IOException {
        Set<String> extractIds = new HashSet<>();
        extractIds.add("6686752822156872965");
        List <String> urlsToFirstPageComments = urlGenerator.generateUrlsToFirstCommentsPage(extractIds);

        String response = collector.collectUrlResponses(urlsToFirstPageComments).get(0);

        NextPages nextPagesInfo = extractor.extractNextPagesInfo(response);
        List <String> urlsToNextPagesComments = urlGenerator.generateUrlsToNextCommentsPages(nextPagesInfo);

        List <String> urlsResponsesFromNextLinks = collector.collectUrlResponses(urlsToNextPagesComments);

        Assert.assertTrue(extractor.extractNextPagesInfo(response).getTotal()>=200);

        CommentDataResult commentDataResult = extractor.extractCommentDataResults(urlsResponsesFromNextLinks);
        Assert.assertTrue(commentDataResult.getAuthors().contains(" Klaudia Hyla"));
        Assert.assertTrue(commentDataResult.getTextMessages().contains("Ej no dobra, ale serio jestes mega podobna do Evelyn z rvd \uD83D\uDE02\uD83E\uDDE1"));
    }
}
