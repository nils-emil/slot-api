package ee.slot.machine.application.config;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Error {
    String reason;
}
