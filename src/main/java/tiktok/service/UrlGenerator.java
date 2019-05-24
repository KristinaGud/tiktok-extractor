package tiktok.service;

import tiktok.result.NextPages;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UrlGenerator {

    public List<String> generateUrlsToFirstCommentsPage(Set<String> ids) {

        List<String> urlsToComments = new ArrayList<>();
        for (String id: ids) {
            String firstPage = "https://www.tiktok.com/share/item/comment/list?id=" +
                    id + "&count=48&cursor=0&_signature=cJfYMBAfLGFyOs.2PI.XMXCX2C";
            urlsToComments.add(firstPage);
        }

        return urlsToComments;
    }

    public List<String> generateUrlsToNextCommentsPages(NextPages nextPages) {
        String url = nextPages.getUrl();
        int numOfpages = Double.valueOf(Math.ceil(nextPages.getTotal() / 48d)).intValue();
        int nextPageCursor = nextPages.getNextPageCursor();
        List<String> nextUrls = new ArrayList <>();

        if (nextPages.isStatus()) {
            int increase = 0;
            for (int i = 0; i < numOfpages; i++) {
                nextUrls.add(url.replaceAll("cursor=\\d+",
                        "cursor="+String.valueOf(nextPageCursor+increase)));
                increase+=48;
            }
        }

        return nextUrls;
    }
}
