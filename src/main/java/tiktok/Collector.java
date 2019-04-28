package tiktok;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class Collector {
    Logger log = LoggerFactory.getLogger(Collector.class);

    public VideoDataResult collectVideoData(String keyword) throws InterruptedException {
        String url = "https://tiktokapi.ga/search/search_hashtag.php?q=" + keyword;
        ChromeDriver chromeDriver = new ChromeDriver();
        List <WebElement> videoData;
        List <WebElement> videoText;

        chromeDriver.navigate().to(url);

        synchronized (chromeDriver) {
            chromeDriver.wait(3000);
            By.xpath("//tbody[@id='tbl_hashtags']/tr[1]/td[1]").findElement(chromeDriver).click();
            chromeDriver.wait(3000);
            By.xpath("//button[@id='btn_fresh']").findElement(chromeDriver).click();
            chromeDriver.wait(10000);
        }

        ArrayList<String> tabs = new ArrayList<> (chromeDriver.getWindowHandles());
        chromeDriver.switchTo().window(tabs.get(1));

        synchronized (chromeDriver) {
            int postsPerPage = 1;
            for (int i = 0; i < 10; i++) {
                if (By.xpath("//div[@class='card-body']/p[2]").findElements(chromeDriver).size() >= postsPerPage) {
                    By.xpath("//button[@id='btn_more']").findElement(chromeDriver).click();
                    chromeDriver.wait(3000);
                    postsPerPage+=10;
                }
            }
        }

        videoData = chromeDriver.findElements(By.xpath("//div[@class='card-body']/p[2]"));
        videoText = chromeDriver.findElements(By.xpath("//div[@class='card-body']/p[1]"));

        List<String> videoDetails = videoData.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        log.info("video details: " + videoDetails.size() + " objects");

        List<String> messages = videoText.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        log.info("video texts: " + messages.size() + " objects");
        log.info("unique details: " + new HashSet <>(videoDetails).size());

        Map<String, String> posts = new HashMap <>();
        for (int i = 0; i < (videoDetails.size()); i++) {
            posts.put(videoDetails.get(i), messages.get(i));
        }

        chromeDriver.quit();

        return new VideoDataResult(videoDetails, messages, posts);
    }
}
