package ee.slot.machine.infrastructure;

import ee.slot.machine.logic.PlayerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class PlayerServiceImpl implements PlayerService {

    private Integer remainingBalance;

    @Value("${game.configuration.initial.player.balance}")
    private Integer initialPlayerBalance;

    @PostConstruct
    public void postConstructInit() {
        this.remainingBalance = this.initialPlayerBalance;
    }

    @Override
    public Integer getPlayerBalance() {
        return remainingBalance;
    }

    @Override
    public Integer updatePlayerBalance(Integer newBalance) {
        this.remainingBalance = newBalance;
        return remainingBalance;
    }

}
