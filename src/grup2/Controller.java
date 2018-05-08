package grup2;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.StageStyle;

import static grup2.Main.OYS;
import static grup2.Main.girisPencere;
import static grup2.Main.kullaniciKaydiPencere;

public class Controller {


    public void btnCikisOnclick(){
        OYS.oyunuKaydet();
        System.exit(0);
    }

    public void btnKayitOlOnclick(){

        try{
            Parent root = FXMLLoader.load(getClass().getResource("KullaniciKaydi.fxml"));
            kullaniciKaydiPencere.setScene(new Scene(root));
            kullaniciKaydiPencere.setResizable(false);
            kullaniciKaydiPencere.show();

        }
        catch(Exception e){
            System.out.println(e.toString());
        }
    }

    public void btnGirisOnclick(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("GirisEkrani.fxml"));
            girisPencere.setScene(new Scene(root));
            girisPencere.setResizable(false);
            girisPencere.show();


        }
        catch(Exception e){
            System.out.println(e.toString());
        }

    }

}
