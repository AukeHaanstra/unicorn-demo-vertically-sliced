package nl.pancompany.unicorn;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("integration-test-run")
@SpringBootTest
class UnicornDemoApplicationSetupTest {

	@Test
	void contextLoads() {
	}

	@Test
	void verticallySlicedModuleRulesAdhered() {
		var modules = ApplicationModules.of(UnicornDemoApplication.class);
		modules.verify().forEach(System.out::println);
	}

}
