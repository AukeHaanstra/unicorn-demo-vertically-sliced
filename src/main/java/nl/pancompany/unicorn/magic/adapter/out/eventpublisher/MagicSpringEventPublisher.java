package nl.pancompany.unicorn.magic.adapter.out.eventpublisher;

import lombok.RequiredArgsConstructor;
import nl.pancompany.unicorn.common.model.LegPosition;
import nl.pancompany.unicorn.common.model.UnicornId;
import nl.pancompany.unicorn.common.events.MagicAbilityDto;
import nl.pancompany.unicorn.common.events.MagicAbilityObtained;
import nl.pancompany.unicorn.magic.application.port.out.MagicEventPublisher;
import org.jmolecules.event.annotation.DomainEventPublisher;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MagicSpringEventPublisher implements MagicEventPublisher {

    private final ApplicationEventPublisher events;

    @Override
    @DomainEventPublisher
    public void magicAbilityObtaied(UnicornId unicornId, LegPosition legPosition, Optional<String> newMagicAbility) {
        events.publishEvent(new MagicAbilityObtained(unicornId, new MagicAbilityDto(legPosition, newMagicAbility)));
    }
}
