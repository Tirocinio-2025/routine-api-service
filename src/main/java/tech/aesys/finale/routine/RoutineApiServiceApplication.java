package tech.aesys.finale.routine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
public class RoutineApiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoutineApiServiceApplication.class, args);
	}

}
