package main.ejempIndata3.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.ejempIndata3.dto.UserDTO;
import main.ejempIndata3.entity.UsuarioEntity;
import main.ejempIndata3.serrvice.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;  //when
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*; // get, post, put, patch

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//@RunWith(SpringRunner.class)
@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private UserService userService;
    UsuarioEntity usuarioEntity;
    UserDTO userDTO;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        usuarioEntity=new UsuarioEntity();
        usuarioEntity.setId(7L);
        usuarioEntity.setName("maria");
        usuarioEntity.setLastname("angeles");
        usuarioEntity.setPassword("1234");
    }

    @Test
    @DisplayName("post controller")
    void postDTOControllerTest() throws Exception {
        UserDTO dto = UserDTO.builder()
                .nombre("maria")
                .apellido("angeles")
                .pass("1234")
                .edad(23)
                .build();

        //when(userService.saveUser(userDTO)).thenReturn(usuarioEntity);
        mvc.perform(post("/v1/user/saveDTO")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nombre").value("maria"))
                .andExpect(jsonPath("$.apellido").value("angeles"))
                .andExpect(jsonPath("$.pass").value("1234"))
                .andExpect(jsonPath("$.edad").value(23));
    }
}