package curtinho.app.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_qrcodes")
@Data
public class Qrcode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "qrcode", columnDefinition = "TEXT")
    private String qrcode;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
