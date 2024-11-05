package nl.pancompany.unicorn.magic.adapter.out.persistence.database;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@Entity
@Table(name = "MAGIC_ABILITY")
public class MagicAbilityJpaEntity {

        @Id
        @GeneratedValue(strategy = IDENTITY)
        private Long id;

        @Column(name = "MAGIC_ABILITY_UUID", nullable = false)
        private String magicAbilityId;

        @Column(name = "UNICORN_UUID", nullable = false)
        private String unicornId;

        @Column(name = "NAME", nullable = false, unique = true)
        private String name;

        @Column(name = "ACTIVATION_COLOR", nullable = false)
        private String activationColor;

        @Column(name = "DESCRIPTION")
        private String description;

}
