package nl.pancompany.unicorn.magic.adapter.out.persistence.database;

import lombok.RequiredArgsConstructor;
import nl.pancompany.unicorn.magic.application.domain.model.MagicAbility;
import nl.pancompany.unicorn.magic.application.port.out.MagicAbilityRepository;
import org.jmolecules.ddd.annotation.Repository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Repository
public class DatabaseMagicAbilityRepository implements MagicAbilityRepository {

    private final MagicAbilityJpaRepository magicAbilityJpaRepository;
    private final MagicAbilityJpaMapper magicAbilityJpaMapper;

    @Override
    public List<MagicAbility> find(String unicornId) {
        return magicAbilityJpaMapper.map(magicAbilityJpaRepository.findByUnicornId(unicornId));
    }
}
