public class PagingResponse {

    private int limit;
    private int offset;
    private int total;

    public PagingResponse(int limit, int offset, int total) {
        this.limit = limit;
        this.offset = offset;
        this.total = total;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "PagingResponse{" +
                "limit=" + limit +
                ", offset=" + offset +
                ", total=" + total +
                '}';
    }
}
