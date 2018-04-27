package grup2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

import static grup2.Main.OYS;
import static grup2.Main.yarismaEkraniPencere;

/**
 * Created by Yavuz on 26.04.2018.
 */
public class YarismaEkraniController implements Initializable {
    int soruSayaci = 1;

    @FXML
    public Label LabelUlkeAdi;

    @FXML
    public Label LabelSoruSayisi;

    @FXML
    public Label LabelSoru;

    @FXML
    public Button ButtonSecenekA;

    @FXML
    public Button ButtonSecenekB;

    @FXML
    public Button ButtonSecenekC;

    @FXML
    public Button ButtonSecenekD;





    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        OYS.soruPaketiOlustur(5);
        System.out.println("ss");
        soruSayaci = 1;
        LabelUlkeAdi.setText(OYS.simdikiUlke.getUlkeAdi());
        getNextSoru(OYS.sorular.poll());
    }

    public void getNextSoru(Soru s){
        LabelSoruSayisi.setText(soruSayaci + "/5");
        LabelSoru.setText(s.soruMetni);
        ButtonSecenekA.setText(s.secenekA);
        ButtonSecenekB.setText(s.secenekB);
        ButtonSecenekC.setText(s.secenekC);
        ButtonSecenekD.setText(s.secenekD);
        soruSayaci++;
    }

    public void buttonAction(ActionEvent event){
        if (!OYS.sorular.isEmpty()){
            getNextSoru(OYS.sorular.poll());
        } else {
            OYS.writeUsersIntoJson();
            yarismaEkraniPencere.close();
        }
    }
}
