package result;

import java.util.List;
import java.util.Map;

public class CommentDataResult {
    private List<String> textMessages;
    private List<String> authors;
    private List<String> dates;
    private Map<String, Comment> comments;

    public CommentDataResult(List <String> textMessages, List <String> authors, List <String> dates, Map <String, Comment> comments) {
        this.textMessages = textMessages;
        this.authors = authors;
        this.dates = dates;
        this.comments = comments;
    }

    public List <String> getTextMessages() {
        return textMessages;
    }

    public void setTextMessages(List <String> textMessages) {
        this.textMessages = textMessages;
    }

    public List <String> getAuthors() {
        return authors;
    }

    public void setAuthors(List <String> authors) {
        this.authors = authors;
    }

    public List <String> getDates() {
        return dates;
    }

    public void setDates(List <String> dates) {
        this.dates = dates;
    }

    public Map <String, Comment> getComments() {
        return comments;
    }

    public void setComments(Map <String, Comment> comments) {
        this.comments = comments;
    }
}
