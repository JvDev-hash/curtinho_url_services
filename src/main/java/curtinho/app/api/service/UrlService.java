package curtinho.app.api.service;

import curtinho.app.api.repository.UrlRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional.*;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    @Autowired
    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String shortenUrl(String longUrl){
        return null;
    }

    public String getOriginalUrl(String shortUri){
        var entity = urlRepository.findByShortenedUri(shortUri)
                .orElseThrow(() -> new EntityNotFoundException("There is no value with " + shortUri));
        return entity.getOriginalUrl();
    }
}
