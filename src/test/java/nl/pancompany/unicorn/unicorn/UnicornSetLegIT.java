package nl.pancompany.unicorn.unicorn;

import lombok.RequiredArgsConstructor;
import nl.pancompany.unicorn.common.events.NewLegObtained;
import nl.pancompany.unicorn.common.model.UnicornId;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.Scenario;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static nl.pancompany.unicorn.common.Constants.INITIAL_UNICORN_UUID;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_CLASS;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql(value = "/unicorn.sql", executionPhase = BEFORE_TEST_CLASS)
@ActiveProfiles("module-integration-test-run")
@ApplicationModuleTest
@AutoConfigureMockMvc
@RequiredArgsConstructor
public class UnicornSetLegIT {

    final MockMvc mockMvc;

    @Test
    void putLegResultsInNewLegObtainedEvent(Scenario scenario) {
        UnicornId unicornId = UnicornId.of(INITIAL_UNICORN_UUID);
        scenario.stimulate(wrap(() -> mockMvc.perform(put("/unicorns/{unicornId}/legs/FRONT_RIGHT", unicornId.toStringValue())
                                .contentType(APPLICATION_JSON)
                                .content("""
                                        {
                                            "legPosition": "FRONT_RIGHT",
                                            "color": "RED"
                                        }
                                        """))
                        .andExpect(status().isOk())
                        .andExpect(content().json("""
                                {
                                    "legPosition": "FRONT_RIGHT",
                                    "color": "RED"
                                }
                                """)))
                )
                .andWaitForEventOfType(NewLegObtained.class)
                .matchingMapped(
                        (Function<NewLegObtained, UnicornId>) NewLegObtained::unicornId,
                        (Predicate<UnicornId>) unicornId::equals)
                .toArrive();
    }

    <T> Supplier<T> wrap(ThrowingSupplier<T> supplier) {
        return () -> {
            try {
                return supplier.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    @FunctionalInterface
    interface ThrowingSupplier<T> {
        T get() throws Exception;
    }
}
