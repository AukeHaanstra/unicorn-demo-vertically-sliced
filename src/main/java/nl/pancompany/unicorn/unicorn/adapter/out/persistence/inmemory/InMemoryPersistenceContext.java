package nl.pancompany.unicorn.unicorn.adapter.out.persistence.inmemory;

import lombok.Getter;
import nl.pancompany.unicorn.unicorn.application.port.out.UnicornRepository;

@Getter
public class InMemoryPersistenceContext {

    private final UnicornRepository unicornRepository;

    public InMemoryPersistenceContext() {
        this.unicornRepository = new InMemoryUnicornRepository(UnicornJpaMapper.INSTANCE);
    }
}
