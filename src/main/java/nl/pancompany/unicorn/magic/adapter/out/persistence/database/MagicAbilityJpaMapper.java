package nl.pancompany.unicorn.magic.adapter.out.persistence.database;

import nl.pancompany.unicorn.common.model.UnicornId;
import nl.pancompany.unicorn.magic.application.domain.model.MagicAbility;
import nl.pancompany.unicorn.magic.application.domain.model.MagicAbilityId;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface MagicAbilityJpaMapper {

    @Mapping(target = "unicornId", source = "unicornId", qualifiedByName = "mapUnicornId")
    @Mapping(target = "magicAbilityId", source = "magicAbilityId", qualifiedByName = "mapMagicAbilityId")
    MagicAbility map(MagicAbilityJpaEntity magicAbilityJpaEntity);

    @Named("mapUnicornId")
    default UnicornId mapUnicornId(String value) {
        return UnicornId.of(value);
    }

    @Named("mapMagicAbilityId")
    default MagicAbilityId mapMagicAbilityId(String value) {
        return MagicAbilityId.of(value);
    }

    @Mapping(target = "id", ignore = true)
    MagicAbilityJpaEntity map(MagicAbility magicAbility);

    default String mapUnicornId(UnicornId unicornId) {
        return unicornId == null ? null : unicornId.toStringValue();
    }

    default String mapMagicAbilityId(MagicAbilityId magicAbilityId) {
        return magicAbilityId == null ? null : magicAbilityId.toStringValue();
    }

    List<MagicAbility> map(List<MagicAbilityJpaEntity> magicAbilities);
}
