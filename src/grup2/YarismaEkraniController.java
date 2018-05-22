package grup2;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

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
    public Label LabelKullaniciAdi1;

    @FXML
    public Label LabelKullaniciBakiyesi;

    @FXML
    public Label labelToplamPuan;
    // End of FXML Kullanici Paneli Objects

    // FXML Oyun Paneli Objects
    @FXML
    public Label LabelUlkeAdi;
    // End of FXML Oyun Paneli Objects

    // FXML Soru Paneli Objects
    @FXML
    public AnchorPane SplitPaneSoru;

    @FXML
    public AnchorPane SplitPaneKullanici;

    @FXML
    public Label LabelKullaniciAdi;

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
        LabelKullaniciAdi.setText(OYS.oyuncu.getKullaniciAdi());

        SoruPaneliGuncelle();

        Iterator itr = OYS.ulkelerGrafı.edgeIterator(OYS.ulkelerListesi.indexOf(OYS.simdikiUlke));
        while (itr.hasNext()){
            System.out.println(itr.next());
        }
    }


    public boolean checkIfTrue(int prediction, int actual){
        if (prediction == actual){
            return true;
        }
        return false;
    }

    public Soru getNextSoru(){
        return OYS.sorular.peek();
    }

    public void KullaniciPaneliGuncelle(){
        LabelKullaniciAdi1.setText(OYS.oyuncu.getKullaniciAdi());
        LabelKullaniciBakiyesi.setText(String.format("%d",OYS.oyuncu.getBakiye()));
        labelToplamPuan.setText(String.format("%d",OYS.oyuncu.getToplamPuan()));
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
        buttonReset();
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
        //if (!OYS.sorular.isEmpty()){
        if (simdikiSoru != null){
            OYS.sorular.poll();
            int prediction = Integer.parseInt(((Button)actionEvent.getSource()).getId().replace("btn",""));

            ((Button)actionEvent.getSource()).setStyle("-fx-background-color: orange");
            disableOtherButtons(prediction);
            new Timer().schedule(
                    new TimerTask() {
                        @Override
                        public void run() {
                            Platform.runLater(() -> {
                                if(checkIfTrue(prediction,simdikiSoru.dogruCevap)){
                                    // TODO tuş renklendirme yapılıp 1-2 saniye beklenecek ve
                                    // TODO bu bekleme sırasında tüm tuşlar disable edilecek
                                    ((Button)actionEvent.getSource()).setStyle("-fx-background-color: green");
                                    anlikPuan += simdikiSoru.puanHesapla();
                                }else {
                                    ((Button)actionEvent.getSource()).setStyle("-fx-background-color: red");

                                }
                            });
                        }
                    },2000
            );

            new Timer().schedule(
                    new TimerTask() {
                        @Override
                        public void run() {
                            Platform.runLater(() -> {

                                if (OYS.sorular.peek() != null){
                                    simdikiSoru = getNextSoru();
                                    SoruPaneliGuncelle();
                                } else {
                                    OYS.oyuncu.setBakiye(anlikPuan);
                                    OYS.oyuncu.setToplamPuan(anlikPuan);
                                    OYS.writeUsersIntoJson();
                                    SplitPaneSoru.setVisible(false);
                                    SplitPaneKullanici.setVisible(true);
                                    KullaniciPaneliGuncelle();
                                }

                            });
                        }
                    },4000
            );

        } else {

            //yarismaEkraniPencere.close();
        }
    }

    public void disableOtherButtons(int selection){

        switch (selection) {

            case 1:
                ButtonSecenekB.setDisable(true);
                ButtonSecenekC.setDisable(true);
                ButtonSecenekD.setDisable(true);
                break;
            case 2:
                ButtonSecenekA.setDisable(true);
                ButtonSecenekC.setDisable(true);
                ButtonSecenekD.setDisable(true);
                break;
            case 3:
                ButtonSecenekA.setDisable(true);
                ButtonSecenekB.setDisable(true);
                ButtonSecenekD.setDisable(true);
                break;
            case 4:
                ButtonSecenekA.setDisable(true);
                ButtonSecenekB.setDisable(true);
                ButtonSecenekC.setDisable(true);
                break;
        }
    }

    public  void buttonReset(){
        ButtonSecenekA.setDisable(false);
        ButtonSecenekB.setDisable(false);
        ButtonSecenekC.setDisable(false);
        ButtonSecenekD.setDisable(false);

        ButtonSecenekA.setStyle("-fx-background-color:#3b3b50");
        ButtonSecenekB.setStyle("-fx-background-color:#3b3b50");
        ButtonSecenekC.setStyle("-fx-background-color:#3b3b50");
        ButtonSecenekD.setStyle("-fx-background-color:#3b3b50");

    }

}