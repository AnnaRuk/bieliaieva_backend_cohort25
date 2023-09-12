package servises.Impl;

import models.Event;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import repository.EventRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@DisplayName("RegisterEventServiceImplTest is works ...")
class RegisterEventServiceImplTest {
    private RegisterEventServiceImpl registerEventService;
    private EventRepository eventRepository;
    private final LocalDate BEGIN_DATA_TODO_1 = LocalDate.of(2023,12, 5);
    private final LocalDate END_DATA_TODO_1 = LocalDate.of(2023,12,6);
    private final String TITLE = "TODO1";



    @BeforeEach
    public void setUp() {
        eventRepository = Mockito.mock(EventRepository.class);
        this.registerEventService = new RegisterEventServiceImpl(eventRepository);

    }

    @Nested
    @DisplayName("registerEvent:")
    class RegisterNewEvent {
        @Test
        public void add_event_on_incorrect_title_throws_exception() {
            assertThrows(IllegalArgumentException.class, () ->
                    registerEventService.register(null, BEGIN_DATA_TODO_1,
                            END_DATA_TODO_1));

        }

        @Test
        public void add_event_returns_created_event() {
            Event actual = registerEventService.register(TITLE, BEGIN_DATA_TODO_1, END_DATA_TODO_1);
            Event expected = new Event(1L, TITLE, BEGIN_DATA_TODO_1, END_DATA_TODO_1);
            verify(eventRepository).addEvent(new Event(TITLE, BEGIN_DATA_TODO_1, END_DATA_TODO_1));
            assertNotNull(actual);
        }

    }

}