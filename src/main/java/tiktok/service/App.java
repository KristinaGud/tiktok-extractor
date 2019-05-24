package tiktok.service;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tiktok.result.TikTokAnalyzedDataResult;
import java.io.IOException;

public class App {
    static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws InterruptedException, IOException {
        Gson gson = new Gson();
        Extractor extractor = new Extractor(gson);
        Collector collector = new Collector();
        UrlGenerator urlGenerator = new UrlGenerator();
        DataAnalyser analyser = new DataAnalyser(collector, extractor, urlGenerator);

        TikTokAnalyzedDataResult VideosResult = analyser.showResults(args[0]);
        LOGGER.info(gson.toJson(VideosResult));
    }
}
