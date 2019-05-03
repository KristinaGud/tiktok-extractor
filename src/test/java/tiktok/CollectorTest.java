package tiktok;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import result.VideoDataResult;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void CommentMessageTextIsGrouped() {
        List<String> urlResponses = new ArrayList <>();
        urlResponses.add("{\"statusCode\":0,\"body\":{\"pageState\":{\"regionAppId\":1233,\"os\":\"linux\",\"region\":\"PL\",\"baseURL\":\"m.tiktok.com\",\"appType\":\"t\",\"fullUrl\":\"https://www.tiktok.com/share/item/comment/list?id=6686441987769371910&count=48&cursor=0&_signature=cJfYMBAfLGFyOs.2PI.XMXCX2C\"},\"commentListData\":[],\"hasMore\":false,\"cursor\":48,\"total\":0},\"errMsg\":null}\n");
        urlResponses.add("{\"statusCode\":200,\"contentType\":\"text/plain\",\"content\":\"\"}\n");
        urlResponses.add("{\"statusCode\":0,\"body\":{\"pageState\":{\"regionAppId\":1233,\"os\":\"linux\",\"region\":\"PL\",\"baseURL\":\"m.tiktok.com\",\"appType\":\"t\",\"fullUrl\":\"https://www.tiktok.com/share/item/comment/list?id=6685918855652445445&count=48&cursor=0&_signature=cJfYMBAfLGFyOs.2PI.XMXCX2C\"},\"commentListData\":[{\"userId\":\"6660063624449146886\",\"text\":\"Love it x\",\"nickname\":\"Jempower\",\"uniqueId\":\"jempower_92\",\"id\":\"6685951746499788806\",\"diggCount\":1,\"createTimestamp\":\"1556694450\",\"coversMedium\":[\"https://p16.muscdn.com/img/musically-maliva-obj/1626198595280901~c5_720x720.jpeg\"],\"coversLarger\":[\"https://p16.muscdn.com/img/musically-maliva-obj/1626198595280901~c5_1080x1080.jpeg\"],\"covers\":[\"https://p16.muscdn.com/img/musically-maliva-obj/1626198595280901~c5_100x100.jpeg\"],\"replyComment\":{}}],\"hasMore\":false,\"cursor\":48,\"total\":1},\"errMsg\":null}\n");
        urlResponses.add("{\"statusCode\":200,\"contentType\":\"text/plain\",\"content\":\"\"}\n");
        urlResponses.add("{\"statusCode\":0,\"body\":{\"pageState\":{\"regionAppId\":1233,\"os\":\"linux\",\"region\":\"PL\",\"baseURL\":\"m.tiktok.com\",\"appType\":\"t\",\"fullUrl\":\"https://www.tiktok.com/share/item/comment/list?id=6686152070195580166&count=48&cursor=0&_signature=cJfYMBAfLGFyOs.2PI.XMXCX2C\"},\"commentListData\":[{\"userId\":\"6559527704805244934\",\"text\":\"That looked easy, but actually it's complicated.\",\"nickname\":\"taylauren3\",\"uniqueId\":\"taylauren3\",\"id\":\"6686195432219181061\",\"diggCount\":0,\"createTimestamp\":\"1556751188\",\"coversMedium\":[\"https://p16.muscdn.com/img/musically-maliva-obj/6559527704805244934~c5_720x720.jpeg\"],\"coversLarger\":[\"https://p16.muscdn.com/img/musically-maliva-obj/6559527704805244934~c5_1080x1080.jpeg\"],\"covers\":[\"https://p16.muscdn.com/img/musically-maliva-obj/6559527704805244934~c5_100x100.jpeg\"],\"replyComment\":{}},{\"userId\":\"6560495962375061509\",\"text\":\"got stunned\uD83D\uDC4D\uD83C\uDFFB\",\"nickname\":\"barhead4lyfe79\",\"uniqueId\":\"barhead4lyfe79\",\"id\":\"6686207806444044293\",\"diggCount\":0,\"createTimestamp\":\"1556754069\",\"coversMedium\":[\"https://p16.muscdn.com/img/musically-maliva-obj/6560495962375061509~c5_720x720.jpeg\"],\"coversLarger\":[\"https://p16.muscdn.com/img/musically-maliva-obj/6560495962375061509~c5_1080x1080.jpeg\"],\"covers\":[\"https://p16.muscdn.com/img/musically-maliva-obj/6560495962375061509~c5_100x100.jpeg\"],\"replyComment\":{}}],\"hasMore\":false,\"cursor\":48,\"total\":2},\"errMsg\":null}\n");
        urlResponses.add("{\"statusCode\":0,\"body\":{\"pageState\":{\"regionAppId\":1233,\"os\":\"linux\",\"region\":\"PL\",\"baseURL\":\"m.tiktok.com\",\"appType\":\"t\",\"fullUrl\":\"https://www.tiktok.com/share/item/comment/list?id=6686257894083529989&count=48&cursor=0&_signature=cJfYMBAfLGFyOs.2PI.XMXCX2C\"},\"commentListData\":[{\"userId\":\"82098281358487552\",\"text\":\"Glee 2da temporada jajaja \uD83D\uDE00\uD83D\uDE05\uD83D\uDE02\uD83D\uDE03\",\"nickname\":\"Jorge Luis Quiliche Tolentino\",\"uniqueId\":\"jquilichet\",\"id\":\"6686615844338106374\",\"diggCount\":0,\"createTimestamp\":\"1556849075\",\"coversMedium\":[\"https://p16.muscdn.com/img/musically-maliva-obj/1628942501948422~c5_720x720.jpeg\"],\"coversLarger\":[\"https://p16.muscdn.com/img/musically-maliva-obj/1628942501948422~c5_1080x1080.jpeg\"],\"covers\":[\"https://p16.muscdn.com/img/musically-maliva-obj/1628942501948422~c5_100x100.jpeg\"],\"replyComment\":{}}],\"hasMore\":false,\"cursor\":48,\"total\":1},\"errMsg\":null}");

        List<String> messages = new ArrayList <>();
        messages.add("Love it x");
        messages.add("That looked easy, but actually it's complicated.");
        messages.add("got stunned\uD83D\uDC4D\uD83C\uDFFB");
        messages.add("Glee 2da temporada jajaja \uD83D\uDE00\uD83D\uDE05\uD83D\uDE02\uD83D\uDE03");

        List<String> textMessages = collector.groupCommentDataResults(urlResponses).getTextMessages();

        Assert.assertEquals(4, textMessages.size());
        Assert.assertEquals(messages, textMessages);
    }

    @Test
    public void CommentAuthorIsGrouped() {
        List<String> urlResponses = new ArrayList <>();
        urlResponses.add("{\"statusCode\":200,\"contentType\":\"text/plain\",\"content\":\"\"}");
        urlResponses.add("{\"statusCode\":0,\"body\":{\"pageState\":{\"regionAppId\":1233,\"os\":\"linux\",\"region\":\"PL\",\"baseURL\":\"m.tiktok.com\",\"appType\":\"t\",\"fullUrl\":\"https://www.tiktok.com/share/item/comment/list?id=6685918855652445445&count=48&cursor=0&_signature=cJfYMBAfLGFyOs.2PI.XMXCX2C\"},\"commentListData\":[{\"userId\":\"6660063624449146886\",\"text\":\"Love it x\",\"nickname\":\"Jempower\",\"uniqueId\":\"jempower_92\",\"id\":\"6685951746499788806\",\"diggCount\":1,\"createTimestamp\":\"1556694450\",\"coversMedium\":[\"https://p16.muscdn.com/img/musically-maliva-obj/1626198595280901~c5_720x720.jpeg\"],\"coversLarger\":[\"https://p16.muscdn.com/img/musically-maliva-obj/1626198595280901~c5_1080x1080.jpeg\"],\"covers\":[\"https://p16.muscdn.com/img/musically-maliva-obj/1626198595280901~c5_100x100.jpeg\"],\"replyComment\":{}}],\"hasMore\":false,\"cursor\":48,\"total\":1},\"errMsg\":null}\n");
        urlResponses.add("{\"statusCode\":0,\"body\":{\"pageState\":{\"regionAppId\":1233,\"os\":\"linux\",\"region\":\"PL\",\"baseURL\":\"m.tiktok.com\",\"appType\":\"t\",\"fullUrl\":\"https://www.tiktok.com/share/item/comment/list?id=6686257894083529989&count=48&cursor=0&_signature=cJfYMBAfLGFyOs.2PI.XMXCX2C\"},\"commentListData\":[{\"userId\":\"82098281358487552\",\"text\":\"Glee 2da temporada jajaja \uD83D\uDE00\uD83D\uDE05\uD83D\uDE02\uD83D\uDE03\",\"nickname\":\"Jorge Luis Quiliche Tolentino\",\"uniqueId\":\"jquilichet\",\"id\":\"6686615844338106374\",\"diggCount\":0,\"createTimestamp\":\"1556849075\",\"coversMedium\":[\"https://p16.muscdn.com/img/musically-maliva-obj/1628942501948422~c5_720x720.jpeg\"],\"coversLarger\":[\"https://p16.muscdn.com/img/musically-maliva-obj/1628942501948422~c5_1080x1080.jpeg\"],\"covers\":[\"https://p16.muscdn.com/img/musically-maliva-obj/1628942501948422~c5_100x100.jpeg\"],\"replyComment\":{}}],\"hasMore\":false,\"cursor\":48,\"total\":1},\"errMsg\":null}");

        List<String> authors = new ArrayList<>();
        authors.add("Jempower");
        authors.add("Jorge Luis Quiliche Tolentino");

        List <String> groupedAuthors = collector.groupCommentDataResults(urlResponses).getAuthors();
        Assert.assertEquals(2, groupedAuthors.size());
        Assert.assertEquals(authors, groupedAuthors);
    }

    @Test
    public void CommentDateIsGrouped() {
        List<String> urlResponses = new ArrayList <>();
        urlResponses.add("{\"statusCode\":0,\"body\":{\"pageState\":{\"regionAppId\":1233,\"os\":\"linux\",\"region\":\"PL\",\"baseURL\":\"m.tiktok.com\",\"appType\":\"t\",\"fullUrl\":\"https://www.tiktok.com/share/item/comment/list?id=6686257894083529989&count=48&cursor=0&_signature=cJfYMBAfLGFyOs.2PI.XMXCX2C\"},\"commentListData\":[{\"userId\":\"82098281358487552\",\"text\":\"Glee 2da temporada jajaja \uD83D\uDE00\uD83D\uDE05\uD83D\uDE02\uD83D\uDE03\",\"nickname\":\"Jorge Luis Quiliche Tolentino\",\"uniqueId\":\"jquilichet\",\"id\":\"6686615844338106374\",\"diggCount\":0,\"createTimestamp\":\"1556849075\",\"coversMedium\":[\"https://p16.muscdn.com/img/musically-maliva-obj/1628942501948422~c5_720x720.jpeg\"],\"coversLarger\":[\"https://p16.muscdn.com/img/musically-maliva-obj/1628942501948422~c5_1080x1080.jpeg\"],\"covers\":[\"https://p16.muscdn.com/img/musically-maliva-obj/1628942501948422~c5_100x100.jpeg\"],\"replyComment\":{}}],\"hasMore\":false,\"cursor\":48,\"total\":1},\"errMsg\":null}");

        Assert.assertTrue(collector.groupCommentDataResults(urlResponses).getDates().contains("1556849075"));
    }

}
