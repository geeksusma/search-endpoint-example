package es.geeksusma.api.contact;

public class ContactFiltersDTO {

    private String name;
    private String phone;

    public ContactFiltersDTO() {
    }

    public ContactFiltersDTO(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}
