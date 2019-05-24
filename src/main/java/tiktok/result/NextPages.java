package tiktok.result;

public class NextPages {
    private String url;
    private boolean status;
    private int total;
    private int nextPageCursor;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getNextPageCursor() {
        return nextPageCursor;
    }

    public void setNextPageCursor(int nextPageCursor) {
        this.nextPageCursor = nextPageCursor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NextPages nextPages = (NextPages) o;

        if (status != nextPages.status) return false;
        if (total != nextPages.total) return false;
        if (nextPageCursor != nextPages.nextPageCursor) return false;
        return url != null ? url.equals(nextPages.url) : nextPages.url == null;
    }

    @Override
    public int hashCode() {
        int result = url != null ? url.hashCode() : 0;
        result = 31 * result + (status ? 1 : 0);
        result = 31 * result + total;
        result = 31 * result + nextPageCursor;
        return result;
    }

    @Override
    public String toString() {
        return "NextPages{" +
                "url='" + url + '\'' +
                ", status=" + status +
                ", total=" + total +
                ", nextPageCursor=" + nextPageCursor +
                '}';
    }
}
