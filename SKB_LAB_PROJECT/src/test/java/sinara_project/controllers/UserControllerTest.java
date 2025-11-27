package sinara_project.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import sinara_project.models.user.UserDto;
import sinara_project.models.user.UserRegisterDto;
import sinara_project.service.user.UserService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void register() throws Exception {
        UserDto userDto = new UserDto();
        UserRegisterDto  userRegisterDto = new  UserRegisterDto();
        userRegisterDto.setName("anatoly");
        userDto.setName("anatoly");

        Mockito.when(userService.register(any())).thenReturn(userDto);
        mockMvc.perform(MockMvcRequestBuilders.get("/register")).andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(userDto)));
    }

    @Test
    void login() throws Exception {

        Mockito.when(userService.verify(any())).thenReturn("token");
        mockMvc.perform(MockMvcRequestBuilders.get("/register")).andExpect(status().isOk());
    }

    @Test
    void getProfile() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setId(1);

        Mockito.when(userService.getProfile(any())).thenReturn(userDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/profile")).andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(userDto)));
    }
}