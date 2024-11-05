package nl.pancompany.unicorn.unicorn.application.port.in;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.NotNull;
import nl.pancompany.unicorn.common.model.UnicornId;
import nl.pancompany.unicorn.common.model.Color;
import nl.pancompany.unicorn.common.model.LegPosition;
import nl.pancompany.unicorn.unicorn.application.domain.model.Leg;
import nl.pancompany.unicorn.unicorn.application.port.out.UnicornRepository;
import org.jmolecules.architecture.hexagonal.PrimaryPort;
import org.jmolecules.ddd.annotation.ValueObject;

@PrimaryPort
public interface SetLegUsecase {

    Leg.LegDto setLeg(SetLegDto setLegDto) throws UnicornRepository.UnicornNotFoundException, ConstraintViolationException;

    @ValueObject
    record SetLegDto(@NotNull UnicornId unicornId, @NotNull LegPosition legPosition, @NotNull Color color) {
    }
}
