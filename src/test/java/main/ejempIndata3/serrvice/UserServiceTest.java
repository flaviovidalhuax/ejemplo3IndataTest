package main.ejempIndata3.serrvice;

import main.ejempIndata3.dto.UserDTO;
import main.ejempIndata3.entity.UsuarioEntity;
import main.ejempIndata3.repository.UserRepository;
import main.ejempIndata3.serrvice.UserService;
import org.junit.jupiter.api.*;
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

//    @BeforeEach
//    void setUp() {
//        usuario1=new UsuarioEntity(1L, "juan", "perez", "1234");
//       // userDTO=new UserDTO("pepe", "lopez", "1234",34);
//        UsuarioEntity user1=UsuarioEntity.builder()
//                .id(1L)
//                .name("flavio")
//                .lastname("hernandez")
//                .password("1234")
//                .build();
//    }
//
//    @Test
//    @Disabled
//    @DisplayName("save test service")
//    void saveServiceTest() {//DDT
//        UsuarioEntity user1=UsuarioEntity.builder()
//                .id(1L)
//                .name("flavio")
//                .lastname("hernandez")
//                .password("1234")
//                .build();
//        UserDTO userDTO1=UserDTO.builder()
//                .nombre("flavio")
//                        .apellido("hernandez")
//                                .edad(32)
//                                        .pass("1234")
//                                                .build();
//        //GIVE
//        when(user1).thenReturn(user1);
//        //WHERE
//       UsuarioEntity data=userService.saveUser(userDTO1);
//        System.out.println("AQUI ESTA EL ERROR DE TESTING----------------");
//        System.out.println("data = " + data);
//        //THEN
//        assertNotNull(data);
//       // assertEquals("flavio", data.getName());
//    }

//    @Test
//    void saveNServiceTest() {
//        when(userRepository.save(usuario1)).thenReturn(usuario1);
//
//        UsuarioEntity data=userService.saveUserN(usuario1);
//        assertNotNull(data);
//        assertEquals("juan" ,data.getName());
//        assertEquals("perez" ,data.getLastname());
//        assertEquals("1234" ,data.getPassword());
//        verify(userRepository,times(1)).save(usuario1);
//    }
//
//    @Test
//    void findByIdServiceTest() {
//        when(userRepository.findById(usuario1.getId())).thenReturn(Optional.ofNullable(usuario1));
//
//         Optional<UsuarioEntity> data=userService.findById(usuario1.getId());
//         assertNotNull(data);
//         assertEquals("juan", data.orElseThrow().getName());
//         assertEquals("perez", data.orElseThrow().getLastname());
//         assertEquals("1234", data.orElseThrow().getPassword());
//         verify(userRepository).findById(usuario1.getId());
//    }
//    @Test
//    void deletedTest(){
//        doNothing().when(userRepository).deleteById(usuario1.getId());
//        UsuarioEntity data=userService.deleted(usuario1.getId());
//        verify(userRepository,times(1)).deleteById(usuario1.getId());
//        assertNull(data);
//    }
//
//    @Test
//    void findAllTest() {
//        List<UsuarioEntity> data=Arrays.asList(usuario1, new UsuarioEntity(3L, "maria", "carmen", "12345"));
//        when(userRepository.findAll()).thenReturn(data);
//        List<UsuarioEntity> dataEsp=userService.findAllUser();
//        assertNotNull(dataEsp);
//        assertEquals( 2,dataEsp.size());
//        verify(userRepository, times(1)).findAll();
//    }

}