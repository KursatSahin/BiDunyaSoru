package grup2;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import static grup2.Main.*;

public class Controller {

    @FXML
    public Button btnGiris;

    public void btnCikisOnclick(){
        if (isAnyStageShowing()) {
            return;
        }

        OYS.oyunuKaydet();
        Platform.exit();
    }

    public void btnKayitOlOnclick(){
        if (isAnyStageShowing()) {
            return;
        }

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
        if (isAnyStageShowing()) {
            return;
        }

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

    public void btnPuanTablosuOnClick(){
        if (isAnyStageShowing()) {
            return;
        }

        try{
            Parent root = FXMLLoader.load(getClass().getResource("PuanTablosuEkrani.fxml"));
            puanTablosuPencere.setScene(new Scene(root));
            puanTablosuPencere.setResizable(false);
            puanTablosuPencere.show();

        }
        catch(Exception e){
            System.out.println(e.toString());
        }

    }

    public boolean isAnyStageShowing(){
        if (girisPencere.isShowing() || kullaniciKaydiPencere.isShowing() || ulkeSecimiPencere.isShowing() ||
                yarismaEkraniPencere.isShowing() || puanTablosuPencere.isShowing()){
            return true;
        }
        return false;
    }

}


