package tech.aesys.finale.routine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RoutineApiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoutineApiServiceApplication.class, args);
	}

}
