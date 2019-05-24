package tiktok.service;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import tiktok.result.VideoDataResult;
import tiktok.service.Collector;

public class CollectorTest {
    static Collector collector = new Collector();
    static VideoDataResult collectedData;

    @BeforeClass
    public static void setup() {
        try {
            collectedData = collector.collectVideoData("avengers");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void collectDataFromFirstFivePagesTest() {
        Assert.assertTrue(collectedData.getVideoData().size() > 50);
    }

    @Test
    public void extractedNumOfIdsAndMessagesAreTheSameTest() {
        int numData = collectedData.getVideoData().size();
        int numMessages = collectedData.getMessages().size();
        Assert.assertEquals(numData,numMessages);
    }

    @Test
    public void mappedElementsNumEqualsUniqueIdsNumTest() {
        int numPosts = collectedData.getPosts().size();
        int ids = (int)collectedData.getVideoData().stream().distinct().count();
        Assert.assertEquals(numPosts, ids);
    }
}
