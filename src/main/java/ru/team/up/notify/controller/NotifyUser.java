package ru.team.up.notify.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.team.up.notify.entity.Notification;
import ru.team.up.notify.repositories.NotificationRepository;

@RestController
@RequestMapping("/notifyuser")
@Slf4j
public class NotifyUser {

    @Autowired
    NotificationRepository notificationRepository;

    /**
     * Запись в базу уведомления
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Notification> notifyUser(@RequestBody Mono<Notification> notificationMono){

        System.out.println("here..");

        return notificationRepository.saveAll(notificationMono).next();

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Mono<Notification> notifyUser(){

        return Mono.just(new Notification());
        //return notificationRepository.saveAll(notificationMono).next();

    }
}
