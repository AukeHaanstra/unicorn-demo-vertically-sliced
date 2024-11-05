package nl.pancompany.unicorn.unicorn.application.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import nl.pancompany.unicorn.common.model.Color;
import nl.pancompany.unicorn.common.model.LegPosition;
import nl.pancompany.unicorn.common.model.UnicornId;
import org.jmolecules.ddd.annotation.AggregateRoot;
import org.jmolecules.ddd.annotation.Identity;
import org.jmolecules.ddd.annotation.ValueObject;

import java.util.Set;

import static java.util.Objects.requireNonNullElseGet;
import static java.util.stream.Collectors.toSet;

@Getter
@ToString
@EqualsAndHashCode
@AggregateRoot
public class Unicorn {

    @Identity
    private final UnicornId unicornId;
    private final String name;
    private final Set<Leg> legs;

    public Unicorn(UnicornId unicornId, @NonNull String name, @NonNull Set<Leg> legs) {
        if (legs.size() != 4) {
            throw new IllegalArgumentException("Unicorns must have four legs.");
        }
        this.unicornId = requireNonNullElseGet(unicornId, UnicornId::generate);
        this.name = name;
        this.legs = legs;
    }

    public Leg getLeg(LegPosition legPosition) {
        return legs.stream()
                .filter(leg -> leg.getLegPosition().equals(legPosition))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    public void setLegColor(LegPosition legPosition, Color color) {
        Leg leg = getLeg(legPosition);
        leg.setColor(color);
    }

    public UnicornDto toDto() {
        return new UnicornDto(unicornId, name, legs.stream()
                .map(Leg::toDto)
                .collect(toSet()));
    }

    @ValueObject
    public record UnicornDto(UnicornId unicornId, String name, Set<Leg.LegDto> legs) {
    }
}