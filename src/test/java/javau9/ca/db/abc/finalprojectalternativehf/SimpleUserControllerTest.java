package javau9.ca.db.abc.finalprojectalternativehf;

import javau9.ca.db.abc.finalprojectalternativehf.Controllers.SimpleUserController;
import javau9.ca.db.abc.finalprojectalternativehf.Models.SimpleUser;
import javau9.ca.db.abc.finalprojectalternativehf.Service.SimpleUserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SimpleUserControllerTest {


    private static final Logger logger = LoggerFactory.getLogger(SimpleUserControllerTest.class);

    @InjectMocks
    private SimpleUserController simpleUserController;

    @Mock
    private SimpleUserService simpleUserService;

    @Before
    public void setUp() {
        logger.info("Setting up SimpleUserControllerTest...");
    }

    @Test
    public void testAddUser() {
        SimpleUser user = new SimpleUser();
        when(simpleUserService.addUser(any(SimpleUser.class))).thenReturn(user);

        ResponseEntity<SimpleUser> response = simpleUserController.addUser(new SimpleUser());

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        logger.info("Test: testAddUser completed successfully.");
    }

    @Test
    public void testGetUserById() {
        Long id = 1L;
        SimpleUser user = new SimpleUser();
        when(simpleUserService.getUserById(id)).thenReturn(user);

        ResponseEntity<SimpleUser> response = simpleUserController.getUserById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
        logger.info("Test: testGetUserById completed successfully");
    }

    @Test
    public void testUpdateUser() {
        Long id = 1L;
        SimpleUser updatedUser = new SimpleUser();
        when(simpleUserService.updateUser(eq(id), any(SimpleUser.class))).thenReturn(updatedUser);

        ResponseEntity<SimpleUser> response = simpleUserController.updateUser(id, updatedUser);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedUser, response.getBody());

        logger.info("Test: testUpdateUser completed successfully");

    }

    @Test
    public void testDeleteUser() {
        Long id = 1L;
        when(simpleUserService.deleteUser(id)).thenReturn(true);

        ResponseEntity<Void> response = simpleUserController.deleteUser(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        logger.info("Test: TestDeleteUser completed successfully");
    }

}
