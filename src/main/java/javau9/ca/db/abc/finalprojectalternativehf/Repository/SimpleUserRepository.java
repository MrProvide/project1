package javau9.ca.db.abc.finalprojectalternativehf.Repository;

import javau9.ca.db.abc.finalprojectalternativehf.Models.SimpleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SimpleUserRepository extends JpaRepository<SimpleUser, Long> {

    Optional<SimpleUser> findByEmail(String email);
}
