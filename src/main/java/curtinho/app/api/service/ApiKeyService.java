package curtinho.app.api.service;

import curtinho.app.api.helper.StringUtils;
import curtinho.app.api.model.ApiKey;
import curtinho.app.api.repository.ApiKeyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class ApiKeyService {

    private final ApiKeyRepository apiKeyRepository;

    @Autowired
    public ApiKeyService(ApiKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }

    public ApiKey getByKey(String key){
        var apiKey = apiKeyRepository.findByHashKey(key)
                .orElseThrow(() -> new EntityNotFoundException("There is no value with " + key));
        return apiKey;
    }

    public String createApiKey(String username, String appName){
        StringUtils utils = new StringUtils();

        String generatedString = utils.generateRandomString(25);

        ApiKey newApiKey = new ApiKey();
        newApiKey.setAppName(appName);
        newApiKey.setUsername(username);
        newApiKey.setHashKey(BCrypt.hashpw(generatedString, BCrypt.gensalt(10)));

        var entity = apiKeyRepository.save(newApiKey);

        return entity.getHashKey();

    }
}
