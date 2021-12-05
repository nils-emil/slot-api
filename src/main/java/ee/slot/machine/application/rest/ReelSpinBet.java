package ee.slot.machine.application.rest;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class ReelSpinBet {

    @NotNull
    @Min(1)
    @Max(10)
    Integer bet;

}
