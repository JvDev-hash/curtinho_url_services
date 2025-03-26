package curtinho.app.api.repository;

import curtinho.app.api.model.ApiKey;
import curtinho.app.api.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UrlRepository  extends JpaRepository<Url, Long> {

    Optional<Url> findByShortenedUri(String shortenUri);

    Optional<Url> findByOriginalUrl(String originalUrl);

    Optional<List<Url>> findAllByEnvironment(ApiKey apiKey);
}
