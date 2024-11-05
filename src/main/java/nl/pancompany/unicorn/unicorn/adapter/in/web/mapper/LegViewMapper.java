package nl.pancompany.unicorn.unicorn.adapter.in.web.mapper;

import nl.pancompany.unicorn.unicorn.adapter.in.web.model.LegView;
import nl.pancompany.unicorn.common.model.UnicornId;
import nl.pancompany.unicorn.unicorn.application.domain.model.Leg;
import nl.pancompany.unicorn.unicorn.application.port.in.SetLegUsecase;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import static org.mapstruct.ReportingPolicy.ERROR;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ERROR)
public interface LegViewMapper {

    LegView map(Leg.LegDto legDto);

    @Mapping(target = "unicornId", source = "unicornId", qualifiedByName = "mapUnicornId")
    SetLegUsecase.SetLegDto map(LegView legDto, String unicornId);

    @Named("mapUnicornId")
    default UnicornId mapUnicornId(String unicornId) {
        return UnicornId.of(unicornId);
    }

}
