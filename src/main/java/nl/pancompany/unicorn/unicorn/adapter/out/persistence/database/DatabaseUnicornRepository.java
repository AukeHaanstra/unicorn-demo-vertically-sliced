package nl.pancompany.unicorn.unicorn.adapter.out.persistence.database;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import nl.pancompany.unicorn.common.model.UnicornId;
import nl.pancompany.unicorn.unicorn.adapter.out.persistence.database.model.UnicornJpaEntity;
import nl.pancompany.unicorn.unicorn.application.domain.model.Unicorn;
import nl.pancompany.unicorn.unicorn.application.port.out.UnicornRepository;
import org.jmolecules.ddd.annotation.Repository;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
@Transactional
@Repository
public class DatabaseUnicornRepository implements UnicornRepository {

    private final UnicornJpaRepository unicornJpaRepository;
    private final UnicornJpaMapper mapper;

    @Override
    public Unicorn find(UnicornId unicornId) throws UnicornNotFoundException {
        requireNonNull(unicornId);
        UnicornJpaEntity unicorn = unicornJpaRepository.findByUnicornId(unicornId.toStringValue())
                .orElseThrow(UnicornNotFoundException::new);
        return mapper.map(unicorn);
    }

    @Override
    public Unicorn add(Unicorn unicorn) throws UnicornAlreadyExistsException {
        requireNonNull(unicorn);
        if (unicornJpaRepository.existsByUnicornId(unicorn.getUnicornId().toStringValue())) {
            throw new UnicornAlreadyExistsException();
        }
        return mapper.map(unicornJpaRepository.save(mapper.map(unicorn)));
    }

    @Override
    public Unicorn update(Unicorn unicorn) throws UnicornNotFoundException {
        requireNonNull(unicorn);
        UnicornJpaEntity persistedUnicorn = unicornJpaRepository.findByUnicornId(unicorn.getUnicornId().toStringValue())
                .orElseThrow(UnicornNotFoundException::new);
        UnicornJpaEntity unicornToMerge = mapper.map(unicorn);
        unicornToMerge.setId(persistedUnicorn.getId());
        return mapper.map(unicornJpaRepository.save(unicornToMerge));
    }

    @Transactional
    @Override
    public Unicorn updateTransactionally(UnicornId unicornId, Consumer<Unicorn> updateLogic) throws UnicornNotFoundException {
        UnicornJpaEntity unicornJpaEntity = unicornJpaRepository.findByUnicornId(unicornId.toStringValue())
                .orElseThrow(UnicornNotFoundException::new);
        Unicorn unicorn = mapper.map(unicornJpaEntity);
        updateLogic.accept(unicorn);
        mapper.map(unicorn, unicornJpaEntity);
        return mapper.map(unicornJpaRepository.save(unicornJpaEntity));
    }

    @Override
    public long count() {
        return unicornJpaRepository.count();
    }

    private static void requireNonNull(Object object) {
        if (object == null) {
            throw new IllegalArgumentException("null");
        }
    }
}
