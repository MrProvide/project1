package javau9.ca.db.abc.finalprojectalternativehf.Service.Implementations;

import javau9.ca.db.abc.finalprojectalternativehf.Models.SimpleUser;
import javau9.ca.db.abc.finalprojectalternativehf.Repository.SimpleUserRepository;
import javau9.ca.db.abc.finalprojectalternativehf.Service.SimpleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SimpleUserServiceImpl implements SimpleUserService {

    private final SimpleUserRepository simpleUserRepository;


    @Autowired
    public SimpleUserServiceImpl(SimpleUserRepository simpleUserRepository) {
        this.simpleUserRepository = simpleUserRepository;

    }

    @Override
    public SimpleUser addUser(SimpleUser simpleUser) {

        return simpleUserRepository.save(simpleUser);
    }

    @Override
    public List<SimpleUser> getAllUsers() {
        return simpleUserRepository.findAll();
    }

    @Override
    public SimpleUser getUserById(Long id) {
        return simpleUserRepository.findById(id).orElse(null);
    }

    @Override
    public SimpleUser updateUser(Long id, SimpleUser updatedUser) {
        SimpleUser existingUser = simpleUserRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword(updatedUser.getPassword());
            return simpleUserRepository.save(existingUser);
        }

        return null;
    }

    @Override
    public boolean deleteUser(Long id) {
        SimpleUser existingUser = simpleUserRepository.findById(id).orElse(null);
        if (existingUser != null) {
            simpleUserRepository.delete(existingUser);
            return true;
        }
        return false;

    }

    @Override
    public void save(SimpleUser user) {

    }

    @Override
    public Optional<SimpleUser> findbyEmail(String email) {
        return Optional.empty();
    }


    @Override
    public Optional<SimpleUser> findByUsername(String username) {
        return Optional.empty();
    }


}

