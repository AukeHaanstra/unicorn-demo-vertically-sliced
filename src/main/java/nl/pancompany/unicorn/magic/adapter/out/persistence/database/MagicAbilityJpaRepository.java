package nl.pancompany.unicorn.magic.adapter.out.persistence.database;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MagicAbilityJpaRepository extends JpaRepository<MagicAbilityJpaEntity, String> {

    List<MagicAbilityJpaEntity> findByUnicornId(String unicornId);

}