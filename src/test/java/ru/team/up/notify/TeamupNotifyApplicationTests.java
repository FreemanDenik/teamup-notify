package ru.team.up.notify;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import ru.team.up.notify.entity.Notification;
import ru.team.up.notify.entity.NotificationStatus;
import ru.team.up.notify.repositories.NotificationRepository;

import java.time.LocalDateTime;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@Slf4j
class TeamupNotifyApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private NotificationRepository notificationRepository;

    /*@Test
    void contextLoads() {
    }*/

    @Test
    public void createNotification(){

        Notification notification = new Notification(null,"Notification Text","Notification Subject",
                "dmitry.koryanov@yandex.ru", Notification.Status.NOT_SENT, LocalDateTime.now(),null);

        /*webTestClient.post().uri("/notifyuser").contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .body(Mono.just(notification),Notification.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.email").isEqualTo("dmitry.koryanov@yandex.ru");*/
    }

}
