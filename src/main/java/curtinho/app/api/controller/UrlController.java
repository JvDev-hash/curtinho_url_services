package curtinho.app.api.controller;

import curtinho.app.api.DTO.UrlDTO;
import curtinho.app.api.service.UrlService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@RestController
@RequestMapping("/")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("s")
    public String shortenUrl(@RequestBody UrlDTO urlDTO){

        return null;
    }

    @GetMapping("p/{shortUri}")
    public RedirectView popUrl(@PathVariable String shortUri){
        var longUrl = urlService.getOriginalUrl(shortUri);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(longUrl);

        return redirectView;
    }
}
