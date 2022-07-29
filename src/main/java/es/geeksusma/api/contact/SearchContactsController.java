package es.geeksusma.api.contact;

import es.geeksusma.domain.contact.Contacts;
import es.geeksusma.domain.contact.Page;
import es.geeksusma.domain.contact.SearchContacts;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("v1")
public class SearchContactsController {

    private final SearchContacts searchContacts;
    private final ContactToDTOMapper mapper;

    public SearchContactsController(SearchContacts searchContacts, ContactToDTOMapper mapper) {
        this.searchContacts = searchContacts;
        this.mapper = mapper;
    }

    @Operation(summary = "Retrieves employees according to their role")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Request was succeeded",
                            content = {@Content(mediaType = "application/json")})
            })
    @GetMapping("/contacts/filters")
    ResponseEntity<ContactsDTO> searchByPage(@ModelAttribute final ContactFiltersDTO filters, @ModelAttribute PageRequest page) {

        int total = searchContacts.count(filters.getName(), filters.getPhone());
        final Contacts contacts = searchContacts.search(filters.getName(), filters.getPhone(), Page.of(page.getPage(), page.getOffset()));

        return ResponseEntity.ok(new ContactsDTO(contacts.getContacts().stream().map(mapper::map).collect(Collectors.toList()), page.getPage(), total));
    }
}
