package ru.team.up.notify.service;

import ru.team.up.notify.entity.Notify;

/**
 * @author Nail Faizullin, Dmitry Koryanov
 */
public interface EmailNotifyService {
    void sendNotifys();
    void sendNotify(Notify notify);
}
