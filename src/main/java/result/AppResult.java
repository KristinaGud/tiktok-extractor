package result;

import java.util.List;
import java.util.Map;

public class AppResult {
    private String dataFrom;
    private String dataTo;
    private Integer analyzedVideosNum;
    private Map<String, Long> topTags;
    private Long uniqueTagsNum;
    private List<String> videosRelatedComments;

    public AppResult(String dataFrom, String dataTill, Integer vidoesNum, Map <String, Long> topTags, Long tagsNum, List <String> allComments) {
        this.dataFrom = dataFrom;
        this.dataTo = dataTill;
        this.analyzedVideosNum = vidoesNum;
        this.topTags = topTags;
        this.uniqueTagsNum = tagsNum;
        this.videosRelatedComments = allComments;
    }

    public String getDataFrom() {
        return dataFrom;
    }

    public void setDataFrom(String dataFrom) {
        this.dataFrom = dataFrom;
    }

    public String getDataTo() {
        return dataTo;
    }

    public void setDataTo(String dataTo) {
        this.dataTo = dataTo;
    }

    public Integer getAnalyzedVideosNum() {
        return analyzedVideosNum;
    }

    public void setAnalyzedVideosNum(Integer analyzedVideosNum) {
        this.analyzedVideosNum = analyzedVideosNum;
    }

    public Map <String, Long> getTopTags() {
        return topTags;
    }

    public void setTopTags(Map <String, Long> topTags) {
        this.topTags = topTags;
    }

    public Long getUniqueTagsNum() {
        return uniqueTagsNum;
    }

    public void setUniqueTagsNum(Long uniqueTagsNum) {
        this.uniqueTagsNum = uniqueTagsNum;
    }

    public List <String> getVideosRelatedComments() {
        return videosRelatedComments;
    }

    public void setVideosRelatedComments(List <String> videosRelatedComments) {
        this.videosRelatedComments = videosRelatedComments;
    }
}
