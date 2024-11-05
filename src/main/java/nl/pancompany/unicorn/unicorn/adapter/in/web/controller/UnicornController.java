package nl.pancompany.unicorn.unicorn.adapter.in.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.pancompany.unicorn.unicorn.adapter.in.web.mapper.UnicornViewMapper;
import nl.pancompany.unicorn.unicorn.adapter.in.web.model.UnicornView;
import nl.pancompany.unicorn.common.model.UnicornId;
import nl.pancompany.unicorn.unicorn.application.domain.model.Unicorn;
import nl.pancompany.unicorn.unicorn.application.port.in.GetUnicornUsecase;
import nl.pancompany.unicorn.unicorn.application.port.out.UnicornRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/unicorns/{unicornId}")
public class UnicornController {

    private final GetUnicornUsecase getUnicornUsecase;
    private final UnicornViewMapper unicornViewMapper;

    @GetMapping
    public ResponseEntity<UnicornView> getUnicorn(@PathVariable("unicornId") String unicornId) throws UnicornRepository.UnicornNotFoundException {
        Unicorn.UnicornDto unicorn = getUnicornUsecase.getUnicorn(UnicornId.of(unicornId));
        return ResponseEntity.ok(unicornViewMapper.map(unicorn));
    }

}
