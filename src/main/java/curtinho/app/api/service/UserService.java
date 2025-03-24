package curtinho.app.api.service;

import curtinho.app.api.helper.StringUtils;
import curtinho.app.api.model.User;
import curtinho.app.api.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getByKey(String key){
        var user = userRepository.findByHashKey(key)
                .orElseThrow(() -> new EntityNotFoundException("There is no value with " + key));
        return user;
    }

    public String createUserKey(String username, String appName){
        StringUtils utils = new StringUtils();

        String generatedString = utils.generateRandomString(25);

        User newUser = new User();
        newUser.setAppName(appName);
        newUser.setUsername(username);
        newUser.setHashKey(BCrypt.hashpw(generatedString, BCrypt.gensalt(10)));

        var entity = userRepository.save(newUser);

        return entity.getHashKey();

    }
}
