package curtinho.app.api.controller;

import curtinho.app.api.DTO.UrlDTO;
import curtinho.app.api.DTO.UrlResponseDTO;
import curtinho.app.api.helper.ReturnPages;
import curtinho.app.api.service.QrcodeService;
import curtinho.app.api.service.UrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/")
public class UrlController {

    private final UrlService urlService;
    private final QrcodeService qrcodeService;

    Logger logger = LoggerFactory.getLogger(UrlController.class);

    public UrlController(UrlService urlService, QrcodeService qrcodeService) {
        this.urlService = urlService;
        this.qrcodeService = qrcodeService;
    }

    @PostMapping("s")
    public ResponseEntity<?> shortenUrl(@RequestBody UrlDTO urlDTO){
        var response = new UrlResponseDTO();
        try {
            response.setShortenUri(urlService.shortenUrl(urlDTO.getLongUrl(), urlDTO.getDays()));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("qr")
    public ResponseEntity<?> createQr(@RequestBody UrlDTO urlDTO){
        var response = new UrlResponseDTO();
        try {
            response.setShortenUri(qrcodeService.generateQrcode(urlDTO.getLongUrl()));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("p/{shortUri}")
    public Object popUrl(@PathVariable String shortUri){
        var entity = urlService.getOriginalUrl(shortUri);
        LocalDateTime today = LocalDateTime.now();

        if(today.isBefore(entity.getExpirationDate())) {
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl(entity.getOriginalUrl());

            return redirectView;

        }
        return new ResponseEntity<>(new ReturnPages().notFoundPage(), HttpStatus.NOT_FOUND);
    }
}
