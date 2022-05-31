package ru.team.up.notify.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import ru.team.up.notify.entity.Notify;
import ru.team.up.notify.entity.NotifyStatus;
import ru.team.up.notify.service.NotifyService;
import ru.team.up.notify.utils.RandomString;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class, SpringExtension.class})
@WebFluxTest(controllers = NotifyUserController.class)
@DisplayName("Unit-тесты реактивного контроллера")
class NotifyUserControllerTest {

    @MockBean
    NotifyService notifyService;

    @Autowired
    private WebTestClient webTestClient;

    @Captor
    ArgumentCaptor<Flux<Notify>> captoredNotifyFlux;

    @Test
    @DisplayName("Тест реактивного контроллера")
    void notifyUserControllerTest() {

        //given
        Notify notifyTest = Notify.builder()
                .text(RandomString.getAlphaNumericString(15))
                .subject(RandomString.getAlphaNumericString(8))
                .email("vasya.pupkin@mail.ru")
                .status(NotifyStatus.NOT_SENT)
                .creationTime(LocalDateTime.now())
                .sentTime(null)
                .build();

        Flux<Notify> notifyTestFlux = Flux.just(notifyTest);

        when(notifyService.save(notifyTestFlux)).thenReturn(notifyTestFlux);

        //when
        webTestClient
                .post()
                .uri("/notify")
                .contentType(MediaType.APPLICATION_JSON)
                .body(notifyTestFlux, Notify.class)
                .exchange()
                .expectStatus().isCreated();


        //then
        verify(notifyService, times(1)).save(captoredNotifyFlux.capture());
        assertThat(captoredNotifyFlux.getValue().blockFirst()).isEqualTo(notifyTest);
    }
}