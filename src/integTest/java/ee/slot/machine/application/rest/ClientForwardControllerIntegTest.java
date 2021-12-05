package ee.slot.machine.application.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:integTest.properties")
class ClientForwardControllerIntegTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void betReturnsOkResponse() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        mockMvc.perform(MockMvcRequestBuilders.post("/play")
                        .contentType("application/json")
                        .content("{\"bet\" : 10}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.initialBet").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalWin").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.playerBalance").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.reelWindow").isNotEmpty());
    }

    @Test
    public void invalidBetReturnBadRequest() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        mockMvc.perform(MockMvcRequestBuilders.post("/play")
                        .contentType("application/json")
                        .content("{\"bet\" : 999}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.reason")
                        .value("Input validation error"));
    }

    @Test
    public void shouldWinMoneyFromSomeGames() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        int totalBet = 0;
        int totalWin = 0;
        int numberOfWins = 0;
        List<String> winningRows = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            ObjectMapper objectMapper = new ObjectMapper();
            String contentAsString = mockMvc.perform(MockMvcRequestBuilders.post("/play")
                            .contentType("application/json")
                            .content("{\"bet\" : 10}"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.initialBet").value(10))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.totalWin").isNotEmpty())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.reelWindow[0]", hasSize(3)))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.reelWindow[1]", hasSize(3)))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.reelWindow[2]", hasSize(3)))
                    .andReturn()
                    .getResponse()
                    .getContentAsString();
            HashMap result = objectMapper.readValue(contentAsString, HashMap.class);
            totalBet += 10;
            totalWin += Integer.parseInt(result.get("totalWin").toString());
            List winningRowsLocal = (List) result.get("winningRows");
            if (!winningRowsLocal.isEmpty()) {
                winningRows.addAll(winningRowsLocal);
                numberOfWins += 1;
            }
        }

        assertEquals(totalBet, 1000);
        assertTrue(totalWin > 0);
        assertTrue(numberOfWins > 1);
        assertTrue(numberOfWins < 99);
        assertTrue(winningRows.size() > 1);
    }

}

