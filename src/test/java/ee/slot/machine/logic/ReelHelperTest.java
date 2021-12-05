package ee.slot.machine.logic;

import ee.slot.machine.logic.model.Card;
import ee.slot.machine.logic.model.WinningRow;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReelHelperTest {


    @Test
    public void findVerticalWinsFindsWinsFromTop() {
        Card[][] window = new Card[][]{
                {Card.ACE, Card.ACE, Card.ACE},
                {Card.NIGHT, Card.ACE, Card.ACE},
                {Card.ACE, Card.NIGHT, Card.ACE}};

        var horizontalWins = ReelHelper.findVerticalWins(window);
        assertEquals(WinningRow.VERTICAL_LEFT, horizontalWins.iterator().next());
    }

    @Test
    public void findVerticalWinsFindsWinsFromMiddle() {
        Card[][] window = new Card[][]{
                {Card.NIGHT, Card.ACE, Card.ACE},
                {Card.ACE, Card.ACE, Card.ACE},
                {Card.ACE, Card.NIGHT, Card.ACE}};

        var horizontalWins = ReelHelper.findVerticalWins(window);
        assertEquals(WinningRow.VERTICAL_MIDDLE, horizontalWins.iterator().next());
    }

    @Test
    public void findVerticalWinsFindsWinsFromRight() {
        Card[][] window = new Card[][]{
                {Card.NIGHT, Card.ACE, Card.ACE},
                {Card.ACE, Card.NIGHT, Card.ACE},
                {Card.ACE, Card.ACE, Card.ACE}};

        var horizontalWins = ReelHelper.findVerticalWins(window);
        assertEquals(WinningRow.VERTICAL_RIGHT, horizontalWins.iterator().next());
    }

    @Test
    public void findHorizontalWinsWinFromTop() {
        Card[][] window = new Card[][]{
                {Card.ACE, Card.SEVEN, Card.ACE},
                {Card.ACE, Card.NIGHT, Card.NIGHT},
                {Card.ACE, Card.SEVEN, Card.ACE}};

        var horizontalWins = ReelHelper.findHorizontalWins(window);
        assertEquals(WinningRow.HORIZONTAL_TOP, horizontalWins.iterator().next());
    }

    @Test
    public void findHorizontalWinsFindsWinFromMiddle() {
        Card[][] window = new Card[][]{
                {Card.ACE, Card.ACE, Card.SEVEN},
                {Card.NIGHT, Card.ACE, Card.NIGHT},
                {Card.ACE, Card.ACE, Card.ACE}};

        var horizontalWins = ReelHelper.findHorizontalWins(window);
        assertEquals(WinningRow.HORIZONTAL_MIDDLE, horizontalWins.iterator().next());
    }


    @Test
    public void findHorizontalWinsFindsWinFromBottom() {
        Card[][] window = new Card[][]{
                {Card.SEVEN, Card.ACE, Card.ACE},
                {Card.NIGHT, Card.NIGHT, Card.ACE},
                {Card.SEVEN, Card.ACE, Card.ACE}};

        var horizontalWins = ReelHelper.findHorizontalWins(window);
        assertEquals(WinningRow.HORIZONTAL_BOTTOM, horizontalWins.iterator().next());
    }

    @Test
    public void findDiagonalWinsCase1() {
        Card[][] window = new Card[][]{
                {Card.SEVEN, Card.ACE, Card.QUEEN},
                {Card.NIGHT, Card.QUEEN, Card.ACE},
                {Card.QUEEN, Card.ACE, Card.ACE}};

        var horizontalWins = ReelHelper.findDiagonalWins(window);
        assertEquals(WinningRow.DIAGONAL_BOTTOM, horizontalWins.iterator().next());
    }

    @Test
    public void findDiagonalWinsCase2() {
        Card[][] window = new Card[][]{
                {Card.QUEEN, Card.ACE, Card.ACE},
                {Card.NIGHT, Card.QUEEN, Card.ACE},
                {Card.SEVEN, Card.ACE, Card.QUEEN}};

        var horizontalWins = ReelHelper.findDiagonalWins(window);
        assertEquals(WinningRow.DIAGONAL_UPPER, horizontalWins.iterator().next());
    }

}