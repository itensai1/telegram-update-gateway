package com.tensai.telegram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.security.autoconfigure.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
public class TelegramUpdateGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(TelegramUpdateGatewayApplication.class, args);
	}

}
