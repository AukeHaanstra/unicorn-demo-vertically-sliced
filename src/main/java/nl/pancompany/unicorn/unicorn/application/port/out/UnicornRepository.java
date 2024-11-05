package nl.pancompany.unicorn.unicorn.application.port.out;

import nl.pancompany.unicorn.common.model.UnicornId;
import nl.pancompany.unicorn.unicorn.application.domain.model.Unicorn;
import org.jmolecules.architecture.hexagonal.SecondaryPort;

import java.util.function.Consumer;

@SecondaryPort
public interface UnicornRepository {

    Unicorn find(UnicornId id) throws UnicornNotFoundException;
    Unicorn add(Unicorn t) throws UnicornAlreadyExistsException;
    Unicorn update(Unicorn t) throws UnicornNotFoundException;
    Unicorn updateTransactionally(UnicornId unicornId, Consumer<Unicorn> updateLogic) throws UnicornNotFoundException;
    long count();

    class UnicornAlreadyExistsException extends Exception {
    }

    class UnicornNotFoundException extends Exception {
    }
}
