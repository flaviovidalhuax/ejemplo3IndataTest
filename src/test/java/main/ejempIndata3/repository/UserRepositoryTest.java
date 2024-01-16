package main.ejempIndata3.repository;

import main.ejempIndata3.dto.UserDTO;
import main.ejempIndata3.entity.UsuarioEntity;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("find all reposirory")
    void findByAllUSerTest(){
        //give
        UsuarioEntity user1= UsuarioEntity.builder()
                        .id(1L)
                        .name("juan")
                        .lastname("perez")
                        .password("1234")
                        .build();
        //where
        userRepository.save(user1);
        List<UsuarioEntity> data = (List<UsuarioEntity>) userRepository.findAll();
        //when
        assertEquals(1, data.size());
        assertNotNull(data);
    }
    @Test
    @DisplayName("save repository")
    void saveUserTest(){
        //give
        UsuarioEntity user1= UsuarioEntity.builder()
                .id(1L)
                .name("juan")
                .lastname("perez")
                .password("1234")
                .build();
        //where
        userRepository.save(user1);
        UsuarioEntity userAux =userRepository.findById(1L).orElse(user1);
        assertNotNull(userAux);
        assertEquals("juan", userAux.getName());
        assertEquals("1234", userAux.getPassword());
    }
    @Test
    @DisplayName("delete repository")
    void deleteByIdTest(){
        //give
        UsuarioEntity user1=UsuarioEntity.builder()
                .id(1L)
                .name("juan")
                .lastname("perez")
                .password("1234")
                .build();
        UsuarioEntity user2 =UsuarioEntity.builder()
                .id(2L)
                .name("pepito")
                .lastname("perez")
                .password("1234")
                .build();
        //where
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.deleteById(user1.getId());
        List<UsuarioEntity> data = (List<UsuarioEntity>) userRepository.findAll();
        //then
        assertNotNull(data);
        assertEquals(1, data.size());
    }
    @Test
    @DisplayName("findById Repository")
    void findByIdTest(){
        //give
        UsuarioEntity user22=UsuarioEntity.builder()
                .id(1L)
                .name("juan")
                .lastname("perez")
                .password("1234")
                .build();
        //where
        userRepository.save(user22);
        UsuarioEntity data= userRepository.findById(4L).orElse(user22);
        assertEquals(4L, data.getId());
        assertEquals("juan", data.getName());
        assertEquals("perez", data.getLastname());

    }

    @Test
    void AllcontructorUserTest() {
        UsuarioEntity usuarioEntity =new UsuarioEntity(1L, "juan", "perez", "1234");

        assertEquals("juan",usuarioEntity.getName());
        assertEquals("perez", usuarioEntity.getLastname());
        assertEquals("1234", usuarioEntity.getPassword());
    }

    @Test
    void userBuildUserTest() {
        UsuarioEntity usuario= UsuarioEntity.builder()
                .id(3L)
                .name("pepe")
                .lastname("bonilla")
                .password("1234")
                .build();
        assertEquals(3L, usuario.getId());
        assertEquals("pepe", usuario.getName());
        assertEquals("bonilla",usuario.getLastname());
        assertEquals("1234",usuario.getPassword());
    }

    void AllcontructorUserDTOTest() {
        UserDTO userDTO=new UserDTO( "juan", "perez","1234",50);

        assertEquals("juan",userDTO.getNombre());
        assertEquals("perez",userDTO.getApellido() );
        assertEquals("1234", userDTO.getPass());
        assertEquals(50, userDTO.getEdad());
    }

    @Test
    void userDTOTestBuilder() {
        UserDTO user=UserDTO.builder()
                .nombre("juan")
                .apellido("perez")
                .pass("1234")
                .edad(23)
                .build();
        assertEquals("juan", user.getNombre());
        assertEquals("perez", user.getApellido());
        assertEquals("1234", user.getPass());
        assertEquals(23, user.getEdad());
    }
}