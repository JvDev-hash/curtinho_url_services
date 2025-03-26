package curtinho.app.api.controller;

import curtinho.app.api.DTO.ApiKeyDTO;
import curtinho.app.api.DTO.ApiKeyResponseDTO;
import curtinho.app.api.service.ApiKeyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/apiKey")
public class ApiKeyController {

    private final ApiKeyService apiKeyService;

    Logger logger = LoggerFactory.getLogger(UrlController.class);

    public ApiKeyController(ApiKeyService apiKeyService) {
        this.apiKeyService = apiKeyService;
    }

    @PostMapping("/gen")
    public ResponseEntity<?> createUsrKey(@RequestBody ApiKeyDTO userDTO){
        var response = new ApiKeyResponseDTO();

        try {
            response.setHashKey(apiKeyService.createApiKey(userDTO.getUsername(), userDTO.getEnvironment()));
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
