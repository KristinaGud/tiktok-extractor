package result;

import java.util.List;
import java.util.Map;

public class VideoDataResult {
    private List <String> videoData;
    private List <String> messages;
    private Map<String, String> posts;

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

    public VideoDataResult(List <String> videoData, List <String> messages, Map<String, String> posts) {
        this.videoData = videoData;
        this.messages = messages;
        this.posts = posts;
    }
}


