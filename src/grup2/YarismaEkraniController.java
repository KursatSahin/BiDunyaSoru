package grup2;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.*;

import static grup2.Main.OYS;
import static java.lang.Thread.sleep;

/**
 * Created by Yavuz on 26.04.2018.
 */
public class YarismaEkraniController implements Initializable {
    // Global Constant Variables
    final int SORU_SAYISI = 5;

    // Data Fields for Yarisma Ekrani Controller
    Integer soruSayaci;
    Soru simdikiSoru;
    Integer anlikPuan;

    // Panels

    @FXML
    public AnchorPane ulkesecimiPanel;
    @FXML
    public AnchorPane secimPanel;



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

    // FXML Ulke Secimi Paneli Objects
    @FXML
    private ListView<String> ulkeListView;

    //ObservableList<String> list = FXCollections.observableArrayList(OYS.getUlkelerListesiAsString());
    public ObservableList<String> list;

    // End of FXML Ulke Secimi Paneli Objects


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
        oyunuBaslat();

        // TODO - alt taraftaki commetler taşınacak/silinecek
//        Iterator itr = OYS.ulkelerGrafı.edgeIterator(OYS.ulkelerListesi.indexOf(OYS.simdikiUlke));
//        while (itr.hasNext()){
//            System.out.println(itr.next());
//        }
    }

    /**
     * Bu metod oyunu baslatir
     * + Soru paketini hazırlar
     * + Oyun ekranındaki soru indeksi kullanıcı puanı gibi değerleri ilk haline döndürür.
     * + Ilk soruyu ekrana basar ve oyun baslatilir
     */
    public void oyunuBaslat(){
        OYS.soruPaketiOlustur(SORU_SAYISI);
        soruSayaci = 1;
        anlikPuan = 0;
        LabelUlkeAdi.setText(OYS.simdikiUlke.getUlkeAdi());

        simdikiSoru = getNextSoru();
        SplitPaneSoru.setVisible(true);
        LabelKullaniciAdi.setText(OYS.oyuncu.getKullaniciAdi());

        SoruPaneliGuncelle();
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
                                    secimPanel.setVisible(true);
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

    public void btnUlkeSec(){
        secimPanel.setVisible(false);
        ulkesecimiPanel.setVisible(true);
        TabloyuDoldur();
    }


    public ArrayList<String> generateKomsularTable(){
        ArrayList<String> tempTable = new ArrayList<>();
        Edge edge;

        for (int i = 0; i < OYS.simdikiUlke.getKomsular().size(); i++) {
            edge = OYS.ulkelerGrafı.getEdge(OYS.ulkelerListesi.indexOf(OYS.simdikiUlke),OYS.ulkelerListesi.indexOf(new Ulke(OYS.simdikiUlke.getKomsular().get(i))));

            tempTable.add(OYS.simdikiUlke.getKomsular().get(i) + "\t" + edge.getWeight());
        }
        return tempTable;
    }

    public void TabloyuDoldur(){
        list = FXCollections.observableArrayList(generateKomsularTable());
        ulkeListView.setItems(list);
        ulkeListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }


    public void btnUlkeSecimiYap(){

        String ulkeadi;
        ulkeadi = ulkeListView.getSelectionModel().getSelectedItem();

        String ulkead[] = ulkeadi.split("\t");


        OYS.ulkeSec(ulkead[0]);
        ulkesecimiPanel.setVisible(false);
        SplitPaneKullanici.setVisible(false);
        SplitPaneSoru.setVisible(true);
        oyunuBaslat();

    }




    public void btnYenidenOyna(){



    }




}