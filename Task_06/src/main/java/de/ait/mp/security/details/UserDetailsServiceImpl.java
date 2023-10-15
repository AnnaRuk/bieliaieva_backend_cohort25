package de.ait.mp.security.details;

import de.ait.mp.models.User;
import de.ait.mp.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsersRepository usersRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with email <" + username + "> not found"));

        return new AuthenticatedUser(user);
    }
}
