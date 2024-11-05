package nl.pancompany.unicorn.unicorn.application;

import lombok.Getter;
import nl.pancompany.unicorn.unicorn.application.port.in.GetLegUsecase;
import nl.pancompany.unicorn.unicorn.application.port.in.GetUnicornUsecase;
import nl.pancompany.unicorn.unicorn.application.port.in.SetLegUsecase;
import nl.pancompany.unicorn.unicorn.application.port.out.UnicornEventPublisher;
import nl.pancompany.unicorn.unicorn.application.port.out.UnicornRepository;
import nl.pancompany.unicorn.unicorn.application.usecase.GetUnicornLegService;
import nl.pancompany.unicorn.unicorn.application.usecase.GetUnicornService;
import nl.pancompany.unicorn.unicorn.application.usecase.SetUnicornLegService;

@Getter
public class UnicornApplicationContext {

    private final GetUnicornUsecase getUnicornUsecase;
    private final GetLegUsecase getLegUsecase;
    private final SetLegUsecase setLegUsecase;

    public UnicornApplicationContext(UnicornRepository unicornRepository, UnicornEventPublisher unicornEventPublisher) {
        this.getUnicornUsecase = new GetUnicornService(unicornRepository);
        this.getLegUsecase = new GetUnicornLegService(unicornRepository);
        this.setLegUsecase = new SetUnicornLegService(unicornRepository, unicornEventPublisher);
    }

}
