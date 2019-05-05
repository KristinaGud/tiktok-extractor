package result;

public class NextPages {
    String url;
    boolean status;
    int total;
    int NextPageCursor;

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
        return NextPageCursor;
    }

    public void setNextPageCursor(int nextPageCursor) {
        this.NextPageCursor = nextPageCursor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NextPages nextPages = (NextPages) o;

        if (status != nextPages.status) return false;
        if (total != nextPages.total) return false;
        if (NextPageCursor != nextPages.NextPageCursor) return false;
        return url != null ? url.equals(nextPages.url) : nextPages.url == null;
    }

    @Override
    public int hashCode() {
        int result = url != null ? url.hashCode() : 0;
        result = 31 * result + (status ? 1 : 0);
        result = 31 * result + total;
        result = 31 * result + NextPageCursor;
        return result;
    }
}
