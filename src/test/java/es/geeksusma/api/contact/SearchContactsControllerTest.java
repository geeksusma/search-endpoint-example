package es.geeksusma.api.contact;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.geeksusma.domain.contact.ContactsRepository;
import es.geeksusma.domain.contact.SearchContacts;
import es.geeksusma.domain.contact.SearchContactsComponent;
import es.geeksusma.helper.RequestHelper;
import es.geeksusma.repository.contact.NoDatabaseContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.LinkedList;
import java.util.List;

@WebMvcTest(SearchContactsController.class)
class SearchContactsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private RequestHelper requestHelper;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        requestHelper = new RequestHelper(mockMvc);

    }


    @Test
    void returnFilteredContacts_for_requestedPageWithFixedOffset() throws Exception {

        ContactsDTO expectedResponse = expectedPage();

        requestHelper.checkGetForSingleObject("/v1/contacts/search?name=Joao&phone=351-1&page=2&offset=5", objectMapper.writeValueAsString(expectedResponse));
    }

    private ContactsDTO expectedPage() {
        List<ContactDTO> filteredContacts = new LinkedList<>();
        filteredContacts.add(new ContactDTO("Test 14, Joao", "351-14"));
        filteredContacts.add(new ContactDTO("Test 15, Joao", "351-15"));
        filteredContacts.add(new ContactDTO("Test 16, Joao", "351-16"));
        filteredContacts.add(new ContactDTO("Test 17, Joao", "351-17"));
        filteredContacts.add(new ContactDTO("Test 18, Joao", "351-18"));
        return new ContactsDTO(filteredContacts, 2, 111);
    }


    @TestConfiguration
    static class TestConfigurationSetup {
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
}