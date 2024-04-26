package curtinho.app.api.repository;

import curtinho.app.api.model.Qrcode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QrcodeRepository extends JpaRepository<Qrcode, Long> {
}
