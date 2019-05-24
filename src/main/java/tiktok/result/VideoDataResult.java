package tiktok.result;

import java.util.List;
import java.util.Map;

public class VideoDataResult {
    private List <String> videoData;
    private List <String> messages;
    private Map<String, String> posts;

    public VideoDataResult(List <String> videoData, List <String> messages, Map<String, String> posts) {
        this.videoData = videoData;
        this.messages = messages;
        this.posts = posts;
    }

    public List <String> getVideoData() {
        return videoData;
    }

    public void setVideoData(List <String> videoData) {
        this.videoData = videoData;
    }

    public List <String> getMessages() {
        return messages;
    }

    public void setMessages(List <String> messages) {
        this.messages = messages;
    }

    public Map <String, String> getPosts() {
        return posts;
    }

    public void setPosts(Map <String, String> posts) {
        this.posts = posts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VideoDataResult that = (VideoDataResult) o;

        if (videoData != null ? !videoData.equals(that.videoData) : that.videoData != null) return false;
        if (messages != null ? !messages.equals(that.messages) : that.messages != null) return false;
        return posts != null ? posts.equals(that.posts) : that.posts == null;
    }

    @Override
    public int hashCode() {
        int result = videoData != null ? videoData.hashCode() : 0;
        result = 31 * result + (messages != null ? messages.hashCode() : 0);
        result = 31 * result + (posts != null ? posts.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "VideoDataResult{" +
                "videoData=" + videoData +
                ", messages=" + messages +
                ", posts=" + posts +
                '}';
    }
}


