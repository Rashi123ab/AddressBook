package com.rashi.AddressBook.Address;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@ComponentScan(basePackages = "com.rashi.AddressBook.Address")
@SpringBootApplication
public class AddressApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(AddressApplication.class, args);
		String activeProfile = context.getEnvironment().getActiveProfiles().length > 0
				? context.getEnvironment().getActiveProfiles()[0]
				: "default";
		log.info("Address Book App Started in {} environment", activeProfile);
	}

}
