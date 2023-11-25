package com.ctf.v1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ctf.v1.auth.AuthenticationService;
import com.ctf.v1.auth.RegisterRequest;

import static com.ctf.v1.model.Role.ADMIN;
import static com.ctf.v1.model.Role.PLAYER;

@SpringBootApplication
public class CtfApplication {

	public static void main(String[] args) {
		SpringApplication.run(CtfApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service) {
		return args -> {
			var admin = RegisterRequest.builder()
					.username("Admin")
					.email("admin@mail.com")
					.password("password1")
					.role(ADMIN)
					.build();
			System.out.println("Admin token: " + service.register(admin).getAccessToken());

			var player = RegisterRequest.builder()
					.username("player")
					.email("player@mail.com")
					.password("password1")
					.role(PLAYER)
					.build();
			System.out.println("Manager token: " + service.register(player).getAccessToken());

		};
	}

}
