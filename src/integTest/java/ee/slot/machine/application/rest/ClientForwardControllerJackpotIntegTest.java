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

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:integTest-jackpot.properties")
class ClientForwardControllerJackpotIntegTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void betHitsJackpot() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        mockMvc.perform(MockMvcRequestBuilders.post("/play")
                        .contentType("application/json")
                        .content("{\"bet\" : 10}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.initialBet").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalWin").value(8000))
                .andExpect(MockMvcResultMatchers.jsonPath("$.playerBalance").value(8990))
                .andExpect(MockMvcResultMatchers.jsonPath("$.reelWindow[0][0]").value("ACE"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.reelWindow[0][1]").value("ACE"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.reelWindow[0][2]").value("ACE"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.winningRows[0]").value("HORIZONTAL_TOP"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.winningRows[6]").value("DIAGONAL_UPPER"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.winningRows[7]").value("DIAGONAL_BOTTOM"));
    }

}

