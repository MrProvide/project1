package javau9.ca.db.abc.finalprojectalternativehf.Controllers;

import jakarta.validation.Valid;
import javau9.ca.db.abc.finalprojectalternativehf.Models.SimpleUser;
import javau9.ca.db.abc.finalprojectalternativehf.Service.SimpleUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class SimpleUserController {

    private final SimpleUserService simpleUserService;

    public SimpleUserController(SimpleUserService simpleUserService) {
        this.simpleUserService = simpleUserService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/addUser")
    public ResponseEntity<SimpleUser> addUser(@Valid @RequestBody SimpleUser simpleUser) {
        SimpleUser newUser = simpleUserService.addUser(simpleUser);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SimpleUser>> getAllUsers() {
        List<SimpleUser> users = simpleUserService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SimpleUser> getUserById(@PathVariable Long id) {
        SimpleUser user = simpleUserService.getUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SimpleUser> updateUser(@PathVariable Long id, @Valid @RequestBody SimpleUser updatedUser) {
        SimpleUser user = simpleUserService.updateUser(id, updatedUser);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean deleted = simpleUserService.deleteUser(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
