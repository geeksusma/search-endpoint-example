package es.geeksusma.api.contact;

public class ContactFiltersDTO {

    private String name;
    private String phone;

    public ContactFiltersDTO() {
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
