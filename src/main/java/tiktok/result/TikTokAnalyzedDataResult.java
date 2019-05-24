package tiktok.result;

import java.util.List;
import java.util.Map;

public class TikTokAnalyzedDataResult {
    private String firstCommentFoundAt;
    private String lastCommentFoundAt;
    private Integer analyzedVideosNum;
    private Map<String, Long> topTags;
    private Long uniqueTagsNum;
    private List<String> videosRelatedComments;

    public TikTokAnalyzedDataResult(String dataFrom, String dataTill, Integer vidoesNum, Map <String, Long> topTags, Long tagsNum, List <String> allComments) {
        this.firstCommentFoundAt = dataFrom;
        this.lastCommentFoundAt = dataTill;
        this.analyzedVideosNum = vidoesNum;
        this.topTags = topTags;
        this.uniqueTagsNum = tagsNum;
        this.videosRelatedComments = allComments;
    }

    public String getFirstCommentFoundAt() {
        return firstCommentFoundAt;
    }

    public void setFirstCommentFoundAt(String firstCommentFoundAt) {
        this.firstCommentFoundAt = firstCommentFoundAt;
    }

    public String getLastCommentFoundAt() {
        return lastCommentFoundAt;
    }

    public void setLastCommentFoundAt(String lastCommentFoundAt) {
        this.lastCommentFoundAt = lastCommentFoundAt;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TikTokAnalyzedDataResult that = (TikTokAnalyzedDataResult) o;

        if (firstCommentFoundAt != null ? !firstCommentFoundAt.equals(that.firstCommentFoundAt) : that.firstCommentFoundAt != null) return false;
        if (lastCommentFoundAt != null ? !lastCommentFoundAt.equals(that.lastCommentFoundAt) : that.lastCommentFoundAt != null) return false;
        if (analyzedVideosNum != null ? !analyzedVideosNum.equals(that.analyzedVideosNum) : that.analyzedVideosNum != null)
            return false;
        if (topTags != null ? !topTags.equals(that.topTags) : that.topTags != null) return false;
        if (uniqueTagsNum != null ? !uniqueTagsNum.equals(that.uniqueTagsNum) : that.uniqueTagsNum != null)
            return false;
        return videosRelatedComments != null ? videosRelatedComments.equals(that.videosRelatedComments) : that.videosRelatedComments == null;
    }

    @Override
    public int hashCode() {
        int result = firstCommentFoundAt != null ? firstCommentFoundAt.hashCode() : 0;
        result = 31 * result + (lastCommentFoundAt != null ? lastCommentFoundAt.hashCode() : 0);
        result = 31 * result + (analyzedVideosNum != null ? analyzedVideosNum.hashCode() : 0);
        result = 31 * result + (topTags != null ? topTags.hashCode() : 0);
        result = 31 * result + (uniqueTagsNum != null ? uniqueTagsNum.hashCode() : 0);
        result = 31 * result + (videosRelatedComments != null ? videosRelatedComments.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TikTokAnalyzedDataResult{" +
                "firstCommentFoundAt='" + firstCommentFoundAt + '\'' +
                ", lastCommentFoundAt='" + lastCommentFoundAt + '\'' +
                ", analyzedVideosNum=" + analyzedVideosNum +
                ", topTags=" + topTags +
                ", uniqueTagsNum=" + uniqueTagsNum +
                ", videosRelatedComments=" + videosRelatedComments +
                '}';
    }
}
