package magic_api.magic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "magic_api.magic.repositories")
public class MtgDeckBuilderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MtgDeckBuilderApplication.class, args);
    }
}

