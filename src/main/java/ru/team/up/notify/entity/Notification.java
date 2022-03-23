package ru.team.up.notify.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author Dmitry Koryanov
 *
 */
@Entity
@Table(name = "NOTIFICATION")
@org.springframework.data.relational.core.mapping.Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Notification {

    /**
     * Уникальный идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Текст уведомления
     */
    @Column(name = "TEXT")
    private String text;

    /**
     * Тема уведомления
     */
    @Column(name = "SUBJECT")
    private String subject;

    /**
     * Email, куда необходимо отправить уведомление
     */
    @Column(name = "EMAIL")
    private String email;

    /**
     * Тип сообщения
     */
    @Column(name = "NOTIFICATION_STATUS")
    @Enumerated(EnumType.STRING)
    private NotificationStatus notificationStatus;

    /**
     * Время создания уведомления
     */
    @Column(name = "CREATION_TIME")
    private LocalDateTime creationTime;

    /**
     * Время отправки уведомления
     */
    @Column(name = "SENT_TIME")
    private LocalDateTime sentTime;

}
