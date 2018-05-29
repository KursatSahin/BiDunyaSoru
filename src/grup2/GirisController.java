package grup2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;

import static grup2.Main.*;
import static grup2.Main.yarismaEkraniPencere;


/**
 * Created by Yavuz on 24.04.2018.
 */
public class GirisController {
    @FXML
    private TextField txtGirisKullaniciAdi;
    @FXML
    private TextField txtGirisSifre;
    @FXML
    private Label lblError;

    @FXML
    public void btnKullaniciGirisOnClick(){

        String kAdi =  txtGirisKullaniciAdi.getText();
        String kSifre = txtGirisSifre.getText();


        if(OYS.girisYap(kAdi,kSifre) == null){
            lblError.setText("Kullanıcı adı veya parolanız hatalı");
        }else{
            OYS.oyuncu = OYS.girisYap(kAdi,kSifre);
            girisPencere.close();

            if (OYS.oyuncu.getGecilenUlkeler().isEmpty()){
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("ulkeSecimi.fxml"));
                    ulkeSecimiPencere.setScene(new Scene(root));
                    ulkeSecimiPencere.show();
                }catch(Exception e){
                    System.out.println(e.toString());
                }
            } else {
                try {
                    OYS.ulkeSec(OYS.oyuncu.gecilenUlkeler.get(OYS.oyuncu.gecilenUlkeler.size()-1));
                    Parent root = FXMLLoader.load(getClass().getResource("yarismaEkrani.fxml"));
                    yarismaEkraniPencere.setScene(new Scene(root));
                    yarismaEkraniPencere.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }

    }

}
