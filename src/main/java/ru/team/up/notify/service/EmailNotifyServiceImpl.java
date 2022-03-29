package ru.team.up.notify.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.team.up.notify.entity.Notification;

import java.time.LocalDateTime;

/**
 * @author Nail Faizullin, Dmitry Koryanov
 * <p>
 * Класс сервиса для отправки уведомлений ru.team.up.notify.entity.Notification
 */
@Slf4j
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EmailNotifyServiceImpl implements EmailNotifyService{

    private NotificationService notificationService;
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
            notification.setStatus(Notification.Status.SENT);
            notification.setSentTime(LocalDateTime.now());
            notificationService.save(notification).subscribe();
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
    @Scheduled(fixedRate = 10000)
    @Override
    public void sendNotifications() {
        log.debug("Вызван метод для отправки уведомлений в статусе NOT_SENT");
        notificationService.findAllByStatusEquals(Notification.Status.NOT_SENT).limitRate(1).subscribe(n -> sendNotification(n));
    }
}
