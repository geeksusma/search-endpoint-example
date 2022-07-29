package es.geeksusma.domain.contact;

public interface ContactsRepository {

    Integer countHits(String name, String phone);

    Contacts search(String name, String phone, Page page);

}
