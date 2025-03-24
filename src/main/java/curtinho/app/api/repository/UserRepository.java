package curtinho.app.api.repository;

import curtinho.app.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByHashKey(String hashKey);
}
