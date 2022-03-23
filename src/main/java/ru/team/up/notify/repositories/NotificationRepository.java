package ru.team.up.notify.repositories;

import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import ru.team.up.notify.entity.Notification;
import ru.team.up.notify.entity.NotificationStatus;

@Repository
public interface NotificationRepository extends ReactiveCrudRepository<Notification, Long> {

    /**
     * Метод возвращает все неотправленные
     */
    Flux<Notification> findAllByNotificationStatusEquals(NotificationStatus notificationStatus);
}
