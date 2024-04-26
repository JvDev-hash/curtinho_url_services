package curtinho.app.api.configuration;

import curtinho.app.api.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthorizationFilter implements Filter {

    @Autowired
    private UserService userService;

    Logger logger = LoggerFactory.getLogger(AuthorizationFilter.class);

    @Override
    public void init(FilterConfig filterConfig){
        logger.info("Filtro Iniciado!");
    }
    
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        var authHeader = req.getHeader("Authorization");
        if(req.getMethod().equals("POST")){
            if (authHeader != null) {
                var user = userService.getByKey(authHeader);
                if(user == null) { res.setStatus(403); servletResponse.getOutputStream().write("Token is not valid".getBytes());}

                filterChain.doFilter(req, res);
            } else {
                res.setStatus(400);
                servletResponse.getOutputStream().write("Token cannot be Null".getBytes());
            }
        } else {
            filterChain.doFilter(req, res);
        }


    }
}
