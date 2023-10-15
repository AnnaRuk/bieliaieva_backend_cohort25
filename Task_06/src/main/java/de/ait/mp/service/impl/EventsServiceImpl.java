package de.ait.mp.service.impl;

import de.ait.mp.dto.event.EventDto;
import de.ait.mp.dto.event.NewEventDto;
import de.ait.mp.dto.user_account.EventMemberDto;
import de.ait.mp.dto.user_account.NewUserDto;
import de.ait.mp.dto.user_account.UserDto;
import de.ait.mp.exceptions.RestException;
import de.ait.mp.models.Event;
import de.ait.mp.models.User;
import de.ait.mp.repositories.EventsRepository;
import de.ait.mp.repositories.UsersRepository;
import de.ait.mp.service.EventsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EventsServiceImpl implements EventsService {

    private final EventsRepository eventsRepository;
    private final UsersRepository usersRepository;

    @Override
    public EventDto addEvent(NewEventDto newEvent) {

            Event event = Event.builder()
                    .title(newEvent.getTitle())
                    .date(LocalDate.parse(newEvent.getDate(), DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                    .venue(null)
                    .build();

            eventsRepository.save(event);
            return EventDto.from(event);

    }

    @Override
    public List<UserDto> addUserToEvent(Long eventId, EventMemberDto newUser) {
        Event event = eventsRepository.findById(eventId).orElseThrow(()->new RestException(HttpStatus.NOT_FOUND,
                "Event with id: " + eventId + " not found"));

        User member  = usersRepository.findById(newUser.getUserId()).orElseThrow(()->new RestException(HttpStatus.NOT_FOUND,
                "User with id: " + newUser.getUserId() + " not found"));

        if (!member.getUserEvents().add(event)){
            throw new RestException(HttpStatus.CONFLICT,"Member with id: " + newUser.getUserId() +
                    " already has event with id: " + eventId);
        }
            usersRepository.save(member);

        Set<User> membersOfEvent = usersRepository.findAllByUserEventsContainsOrderById(event);
        return UserDto.from(membersOfEvent);

        }

    @Override
    public List<UserDto> getAllMembersOfEvent(Long eventId) {
        Event event = eventsRepository.findById(eventId).orElseThrow(()->new RestException(HttpStatus.NOT_FOUND,
                "Event with id: " + eventId + " not found"));
        Set<User> membersOfEvent = usersRepository.findAllByUserEventsContainsOrderById(event);
        if(!membersOfEvent.isEmpty()){
            return UserDto.from(membersOfEvent);
        }
        throw new RestException(HttpStatus.NOT_FOUND,"This event does not have members");
    }

}

