package de.ait.mp.service;

import de.ait.mp.dto.event.EventDto;
import de.ait.mp.dto.event.NewEventDto;
import de.ait.mp.dto.user_account.EventMemberDto;
import de.ait.mp.dto.user_account.NewUserDto;
import de.ait.mp.dto.user_account.UserDto;
import de.ait.mp.models.User;

import java.util.List;

public interface EventsService {
    EventDto addEvent(NewEventDto newEvent);

    List<UserDto> addUserToEvent(Long eventId, EventMemberDto newUser);


    List<UserDto> getAllMembersOfEvent(Long eventId);
}
