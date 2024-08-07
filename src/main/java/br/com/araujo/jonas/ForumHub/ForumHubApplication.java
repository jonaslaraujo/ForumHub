package br.com.araujo.jonas.ForumHub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ForumHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForumHubApplication.class, args);
	}

}
