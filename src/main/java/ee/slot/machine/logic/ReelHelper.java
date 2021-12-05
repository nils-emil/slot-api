package ee.slot.machine.logic;

import ee.slot.machine.logic.model.Card;
import ee.slot.machine.logic.model.WinningRow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ReelHelper {

    public static Collection<WinningRow> findVerticalWins(Card[][] reelState) {
        List<WinningRow> winningRows = new ArrayList<>();
        for (int y = 0; y < 3; y++) {
            if (reelState[0][y] == reelState[1][y] && reelState[1][y] == reelState[2][y]) {
                switch (y) {
                    case 0:
                        winningRows.add(WinningRow.VERTICAL_LEFT);
                        break;
                    case 1:
                        winningRows.add(WinningRow.VERTICAL_MIDDLE);
                        break;
                    case 2:
                        winningRows.add(WinningRow.VERTICAL_RIGHT);
                        break;
                    default:
                        // code block
                }
            }
        }
        return winningRows;
    }

    public static Collection<WinningRow> findHorizontalWins(Card[][] reelState) {
        List<WinningRow> winningRows = new ArrayList<>();
        for (int x = 0; x < 3; x++) {
            if (reelState[x][0] == reelState[x][1] && reelState[x][1] == reelState[x][2]) {
                switch (x) {
                    case 0:
                        winningRows.add(WinningRow.HORIZONTAL_TOP);
                        break;
                    case 1:
                        winningRows.add(WinningRow.HORIZONTAL_MIDDLE);
                        break;
                    case 2:
                        winningRows.add(WinningRow.HORIZONTAL_BOTTOM);
                        break;
                    default:
                        // code block
                }
            }
        }
        return winningRows;
    }

    public static Collection<WinningRow> findDiagonalWins(Card[][] reelState) {
        List<WinningRow> winningRows = new ArrayList<>();
        if (reelState[0][0] == reelState[1][1] && reelState[1][1] == reelState[2][2]) {
            winningRows.add(WinningRow.DIAGONAL_UPPER);
        }
        if (reelState[0][2] == reelState[1][1] && reelState[1][1] == reelState[2][0]) {
            winningRows.add(WinningRow.DIAGONAL_BOTTOM);
        }
        return winningRows;
    }

}
