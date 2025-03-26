package curtinho.app.api.model;

import java.util.Set;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tbl_apiKeys")
@Data
public class ApiKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "environment")
    private String environment;

    @Column(name = "hash_key")
    private String hashKey;

    @OneToMany(mappedBy = "apiKey")
    private Set<Url> urls;
}
