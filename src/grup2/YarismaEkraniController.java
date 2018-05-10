package grup2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

import static grup2.Main.OYS;
import static java.lang.Thread.sleep;

/**
 * Created by Yavuz on 26.04.2018.
 */
public class YarismaEkraniController implements Initializable {
    Integer soruSayaci = 1;
    Soru simdikiSoru ;
    Integer anlikPuan = 0;


    // FXML Kullanici Paneli Objects
    @FXML
    public Label LabelKullaniciAdi;

    @FXML
    public Label LabelKullaniciBakiyesi;
    // End of FXML Kullanici Paneli Objects

    // FXML Oyun Paneli Objects
    @FXML
    public Label LabelUlkeAdi;
    // End of FXML Oyun Paneli Objects

    // FXML Soru Paneli Objects
    @FXML
    public SplitPane SplitPaneSoru;

    @FXML
    public SplitPane SplitPaneKullanici;

    @FXML
    public Label LabelZaman;

    @FXML
    public Label LabelPuan;

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
    // End of FXML Soru Paneli Objects



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
        simdikiSoru = getNextSoru();
        SplitPaneSoru.setVisible(true);
        SoruPaneliGuncelle();
    }


    public boolean checkIfTrue(int prediction, int actual){
        if (prediction == actual){
            return true;
        }
        return false;
    }

    public Soru getNextSoru(){
        return OYS.sorular.poll();
    }

    public void KullaniciPaneliGuncelle(){
        LabelKullaniciAdi.setText(OYS.oyuncu.getKullaniciAdi());
        LabelKullaniciBakiyesi.setText(String.format("%d ____ %d",OYS.oyuncu.getBakiye(),OYS.oyuncu.getToplamPuan()));
    }

    public void SoruPaneliGuncelle(){
        // TODO bekleme bitip panel güncellendikten sonra tuşların rengi ve aktiflik durumu düzeltilecek
        puanGuncelle();
        zamanGuncelle();
        soruGuncelle(simdikiSoru);
    }

    public void puanGuncelle(){
        LabelPuan.setText(anlikPuan.toString());
    }

    public void zamanGuncelle(){
        LabelZaman.setText(String.format("00:0%d",soruSayaci));
    }

    public void soruGuncelle(Soru s){
        LabelSoruSayisi.setText(soruSayaci + "/5");
        LabelSoru.setText(s.soruMetni);
        if( LabelSoru.getText().length()>60){
            LabelSoru.setFont(Font.font(18));
        }else {
            LabelSoru.setFont(Font.font(23));
        }

        ButtonSecenekA.setText(s.secenekA);
        ButtonSecenekB.setText(s.secenekB);
        ButtonSecenekC.setText(s.secenekC);
        ButtonSecenekD.setText(s.secenekD);
        soruSayaci++;
    }

    public void buttonAction(ActionEvent actionEvent){
        if (!OYS.sorular.isEmpty()){
            int prediction = Integer.parseInt(((Button)actionEvent.getSource()).getId().replace("btn",""));

//            switch (prediction) {
//                case 1: break;
//                case 2: break;
//                case 3: break;
//                case 4: break;
//            }

            if(checkIfTrue(prediction,simdikiSoru.dogruCevap)){
                // TODO tuş renklendirme yapılıp 1-2 saniye beklenecek ve
                // TODO bu bekleme sırasında tüm tuşlar disable edilecek
                //ButtonSecenekA.setStyle("-fx-background-color: green");

                anlikPuan += simdikiSoru.puanHesapla();
            }else {
                //ButtonSecenekA.setStyle("-fx-background-color: red");
            }

            simdikiSoru = getNextSoru();
            SoruPaneliGuncelle();

        } else {
            OYS.oyuncu.setBakiye(anlikPuan);
            OYS.oyuncu.setToplamPuan(anlikPuan);
            OYS.writeUsersIntoJson();
            SplitPaneSoru.setVisible(false);
            SplitPaneKullanici.setVisible(true);
            KullaniciPaneliGuncelle();

            //yarismaEkraniPencere.close();
        }
    }
}