package nl.pancompany.unicorn.magic;

import nl.pancompany.unicorn.magic.application.MagicApplicationContext;
import nl.pancompany.unicorn.magic.application.port.in.ActivateMagicAbilityUsecase;
import nl.pancompany.unicorn.magic.application.port.out.MagicAbilityRepository;
import nl.pancompany.unicorn.magic.application.port.out.MagicEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MagicAppConfiguration {

    @Bean
    public MagicApplicationContext magicApplicationContext(MagicAbilityRepository magicAbilityRepository, MagicEventPublisher magicEventPublisher) {
        return new MagicApplicationContext(magicAbilityRepository, magicEventPublisher);
    }

    @Bean
    public ActivateMagicAbilityUsecase activateMagicAbilityUsecase(MagicApplicationContext magicApplicationContext) {
        return magicApplicationContext.getActivateMagicAbilityUsecase();
    }

}
