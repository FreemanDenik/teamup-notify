package ru.team.up.notify.sup.service;

import ru.team.up.dto.SupParameterDto;
import ru.team.up.notify.sup.entity.SupParameter;

import java.util.List;

/**
 * Интерфейс сервиса для получения локальных параметров и установки значений по умолчанию
 */
public interface ParameterService {

    /**
     * Параметры модуля по умолчанию
     */
    /*--------------AUTH module--------------*/
    SupParameter<String> emailFrom = new SupParameter<>(
            "TEAMUP_NOTIFY_EMAIL_FROM",
            "teamupteamupov.1@yandex.ru");

    SupParameter<String> emailLogin = new SupParameter<>(
            "TEAMUP_NOTIFY_EMAIL_LOGIN",
            "teamupteamupov.1@yandex.ru");

    SupParameter<String> emailPassword = new SupParameter<>(
            "TEAMUP_NOTIFY_EMAIL_PASSWORD",
            "lwlcqvvyvdyjbvvm");

    SupParameter<String> emailTest = new SupParameter<>(
            "TEAMUP_NOTIFY_EMAIL_TEST",
            "Gzubarew111@yandex.ru");

    SupParameter<Long> scheduledTimer = new SupParameter<>(
            "TEAMUP_NOTIFY_SCHEDULED_TIMER",
            30000L);


    /**
     * Получение листа текущих параметров из кэша
     */
    List<SupParameterDto<?>> getAll();

    /**
     * Добавление или перезапись параметра
     */
    void addParam(SupParameterDto<?> parameter);

    /**
     * Поиск параметра по имени
     */
    SupParameterDto<?> getParamByName(String name);

}