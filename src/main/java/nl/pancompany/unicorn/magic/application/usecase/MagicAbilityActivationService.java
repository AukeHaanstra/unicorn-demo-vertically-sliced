package nl.pancompany.unicorn.magic.application.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.pancompany.unicorn.common.events.NewLegObtained;
import nl.pancompany.unicorn.magic.application.domain.model.MagicAbility;
import nl.pancompany.unicorn.magic.application.port.in.ActivateMagicAbilityUsecase;
import nl.pancompany.unicorn.magic.application.port.out.MagicAbilityRepository;
import nl.pancompany.unicorn.magic.application.port.out.MagicEventPublisher;
import org.jmolecules.ddd.annotation.Service;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class MagicAbilityActivationService implements ActivateMagicAbilityUsecase {

    private final MagicAbilityRepository magicAbilityRepository;
    private final MagicEventPublisher magicEventPublisher;

    @Override
    public void activateMagicAbilityFor(NewLegObtained newLegObtained) {
        List<MagicAbility> magicalAbilities = magicAbilityRepository.find(newLegObtained.unicornId().toStringValue());
        Optional<String> newMagicAbility = magicalAbilities.stream()
                .filter(magicalAbility -> magicalAbility.isActivatedBy(newLegObtained.color()))
                .map(MagicAbility::getName)
                .findFirst();
        log.info("New magic ability found: {}", newMagicAbility.orElse("<none>"));
        magicEventPublisher.magicAbilityObtaied(newLegObtained.unicornId(), newLegObtained.legPosition(), newMagicAbility);
    }

}
