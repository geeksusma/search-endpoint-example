package es.geeksusma.domain.contact;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Contacts {

    private final List<Contact> contacts;

    private Contacts() {
        this.contacts = new LinkedList<>();
    }

    public static Contacts createEmpty() {
        return new Contacts();
    }

    public void add(Contact contact) {
        this.contacts.add(contact);
    }

    public Integer getSize() {
        return contacts.size();
    }

    public Contact get(Integer index) {
        return contacts.get(index);
    }

    public List<Contact> getContacts() {
        return new LinkedList<>(contacts);
    }
}
