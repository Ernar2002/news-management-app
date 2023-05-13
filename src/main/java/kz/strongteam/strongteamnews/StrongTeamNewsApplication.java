package kz.strongteam.strongteamnews;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class StrongTeamNewsApplication {

	public static void main(String[] args) {
		SpringApplication.run(StrongTeamNewsApplication.class, args);
	}

}
