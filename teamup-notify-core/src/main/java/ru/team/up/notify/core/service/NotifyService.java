package ru.team.up.notify.core.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.team.up.notify.core.entity.Notify;
import ru.team.up.notify.core.entity.NotifyStatus;

/**
 * @author Nail Faizullin, Dmitry Koryanov
 *
 */
public interface NotifyService {

    Flux<Notify> save(Flux<Notify> notify);
    Mono<Notify> save(Notify notify);
    Flux<Notify> findAllByStatusEquals(NotifyStatus notifyStatus);

}
