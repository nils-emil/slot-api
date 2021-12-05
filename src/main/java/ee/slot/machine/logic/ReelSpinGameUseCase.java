package ee.slot.machine.logic;

import ee.slot.machine.logic.model.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReelSpinGameUseCase {

    private final ReelSpinGameUseCaseSteps steps;

    public GameResult spinReels(PlayReelSpinGameCommand command) {
        steps.validateUserBalance(command.getBet());
        List<List<Card>> reels = steps.findInitialReels();
        List<List<Card>> newReelState = steps.spinReels(reels);
        Card[][] reelWindow = steps.getReelWindow(newReelState);
        List<WinningRow> winningRows = steps.findWinningRows(reelWindow);
        int totalWin = command.getBet() * steps.calculateBaseWin(reelWindow, winningRows);
        steps.updateUserBalance(command.getBet(), totalWin);
        return GameResult
                .builder()
                .initialBet(command.getBet())
                .totalWin(totalWin)
                .winningRows(winningRows)
                .reelWindow(reelWindow)
                .build();
    }

}
