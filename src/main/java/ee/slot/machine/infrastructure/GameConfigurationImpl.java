package ee.slot.machine.infrastructure;

import ee.slot.machine.logic.GameConfiguration;
import ee.slot.machine.logic.model.Card;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class GameConfigurationImpl implements GameConfiguration {

    @Value("${game.configuration.reel.1.order}")
    private String reel1;
    @Value("${game.configuration.reel.2.order}")
    private String reel2;
    @Value("${game.configuration.reel.3.order}")
    private String reel3;
    @Value("${game.configuration.max.number.of.shifts}")
    private Integer maxNumberOfShifts;

    @Override
    public List<List<Card>> getSlotMachineReels() {
        List<List<Card>> reels = new ArrayList<>();
        reels.add(stringToReelCollection(reel1));
        reels.add(stringToReelCollection(reel2));
        reels.add(stringToReelCollection(reel3));
        return reels;
    }

    private List<Card> stringToReelCollection(String reel) {
        String[] split = reel.split(",");
        List<Card> reelCollection = new ArrayList<>();
        for (String card : split) {
            reelCollection.add(Card.valueOf(card.toUpperCase(Locale.ROOT)));
        }
        return reelCollection;
    }

    @Override
    public Integer getMaximumNumberOfRotations() {
        return maxNumberOfShifts;
    }

}
