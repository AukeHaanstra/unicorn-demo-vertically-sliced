package nl.pancompany.unicorn.magic.application;

import lombok.Getter;
import nl.pancompany.unicorn.magic.application.port.in.ActivateMagicAbilityUsecase;
import nl.pancompany.unicorn.magic.application.port.out.MagicAbilityRepository;
import nl.pancompany.unicorn.magic.application.port.out.MagicEventPublisher;
import nl.pancompany.unicorn.magic.application.usecase.MagicAbilityActivationService;

@Getter
public class MagicApplicationContext {

    private final ActivateMagicAbilityUsecase activateMagicAbilityUsecase;

    public MagicApplicationContext(MagicAbilityRepository magicAbilityRepository, MagicEventPublisher magicEventPublisher) {
        this.activateMagicAbilityUsecase = new MagicAbilityActivationService(magicAbilityRepository, magicEventPublisher);
    }

}
