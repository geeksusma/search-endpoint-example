package es.geeksusma.api.contact;

import es.geeksusma.domain.contact.Contact;
import es.geeksusma.domain.contact.Name;
import es.geeksusma.domain.contact.Phone;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ContactToDTOMapperTest {

    @Test
    void mapToExpected_when_map() {

        assertThat(new ContactToDTOMapper().map(aContact())).extracting("name", "phone")
                .containsOnly("Nunes, Joao", "351-123321");
    }

    private Contact aContact() {
        return Contact.of(Name.of("Joao", "Nunes"), Phone.of("351", "123321"));
    }
}