package ee.slot.machine.infrastructure;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerServiceImplTest {

    @Test
    public void getSlotMachineReelsParsesStringCorrectly() throws IllegalAccessException {
        PlayerServiceImpl playerService = new PlayerServiceImpl();
        FieldUtils.writeField(playerService, "remainingBalance", 20, true);

        assertEquals(20, playerService.getPlayerBalance());
        playerService.updatePlayerBalance(40);
        assertEquals(40, playerService.getPlayerBalance());
    }

}