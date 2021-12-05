package ee.slot.machine.logic.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GameResult {

    private int initialBet;
    private int totalWin;
    private Card[][] reelWindow;
    private List<WinningRow> winningRows;

}
