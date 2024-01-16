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

    @Test
    @DisplayName("getbyAll controller")
    void getAllUSerTest() throws Exception {//TDD
        //give
        List data=Arrays.asList(usuarioEntity, new UsuarioEntity(2L,"pepito","hernandez","6789"));
        //when
        when(userService.findAllUser()).thenReturn(data);
        //where
        mvc.perform(get("/v1/user"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("maria"))
                .andExpect(jsonPath("$[0].lastname").value("angeles"))
                .andExpect(jsonPath("$[0].password").value("1234"))
                .andExpect(jsonPath("$[1].name").value("pepito"))
                .andExpect(jsonPath("$[1].lastname").value("hernandez"))
                .andExpect(jsonPath("$[1].password").value("6789"));
    }

    @Test
    void getByIdControllerTest() throws Exception {

        when(userService.findById(usuarioEntity.getId())).thenReturn(Optional.ofNullable(usuarioEntity));
        mvc.perform(get("/v1/user/7"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(7L))
                .andExpect(jsonPath("$.name").value("maria"))
                .andExpect(jsonPath("$.lastname").value("angeles"))
                .andExpect(jsonPath("$.password").value("1234"));
    }

    @Test
    void postUserTest() throws Exception {
        when(userService.saveUserN(usuarioEntity)).thenReturn(usuarioEntity);

        mvc.perform(post("/v1/user/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuarioEntity)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("maria"))
                .andExpect(jsonPath("$.lastname").value("angeles"))
                .andExpect(jsonPath("$.password").value("1234"));

    }

    @Test
    void deletedControllerTest() throws Exception {
        //doNothing().when(userService.deleted(usuarioEntity.getId()));
        mvc.perform(delete("/v1/user/elim/{id}",usuarioEntity.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
    @Test
    void deletedControllerTest2() throws Exception {
        //doNothing().when(userService.deleted(usuarioEntity.getId()));
        mvc.perform(delete("/v1/user/elim/7").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}