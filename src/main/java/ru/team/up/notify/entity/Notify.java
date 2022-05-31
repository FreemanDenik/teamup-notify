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
@Document(collection="notify")
@Builder
@EqualsAndHashCode
public class Notify {

    /**
     * Уникальный идентификатор, генерится самой базой при сохранении записи в БД
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
    private NotifyStatus status;

    /**
     * Время создания уведомления
     */
    private LocalDateTime creationTime;

    /**
     * Время отправки уведомления
     */
    private LocalDateTime sentTime;

}
