package curtinho.app.api.service;

import curtinho.app.api.helper.StringUtils;
import curtinho.app.api.model.Qrcode;
import curtinho.app.api.repository.QrcodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;

@Service
public class QrcodeService {

    private final QrcodeRepository qrcodeRepository;

    @Autowired
    public QrcodeService(QrcodeRepository qrcodeRepository){
        this.qrcodeRepository = qrcodeRepository;
    }

    public String generateQrcode(String text) throws Exception {
        StringUtils utils = new StringUtils();
        Qrcode qrcode = new Qrcode();
        LocalDateTime today = LocalDateTime.now();
        try {
            qrcode.setQrcode("data:image/png;base64,"+ new String((utils.generateQRCodeImage(text))));
            qrcode.setCreatedAt(today);
            var entity = qrcodeRepository.save(qrcode);

            return entity.getQrcode();
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
