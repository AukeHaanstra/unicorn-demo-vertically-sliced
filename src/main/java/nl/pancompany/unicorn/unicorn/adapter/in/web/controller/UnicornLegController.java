package nl.pancompany.unicorn.unicorn.adapter.in.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.pancompany.unicorn.unicorn.adapter.in.web.mapper.LegViewMapper;
import nl.pancompany.unicorn.unicorn.adapter.in.web.model.LegView;
import nl.pancompany.unicorn.common.model.LegPosition;
import nl.pancompany.unicorn.common.model.UnicornId;
import nl.pancompany.unicorn.unicorn.application.domain.model.Leg;
import nl.pancompany.unicorn.unicorn.application.port.in.GetLegUsecase;
import nl.pancompany.unicorn.unicorn.application.port.in.SetLegUsecase;
import nl.pancompany.unicorn.unicorn.application.port.out.UnicornRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/unicorns/{unicornId}/legs/{legPosition}")
public class UnicornLegController {

    private final LegViewMapper legViewMapper;
    private final GetLegUsecase getLegUsecase;
    private final SetLegUsecase setLegUsecase;

    @GetMapping
    public ResponseEntity<LegView> getLeg(@PathVariable("unicornId") String unicornId,
                                          @PathVariable("legPosition") LegPosition legPosition) throws UnicornRepository.UnicornNotFoundException {
        Leg.LegDto leg = getLegUsecase.getLeg(new GetLegUsecase.QueryLegDto(UnicornId.of(unicornId), legPosition));
        return ResponseEntity.ok(legViewMapper.map(leg));
    }

    @PutMapping
    public ResponseEntity<LegView> putLeg(@PathVariable("unicornId") String unicornId,
                                          @PathVariable("legPosition") LegPosition legPosition,
                                          @RequestBody LegView legView) throws UnicornRepository.UnicornNotFoundException {
        legView = new LegView(legPosition, legView.color());
        LegView newLegView = legViewMapper.map(setLegUsecase.setLeg(legViewMapper.map(legView, unicornId)));
        return ResponseEntity.ok(newLegView);
    }

}
