package nl.pancompany.unicorn.unicorn.application.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.pancompany.unicorn.common.model.UnicornId;
import nl.pancompany.unicorn.unicorn.application.domain.model.Unicorn;
import nl.pancompany.unicorn.unicorn.application.port.in.GetUnicornUsecase;
import nl.pancompany.unicorn.unicorn.application.port.out.UnicornRepository;
import org.jmolecules.ddd.annotation.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class GetUnicornService implements GetUnicornUsecase {

    private final UnicornRepository unicornRepository;

    @Override
    public Unicorn.UnicornDto getUnicorn(UnicornId unicornId) throws UnicornRepository.UnicornNotFoundException {
        Unicorn unicorn = unicornRepository.find(unicornId);
        return unicorn.toDto();
    }

}
