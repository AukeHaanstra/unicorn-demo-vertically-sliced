package nl.pancompany.unicorn.unicorn.adapter.out.persistence.inmemory;

import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import nl.pancompany.unicorn.unicorn.application.domain.model.Unicorn;
import nl.pancompany.unicorn.common.model.UnicornId;
import nl.pancompany.unicorn.unicorn.application.port.out.UnicornRepository;
import org.jmolecules.ddd.annotation.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@RequiredArgsConstructor
@Repository
public class InMemoryUnicornRepository implements UnicornRepository {

    private final UnicornJpaMapper unicornJpaMapper;

    private final Map<String, Unicorn> inMemoryStore = new HashMap<>();

    @Override
    public Unicorn find(UnicornId unicornId) throws UnicornNotFoundException {
        requireNonNull(unicornId);
        Unicorn foundUnicorn = inMemoryStore.get(unicornId.toStringValue());
        if (foundUnicorn == null) {
            throw new UnicornNotFoundException();
        }
        return safeCopy(foundUnicorn);
    }

    @Override
    public Unicorn add(Unicorn unicorn) throws UnicornAlreadyExistsException {
        requireNonNull(unicorn);
        String unicornId = unicorn.getUnicornId().toStringValue();
        if (inMemoryStore.containsKey(unicornId)) {
            throw new UnicornAlreadyExistsException();
        } else {
            inMemoryStore.put(unicornId, unicorn);
        }
        inMemoryStore.put(unicornId, unicorn);
        return safeCopy(unicorn);
    }

    @Override
    public Unicorn update(Unicorn unicorn) throws UnicornNotFoundException {
        requireNonNull(unicorn);
        String unicornId = unicorn.getUnicornId().toStringValue();
        if (inMemoryStore.containsKey(unicornId)) {
            inMemoryStore.put(unicornId, unicorn);
        } else {
            throw new UnicornNotFoundException();
        }
        return safeCopy(unicorn);
    }

    @Override
    @Synchronized
    public Unicorn updateTransactionally(UnicornId unicornId, Consumer<Unicorn> updateLogic) {
        Unicorn unicorn = inMemoryStore.get(unicornId.toStringValue());
        updateLogic.accept(unicorn);
        return safeCopy(unicorn);
    }

    @Override
    public long count() {
        return inMemoryStore.size();
    }

    public void clear() {
        inMemoryStore.clear();
    }

    private Unicorn safeCopy(Unicorn source) {
        return unicornJpaMapper.map(unicornJpaMapper.map(source));
    }

    private static void requireNonNull(Object object) {
        if (object == null) {
            throw new IllegalArgumentException("null");
        }
    }

}
