package nl.pancompany.unicorn.magic.adapter.in.eventlistener;


import lombok.RequiredArgsConstructor;
import nl.pancompany.unicorn.common.events.NewLegObtained;
import nl.pancompany.unicorn.magic.application.port.in.ActivateMagicAbilityUsecase;
import org.jmolecules.event.annotation.DomainEventHandler;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Component // Be careful! Don't use @Service, or you won't receive any event messages!
@RequiredArgsConstructor
public class MagicSpringEventListener {

    private final ActivateMagicAbilityUsecase activateMagicAbilityUsecase;

    @ApplicationModuleListener
    @DomainEventHandler
    void on(NewLegObtained newLegObtained) {
        activateMagicAbilityUsecase.activateMagicAbilityFor(newLegObtained);
    }
}
