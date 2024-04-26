package curtinho.app.api.service;

import curtinho.app.api.model.User;
import curtinho.app.api.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getByKey(String key){
        var user = userRepository.findByKey(key)
                .orElseThrow(() -> new EntityNotFoundException("There is no value with " + key));
        return user;
    }
}
