package nl.pancompany.unicorn.common.events;

import nl.pancompany.unicorn.common.model.UnicornId;
import org.jmolecules.event.annotation.DomainEvent;

@DomainEvent
public record MagicAbilityObtained(UnicornId unicornId, MagicAbilityDto obtainedMagicAbility) {
}
