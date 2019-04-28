package tiktok;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExtractorTest {
    Extractor extractor = new Extractor();

    @Test
    public void matchedStringIsIdTest() {
        List<String> data = new ArrayList <>();
        data.add("Video Id: 6684634106698927365 Post Author: http.nínì");
        Assert.assertEquals(extractor.extractIds(data).iterator().next(),
                "6684634106698927365");
    }

    @Test
    public void matchedStringsAreIdsTest() {
        List<String> data = new ArrayList <>();
        data.add("Video Id: 6684634106698927365 Post Author: http.nínì");
        data.add("Video Id: 6684821989992959238\n" +
                "Post Author: thiccenchilada");
        data.add("Video Id: 6684709138338974978\n" +
                "Post Author: 2162175438");
        String next = extractor.extractIds(data).iterator().next();

        Assert.assertTrue(next
                .contains("6684634106698927365")||next.contains("6684821989992959238")||
        next.contains("6684709138338974978"));

        Assert.assertEquals(3, extractor.extractIds(data).size());
    }

    @Test
    public void allTagsExtracted() {
        List<String> data = new ArrayList <>();
        data.add("Fail\uD83D\uDE02 De azért nem lett olyan rossz#lol#tiktok#i can see you#from#bihend");
        data.add("Guess what brand of phone I use ?? ❤️#slowmotion #transition #l4l.#sg?#me #lol\n" +
                "\n");

        Assert.assertEquals(10, extractor.extractTopHashTags(data, 10).size());

    }

    @Test
    public void extractHashTagsTest() {
        List<String> data = new ArrayList <>();
        data.add("Kurz und knapp mein #spind. #foryoupage #foryou #fortnite #meme #lol");
        data.add("\uD83E\uDD23#trend #foryoupage #huh #lol I’ll give 10$ to the person that duets and duets and does it right\uD83D\uDE0A");
        data.add("#duett med @reagera_giirl jag tittar inte på dem här sakerna#lol");
        data.add("new trend! Sorry for android lag #foryou #fakememe #foryoupage #meme #lol #duet");
        data.add("Who wants to date? You have to be 10-14 \uD83E\uDD2A\uD83D\uDC4B\uD83C\uDFFC\uD83D\uDC95] #foru #yeet #lol #og #oj #omega #lmao #date #singl");

        Assert.assertTrue(extractor.extractTopHashTags(data, 3).containsKey("lol")&&
                extractor.extractTopHashTags(data, 3).containsKey("foryoupage")&&
                extractor.extractTopHashTags(data, 3).containsKey("meme"));
    }

    @Test
    public void extract5Tags() {
        List<String> data = new ArrayList <>();
        data.add("Kurz und knapp mein #spind. #foryoupage #foryou #fortnite #meme #lol");
        data.add("\uD83E\uDD23#trend #foryoupage #huh #lol I’ll give 10$ to the person that duets and duets and does it right\uD83D\uDE0A");
        data.add("#duett med @reagera_giirl jag tittar inte på dem här sakerna#lol");
        data.add("new trend! Sorry for android lag #foryou #fakememe #foryoupage #meme #lol #duet");
        data.add("Who wants to date? You have to be 10-14 \uD83E\uDD2A\uD83D\uDC4B\uD83C\uDFFC\uD83D\uDC95] #foru #yeet #lol #og #oj #omega #lmao #date #singl");
        data.add("Premier tik tok #cochon #friends #france #lol");
        data.add("Fail\uD83D\uDE02 De azért nem lett olyan rossz#lol#tiktok#i can see you#from#bihend");
        data.add("Guess what brand of phone I use ?? ❤️#slowmotion #transition #l4l #sg #me #lol\n" +
                "\n");

        Assert.assertEquals(5, extractor.extractTopHashTags(data, 5).size());
    }


}
