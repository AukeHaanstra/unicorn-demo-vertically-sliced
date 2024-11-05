package nl.pancompany.unicorn.unicorn;

import nl.pancompany.unicorn.unicorn.application.UnicornApplicationContext;
import nl.pancompany.unicorn.unicorn.application.port.in.GetLegUsecase;
import nl.pancompany.unicorn.unicorn.application.port.in.GetUnicornUsecase;
import nl.pancompany.unicorn.unicorn.application.port.in.SetLegUsecase;
import nl.pancompany.unicorn.unicorn.application.port.out.UnicornEventPublisher;
import nl.pancompany.unicorn.unicorn.application.port.out.UnicornRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UnicornAppConfiguration {

    @Bean
    public UnicornApplicationContext unicornApplicationContext(UnicornRepository unicornRepository, UnicornEventPublisher unicornEventPublisher) {
        return new UnicornApplicationContext(unicornRepository, unicornEventPublisher);
    }

    @Bean
    public GetUnicornUsecase getUnicornUsecase(UnicornApplicationContext unicornApplicationContext) {
        return unicornApplicationContext.getGetUnicornUsecase();
    }

    @Bean
    public GetLegUsecase getLegUsecase(UnicornApplicationContext unicornApplicationContext) {
        return unicornApplicationContext.getGetLegUsecase();
    }

    @Bean
    public SetLegUsecase setLegUsecase(UnicornApplicationContext unicornApplicationContext) {
        return unicornApplicationContext.getSetLegUsecase();
    }


}
