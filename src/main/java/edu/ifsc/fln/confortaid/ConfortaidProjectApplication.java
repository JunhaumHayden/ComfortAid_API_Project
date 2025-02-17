package edu.ifsc.fln.confortaid;

import edu.ifsc.fln.confortaid.config.InsertImages;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ConfortaidProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfortaidProjectApplication.class, args);
	}

	@Bean
	public CommandLineRunner run() {
		return args -> {
			InsertImages.main(new String[]{});
		};
	}

}
