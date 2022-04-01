package ru.team.up.notify.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.team.up.notify.entity.Notify;
import ru.team.up.notify.entity.NotifyStatus;
import ru.team.up.notify.repositories.NotifyRepository;

/**
 * @author Nail Faizullin, Dmitry Koryanov
 * <p>
 * Класс сервиса для управления уведомлениями ru.team.up.notify.entity.Notify
 */
@Slf4j
@Service
public class NotifyServiceImpl implements NotifyService{

    @Autowired
    NotifyRepository notifyRepository;

    /**
     * @param notify Flux, параметризованный уведомлением
     * @return Mono, параметризованное уведомлением с заполенным базой Id уведомления
     */
    @Override
    public Flux<Notify> save(Flux<Notify> notify) {
        log.debug("Сохраняем Flux<уведомление> в базу данных для последующей отправки.");
        return notifyRepository.saveAll(notify);
    }

    /**
     * @param notify Объект уведомления
     * @return Mono, параметризованное уведомлением с заполненным базой Id уведомления
     */
    @Override
    public Mono<Notify> save(Notify notify) {
        log.debug("Сохраняем уведомление в базу данных {}}", notify);
        return notifyRepository.save(notify);
    }

    /**
     * @param notifyStatus Статуст уведомлений, которые надо вернуть в поток
     * @return поток Flux, параметризованный уведомлениеми найденными в данном статусе
     */
    @Override
    public Flux<Notify> findAllByStatusEquals(NotifyStatus notifyStatus) {
        return notifyRepository.findAllByStatusEquals(notifyStatus);
    }
}
