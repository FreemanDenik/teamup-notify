package ru.team.up.notify.service;

import reactor.core.publisher.Flux;
import ru.team.up.notify.entity.Notify;
import reactor.core.publisher.Mono;
import ru.team.up.notify.entity.NotifyStatus;

/**
 * @author Nail Faizullin, Dmitry Koryanov
 *
 */
public interface NotifyService {

    Flux<Notify> save(Flux<Notify> notify);
    Mono<Notify> save(Notify notify);
    Flux<Notify> findAllByStatusEquals(NotifyStatus notifyStatus);

}
