package ru.team.up.notify.core.service;

import ru.team.up.notify.core.entity.Notify;

/**
 * @author Nail Faizullin, Dmitry Koryanov
 */
public interface EmailNotifyService {
    void sendNotifys();
    void sendNotify(Notify notify);
}
