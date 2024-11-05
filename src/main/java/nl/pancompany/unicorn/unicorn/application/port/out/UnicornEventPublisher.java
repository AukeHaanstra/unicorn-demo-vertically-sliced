package nl.pancompany.unicorn.unicorn.application.port.out;

import nl.pancompany.unicorn.common.model.Color;
import nl.pancompany.unicorn.common.model.LegPosition;
import nl.pancompany.unicorn.common.model.UnicornId;
import org.jmolecules.architecture.hexagonal.SecondaryPort;

@SecondaryPort
public interface UnicornEventPublisher {
    void newLegObtained(UnicornId unicornId, LegPosition legPosition, Color color);
}
