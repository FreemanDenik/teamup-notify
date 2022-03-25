package ru.team.up.notify.repositories;

import org.springframework.data.mongodb.core.aggregation.BooleanOperators;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.team.up.notify.entity.Notification;
import ru.team.up.notify.entity.NotificationStatus;

@Repository
public interface NotificationRepository extends ReactiveCrudRepository<Notification, Long> {

    /**
     * Метод возвращает все неотправленные
     */
    Flux<Notification> findAllByStatusEquals(Notification.Status notificationStatus);
}
