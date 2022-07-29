package es.geeksusma.configuration;

import es.geeksusma.api.contact.ContactToDTOMapper;
import es.geeksusma.domain.contact.ContactsRepository;
import es.geeksusma.domain.contact.SearchContacts;
import es.geeksusma.domain.contact.SearchContactsComponent;
import es.geeksusma.repository.contact.NoDatabaseContactRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {

    @Bean
    public ContactToDTOMapper contactToDTOMapper() {
        return new ContactToDTOMapper();
    }

    @Bean
    public SearchContacts searchContacts(ContactsRepository contactsRepository) {
        return new SearchContactsComponent(contactsRepository);
    }

    @Bean
    public ContactsRepository contactsRepository() {
        return new NoDatabaseContactRepository();
    }

}
