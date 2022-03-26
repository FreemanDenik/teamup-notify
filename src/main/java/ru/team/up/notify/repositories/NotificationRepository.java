package ru.team.up.notify.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import ru.team.up.notify.entity.Notification;

@Repository
public interface NotificationRepository extends ReactiveCrudRepository<Notification, Long> {

    /**
     * Метод возвращает все неотправленные уведомления
     */
    /**
     * Метод находит все уведолмения определённого статуса.
     * @param notificationStatus статус уведомлениея.
     * @return возвращает поток уведомлений в данном статусе.
     */
    Flux<Notification> findAllByStatusEquals(Notification.Status notificationStatus);
}
