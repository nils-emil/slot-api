package ee.slot.machine.logic;

import ee.slot.machine.logic.model.Card;
import ee.slot.machine.logic.model.PlayReelSpinGameCommand;
import ee.slot.machine.logic.model.WinningRow;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class ReelSpinGameUseCaseSteps {

    private final GameConfiguration gameConfiguration;
    private final PlayerService playerService;

    public List<List<Card>> findInitialReels() {
        return gameConfiguration.getSlotMachineReels();
    }

    public List<List<Card>> spinReels(List<List<Card>> reels) {
        Random rand = new Random();
        List<List<Card>> shiftedReels = new ArrayList<>();
        for (List<Card> reel : reels) {
            int rotateAmount = rand.nextInt(gameConfiguration.getMaximumNumberOfRotations());
            int numberOfElementsInReel = reel.size();
            int realRotation = rotateAmount % numberOfElementsInReel;
            List<Card> e = new ArrayList<>();
            e.addAll(reel.subList(realRotation, reel.size()));
            e.addAll(reel.subList(0, realRotation));
            shiftedReels.add(e);
        }
        return shiftedReels;
    }

    public Card[][] getReelWindow(List<List<Card>> newReelState) {
        Card[][] cards = new Card[3][3];
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                cards[x][y] = newReelState.get(x).get(y);
            }
        }
        return cards;
    }

    public List<WinningRow> findWinningRows(Card[][] reelState) {
        List<WinningRow> winners = new ArrayList<>();
        winners.addAll(ReelHelper.findHorizontalWins(reelState));
        winners.addAll(ReelHelper.findVerticalWins(reelState));
        winners.addAll(ReelHelper.findDiagonalWins(reelState));
        return winners;
    }

    public Integer calculateBaseWin(Card[][] reelWindow, List<WinningRow> winningRows) {
        int totalWin = 0;
        for (WinningRow winningRow : winningRows) {
            totalWin += reelWindow[winningRow.xCoordinateStart][winningRow.yCoordinateStart].win;
        }
        return totalWin;
    }

    public void validateUserBalance(Integer bet) {
        if (playerService.getPlayerBalance() < bet) {
            throw new IllegalArgumentException("User does not have enought balance to play");
        }
    }

    public void updateUserBalance(int bet, int totalWin) {
        Integer currentBalance = playerService.getPlayerBalance();
        playerService.updatePlayerBalance(currentBalance - bet + totalWin);
    }
}
