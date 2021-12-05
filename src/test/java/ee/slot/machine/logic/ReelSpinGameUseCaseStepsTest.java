package ee.slot.machine.logic;

import ee.slot.machine.ReelFactory;
import ee.slot.machine.logic.model.Card;
import ee.slot.machine.logic.model.WinningRow;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReelSpinGameUseCaseStepsTest {


    @InjectMocks
    private ReelSpinGameUseCaseSteps useCaseSteps;

    @Mock
    private GameConfiguration gameConfiguration;

    @Mock
    private PlayerService playerService;

    @Test
    public void findInitialReels() {
        List<List<Card>> gameConfig = Collections.singletonList(Collections.singletonList(Card.ACE));
        when(gameConfiguration.getSlotMachineReels()).thenReturn(gameConfig);

        List<List<Card>> result = useCaseSteps.findInitialReels();

        assertEquals(1, result.size());
        assertEquals(1, result.get(0).size());
        assertEquals(Card.ACE, result.get(0).get(0));
    }

    @Test
    public void spinReelsSpinsButDoesNotChangeCardOrder() {
        List<Card> cards = new ArrayList<>();
        cards.add(Card.ACE);
        cards.add(Card.KING);
        cards.add(Card.QUEEN);
        cards.add(Card.NIGHT);
        cards.add(Card.TEN);
        cards.add(Card.NINE);
        cards.add(Card.EIGHT);
        cards.add(Card.SEVEN);

        List<List<Card>> gameConfig = Collections.singletonList(cards);
        when(gameConfiguration.getMaximumNumberOfRotations()).thenReturn(4);

        List<Card> result = useCaseSteps.spinReels(gameConfig).get(0);

        assertEquals(8, result.size());
        assertEquals(8, cards.size());
        assertEquals((result.indexOf(Card.ACE) + 1) % 8, result.indexOf(Card.KING) % 8);
        assertEquals((result.indexOf(Card.KING) + 1) % 8, result.indexOf(Card.QUEEN) % 8);
        assertEquals((result.indexOf(Card.QUEEN) + 1) % 8, result.indexOf(Card.NIGHT) % 8);
        assertEquals((result.indexOf(Card.NIGHT) + 1) % 8, result.indexOf(Card.TEN) % 8);
        assertEquals((result.indexOf(Card.TEN) + 1) % 8, result.indexOf(Card.NINE) % 8);
        assertEquals((result.indexOf(Card.NINE) + 1) % 8, result.indexOf(Card.EIGHT) % 8);
        assertEquals((result.indexOf(Card.EIGHT) + 1) % 8, result.indexOf(Card.SEVEN) % 8);
    }

    @Test
    public void getReelWindowShouldShowCorrectWindows() {
        List<List<Card>> reels = ReelFactory.reels();
        Card[][] result = useCaseSteps.getReelWindow(reels);

        assertEquals(Card.ACE, result[0][0]);
        assertEquals(Card.ACE, result[1][0]);
        assertEquals(Card.SEVEN, result[2][0]);
        assertEquals(Card.KING, result[0][1]);
        assertEquals(Card.KING, result[1][1]);
        assertEquals(Card.ACE, result[2][1]);
        assertEquals(Card.QUEEN, result[0][2]);
        assertEquals(Card.QUEEN, result[1][2]);
        assertEquals(Card.KING, result[2][2]);
    }


    @Test
    public void findWinningRowsFindsAllWins() {
        Card[][] window = new Card[][]{
                {Card.ACE, Card.ACE, Card.ACE},
                {Card.ACE, Card.ACE, Card.ACE},
                {Card.ACE, Card.ACE, Card.ACE}};

        List<WinningRow> result = useCaseSteps.findWinningRows(window);

        assertEquals(WinningRow.HORIZONTAL_TOP, result.get(0));
        assertEquals(WinningRow.HORIZONTAL_MIDDLE, result.get(1));
        assertEquals(WinningRow.HORIZONTAL_BOTTOM, result.get(2));
        assertEquals(WinningRow.VERTICAL_LEFT, result.get(3));
        assertEquals(WinningRow.VERTICAL_MIDDLE, result.get(4));
        assertEquals(WinningRow.VERTICAL_RIGHT, result.get(5));
        assertEquals(WinningRow.DIAGONAL_UPPER, result.get(6));
        assertEquals(WinningRow.DIAGONAL_BOTTOM, result.get(7));
    }

    @Test
    public void findWinningRowsFindsNoWins() {
        Card[][] window = new Card[][]{
                {Card.ACE, Card.NIGHT, Card.NINE},
                {Card.KING, Card.NIGHT, Card.EIGHT},
                {Card.QUEEN, Card.TEN, Card.SEVEN}};

        List<WinningRow> result = useCaseSteps.findWinningRows(window);

        assertEquals(0, result.size());
    }

    @Test
    public void calculateBaseWinCase1() {
        Card[][] window = new Card[][]{
                {Card.ACE, Card.NIGHT, Card.QUEEN},
                {Card.KING, Card.QUEEN, Card.EIGHT},
                {Card.QUEEN, Card.TEN, Card.SEVEN}};

        Integer result = useCaseSteps.calculateBaseWin(window, Collections.singletonList(WinningRow.DIAGONAL_BOTTOM));

        assertEquals(50, result);
    }

    @Test
    public void calculateBaseWinCase2() {
        Card[][] window = new Card[][]{
                {Card.KING, Card.NIGHT, Card.NINE},
                {Card.KING, Card.NIGHT, Card.EIGHT},
                {Card.KING, Card.TEN, Card.SEVEN}};

        Integer result = useCaseSteps.calculateBaseWin(window, Collections.singletonList(WinningRow.VERTICAL_LEFT));

        assertEquals(80, result);
    }

    @Test
    public void calculateBaseWinCase4() {
        Card[][] window = new Card[][]{
                {Card.ACE, Card.QUEEN, Card.QUEEN},
                {Card.KING, Card.NIGHT, Card.EIGHT},
                {Card.QUEEN, Card.TEN, Card.SEVEN}};

        Integer result = useCaseSteps.calculateBaseWin(window, Collections.singletonList(WinningRow.HORIZONTAL_TOP));

        assertEquals(100, result);
    }

    @Test
    public void validateUserBalanceSuccess() {
        when(playerService.getPlayerBalance()).thenReturn(80);

        assertDoesNotThrow(() -> useCaseSteps.validateUserBalance(60));
    }

    @Test
    public void validateUserBalanceSuccessBetTakesWholeBalance() {
        when(playerService.getPlayerBalance()).thenReturn(60);

        assertDoesNotThrow(() -> useCaseSteps.validateUserBalance(60));
    }

    @Test
    public void validateUserBalanceFails() {
        when(playerService.getPlayerBalance()).thenReturn(50);

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> useCaseSteps.validateUserBalance(60));

        assertEquals("User does not have enought balance to play", illegalArgumentException.getMessage());
    }

    @Test
    public void updateUserBalance() {
        when(playerService.getPlayerBalance()).thenReturn(50);

        useCaseSteps.updateUserBalance(20, 5);

        verify(playerService, times(1)).updatePlayerBalance(35);
    }

}