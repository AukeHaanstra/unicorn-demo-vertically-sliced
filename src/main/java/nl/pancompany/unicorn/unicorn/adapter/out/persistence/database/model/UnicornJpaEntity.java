package nl.pancompany.unicorn.unicorn.adapter.out.persistence.database.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nl.pancompany.unicorn.unicorn.application.domain.model.Leg;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;


@Data
@NoArgsConstructor
@Entity
@Table(name = "UNICORN")
public class UnicornJpaEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "UNICORN_UUID")
    private String unicornId;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "unicorn", cascade = ALL, fetch = LAZY, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<UnicornLegJpaEntity> legs = new HashSet<>();

    public void addLeg(UnicornLegJpaEntity leg) {
        var existingLeg = legs.stream()
                .filter(l -> l.getLegPosition().equals(leg.getLegPosition()))
                .findFirst();

        if (existingLeg.isPresent()) {
            existingLeg.get().setColor(leg.getColor());
        } else {
            legs.add(leg);
            leg.setUnicorn(this);
        }
    }

}
