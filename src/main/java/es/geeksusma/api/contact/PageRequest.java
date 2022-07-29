package es.geeksusma.api.contact;

public class PageRequest {

    private Integer page;

    private Integer offset;

    public PageRequest() {
    }


    public Integer getPage() {
        return page;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
