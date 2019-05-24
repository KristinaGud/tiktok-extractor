package tiktok.result;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentDataResult that = (CommentDataResult) o;

        if (textMessages != null ? !textMessages.equals(that.textMessages) : that.textMessages != null) return false;
        if (authors != null ? !authors.equals(that.authors) : that.authors != null) return false;
        if (dates != null ? !dates.equals(that.dates) : that.dates != null) return false;
        return comments != null ? comments.equals(that.comments) : that.comments == null;
    }

    @Override
    public int hashCode() {
        int result = textMessages != null ? textMessages.hashCode() : 0;
        result = 31 * result + (authors != null ? authors.hashCode() : 0);
        result = 31 * result + (dates != null ? dates.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CommentDataResult{" +
                "textMessages=" + textMessages +
                ", authors=" + authors +
                ", dates=" + dates +
                ", comments=" + comments +
                '}';
    }
}
