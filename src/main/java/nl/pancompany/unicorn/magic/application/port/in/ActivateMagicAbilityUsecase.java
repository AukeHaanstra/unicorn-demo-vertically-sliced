package nl.pancompany.unicorn.magic.application.port.in;

import nl.pancompany.unicorn.common.events.NewLegObtained;
import org.jmolecules.architecture.hexagonal.PrimaryPort;

@PrimaryPort
public interface ActivateMagicAbilityUsecase {
    void activateMagicAbilityFor(NewLegObtained newLegObtained);
}
