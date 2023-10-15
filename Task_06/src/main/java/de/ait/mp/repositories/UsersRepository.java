package de.ait.mp.repositories;

import de.ait.mp.models.Event;
import de.ait.mp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UsersRepository extends JpaRepository<User,Long> {

    boolean existsByEmail(String email);
    Set<User> findAllByUserEventsContainsOrderById(Event event);

    Optional<User> findByEmail(String username);
}
