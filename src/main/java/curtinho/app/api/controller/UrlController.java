package curtinho.app.api.controller;

import curtinho.app.api.DTO.UrlDTO;
import curtinho.app.api.DTO.UrlResponseDTO;
import curtinho.app.api.service.QrcodeService;
import curtinho.app.api.service.UrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

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
            response.setShortenUri(urlService.shortenUrl(urlDTO.getLongUrl()));
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
    public RedirectView popUrl(@PathVariable String shortUri){
        var longUrl = urlService.getOriginalUrl(shortUri);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(longUrl);

        return redirectView;
    }
}
