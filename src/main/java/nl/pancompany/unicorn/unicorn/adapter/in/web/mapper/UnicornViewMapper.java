package nl.pancompany.unicorn.unicorn.adapter.in.web.mapper;

import nl.pancompany.unicorn.unicorn.adapter.in.web.model.UnicornView;
import nl.pancompany.unicorn.common.model.UnicornId;
import nl.pancompany.unicorn.unicorn.application.domain.model.Unicorn;
import org.mapstruct.Mapper;

import static org.mapstruct.ReportingPolicy.ERROR;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ERROR, uses = LegViewMapper.class)
public interface UnicornViewMapper {

    UnicornView map(Unicorn.UnicornDto unicorn);

    default String mapUnicornId(UnicornId unicornId) {
        return unicornId.toStringValue();
    }
}
