package curtinho.app.api.configuration;

import curtinho.app.api.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var authHeader = request.getHeader("Authorization");

        if(authHeader != null){
            var user = this.userRepository.findByKey(authHeader)
                    .orElseThrow(() -> new EntityNotFoundException("There is no value with " + authHeader));
            filterChain.doFilter(request, response);
        } else {
            throw new IllegalArgumentException("Token cannot be Null");
        }


    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request){
        String path = request.getServletPath();
        return !path.startsWith("p/{shortUri}");
    }
}
