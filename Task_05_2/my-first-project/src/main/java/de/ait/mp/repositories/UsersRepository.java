package de.ait.mp.repositories;

import de.ait.mp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User,Long> {

    boolean existsByEmail(String email);
}
