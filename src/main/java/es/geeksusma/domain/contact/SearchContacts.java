package es.geeksusma.domain.contact;

public interface SearchContacts {

    Contacts search(String name, String phone, Page page);
}
