package es.geeksusma.api.contact;

import java.util.List;

public class ContactsDTO {

    private List<ContactDTO> contacts;

    private Integer pageNumber;
    private Integer total;

    public ContactsDTO() {
    }

    public ContactsDTO(List<ContactDTO> contacts, Integer pageNumber, Integer total) {
        this.contacts = contacts;
        this.pageNumber = pageNumber;
        this.total = total;
    }

    public List<ContactDTO> getContacts() {
        return contacts;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public Integer getTotal() {
        return total;
    }
}
