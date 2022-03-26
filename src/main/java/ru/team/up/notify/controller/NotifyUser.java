package ru.team.up.notify.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.team.up.notify.entity.Notification;
import ru.team.up.notify.service.NotificationService;

/**
 * REST-контроллер для уведомлений
 *
 * @author Nail Faizullin, Dmitry Koryanov
 * @link localhost:8080/swagger-ui.html
 * Документация API
 */
@Tag(name = "Notification Controller", description = "Notification API")
@RestController
@RequestMapping("/notification")
@Slf4j
public class NotifyUser {

    @Autowired
    NotificationService notificationService;

    /**
     * Запись в базу уведомления.
     * @param notification Mono, параметризованный уведомлением.
     * @return возвращает записанное уведомление с заполненным базой Id идентификатором.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Notification> notifyUser(@RequestBody Mono<Notification> notification){
        return notificationService.save(notification);
    }
}
