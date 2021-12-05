package ee.slot.machine.application.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:integTest-no-winnings.properties")
class ClientForwardControllerLoseBetIntegTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void betLost() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        mockMvc.perform(MockMvcRequestBuilders.post("/play")
                        .contentType("application/json")
                        .content("{\"bet\" : 10}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.initialBet").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalWin").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.playerBalance").value(990))
                .andExpect(MockMvcResultMatchers.jsonPath("$.winningRows", hasSize(0)));
    }

}

