package tiktok.service;

import org.junit.Assert;
import org.junit.Test;
import tiktok.result.NextPages;
import tiktok.service.UrlGenerator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UrlGeneratorTest {

    UrlGenerator urlGenerator = new UrlGenerator();

    @Test
    public void urlsToFirstPageAreGenerated() {
        Set<String> ids = new HashSet<>();
        ids.add("6685975523882978565");
        ids.add("6685969384889912582");
        ids.add("6685956087025241350");

        List<String> urls = new ArrayList<>();
        urls.add("https://www.tiktok.com/share/item/comment/list?id=6685975523882978565&count=48&cursor=0");
        urls.add("https://www.tiktok.com/share/item/comment/list?id=6685969384889912582&count=48&cursor=0");
        urls.add("https://www.tiktok.com/share/item/comment/list?id=6685956087025241350&count=48&cursor=0");

        Assert.assertTrue(urlGenerator.generateUrlsToFirstCommentsPage(ids).contains(urls.iterator().next()));
    }

    @Test
    public void properSizeOfListToNextPagesGenerated() {
        NextPages nextPages = new NextPages();
        nextPages.setUrl("https://www.tiktok.com/share/item/comment/list?id=937864062438662&count=48&cursor=0&_signature=Huy4ihAeQiYcQa9MNuoS0x7suJ");
        nextPages.setStatus(true);
        nextPages.setNextPageCursor(48);
        nextPages.setTotal(3883);

        Assert.assertEquals(81, urlGenerator.generateUrlsToNextCommentsPages(nextPages).size());
    }

    @Test
    public void urlsToNexPagesAreGenerated() {
        NextPages nextPages = new NextPages();
        nextPages.setUrl("https://www.tiktok.com/share/item/comment/list?id=6684&count=48&cursor=0&_signature=HuyJ");
        nextPages.setStatus(true);
        nextPages.setTotal(100);
        nextPages.setNextPageCursor(48);

        List<String> urls = new ArrayList <>();
        urls.add("https://www.tiktok.com/share/item/comment/list?id=6684&count=48&cursor=48&_signature=HuyJ");
        urls.add("https://www.tiktok.com/share/item/comment/list?id=6684&count=48&cursor=96&_signature=HuyJ");
        urls.add("https://www.tiktok.com/share/item/comment/list?id=6684&count=48&cursor=144&_signature=HuyJ");

        Assert.assertEquals(urls, urlGenerator.generateUrlsToNextCommentsPages(nextPages));
    }

}
