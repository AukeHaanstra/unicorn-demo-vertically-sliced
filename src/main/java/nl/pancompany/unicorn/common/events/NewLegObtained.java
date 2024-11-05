package nl.pancompany.unicorn.common.events;

import nl.pancompany.unicorn.common.model.Color;
import nl.pancompany.unicorn.common.model.LegPosition;
import nl.pancompany.unicorn.common.model.UnicornId;
import org.jmolecules.event.annotation.DomainEvent;

@DomainEvent
public record NewLegObtained(UnicornId unicornId, LegPosition legPosition, Color color) {
}
