package grup2;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import static grup2.Main.OYS;
import static grup2.Main.kullaniciKaydiPencere;


/**
 * Created by Yavuz on 24.04.2018.
 */
public class KullaniciKaydiController {
    @FXML
    private TextField txtKullaniciAdi;
    @FXML
    private TextField txtSifre;


    @FXML
    public void btnKullaniciKayitOnClick(){



        String kAdi =  txtKullaniciAdi.getText();
        String kSifre = txtSifre.getText();

        OYS.kayitOl(kAdi,kSifre);

        kullaniciKaydiPencere.close();

    }





}
