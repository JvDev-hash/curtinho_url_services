package curtinho.app.api.model;

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

    @Column(name = "app_name")
    private String appName;

    @Column(name = "hash_key")
    private String hashKey;
}
