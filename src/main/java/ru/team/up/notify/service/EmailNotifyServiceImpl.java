package ru.team.up.notify.service;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.team.up.notify.entity.Notify;
import ru.team.up.notify.entity.NotifyStatus;

import java.time.LocalDateTime;

/**
 * @author Nail Faizullin, Dmitry Koryanov
 * <p>
 * Класс сервиса для отправки уведомлений ru.team.up.notify.entity.Notify
 */
@Slf4j
@Service
@Setter
public class EmailNotifyServiceImpl implements EmailNotifyService {

    @Autowired
    private NotifyService notifyService;
    @Autowired
    private JavaMailSender emailSender;

    @Value("${scheduler.emails.number}")
    private int numOfEmailsToSend;

    /**
     * Метод отправляет уведомление по электронной почте
     */
    @Override
    public void sendNotify(Notify notify) {
        log.debug("Подготовка уведомления id:{} к отправке на e-mail:{}",
                notify.getId(), notify.getEmail());

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(notify.getEmail());
        message.setSubject(notify.getSubject());
        message.setText(notify.getText());

        try {
            emailSender.send(message);
            notify.setStatus(NotifyStatus.SENT);
            notify.setSentTime(LocalDateTime.now());
            notifyService.save(notify).subscribe();
            log.debug("Уведомление id:{} отправлено на электронную почту {}.",
                    notify.getId(), notify.getEmail());
        } catch (MailException e) {
            log.debug("Ошибка при отправке уведомления по электронной почте. Уведомление id:{}, e-mail:{}. {}",
                    notify.getId(), notify.getEmail(), e.toString());
        }

    }

    /**
     * Метод выбирает уведомления для отправки по электронной почте из базы
     */
    @Scheduled(fixedRateString = "${scheduler.delay}")
    @Override
    public void sendNotifys() {
        log.debug("Вызван метод для отправки уведомлений в статусе NOT_SENT. Отсылаем {} уведомления(й)",numOfEmailsToSend);

        notifyService
                .findAllByStatusEquals(NotifyStatus.NOT_SENT)
                .take(numOfEmailsToSend)
                .subscribe(n -> sendNotify(n));
    }
}
