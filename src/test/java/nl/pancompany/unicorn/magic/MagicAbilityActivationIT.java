package nl.pancompany.unicorn.magic;

import nl.pancompany.unicorn.common.events.MagicAbilityObtained;
import nl.pancompany.unicorn.common.events.NewLegObtained;
import nl.pancompany.unicorn.common.model.Color;
import nl.pancompany.unicorn.common.model.LegPosition;
import nl.pancompany.unicorn.common.model.UnicornId;
import org.junit.jupiter.api.Test;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.Scenario;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.function.Function;
import java.util.function.Predicate;

import static nl.pancompany.unicorn.common.Constants.INITIAL_UNICORN_UUID;
import static org.assertj.core.api.Assertions.assertThat;

@Sql(value = "classpath:magic.sql")
@ActiveProfiles("module-integration-test-run")
@ApplicationModuleTest
public class MagicAbilityActivationIT {

    @Test
    void newLegObtainedEventResultsInMagicAbilityObtainedEvent(Scenario scenario) {
        // this is a change
        UnicornId unicornId = UnicornId.of(INITIAL_UNICORN_UUID);
        var newLegObtainedEvent = new NewLegObtained(unicornId, LegPosition.BACK_LEFT, Color.RED);

        scenario.publish(newLegObtainedEvent)
                .andWaitForEventOfType(MagicAbilityObtained.class)
                .matchingMapped(
                        (Function<MagicAbilityObtained, UnicornId>) MagicAbilityObtained::unicornId,
                        (Predicate<UnicornId>) unicornId::equals)
                .toArriveAndVerify(magicAbilityObtained ->
                        assertThat(magicAbilityObtained.obtainedMagicAbility().magicAbility()).isPresent());
    }

}
