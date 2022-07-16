package ru.team.up.notify.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@EnableScheduling
@EnableWebMvc
@ComponentScan({"ru.team.up.notify"})
@EnableReactiveMongoRepositories("ru.team.up.notify.core.repositories")
@EntityScan("ru.team.up.notify.core")
@PropertySource({
        "classpath:sup.properties",
        "classpath:kafka.properties"
})
public class TeamupNotifyApplication {

    public static void main(String[] args) {

        SpringApplication.run(TeamupNotifyApplication.class, args);
    }

}
