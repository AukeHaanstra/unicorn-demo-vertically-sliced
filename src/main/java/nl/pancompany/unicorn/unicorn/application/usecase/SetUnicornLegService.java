package nl.pancompany.unicorn.unicorn.application.usecase;

import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.pancompany.unicorn.unicorn.application.domain.model.Leg;
import nl.pancompany.unicorn.unicorn.application.domain.model.Unicorn;
import nl.pancompany.unicorn.unicorn.application.port.in.SetLegUsecase;
import nl.pancompany.unicorn.unicorn.application.port.out.UnicornEventPublisher;
import nl.pancompany.unicorn.unicorn.application.port.out.UnicornRepository;
import org.jmolecules.ddd.annotation.Service;

import static nl.pancompany.unicorn.common.validation.ConstraintValidator.validate;

@Slf4j
@RequiredArgsConstructor
@Service
public class SetUnicornLegService implements SetLegUsecase {

    private final UnicornRepository unicornRepository;
    private final UnicornEventPublisher unicornEventPublisher;

    public Leg.LegDto setLeg(SetLegDto setLegDto) throws UnicornRepository.UnicornNotFoundException, ConstraintViolationException {
        validate(setLegDto);
        Unicorn updatedUnicorn = unicornRepository.updateTransactionally(setLegDto.unicornId(), unicorn -> {
                    unicorn.setLegColor(setLegDto.legPosition(), setLegDto.color());
                    unicornEventPublisher.newLegObtained(unicorn.getUnicornId(), setLegDto.legPosition(), setLegDto.color());
                    log.info("Updated leg of unicorn with id={}", unicorn.getUnicornId().toStringValue());
                });
        return updatedUnicorn.getLeg(setLegDto.legPosition()).toDto();
    }

}
