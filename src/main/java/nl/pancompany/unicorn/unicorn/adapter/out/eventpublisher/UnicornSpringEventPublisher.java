package nl.pancompany.unicorn.unicorn.adapter.out.eventpublisher;

import lombok.RequiredArgsConstructor;
import nl.pancompany.unicorn.common.model.Color;
import nl.pancompany.unicorn.common.model.LegPosition;
import nl.pancompany.unicorn.common.model.UnicornId;
import nl.pancompany.unicorn.common.events.NewLegObtained;
import nl.pancompany.unicorn.unicorn.application.port.out.UnicornEventPublisher;
import org.jmolecules.event.annotation.DomainEventPublisher;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UnicornSpringEventPublisher implements UnicornEventPublisher {

    private final ApplicationEventPublisher events;

    @Override
    @DomainEventPublisher
    public void newLegObtained(UnicornId unicornId, LegPosition legPosition, Color color) {
        events.publishEvent(new NewLegObtained(unicornId, legPosition, color));
    }
}
