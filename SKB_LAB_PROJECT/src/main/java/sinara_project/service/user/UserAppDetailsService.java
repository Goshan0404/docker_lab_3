package sinara_project.service.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sinara_project.models.user.UserApp;
import sinara_project.models.user.UserAppDetails;
import sinara_project.repositories.UserRepository;

@Service
@Slf4j
public class UserAppDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserApp user = userRepository.findByName(username);

        if (user == null) {
            log.info("User not found");
            throw new UsernameNotFoundException("user not found");
        }
        return new UserAppDetails(user);
    }
}
