package es.geeksusma.domain.contact;

import es.geeksusma.repository.contact.NoDatabaseContactRepository;

public class SearchContactsComponent implements SearchContacts {

    private final ContactsRepository contactsRepository;

    public SearchContactsComponent() {
        contactsRepository = new NoDatabaseContactRepository();
    }

    @Override
    public int count(String name, String phone) {
        return contactsRepository.countHits(name, phone);
    }

    @Override
    public Contacts search(String name, String phone, Page page) {
        return contactsRepository.search(name, phone, page);
    }
}
