package curtinho.app.api.repository;

import curtinho.app.api.model.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {

    Optional<ApiKey> findByHashKey(String hashKey);
}
