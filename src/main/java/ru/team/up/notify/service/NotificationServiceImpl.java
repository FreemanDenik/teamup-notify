package ru.team.up.notify.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.team.up.notify.entity.Notification;
import ru.team.up.notify.repositories.NotificationRepository;

/**
 * @author Nail Faizullin, Dmitry Koryanov
 * <p>
 * Класс сервиса для управления уведомлениями ru.team.up.notify.entity.Notification
 */
@Slf4j
@Service
public class NotificationServiceImpl implements NotificationService{

    @Autowired
    NotificationRepository notificationRepository;

    /**
     * @param notification Mono, параметризованное уведомлением
     * @return Mono, параметризованное уведомлением с заполенным базой Id уведомления
     */
    @Override
    public Mono<Notification> save(Mono<Notification> notification) {
        log.debug("Сохраняем Mono<уведомление> в базу данных для последующей отправки.");
        return notificationRepository.saveAll(notification).next();
    }

    /**
     * @param notification Объект уведомления
     * @return Mono, параметризованное уведомлением с заполненным базой Id уведомления
     */
    @Override
    public Mono<Notification> save(Notification notification) {
        log.debug("Сохраняем уведомление в базу данных {}}", notification);
        return notificationRepository.save(notification);
    }

    /**
     * @param notificationStatus Статуст уведомлений, которые надо вернуть в поток
     * @return поток Flux, параметризованный уведомлениеми найденными в данном статусе
     */
    @Override
    public Flux<Notification> findAllByStatusEquals(Notification.Status notificationStatus) {
        return notificationRepository.findAllByStatusEquals(notificationStatus);
    }
}
