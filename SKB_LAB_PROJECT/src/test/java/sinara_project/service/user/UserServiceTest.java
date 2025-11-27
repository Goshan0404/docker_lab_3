package sinara_project.service.user;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import sinara_project.models.user.UserApp;
import sinara_project.models.user.UserDto;
import sinara_project.models.user.UserRegisterDto;
import sinara_project.repositories.UserRepository;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private JWTService jwtService;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private ModelMapper modelMapper;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setup() {
        userService = new UserService(userRepository, jwtService, authenticationManager, modelMapper, 10);
    }

    @Test
    void testRegister() {
        UserRegisterDto userDto = new UserRegisterDto();
        userDto.setName("ivan");
        userDto.setPassword("123");

        UserApp mappedUser = new UserApp();
        mappedUser.setName("ivan");
        mappedUser.setPassword("123");

        UserDto resultDto = new UserDto();
        resultDto.setName("ivan");

        when(modelMapper.map(userDto, UserApp.class)).thenReturn(mappedUser);
        when(modelMapper.map(userDto, UserDto.class)).thenReturn(resultDto);

        UserDto result = userService.register(userDto);

        assertThat(result).isEqualTo(resultDto);
    }

    @Test
    void testVerify() {
        UserRegisterDto dto = new UserRegisterDto();
        dto.setName("ivan");
        dto.setPassword("1213");

        Authentication auth = mock(Authentication.class);
        when(auth.isAuthenticated()).thenReturn(true);
        when(authenticationManager.authenticate(any())).thenReturn(auth);
        when(jwtService.generateToken("ivan")).thenReturn("token");

        String result = userService.verify(dto);

        assertThat(result).isEqualTo("token");
    }

    @Test
    void testGetProfile() {
        UserApp user = new UserApp();
        user.setId(1);
        UserDto dto = new UserDto();
        dto.setId(1);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(modelMapper.map(user, UserDto.class)).thenReturn(dto);

        UserDto result = userService.getProfile(1);

        assertThat(result).isEqualTo(dto);
    }

}
