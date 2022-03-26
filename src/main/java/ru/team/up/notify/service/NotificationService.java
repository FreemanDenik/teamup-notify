package ru.team.up.notify.service;

import reactor.core.publisher.Flux;
import ru.team.up.notify.entity.Notification;
import reactor.core.publisher.Mono;

/**
 * @author Nail Faizullin, Dmitry Koryanov
 *
 */
public interface NotificationService {

    Mono<Notification> save(Mono<Notification> notification);
    Mono<Notification> save(Notification notification);
    Flux<Notification> findAllByStatusEquals(Notification.Status notificationStatus);

}
