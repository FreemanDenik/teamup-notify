package ru.team.up.notify.sup.service;


/**
 * Интерфейс сервиса для отправки запросов в kafka
 */

public interface KafkaSupService {
    /**
     * Отправка запроса на получение всех параметров модуля из брокера сообщений
     */
    void getAllModuleParameters();
}