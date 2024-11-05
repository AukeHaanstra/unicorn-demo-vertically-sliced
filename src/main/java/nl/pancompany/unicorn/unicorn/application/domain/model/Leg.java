package nl.pancompany.unicorn.unicorn.application.domain.model;

import lombok.*;
import nl.pancompany.unicorn.common.model.Color;
import nl.pancompany.unicorn.common.model.LegPosition;
import org.jmolecules.ddd.annotation.Entity;
import org.jmolecules.ddd.annotation.Identity;
import org.jmolecules.ddd.annotation.ValueObject;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Entity
public class Leg {

        @NonNull
        @Identity
        private final LegPosition legPosition;

        @Setter(AccessLevel.PACKAGE)
        @NonNull
        private Color color;

        public LegDto toDto() {
                return new LegDto(legPosition, color);
        }

        @ValueObject
        public record LegDto(LegPosition legPosition, Color color) {
        }
}
