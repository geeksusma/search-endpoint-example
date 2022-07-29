package es.geeksusma.domain.contact;

public class Page {

    public final Integer number;
    public final Integer offset;

    private Page(Integer number, Integer offset) {
        this.number = number;
        this.offset = offset;
    }

    public static Page of(Integer number, Integer offset) {
        return new Page(number, offset);
    }

    public int getStartPosition() {
        return this.number == 1 ? 0 : (this.number - 1) * this.offset;

    }
}
