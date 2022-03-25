package ru.team.up.notify.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.team.up.notify.entity.Notification;
import ru.team.up.notify.repositories.NotificationRepository;
import ru.team.up.notify.service.EmailNotifyService;
import ru.team.up.notify.utils.RandomString;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/notifyuser")
@Slf4j
public class NotifyUser {

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    EmailNotifyService emailNotifyService;

    /**
     * Запись в базу уведомления
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Notification> notifyUser(@RequestBody Mono<Notification> notificationMono){

        return notificationRepository.saveAll(notificationMono).next();

    }

    @GetMapping("/sendall")
    @ResponseStatus(HttpStatus.OK)
    public void sendAllNotofications(){
        emailNotifyService.sendNotifications();
    }

    @GetMapping("/updateall")
    @ResponseStatus(HttpStatus.OK)
    public void updateAllNotofications(){

        notificationRepository.saveAll(notificationRepository.findAllByStatusEquals(Notification.Status.SENT)
                .map(n -> {n.setStatus(Notification.Status.NOT_SENT); return n;})).subscribe();
    }

    @GetMapping("/send")
    @ResponseStatus(HttpStatus.OK)
    public void sendNotofications(){

        LocalDateTime localDateTime = LocalDateTime.now();

        Notification notification = Notification.builder()
                .text(RandomString.getAlphaNumericString(15))
                .subject(RandomString.getAlphaNumericString(8))
                .email("dmitry.koryanov@yandex.ru")
                .status(Notification.Status.NOT_SENT)
                .creationTime(localDateTime)
                .sentTime(localDateTime)
                .build();

        Mono<Notification> notificationMono = Mono.just(notification);

        Mono<Notification> savedNotificationMono = notificationRepository.saveAll(notificationMono).next();

        savedNotificationMono.subscribe();

        /*savedNotificationMono.subscribe(n -> emailNotifyService.sendNotification(n), e -> log.debug(e.getMessage()), () -> {
            log.debug("Сохраняем изменения в уведомлении..");
            notification.setStatus(Notification.Status.SENT);
            notification.setSentTime(LocalDateTime.now());
            notificationRepository.save(notification).subscribe();
        });*/
    }

}
