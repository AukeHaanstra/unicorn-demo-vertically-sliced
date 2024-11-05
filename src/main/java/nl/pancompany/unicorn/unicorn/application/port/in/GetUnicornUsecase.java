package nl.pancompany.unicorn.unicorn.application.port.in;


import nl.pancompany.unicorn.common.model.UnicornId;
import nl.pancompany.unicorn.unicorn.application.domain.model.Unicorn;
import nl.pancompany.unicorn.unicorn.application.port.out.UnicornRepository;
import org.jmolecules.architecture.hexagonal.PrimaryPort;

@PrimaryPort
public interface GetUnicornUsecase {

    Unicorn.UnicornDto getUnicorn(UnicornId unicornId) throws UnicornRepository.UnicornNotFoundException;

}
