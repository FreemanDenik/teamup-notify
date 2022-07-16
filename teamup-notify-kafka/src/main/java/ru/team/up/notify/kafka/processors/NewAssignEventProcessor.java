package ru.team.up.notify.kafka.processors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.team.up.dto.KafkaEventTypeDto;

/**
 * Процессор для обработки события "Назначение нового мероприятия на модератора"
 */

@Slf4j
@Component
public class NewAssignEventProcessor implements KafkaEventProcessor{

    /**
     * Тип событий, которые обрабатывает процессор
     */
    private static final KafkaEventTypeDto supportType = KafkaEventTypeDto.NEW_ASSIGN_EVENT;

    /**
     * Метод обработки события "Назначение нового мероприятия на модератора"
     */
    @Override
    public void perform(KafkaEventTypeDto supportedType) {
        //TODO Добавить в параметры необходимые данные из KafkaEventDto
        //TODO Добавить логику обработки события
    }
}
