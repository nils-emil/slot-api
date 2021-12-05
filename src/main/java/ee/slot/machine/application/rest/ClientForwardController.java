package ee.slot.machine.application.rest;

import ee.slot.machine.logic.ReelSpinGameUseCase;
import ee.slot.machine.logic.model.GameResult;
import ee.slot.machine.logic.model.PlayReelSpinGameCommand;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class ClientForwardController {

    private final ReelSpinGameUseCase slotMachineUseCase;

    @PostMapping(value = "/play")
    public GameResult roll(@Valid @RequestBody ReelSpinBet reelSpinBet) {
        return slotMachineUseCase.spinReels(PlayReelSpinGameCommand.builder().bet(reelSpinBet.getBet()).build());
    }

}