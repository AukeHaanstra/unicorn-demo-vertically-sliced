package nl.pancompany.unicorn.unicorn;

import lombok.RequiredArgsConstructor;
import nl.pancompany.unicorn.common.events.NewLegObtained;
import nl.pancompany.unicorn.common.model.Color;
import nl.pancompany.unicorn.common.model.LegPosition;
import nl.pancompany.unicorn.common.model.UnicornId;
import nl.pancompany.unicorn.unicorn.adapter.out.eventpublisher.UnicornSpringEventPublisher;
import org.junit.jupiter.api.Test;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.AssertablePublishedEvents;
import org.springframework.modulith.test.PublishedEvents;
import org.springframework.modulith.test.Scenario;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.function.Function;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_CLASS;

@Sql(value = "/unicorn.sql", executionPhase = BEFORE_TEST_CLASS)
@ActiveProfiles("module-integration-test-run")
@ApplicationModuleTest
@RequiredArgsConstructor
public class UnicornSpringEventPublisherIT {

    final UnicornSpringEventPublisher unicornSpringEventPublisher;

    @Test
    void newLegObtainedEventIsPublished(PublishedEvents events) {
        UnicornId unicornId = UnicornId.generate();
        unicornSpringEventPublisher.newLegObtained(unicornId, LegPosition.BACK_LEFT, Color.BLUE);

        var matchingMapped = events.ofType(NewLegObtained.class).matching(
                (Function<NewLegObtained, UnicornId>) NewLegObtained::unicornId,
                (Predicate<UnicornId>) unicornId::equals);

        assertThat(matchingMapped).hasSize(1);
    }

    @Test
    void newLegObtainedEventIsPublished(AssertablePublishedEvents events) {
        UnicornId unicornId = UnicornId.generate();
        unicornSpringEventPublisher.newLegObtained(unicornId, LegPosition.BACK_LEFT, Color.BLUE);

        assertThat(events)
                .contains(NewLegObtained.class)
                .matching(
                        (Function<NewLegObtained, UnicornId>) NewLegObtained::unicornId,
                        (Predicate<UnicornId>) unicornId::equals);
    }

    @Test
    void newLegObtainedEventIsPublished(Scenario scenario) {
        UnicornId unicornId = UnicornId.generate();

        scenario.stimulate(() -> unicornSpringEventPublisher.newLegObtained(unicornId, LegPosition.BACK_LEFT, Color.BLUE))
                .andWaitForEventOfType(NewLegObtained.class)
                .matchingMapped(
                        (Function<NewLegObtained, UnicornId>) NewLegObtained::unicornId,
                        (Predicate<UnicornId>) unicornId::equals)
                .toArrive();
    }

}
