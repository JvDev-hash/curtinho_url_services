package curtinho.app.api.configuration;

import curtinho.app.api.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;

@Component
public class AuthorizationFilter implements Filter {

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

        // Skip authorization for H2 console and usrKey endpoint
        if (req.getRequestURI().startsWith("/h2-console") || req.getRequestURI().equals("/usrKey")) {
            filterChain.doFilter(req, res);
            return;
        }

        ServletContext servletContext = servletRequest.getServletContext();
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        try {
            userService = webApplicationContext.getBean(UserService.class);
        }catch (Exception e) {
            logger.error(e.getMessage());
        }

        var authHeader = req.getHeader("Authorization");
        if(req.getMethod().equals("POST")){
            if (authHeader != null) {
                try {
                    var user = userService.getByKey(authHeader);
                    filterChain.doFilter(req, res);
                } catch (EntityNotFoundException e){
                    res.setStatus(403);
                    servletResponse.getOutputStream().write("Token is not valid".getBytes());
                }
            } else {
                res.setStatus(400);
                servletResponse.getOutputStream().write("Token cannot be Null".getBytes());
            }
        } else {
            filterChain.doFilter(req, res);
        }
    }
}
