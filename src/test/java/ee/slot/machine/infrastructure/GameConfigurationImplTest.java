package ee.slot.machine.infrastructure;

import ee.slot.machine.logic.model.Card;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameConfigurationImplTest {

    @Test
    public void getSlotMachineReelsParsesStringCorrectly() throws IllegalAccessException {
        GameConfigurationImpl gameConfiguration = new GameConfigurationImpl();
        FieldUtils.writeField(gameConfiguration, "reel1", "ACE,KING,QUEEN,ACE", true);
        FieldUtils.writeField(gameConfiguration, "reel2", "ACE,ACE,ACE,ACE", true);
        FieldUtils.writeField(gameConfiguration, "reel3", "ACE,KING,QUEEN,QUEEN", true);

        List<List<Card>> slotMachineReels = gameConfiguration.getSlotMachineReels();
        assertEquals(Card.ACE, slotMachineReels.get(0).get(0));
        assertEquals(Card.KING, slotMachineReels.get(0).get(1));
        assertEquals(Card.QUEEN, slotMachineReels.get(0).get(2));
        assertEquals(Card.ACE, slotMachineReels.get(0).get(3));
        assertEquals(3, slotMachineReels.size());
        assertEquals(4, slotMachineReels.get(2).size());
    }

    @Test
    public void getMaximumNumberOfRotations() throws IllegalAccessException {
        GameConfigurationImpl gameConfiguration = new GameConfigurationImpl();
        FieldUtils.writeField(gameConfiguration, "maxNumberOfShifts", 5, true);

        Integer slotMachineReels = gameConfiguration.getMaximumNumberOfRotations();

        assertEquals(5, slotMachineReels);
    }

}