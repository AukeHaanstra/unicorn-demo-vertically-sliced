package nl.pancompany.unicorn.magic.application.domain.model;

import lombok.Value;

import java.util.UUID;

@Value(staticConstructor = "of")
public class MagicAbilityId {

    UUID value;

    public String toStringValue() {
        return value.toString();
    }

    public static MagicAbilityId generate() {
        return new MagicAbilityId(UUID.randomUUID());
    }

    public static MagicAbilityId of(String uuid) {
        return new MagicAbilityId(UUID.fromString(uuid));
    }
}
