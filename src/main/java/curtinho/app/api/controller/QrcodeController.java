package curtinho.app.api.controller;

import curtinho.app.api.DTO.UrlDTO;
import curtinho.app.api.DTO.UrlResponseDTO;
import curtinho.app.api.service.QrcodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/qr")
public class QrcodeController {

    private final QrcodeService qrcodeService;

    Logger logger = LoggerFactory.getLogger(UrlController.class);

    public QrcodeController(QrcodeService qrcodeService) {
        this.qrcodeService = qrcodeService;
    }

    @PostMapping("/gen")
    public ResponseEntity<?> createQr(@RequestBody UrlDTO urlDTO){
        var response = new UrlResponseDTO();
        try {
            response.setShortenUri(qrcodeService.generateQrcode(urlDTO.getLongUrl()));
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    
}
