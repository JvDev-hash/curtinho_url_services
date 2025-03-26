package curtinho.app.api.service;

import curtinho.app.api.helper.StringUtils;
import curtinho.app.api.model.ApiKey;
import curtinho.app.api.model.Url;
import curtinho.app.api.repository.UrlRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    @Autowired
    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String shortenUrl(String longUrl, Integer days, ApiKey apiKey){

        StringUtils utils = new StringUtils();

        String generatedString = utils.generateRandomString(8);
        LocalDateTime today = LocalDateTime.now();
        var url = new Url();

        url.setOriginalUrl(longUrl);
        url.setShortenedUri(generatedString);
        url.setAccessCount(0L);
        url.setEnvironment(apiKey);
        if(days.compareTo(365) < 0) {
            url.setExpirationDate(today.plusDays(days));
        } else {
            url.setExpirationDate(today.plusDays(365));
        }

        var entity = urlRepository.save(url);

        return entity.getShortenedUri();
    }

    public List<Url> listByEnv(ApiKey apiKey){
        return urlRepository.findAllByEnvironment(apiKey)
                .orElseThrow(() -> new EntityNotFoundException("There is no entity with " + apiKey.getEnvironment()));
    }

    public Url getOriginalUrl(String shortUri){
        return urlRepository.findByShortenedUri(shortUri)
                .orElseThrow(() -> new EntityNotFoundException("There is no entity with " + shortUri));
    }

    public Url listByOriginalUrl(String url, ApiKey apiKey){
        return urlRepository.findByOriginalUrlAndEnvironment(url, apiKey)
                .orElseThrow(() -> new EntityNotFoundException("There is no entity with " + url));
    }

    public Url updateAccessCount(Url targetUrl, Long newValue){
        targetUrl.setAccessCount(newValue);

        return urlRepository.save(targetUrl);
    }
}
