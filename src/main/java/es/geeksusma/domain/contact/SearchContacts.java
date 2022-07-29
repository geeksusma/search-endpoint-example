package es.geeksusma.domain.contact;

public interface SearchContacts {

    int count(String name, String phone);

    Contacts search(String name, String phone, Page page);
}
