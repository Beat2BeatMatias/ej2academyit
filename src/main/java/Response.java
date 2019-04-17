import java.util.Arrays;

public class Response {

    private PagingResponse paging;
    private Agency[] results;

    public Response() {

    }


    public Response(PagingResponse paging, Agency[] results) {
        this.paging = paging;
        this.results = results;
    }

    public PagingResponse getPaging() {
        return paging;
    }

    public void setPaging(PagingResponse paging) {
        this.paging = paging;
    }

    public Agency[] getResults() {
        return results;
    }

    public void setResults(Agency[] results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "Response{" +
                "paging=" + paging +
                ", results=" + Arrays.toString(results) +
                '}';
    }
}
