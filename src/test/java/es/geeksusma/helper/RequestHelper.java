package es.geeksusma.helper;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RequestHelper {

    private MockMvc mockMvc;

    public RequestHelper(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }


    public void checkGetForSingleObject(String endpoint, String expectedBody) throws Exception {
        String contentAsString =
                doGet(endpoint)
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse()
                        .getContentAsString(StandardCharsets.UTF_8);
        assertThat(contentAsString).isEqualTo(expectedBody);
    }


    private ResultActions doGet(String endpoint) throws Exception {
        return this.mockMvc.perform(get(endpoint).contentType(MediaType.APPLICATION_JSON));
    }

}
