package nl.pancompany.unicorn.unicorn.adapter.out.presenter.console;

import nl.pancompany.unicorn.unicorn.application.port.in.GetUnicornUsecase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("console-presenter-components")
@Configuration
public class ConsolePresenterContext {

    @Bean
    public WalkingUnicorn walkingUnicorn(GetUnicornUsecase getUnicornUsecase) {
        return new WalkingUnicorn(getUnicornUsecase);
    }
}
