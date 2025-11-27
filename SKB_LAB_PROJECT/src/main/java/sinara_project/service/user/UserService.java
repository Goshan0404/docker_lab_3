package sinara_project.service.user;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sinara_project.models.user.UserApp;
import sinara_project.models.user.UserDto;
import sinara_project.models.user.UserRegisterDto;
import sinara_project.repositories.UserRepository;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;

    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       JWTService jwtService,
                       AuthenticationManager authenticationManager,
                       ModelMapper modelMapper,
                       @Value("${security.encoder.strength}") int encoderStrength) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.modelMapper = modelMapper;
        this.encoder = new BCryptPasswordEncoder(encoderStrength);
    }

    public UserDto register(UserRegisterDto user) {
        log.info("Registering user");
        UserApp userApp = modelMapper.map(user, UserApp.class);
        userApp.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(userApp);
        return modelMapper.map(user, UserDto.class);
    }

    public String verify(UserRegisterDto user) {
        log.info("Authentication user");
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));

        if (!authentication.isAuthenticated()) {
            log.info("Auth failed");
            return "fail";
        }
        return jwtService.generateToken(user.getName());
    }

    public UserDto getProfile(long id) {
        UserApp user = userRepository.findById(id).orElseThrow(() -> {
            log.info("User is not found");
            return new EntityNotFoundException("User is not found");
        });
        return modelMapper.map(user, UserDto.class);
    }

    public void deleteProflie(long id) {
        userRepository.deleteById(id);
    }
}
