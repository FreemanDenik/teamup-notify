package ru.team.up.notify.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import ru.team.up.notify.entity.Notify;
import ru.team.up.notify.service.NotifyService;

/**
 * REST-контроллер для уведомлений
 *
 * @author Nail Faizullin, Dmitry Koryanov
 * @link localhost:8085/swagger-ui.html
 * Документация API
 */
@Tag(name = "Notify Controller", description = "Notify API")
@RestController
@RequestMapping("/notify")
@Slf4j
public class NotifyUserController {

    @Autowired
    NotifyService notifyService;

    /**
     * Запись в базу уведомления.
     * @param notify Mono, параметризованный уведомлением.
     * @return возвращает записанное уведомление с заполненным базой Id идентификатором.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Flux<Notify> notifyUser(@RequestBody Flux<Notify> notify){
        return notifyService.save(notify);
    }
}
