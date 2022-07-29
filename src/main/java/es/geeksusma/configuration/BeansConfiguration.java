package es.geeksusma.configuration;

import es.geeksusma.domain.contact.ContactsRepository;
import es.geeksusma.domain.contact.SearchContacts;
import es.geeksusma.domain.contact.SearchContactsComponent;
import es.geeksusma.repository.contact.NoDatabaseContactRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {

    @Bean
    public SearchContacts searchContacts(ContactsRepository contactsRepository) {
        return new SearchContactsComponent(contactsRepository);
    }

    @Bean
    public ContactsRepository contactsRepository() {
        return new NoDatabaseContactRepository();
    }

}
