package nl.pancompany.unicorn.unicorn.application.domain.model;

import lombok.*;
import nl.pancompany.unicorn.common.model.Color;
import nl.pancompany.unicorn.common.model.LegPosition;
import org.jmolecules.ddd.annotation.AggregateRoot;
import org.jmolecules.ddd.annotation.Entity;
import org.jmolecules.ddd.annotation.Identity;
import org.jmolecules.ddd.annotation.ValueObject;
import org.mapstruct.Mapper;

import static org.mapstruct.ReportingPolicy.ERROR;
import static org.mapstruct.factory.Mappers.getMapper;

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
                return LegDtoMapper.INSTANCE.map(this);
        }

        @Mapper(unmappedTargetPolicy = ERROR)
        interface LegDtoMapper {

            LegDtoMapper INSTANCE = getMapper(LegDtoMapper.class);

            LegDto map(Leg leg);

            Leg map(LegDto legDto);
        }

        @ValueObject
        public record LegDto(LegPosition legPosition, Color color) {
        }
}
