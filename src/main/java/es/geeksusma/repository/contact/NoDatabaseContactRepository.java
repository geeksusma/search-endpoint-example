package es.geeksusma.repository.contact;

import es.geeksusma.domain.contact.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NoDatabaseContactRepository implements ContactsRepository {

    private final static Integer CONTACTS = 200;

    private final Contacts dataSource;

    public NoDatabaseContactRepository() {
        this.dataSource = Contacts.createEmpty();

        for (int contactNumber = 0; contactNumber < CONTACTS; contactNumber++) {
            this.dataSource.add(createDummySpanishContacts(contactNumber));
        }

        for (int contactNumber = 0; contactNumber < CONTACTS; contactNumber++) {
            this.dataSource.add(createDummyPortugueseContacts(contactNumber));
        }
    }

    Contact createDummySpanishContacts(int contactNumber) {
        return Contact.of(Name.of("Juan", "Test " + contactNumber), Phone.of("34", String.valueOf(contactNumber)));
    }

    Contact createDummyPortugueseContacts(int contactNumber) {
        return Contact.of(Name.of("Joao", "Test " + contactNumber), Phone.of("351", String.valueOf(contactNumber)));
    }

    public Integer countHits(String name, String phone) {


        return (int) filteredStream(name, phone, dataSource.getContacts()).count();

    }

    public Contacts search(String name, String phone, Page page) {
        final List<Contact> collectedContacts = filteredStream(name, phone, dataSource.getContacts()).collect(Collectors.toList());

        return applyPagination(page, collectedContacts);
    }

    private Contacts applyPagination(Page page, List<Contact> collectedContacts) {
        final Contacts filteredContacts = Contacts.createEmpty();
        int currentOffset = 0;
        int maxOffset = calculateMaxOffset(page.offset, collectedContacts.size());
        collectElements(page, collectedContacts, filteredContacts, currentOffset, maxOffset);
        return filteredContacts;
    }

    private void collectElements(Page page, List<Contact> collectedContacts, Contacts filteredContacts, int currentOffset, int maxOffset) {
        if (page.getStartPosition() < 0) {
            return;
        }
        int position = calculateStartPosition(page, collectedContacts, maxOffset);

        for (; currentOffset < maxOffset; currentOffset++) {
            filteredContacts.add(collectedContacts.get(position));
            position++;
        }
    }

    private int calculateStartPosition(Page page, List<Contact> collectedContacts, int maxOffset) {
        int position = page.getStartPosition();
        if (collectedContacts.size() == maxOffset) {
            position = 0;
        }
        return position;
    }

    private int calculateMaxOffset(Integer requestedOffset, int hits) {
        return hits >= requestedOffset ? requestedOffset : hits;
    }

    private Stream<Contact> filteredStream(String name, String phone, List<Contact> contacts) {
        return contacts.stream().filter(c -> filter(name, phone, c));
    }


    private boolean filter(String name, String phone, Contact c) {
        boolean containsName = true;
        boolean containsPhone = true;
        if (name != null && !"".equals(name)) {
            containsName = c.getName().contains(name);
        }
        if (phone != null && !"".equals(phone)) {
            containsPhone = c.getPhone().contains(phone);
        }
        return containsName && containsPhone;
    }

}
