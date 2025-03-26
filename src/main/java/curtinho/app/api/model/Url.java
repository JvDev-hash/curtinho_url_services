package curtinho.app.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
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
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy' 'HH:mm:ss")
    private LocalDateTime expirationDate;

    @Column(name = "acess_count")
    private Long accessCount;

    @ManyToOne
    @JoinColumn(name="environment_id", nullable = false)
    private ApiKey environment;

}
