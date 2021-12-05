package ee.slot.machine.logic.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayReelSpinGameCommand {

    private int bet;

}
