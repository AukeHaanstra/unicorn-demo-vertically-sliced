package nl.pancompany.unicorn.magic.application.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import nl.pancompany.unicorn.common.model.Color;
import nl.pancompany.unicorn.common.model.UnicornId;
import org.jmolecules.ddd.annotation.AggregateRoot;
import org.jmolecules.ddd.annotation.Association;
import org.jmolecules.ddd.annotation.Identity;

import static java.util.Objects.requireNonNullElseGet;

@Getter
@ToString
@EqualsAndHashCode
@AggregateRoot
public class MagicAbility {

    @Identity
    private MagicAbilityId magicAbilityId;
    @Association
    private UnicornId unicornId;
    private String name;
    private Color activationColor;
    private String description;

    public MagicAbility(MagicAbilityId magicAbilityId, @NonNull UnicornId unicornId, @NonNull String name, @NonNull Color activationColor, String description) {
        this.magicAbilityId = requireNonNullElseGet(magicAbilityId, MagicAbilityId::generate);
        this.unicornId = unicornId;
        this.name = name;
        this.activationColor = activationColor;
        this.description = description;
    }

    public boolean isActivatedBy(Color color) {
        return activationColor.equals(color);
    }
}
