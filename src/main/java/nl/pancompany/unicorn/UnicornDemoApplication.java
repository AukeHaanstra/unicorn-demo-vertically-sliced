package nl.pancompany.unicorn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.modulith.Modulithic;

@Modulithic(systemName = "Unicorn Demo")
@SpringBootApplication
public class UnicornDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnicornDemoApplication.class, args);
	}

}
