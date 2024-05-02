package javau9.ca.db.abc.finalprojectalternativehf;

import javau9.ca.db.abc.finalprojectalternativehf.Models.SimpleUser;
import javau9.ca.db.abc.finalprojectalternativehf.Repository.SimpleUserRepository;
import javau9.ca.db.abc.finalprojectalternativehf.Service.SimpleUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
public class SimpleUserServiceTest {

    @Autowired
    private SimpleUserService simpleUserService;

    @MockBean
    private SimpleUserRepository simpleUserRepository;

    @Test
    public void testAddUser() {
        SimpleUser newUser = new SimpleUser();
        newUser.setUsername("testuser");
        newUser.setEmail("test@example.com");
        newUser.setPassword("testpassword");

        when(simpleUserRepository.save(any(SimpleUser.class))).thenReturn(newUser);

        SimpleUser addedUser = simpleUserService.addUser(newUser);
        assertNotNull(addedUser);
        assertEquals("testuser", addedUser.getUsername());
        assertEquals("test@example.com", addedUser.getEmail());
        assertEquals("testpassword", addedUser.getPassword());
    }

    @Test
    public void testGetAllUsers() {
        SimpleUser user1 = new SimpleUser();
        user1.setId(1L);
        user1.setUsername("user1");
        user1.setEmail("user1@example.com");
        user1.setPassword("password1");

        SimpleUser user2 = new SimpleUser();
        user2.setId(2L);
        user2.setUsername("user2");
        user2.setEmail("user2@example.com");
        user2.setPassword("password2");

        List<SimpleUser> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        when(simpleUserRepository.findAll()).thenReturn(users);

        List<SimpleUser> foundUsers = simpleUserService.getAllUsers();
        assertEquals(2, foundUsers.size());
        assertTrue(foundUsers.contains(user1));
        assertTrue(foundUsers.contains(user2));
    }

    @Test
    public void testGetUserById() {
        SimpleUser user = new SimpleUser();
        user.setId(1L);
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        user.setPassword("testpassword");

        when(simpleUserRepository.findById(1L)).thenReturn(Optional.of(user));

        SimpleUser foundUser = simpleUserService.getUserById(1L);
        assertNotNull(foundUser);
        assertEquals("testuser", foundUser.getUsername());
        assertEquals("test@example.com", foundUser.getEmail());
        assertEquals("testpassword", foundUser.getPassword());
    }

    @Test
    public void testUpdateUser() {
        SimpleUser existingUser = new SimpleUser();
        existingUser.setId(1L);
        existingUser.setUsername("existinguser");
        existingUser.setEmail("existing@example.com");
        existingUser.setPassword("existingpassword");

        SimpleUser updatedUser = new SimpleUser();
        updatedUser.setUsername("updateduser");
        updatedUser.setEmail("updated@example.com");
        updatedUser.setPassword("updatedpassword");

        when(simpleUserRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(simpleUserRepository.save(any(SimpleUser.class))).thenReturn(updatedUser);

        SimpleUser result = simpleUserService.updateUser(1L, updatedUser);
        assertNotNull(result);
        assertEquals("updateduser", result.getUsername());
        assertEquals("updated@example.com", result.getEmail());
        assertEquals("updatedpassword", result.getPassword());
    }

    @Test
    public void testDeleteUser() {
        SimpleUser existingUser = new SimpleUser();
        existingUser.setId(1L);
        existingUser.setUsername("existinguser");
        existingUser.setEmail("existing@example.com");
        existingUser.setPassword("existingpassword");

        when(simpleUserRepository.findById(1L)).thenReturn(Optional.of(existingUser));

        boolean deleted = simpleUserService.deleteUser(1L);
        assertTrue(deleted);
        verify(simpleUserRepository, times(1)).delete(existingUser);
    }
}
