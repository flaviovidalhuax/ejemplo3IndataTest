package main.ejempIndata3.serrvice;

import main.ejempIndata3.dto.UserDTO;
import main.ejempIndata3.entity.UsuarioEntity;
import main.ejempIndata3.repository.UserRepository;
import main.ejempIndata3.serrvice.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceTest {
    @MockBean
    UserRepository userRepository;
    @Autowired
    UserService userService;
    UsuarioEntity usuario1;
    UserDTO userDTO;

    @BeforeEach
    void setUp() {
        usuario1=new UsuarioEntity(1L, "juan", "perez", "1234");
        userDTO=new UserDTO("pepe", "lopez", "1234",34);
    }

    @Test
    @DisplayName("save test service")
    void saveServiceTest() {//DDT
        //GIVE
        when(userRepository.save(usuario1)).thenReturn(usuario1);
        //WHERE
        UsuarioEntity data=userService.saveUser(userDTO);
        //THEN
        assertNotNull(data);
        assertEquals("pepe", data.getName());
    }

    @Test
    void saveNServiceTest() {
        when(userRepository.save(usuario1)).thenReturn(usuario1);

        UsuarioEntity data=userService.saveUserN(usuario1);
        assertNotNull(data);
        assertEquals("juan" ,data.getName());
        assertEquals("perez" ,data.getLastname());
        assertEquals("1234" ,data.getPassword());
        verify(userRepository,times(1)).save(usuario1);
    }

    @Test
    void findByIdServiceTest() {
        when(userRepository.findById(usuario1.getId())).thenReturn(Optional.ofNullable(usuario1));

         Optional<UsuarioEntity> data=userService.findById(usuario1.getId());
         assertNotNull(data);
         assertEquals("juan", data.orElseThrow().getName());
         assertEquals("perez", data.orElseThrow().getLastname());
         assertEquals("1234", data.orElseThrow().getPassword());
         verify(userRepository).findById(usuario1.getId());
    }
    @Test
    void deletedTest(){
        doNothing().when(userRepository).deleteById(usuario1.getId());
        UsuarioEntity data=userService.deleted(usuario1.getId());
        verify(userRepository,times(1)).deleteById(usuario1.getId());
        assertNull(data);
    }

    @Test
    void findAllTest() {
        List<UsuarioEntity> data=Arrays.asList(usuario1, new UsuarioEntity(3L, "maria", "carmen", "12345"));
        when(userRepository.findAll()).thenReturn(data);
        List<UsuarioEntity> dataEsp=userService.findAllUser();
        assertNotNull(dataEsp);
        assertEquals( 2,dataEsp.size());
        verify(userRepository, times(1)).findAll();
    }

}