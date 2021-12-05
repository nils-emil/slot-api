package ee.slot.machine.logic;

public interface PlayerService {

    Integer getPlayerBalance();

    Integer updatePlayerBalance(Integer newBalance);

}
