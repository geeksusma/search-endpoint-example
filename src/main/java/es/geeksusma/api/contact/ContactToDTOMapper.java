package es.geeksusma.api.contact;

import es.geeksusma.domain.contact.Contact;

public class ContactToDTOMapper {

    public ContactDTO map(Contact contact) {
        return new ContactDTO(contact.getName(), contact.getPhone());
    }
}
