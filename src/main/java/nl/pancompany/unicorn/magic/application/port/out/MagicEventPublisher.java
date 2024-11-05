package nl.pancompany.unicorn.magic.application.port.out;

import nl.pancompany.unicorn.common.model.LegPosition;
import nl.pancompany.unicorn.common.model.UnicornId;
import org.jmolecules.architecture.hexagonal.SecondaryPort;

import java.util.Optional;

@SecondaryPort
public interface MagicEventPublisher {

    void magicAbilityObtaied(UnicornId unicornId, LegPosition legPosition, Optional<String> newMagicAbility);
}
