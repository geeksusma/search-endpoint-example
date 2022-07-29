package es.geeksusma.api.contact;

public class PageRequest {

    private Integer page;

    private Integer offset;

    public PageRequest() {
    }

    public PageRequest(Integer page, Integer offset) {
        this.page = page;
        this.offset = offset;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getOffset() {
        return offset;
    }
}
