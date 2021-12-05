package ee.slot.machine.logic;

import ee.slot.machine.logic.model.Card;
import ee.slot.machine.logic.model.WinningRow;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReelHelperTest {


    @Test
    public void findHorizontalWinFindsWinsFromTop() {
        Card[][] window = new Card[][]{
                {Card.ACE, Card.ACE, Card.ACE},
                {Card.NIGHT, Card.ACE, Card.ACE},
                {Card.ACE, Card.NIGHT, Card.ACE}};

        var horizontalWins = ReelHelper.findHorizontalWins(window);
        assertEquals(WinningRow.HORIZONTAL_TOP, horizontalWins.iterator().next());
    }

    @Test
    public void findHorizontalWinFindsWinsFromMiddle() {
        Card[][] window = new Card[][]{
                {Card.NIGHT, Card.ACE, Card.ACE},
                {Card.ACE, Card.ACE, Card.ACE},
                {Card.ACE, Card.NIGHT, Card.ACE}};

        var horizontalWins = ReelHelper.findHorizontalWins(window);
        assertEquals(WinningRow.HORIZONTAL_MIDDLE, horizontalWins.iterator().next());
    }

    @Test
    public void findHorizontalWinFindsWinsFromBottom() {
        Card[][] window = new Card[][]{
                {Card.NIGHT, Card.ACE, Card.ACE},
                {Card.ACE, Card.NIGHT, Card.ACE},
                {Card.ACE, Card.ACE, Card.ACE}};

        var horizontalWins = ReelHelper.findHorizontalWins(window);
        assertEquals(WinningRow.HORIZONTAL_BOTTOM, horizontalWins.iterator().next());
    }

    @Test
    public void findVerticalWinsFindsWinFromLeft() {
        Card[][] window = new Card[][]{
                {Card.ACE, Card.SEVEN, Card.ACE},
                {Card.ACE, Card.NIGHT, Card.NIGHT},
                {Card.ACE, Card.SEVEN, Card.ACE}};

        var horizontalWins = ReelHelper.findVerticalWins(window);
        assertEquals(WinningRow.VERTICAL_LEFT, horizontalWins.iterator().next());
    }

    @Test
    public void findVerticalWinsFindsWinFromMiddle() {
        Card[][] window = new Card[][]{
                {Card.ACE, Card.ACE, Card.SEVEN},
                {Card.NIGHT, Card.ACE, Card.NIGHT},
                {Card.ACE, Card.ACE, Card.ACE}};

        var horizontalWins = ReelHelper.findVerticalWins(window);
        assertEquals(WinningRow.VERTICAL_MIDDLE, horizontalWins.iterator().next());
    }


    @Test
    public void findVerticalWinsFindsWinFromRight() {
        Card[][] window = new Card[][]{
                {Card.SEVEN, Card.ACE, Card.ACE},
                {Card.NIGHT, Card.NIGHT, Card.ACE},
                {Card.SEVEN, Card.ACE, Card.ACE}};

        var horizontalWins = ReelHelper.findVerticalWins(window);
        assertEquals(WinningRow.VERTICAL_RIGHT, horizontalWins.iterator().next());
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