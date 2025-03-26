package curtinho.app.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name="tbl_urls")
@Data
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "shortened_uri")
    private String shortenedUri;

    @Column(name = "original_url", nullable = false)
    private String originalUrl;

    @Column(name = "expiration_date", nullable = false)
    private LocalDateTime expirationDate;

    @Column(name = "acess_count")
    private Long accessCount;

    @ManyToOne
    @JoinColumn(name="environment_id", nullable = false)
    private ApiKey environment;

}
