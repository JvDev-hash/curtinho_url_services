package curtinho.app.api.repository;

import curtinho.app.api.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository  extends JpaRepository<Url, Long> {

    Optional<Url> findByShortenedUri(String shortenUri);
}
