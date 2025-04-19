package org.errorlabel.projects;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
		scanBasePackages = {"org.errorlabel.persistence", "org.errorlabel.projects"}
)
public class ErrorLabelApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErrorLabelApplication.class, args);
	}

}
