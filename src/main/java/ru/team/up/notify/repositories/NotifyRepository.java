package ru.team.up.notify.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import ru.team.up.notify.entity.Notify;
import ru.team.up.notify.entity.NotifyStatus;

@Repository
public interface NotifyRepository extends ReactiveCrudRepository<Notify, Long> {

    /**
     * Метод возвращает все неотправленные уведомления
     */
    /**
     * Метод находит все уведолмения определённого статуса.
     * @param notifyStatus статус уведомлениея.
     * @return возвращает поток уведомлений в данном статусе.
     */
    Flux<Notify> findAllByStatusEquals(NotifyStatus notifyStatus);
}
