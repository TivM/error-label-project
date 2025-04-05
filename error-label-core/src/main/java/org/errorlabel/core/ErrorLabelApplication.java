package org.errorlabel.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(
		scanBasePackages = {"org.errorlabel.persistence", "org.errorlabel.core"}
)
public class ErrorLabelApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErrorLabelApplication.class, args);
	}

}
