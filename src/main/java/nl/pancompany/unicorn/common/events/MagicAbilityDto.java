package nl.pancompany.unicorn.common.events;

import nl.pancompany.unicorn.common.model.LegPosition;

import java.util.Objects;
import java.util.Optional;

public record MagicAbilityDto(LegPosition legPosition, Optional<String> magicAbility) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MagicAbilityDto that)) return false;
        return legPosition == that.legPosition;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(legPosition);
    }
}
