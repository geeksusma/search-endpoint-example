package es.geeksusma.repository.contact;


import es.geeksusma.domain.contact.Contact;
import es.geeksusma.domain.contact.Contacts;
import es.geeksusma.domain.contact.Page;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class NoDatabaseContactRepositoryShould {

    private NoDatabaseContactRepository repository;

    @BeforeEach
    void setUp() {
        repository = new NoDatabaseContactRepository();
    }

    @Test
    void initialiseWithData_when_create() {

        assertThat(repository)
                .extracting("dataSource.size").isEqualTo(400);
    }

    @Test
    void countHits_basedOn_filters() {

        final Integer hits = repository.countHits("J", "1");

        //all the portuguese people, 200 + only spanish people who had a 1 written on their number
        assertThat(hits).isEqualTo(319);
    }

    @Test
    void returnAllHits_when_NoFilters() {


        assertThat(repository.countHits("", "")).isEqualTo(400);
        assertThat(repository.countHits(null
                , null)).isEqualTo(400);
    }

    @Test
    void filterByName_when_onlyName() {

        assertThat(repository.search("Joao", null, Page.of(1, 200)).getSize()).isEqualTo(200);
        assertThat(repository.search("Juan", null, Page.of(1, 200)).getSize()).isEqualTo(200);
    }

    @Test
    void filterByPhone_when_onlyPhone() {
        final NoDatabaseContactRepository repository = new NoDatabaseContactRepository();

        assertThat(repository.search(null, "351", Page.of(1, 200)).getSize()).isEqualTo(200);
    }

    @Test
    void ignoreOffset_when_tooHigh() {
        final NoDatabaseContactRepository repository = new NoDatabaseContactRepository();

        assertThat(repository.search(null, "34", Page.of(1, 300)).getSize()).isEqualTo(202);
    }

    @Test
    void filterByPhoneAndName_when_allFilters() {

        assertThat(repository.search("J", "1", Page.of(1, 400)).getSize()).isEqualTo(319);
    }

    @Test
    void returnFirstPage_when_firstPageRequested() {

        final Contacts filteredContacts = repository.search("J", "1", Page.of(1, 10));

        assertThat(filteredContacts.getSize()).isEqualTo(10);
        assertThat(filteredContacts.getContacts()).containsSequence(
                firstPageOfTenElements()
        ).doesNotContainSequence(secondPageOfTenElements()).doesNotContainSequence(thirdPageOfTenElements());

    }

    @Test
    void returnOthersPages_when_notFirstPageRequested() {

        final Contacts filteredContactsForSecondPage = repository.search("J", "1", Page.of(2, 10));
        final Contacts filteredContactsForThirdPage = repository.search("J", "1", Page.of(3, 10));

        assertThat(filteredContactsForSecondPage.getSize()).isEqualTo(10);
        assertThat(filteredContactsForSecondPage.getContacts()).containsSequence(
                secondPageOfTenElements()
        ).doesNotContainSequence(firstPageOfTenElements()).doesNotContainSequence(thirdPageOfTenElements());

        assertThat(filteredContactsForThirdPage.getSize()).isEqualTo(10);
        assertThat(filteredContactsForThirdPage.getContacts()).containsSequence(
                thirdPageOfTenElements()
        ).doesNotContainSequence(firstPageOfTenElements()).doesNotContainSequence(secondPageOfTenElements());
    }

    @Test
    void returnEmpty_when_noOffsetNeitherPage() {

        assertThat(repository.search(null, null, Page.of(0, 10)).getContacts()).isEmpty();
        assertThat(repository.search(null, null, Page.of(-1, 10)).getContacts()).isEmpty();
        assertThat(repository.search(null, null, Page.of(10, 0)).getContacts()).isEmpty();
        assertThat(repository.search(null, null, Page.of(10, -1)).getContacts()).isEmpty();

    }

    private List<Contact> firstPageOfTenElements() {
        List<Contact> contacts = new LinkedList<>();

        contacts.add(repository.createDummySpanishContacts(1));
        contacts.add(repository.createDummySpanishContacts(10));
        contacts.add(repository.createDummySpanishContacts(11));
        contacts.add(repository.createDummySpanishContacts(12));
        contacts.add(repository.createDummySpanishContacts(13));
        contacts.add(repository.createDummySpanishContacts(14));
        contacts.add(repository.createDummySpanishContacts(15));
        contacts.add(repository.createDummySpanishContacts(16));
        contacts.add(repository.createDummySpanishContacts(17));
        contacts.add(repository.createDummySpanishContacts(18));

        return contacts;
    }

    private List<Contact> secondPageOfTenElements() {
        List<Contact> contacts = new LinkedList<>();
        contacts.add(repository.createDummySpanishContacts(19));
        contacts.add(repository.createDummySpanishContacts(21));
        contacts.add(repository.createDummySpanishContacts(31));
        contacts.add(repository.createDummySpanishContacts(41));
        contacts.add(repository.createDummySpanishContacts(51));
        contacts.add(repository.createDummySpanishContacts(61));
        contacts.add(repository.createDummySpanishContacts(71));
        contacts.add(repository.createDummySpanishContacts(81));
        contacts.add(repository.createDummySpanishContacts(91));
        contacts.add(repository.createDummySpanishContacts(100));
        return contacts;
    }

    private List<Contact> thirdPageOfTenElements() {
        List<Contact> contacts = new LinkedList<>();
        contacts.add(repository.createDummySpanishContacts(101));
        contacts.add(repository.createDummySpanishContacts(102));
        contacts.add(repository.createDummySpanishContacts(103));
        contacts.add(repository.createDummySpanishContacts(104));
        contacts.add(repository.createDummySpanishContacts(105));
        contacts.add(repository.createDummySpanishContacts(106));
        contacts.add(repository.createDummySpanishContacts(107));
        contacts.add(repository.createDummySpanishContacts(108));
        contacts.add(repository.createDummySpanishContacts(109));
        contacts.add(repository.createDummySpanishContacts(110));
        return contacts;
    }
}