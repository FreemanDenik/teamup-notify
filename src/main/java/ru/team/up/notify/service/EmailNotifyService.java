package ru.team.up.notify.service;

import ru.team.up.notify.entity.Notification;

/**
 * @author Dmitry Koryanov
 */
public interface EmailNotifyService {
    void sendNotifications();
    void sendNotification(Notification notification);
}
