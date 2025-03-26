package curtinho.app.api.controller;

import curtinho.app.api.DTO.UrlDTO;
import curtinho.app.api.DTO.UriResponseDTO;
import curtinho.app.api.helper.ReturnPages;
import curtinho.app.api.service.ApiKeyService;
import curtinho.app.api.service.UrlService;
import jakarta.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/url")
public class UrlController {

    private final UrlService urlService;
    private final ApiKeyService apiKeyService;

    Logger logger = LoggerFactory.getLogger(UrlController.class);

    public UrlController(UrlService urlService, ApiKeyService apiKeyService) {
        this.urlService = urlService;
        this.apiKeyService = apiKeyService;
    }

    @PostMapping("/gen")
    public ResponseEntity<?> shortenUrl(@RequestBody UrlDTO urlDTO, @RequestHeader("Authorization") String apiKey){
        var response = new UriResponseDTO();
        try {
            var referenceKey = apiKeyService.getByKey(apiKey);
            response.setShortenUri(urlService.shortenUrl(urlDTO.getLongUrl(), urlDTO.getDays(), referenceKey));
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pop/{shortUri}")
    public Object popUrl(@PathVariable String shortUri){
        try{
            var entity = urlService.getOriginalUrl(shortUri);
            LocalDateTime today = LocalDateTime.now();

            if(today.isBefore(entity.getExpirationDate())) {
                RedirectView redirectView = new RedirectView();
                var updatedEntity = urlService.updateAccessCount(entity, entity.getAccessCount() + 1);
                redirectView.setUrl(updatedEntity.getOriginalUrl());
    
                return redirectView;
    
            }
            
        } catch (EntityNotFoundException e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(new ReturnPages().notFoundPage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ReturnPages().notFoundPage(), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/del")
    public ResponseEntity<?> deleteShortenUrl(@RequestBody UriResponseDTO uri){
        try{
            urlService.deleteUrl(uri.getShortenUri());

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> listUrl(@RequestHeader("Authorization") String apiKey){
        try {
            var referenceKey = apiKeyService.getByKey(apiKey);
            var entity = urlService.listByEnv(referenceKey);

            return new ResponseEntity<>(entity, HttpStatus.OK);
        } catch (EntityNotFoundException e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        
    }

    @GetMapping("/findUri")
    public ResponseEntity<?> findUriByUrl(@RequestBody String url, @RequestHeader("Authorization") String apiKey){
        var response = new UriResponseDTO();
        try {
            var referenceKey = apiKeyService.getByKey(apiKey);
            var entity = urlService.listByOriginalUrl(url, referenceKey);

            response.setShortenUri(entity.getShortenedUri());

            return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (EntityNotFoundException e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }
}
