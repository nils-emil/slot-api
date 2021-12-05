package ee.slot.machine.application.rest;

import ee.slot.machine.logic.ReelSpinGameUseCase;
import ee.slot.machine.logic.model.Card;
import ee.slot.machine.logic.model.GameResult;
import ee.slot.machine.logic.model.PlayReelSpinGameCommand;
import ee.slot.machine.logic.model.WinningRow;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientForwardControllerTest {

    @InjectMocks
    private ClientForwardController clientForwardController;

    @Mock
    private ReelSpinGameUseCase reelSpinGameUseCase;

    @Test
    public void rollHappyPath() {
        ArgumentCaptor<PlayReelSpinGameCommand> captor = ArgumentCaptor.forClass(PlayReelSpinGameCommand.class);
        ReelSpinBet bet = new ReelSpinBet();
        bet.setBet(5);
        Card[][] cards = new Card[3][3];
        cards[0][0] = Card.ACE;
        GameResult gameResult = GameResult
                .builder()
                .totalWin(22)
                .initialBet(2)
                .winningRows(Collections.singletonList(WinningRow.DIAGONAL_BOTTOM))
                .reelWindow(cards)
                .build();
        when(reelSpinGameUseCase.spinReels(any())).thenReturn(gameResult);

        GameResult result = clientForwardController.roll(bet);
        verify(reelSpinGameUseCase, times(1)).spinReels(captor.capture());

        PlayReelSpinGameCommand argument = captor.getValue();
        assertEquals(5, argument.getBet());
        assertEquals(22, result.getTotalWin());
        assertEquals(2, result.getInitialBet());
        assertEquals(WinningRow.DIAGONAL_BOTTOM, result.getWinningRows().get(0));
        assertEquals(Card.ACE, result.getReelWindow()[0][0]);
    }

}