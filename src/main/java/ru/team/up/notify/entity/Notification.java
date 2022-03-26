package ru.team.up.notify.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * Сущность уведомления
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="notification")
@Builder
public class Notification {

    /**
     * Статус уведомления
     */
    public static enum Status {
        NOT_SENT,
        SENT;
    }

    /**
     * Уникальный идентификатор, генерится самой базой
     */
    @Id
    private String id;

    /**
     * Текст уведомления
     */
    private String text;

    /**
     * Тема уведомления
     */
    private String subject;

    /**
     * Email, куда необходимо отправить уведомление
     */
    private String email;

    /**
     * Статус уведомления
     */
    @Indexed
    private Status status;

    /**
     * Время создания уведомления
     */
    private LocalDateTime creationTime;

    /**
     * Время отправки уведомления
     */
    private LocalDateTime sentTime;

}
