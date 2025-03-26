package curtinho.app.api.controller;

import curtinho.app.api.DTO.UrlDTO;
import curtinho.app.api.DTO.UrlResponseDTO;
import curtinho.app.api.helper.ReturnPages;
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

    Logger logger = LoggerFactory.getLogger(UrlController.class);

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/gen")
    public ResponseEntity<?> shortenUrl(@RequestBody UrlDTO urlDTO){
        var response = new UrlResponseDTO();
        try {
            response.setShortenUri(urlService.shortenUrl(urlDTO.getLongUrl(), urlDTO.getDays()));
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
}
