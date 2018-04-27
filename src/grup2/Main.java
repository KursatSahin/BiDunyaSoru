package grup2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("AnaMenu.fxml"));
        primaryStage.setTitle("Bi Dünya Soru");
        primaryStage.setScene(new Scene(root, 600, 480));
        primaryStage.show();

        OYS =  new OyunYonetimSistemi();
        girisPencere = new Stage();
        anaPencere = new Stage();
        kullaniciKaydiPencere = new Stage();
        ulkeSecimiPencere = new Stage();
        yarismaEkraniPencere = new Stage();

        OYS.oyunuYukle();
    }

    public static OyunYonetimSistemi OYS;
    public static Stage girisPencere;
    public static Stage anaPencere;
    public static Stage kullaniciKaydiPencere;
    public static Stage ulkeSecimiPencere;
    public static Stage yarismaEkraniPencere;

    public static void main(String[] args) {

        launch(args);

    }
}
