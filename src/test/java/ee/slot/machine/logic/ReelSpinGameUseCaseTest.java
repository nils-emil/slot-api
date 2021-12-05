package ee.slot.machine.logic;

import ee.slot.machine.ReelFactory;
import ee.slot.machine.logic.model.Card;
import ee.slot.machine.logic.model.GameResult;
import ee.slot.machine.logic.model.PlayReelSpinGameCommand;
import ee.slot.machine.logic.model.WinningRow;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReelSpinGameUseCaseTest {

    @InjectMocks
    private ReelSpinGameUseCase reelSpinGameUseCase;

    @Mock
    private ReelSpinGameUseCaseSteps steps;

    @Test
    public void spinReelsHappyPath() {
        PlayReelSpinGameCommand command = PlayReelSpinGameCommand.builder().bet(5).build();
        List<List<Card>> reels = ReelFactory.reels();
        Card[][] reelWindow = new Card[3][3];
        reelWindow[0][0] = Card.ACE;
        when(steps.findInitialReels()).thenReturn(reels);
        when(steps.spinReels(reels)).thenReturn(reels);
        when(steps.getReelWindow(reels)).thenReturn(reelWindow);
        List<WinningRow> winningRows = Collections.singletonList(WinningRow.HORIZONTAL_MIDDLE);
        when(steps.findWinningRows(reelWindow)).thenReturn(winningRows);
        when(steps.calculateBaseWin(reelWindow, winningRows)).thenReturn(100);

        GameResult result = reelSpinGameUseCase.spinReels(command);

        assertEquals(Card.ACE, result.getReelWindow()[0][0]);
        assertEquals(5, result.getInitialBet());
        assertEquals(500, result.getTotalWin());
        assertEquals(1, result.getWinningRows().size());
        assertEquals(WinningRow.HORIZONTAL_MIDDLE, result.getWinningRows().get(0));
    }

}