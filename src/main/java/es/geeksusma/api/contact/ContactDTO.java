package es.geeksusma.api.contact;

public class ContactDTO {

    private String name;
    private String phone;

    public ContactDTO() {
    }

    public ContactDTO(String name, String phone) {
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
