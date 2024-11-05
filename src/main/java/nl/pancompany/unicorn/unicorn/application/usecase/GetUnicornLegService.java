package nl.pancompany.unicorn.unicorn.application.usecase;

import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.pancompany.unicorn.unicorn.application.domain.model.Leg;
import nl.pancompany.unicorn.unicorn.application.domain.model.Unicorn;
import nl.pancompany.unicorn.unicorn.application.port.in.GetLegUsecase;
import nl.pancompany.unicorn.unicorn.application.port.out.UnicornRepository;
import org.jmolecules.ddd.annotation.Service;

import static nl.pancompany.unicorn.common.validation.ConstraintValidator.validate;

@Slf4j
@RequiredArgsConstructor
@Service
public class GetUnicornLegService implements GetLegUsecase {

    private final UnicornRepository unicornRepository;

    public Leg.LegDto getLeg(QueryLegDto queryLegDto) throws UnicornRepository.UnicornNotFoundException, ConstraintViolationException {
        validate(queryLegDto);
        Unicorn unicorn = unicornRepository.find(queryLegDto.unicornId());
        return unicorn.getLeg(queryLegDto.legPosition()).toDto();
    }

}
