package nl.pancompany.unicorn.magic.application.port.out;

import nl.pancompany.unicorn.magic.application.domain.model.MagicAbility;
import org.jmolecules.architecture.hexagonal.SecondaryPort;

import java.util.List;

@SecondaryPort
public interface MagicAbilityRepository {

    List<MagicAbility> find(String unicornId);

}
