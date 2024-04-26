package curtinho.app.api.service;

import curtinho.app.api.helper.StringUtils;
import curtinho.app.api.model.Url;
import curtinho.app.api.repository.UrlRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    @Autowired
    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String shortenUrl(String longUrl, Integer days){

        StringUtils utils = new StringUtils();

        String generatedString = utils.generateRandomString(8);
        LocalDateTime today = LocalDateTime.now();
        var url = new Url();

        url.setOriginalUrl(longUrl);
        url.setShortenedUri(generatedString);
        if(days.compareTo(365) < 0) {
            url.setExpirationDate(today.plusDays(days));
        } else {
            url.setExpirationDate(today.plusDays(365));
        }

        var entity = urlRepository.save(url);

        return entity.getShortenedUri();
    }

    public Url getOriginalUrl(String shortUri){
        var entity = urlRepository.findByShortenedUri(shortUri)
                .orElseThrow(() -> new EntityNotFoundException("There is no value with " + shortUri));
        return entity;
    }
}
