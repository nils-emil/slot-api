package ee.slot.machine;

import ee.slot.machine.logic.model.Card;

import java.util.ArrayList;
import java.util.List;

public class ReelFactory {

    public static List<List<Card>> reels() {
        List<List<Card>> reels = new ArrayList<>();
        List<Card> reel1 = new ArrayList<>();
        reels.add(reel1);
        reel1.add(Card.ACE);
        reel1.add(Card.KING);
        reel1.add(Card.QUEEN);
        reel1.add(Card.NIGHT);
        reel1.add(Card.TEN);
        reel1.add(Card.NINE);
        reel1.add(Card.EIGHT);
        reel1.add(Card.SEVEN);
        List<Card> reel2 = new ArrayList<>(reel1);
        List<Card> reel3 = new ArrayList<>(reel1);
        reel3.add(0, Card.SEVEN);
        reel3.remove(reel3.size() - 1);
        reels.add(reel2);
        reels.add(reel3);
        return reels;
    }

}
