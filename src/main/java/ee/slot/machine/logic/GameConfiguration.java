package ee.slot.machine.logic;

import ee.slot.machine.logic.model.Card;

import java.util.List;

public interface GameConfiguration {

    List<List<Card>> getSlotMachineReels();

    Integer getMaximumNumberOfRotations();

}
