package ru.team.up.notify.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import ru.team.up.notify.entity.Notification;
import ru.team.up.notify.entity.NotificationStatus;
import ru.team.up.notify.repositories.NotificationRepository;

import java.time.LocalDateTime;

/**
 * @author Dmitry Koryanov
 */
@Slf4j
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EmailNotifyServiceImpl implements EmailNotifyService{

    private NotificationRepository notificationRepository;
    private JavaMailSender emailSender;

    /**
     * Метод отправляет уведомление по электронной почте
     */
    @Override
    public void sendNotification(Notification notification){
        log.debug("Подготовка уведомления id:{} к отправке на e-mail:{}",
                notification.getId(), notification.getEmail());

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(notification.getEmail());
        message.setSubject(notification.getSubject());
        message.setText(notification.getText());

        try {
            emailSender.send(message);
            notification.setNotificationStatus(NotificationStatus.SENT);
            notification.setSentTime(LocalDateTime.now());
            notificationRepository.save(notification);
            log.debug("Уведомление id:{} отправлено на электронную почту {}.",
                    notification.getId(), notification.getEmail());
        } catch (MailException e) {
            log.debug("Ошибка при отправке уведомления по электронной почте. Уведомление id:{}, e-mail:{}. {}",
                    notification.getId(), notification.getEmail(), e.toString());
        }

    }

    /**
     * Метод выбирает уведомления для отправки по электронной почте из базы
     */
    @Override
    public void sendNotifications() {

        Flux<Notification> notifications = notificationRepository.findAllByNotificationStatusEquals(NotificationStatus.NOT_SENT);

        notifications.subscribe(n -> sendNotification(n));

    }
}
