package grup2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.util.Duration;

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

    @FXML
    public ImageView bayrakResim;

    // End of FXML Soru Paneli Objects

    // FXML Ulke Secimi Paneli Objects
    @FXML
    private ListView<String> ulkeListView;

    //ObservableList<String> list = FXCollections.observableArrayList(OYS.getUlkelerListesiAsString());
    public ObservableList<String> list;


    private static final Integer STARTTIME = 15;
    private Timeline timeline;
    private Integer timeSeconds = STARTTIME;

    // End of FXML Ulke Secimi Paneli Objects

    // FXML Secim Paneli Objects

    ArrayList<Button> gecilenUlkeButonlari = new ArrayList<>();
    ArrayList<ImageView> ulkeBayraklari = new ArrayList<>();

    // End of FXML Secim Paneli Objects

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
        generateGecilenUlkelerButtons();
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
    public void oyunuBaslat() {

        OYS.soruPaketiOlustur(SORU_SAYISI);
        soruSayaci = 1;
        anlikPuan = 0;
        LabelUlkeAdi.setText(OYS.simdikiUlke.getUlkeAdi());



        Image bayrak = new Image(OYS.simdikiUlke.getBayrak());

        bayrakResim.setImage(bayrak);

        simdikiSoru = getNextSoru();
        SplitPaneSoru.setVisible(true);
        LabelKullaniciAdi.setText(OYS.oyuncu.getKullaniciAdi());

        SoruPaneliGuncelle();
    }


    public boolean checkIfTrue(int prediction, int actual) {
        if (prediction == actual) {
            return true;
        }
        return false;
    }

    public Soru getNextSoru() {
        return OYS.sorular.peek();
    }

    public void KullaniciPaneliGuncelle() {
        LabelKullaniciAdi1.setText(OYS.oyuncu.getKullaniciAdi());
        LabelKullaniciBakiyesi.setText(String.format("%d", OYS.oyuncu.getBakiye()));
        labelToplamPuan.setText(String.format("%d", OYS.oyuncu.getToplamPuan()));
    }

    public void SoruPaneliGuncelle() {
        // TODO bekleme bitip panel güncellendikten sonra tuşların rengi ve aktiflik durumu düzeltilecek
        puanGuncelle();
        zamanGuncelle(true);
        soruGuncelle(simdikiSoru);
    }

    public void puanGuncelle() {
        LabelPuan.setText(anlikPuan.toString());
    }

    public void zamanGuncelle(boolean state) {
//        LabelZaman.setText(String.format("00:0%d",soruSayaci));
        if (state == true) {
            if (timeline != null) {
                timeline.stop();
            }
            timeSeconds = STARTTIME;

            // update timerLabel
            LabelZaman.setText(timeSeconds.toString());
            timeline = new Timeline();
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.getKeyFrames().add(
                    new KeyFrame(Duration.seconds(1),
                            new EventHandler() {
                                @Override
                                public void handle(Event event) {
                                    timeSeconds--;
                                    // update timerLabel
                                    LabelZaman.setText(timeSeconds.toString());
                                    if (timeSeconds <= 0) {
                                        timeline.stop();
                                        lightUpRightAnswer();
                                        disableButtons();
                                        OYS.sorular.poll();
                                        new Timer().schedule(
                                                new TimerTask() {
                                                    @Override
                                                    public void run() {
                                                        Platform.runLater(() -> {

                                                            if (OYS.sorular.peek() != null) {
                                                                simdikiSoru = getNextSoru();
                                                                SoruPaneliGuncelle();
                                                            } else {
                                                                OYS.oyuncu.bakiyeArttir(anlikPuan);
                                                                OYS.oyuncu.toplamPuanArttir(anlikPuan);
                                                                OYS.writeUsersIntoJson();
                                                                SplitPaneSoru.setVisible(false);
                                                                SplitPaneKullanici.setVisible(true);
                                                                secimPanel.setVisible(true);
                                                                KullaniciPaneliGuncelle();
                                                            }

                                                        });
                                                    }
                                                }, 3000
                                        );
                                    }
                                }
                            }));
            timeline.playFromStart();
        } else {
            timeline.stop();
            LabelZaman.setText(timeSeconds.toString());
        }
    }

    public void buttonTextFitter(Button button){
        if (button.getText().length()>20){
            button.setFont(Font.font(13));
        }else{
            button.setFont(Font.font(18));
        }
    }

    public void soruGuncelle(Soru s) {
        buttonReset();
        LabelSoruSayisi.setText(soruSayaci + "/5");
        LabelSoru.setText(s.soruMetni);
        if (LabelSoru.getText().length() > 60) {
            LabelSoru.setFont(Font.font(18));
        } else {
            LabelSoru.setFont(Font.font(23));
        }

        ButtonSecenekA.setText(s.secenekA);
        buttonTextFitter(ButtonSecenekA);

        ButtonSecenekB.setText(s.secenekB);
        buttonTextFitter(ButtonSecenekB);

        ButtonSecenekC.setText(s.secenekC);
        buttonTextFitter(ButtonSecenekC);

        ButtonSecenekD.setText(s.secenekD);
        buttonTextFitter(ButtonSecenekD);
        soruSayaci++;
    }

    //public void buttonAction(ActionEvent actionEvent){
    public void buttonAction(ActionEvent actionEvent) {
        //if (!OYS.sorular.isEmpty()){
        if (simdikiSoru != null) {
            OYS.sorular.poll();
            int prediction = Integer.parseInt(((Button) actionEvent.getSource()).getId().replace("btn", ""));

            ((Button) actionEvent.getSource()).setStyle("-fx-background-color: orange");
            zamanGuncelle(false);
            disableButtons();
            new Timer().schedule(
                    new TimerTask() {
                        @Override
                        public void run() {
                            Platform.runLater(() -> {

                                if (checkIfTrue(prediction, simdikiSoru.dogruCevap)) {
                                    ((Button) actionEvent.getSource()).setStyle("-fx-background-color: green");

                                    anlikPuan += simdikiSoru.puanHesapla();
                                } else {
                                    ((Button) actionEvent.getSource()).setStyle("-fx-background-color: red");
                                    lightUpRightAnswer();
                                }
                            });
                        }
                    }, 2000
            );

            new Timer().schedule(
                    new TimerTask() {
                        @Override
                        public void run() {
                            Platform.runLater(() -> {

                                if (OYS.sorular.peek() != null) {
                                    simdikiSoru = getNextSoru();
                                    SoruPaneliGuncelle();
                                } else {
                                    OYS.oyuncu.bakiyeArttir(anlikPuan);
                                    OYS.oyuncu.toplamPuanArttir(anlikPuan);
                                    OYS.writeUsersIntoJson();
                                    SplitPaneSoru.setVisible(false);
                                    SplitPaneKullanici.setVisible(true);
                                    secimPanel.setVisible(true);
                                    KullaniciPaneliGuncelle();
                                }

                            });
                        }
                    }, 4000
            );

        } else {

            //yarismaEkraniPencere.close();
        }
    }

    public void lightUpRightAnswer() {


        switch (simdikiSoru.dogruCevap) {
            case 1:
                ButtonSecenekA.setStyle("-fx-background-color: green");
                break;
            case 2:
                ButtonSecenekB.setStyle("-fx-background-color: green");
                break;
            case 3:
                ButtonSecenekC.setStyle("-fx-background-color: green");
                break;
            case 4:
                ButtonSecenekD.setStyle("-fx-background-color: green");
                break;

        }

    }

    public void disableButtons() {

        ButtonSecenekA.setDisable(true);

        ButtonSecenekB.setDisable(true);
//        ButtonSecenekB.setStyle("-fx-opacity: 0");
        ButtonSecenekC.setDisable(true);

        ButtonSecenekD.setDisable(true);
    }

    public void buttonReset() {
        ButtonSecenekA.setDisable(false);
        ButtonSecenekB.setDisable(false);
        ButtonSecenekC.setDisable(false);
        ButtonSecenekD.setDisable(false);

        ButtonSecenekA.setStyle("-fx-background-color:#3b3b50");
        ButtonSecenekB.setStyle("-fx-background-color:#3b3b50");
        ButtonSecenekC.setStyle("-fx-background-color:#3b3b50");
        ButtonSecenekD.setStyle("-fx-background-color:#3b3b50");

    }

    public void btnUlkeSec() {
        secimPanel.setVisible(false);
        ulkesecimiPanel.setVisible(true);
        TabloyuDoldur();
    }


    public ArrayList<String> generateKomsularTable() {
        ArrayList<String> tempTable = new ArrayList<>();
        Edge edge;

        Iterator itr = OYS.ulkelerGrafı.edgeIterator(OYS.ulkelerListesi.indexOf(OYS.simdikiUlke));

        while (itr.hasNext()) {
            edge = (Edge) itr.next();
            tempTable.add(String.format("%-20s\t%-10d", OYS.ulkelerListesi.get(edge.getDest()).getUlkeAdi(), (int) edge.getWeight()));
        }

        /*
        for (int i = 0; i < OYS.simdikiUlke.getKomsular().size(); i++) {
            edge = OYS.ulkelerGrafı.getEdge(OYS.ulkelerListesi.indexOf(OYS.simdikiUlke),OYS.ulkelerListesi.indexOf(new Ulke(OYS.simdikiUlke.getKomsular().get(i))));

            tempTable.add(String.format("%-20s\t%-10d",OYS.simdikiUlke.getKomsular().get(i),(int)edge.getWeight()));

        }
        */
        return tempTable;
    }

    public void TabloyuDoldur() {
        list = FXCollections.observableArrayList(generateKomsularTable());
        ulkeListView.setItems(list);
        ulkeListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }


    public void btnUlkeSecimiYap() {

        String ulkeSecimiListViewRow;
        ulkeSecimiListViewRow = ulkeListView.getSelectionModel().getSelectedItem();

        // Tokenize ListView Row to ulkeadi and distance
        String tokens[] = ulkeSecimiListViewRow.split(" \t");

        int distance = Integer.parseInt(tokens[1].trim());
        String ulkeadi = tokens[0].trim();

        if(OYS.oyuncu.getBakiye() >= distance){

            OYS.ulkeSec(ulkeadi);
            OYS.oyuncu.gecilenUlkeEkle(ulkeadi);
            updateGecilenUlkelerButtons();
            OYS.oyuncu.bakiyeAzalt(distance);
            ulkesecimiPanel.setVisible(false);
            SplitPaneKullanici.setVisible(false);
            //SplitPaneSoru.setVisible(true);
            oyunuBaslat();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Bakiyeniz yeterli değildir");
            alert.setHeaderText("Uyarı");

            alert.showAndWait();
        }

    }

    public void btnYenidenOyna() {
        oyunuBaslat();
        secimPanel.setVisible(false);
        SplitPaneKullanici.setVisible(false);
    }

    public void btnGeri() {
        ulkesecimiPanel.setVisible(false);
        secimPanel.setVisible(true);
    }

    public void generateGecilenUlkelerButtons() {
        //DEBUG
        //SplitPaneSoru.setVisible(false);
        //SplitPaneKullanici.setVisible(true);
        //secimPanel.setVisible(true);
        //DEBUG

        for (int i = 0; i < OYS.ulkelerListesi.size(); i++) {
            Image img = new Image(OYS.ulkelerListesi.get(i).getBayrak());
            ulkeBayraklari.add (new ImageView(img));
            ulkeBayraklari.get(i).setFitHeight(20);
            ulkeBayraklari.get(i).setFitWidth(20);
        }

        for (int i = 0; i < OYS.ulkelerListesi.size(); i++) {
            gecilenUlkeButonlari.add(new Button());
            gecilenUlkeButonlari.get(i).setLayoutX((i*35)+(5*(i)));
            gecilenUlkeButonlari.get(i).setLayoutY(5);
            gecilenUlkeButonlari.get(i).setPrefSize(25,25);
            gecilenUlkeButonlari.get(i).maxHeight(25);
            gecilenUlkeButonlari.get(i).maxWidth(25);
            gecilenUlkeButonlari.get(i).setStyle("-fx-focus-color: transparent;");
            gecilenUlkeButonlari.get(i).setDisable(true);

            secimPanel.getChildren().add(gecilenUlkeButonlari.get(i));

            gecilenUlkeButonlari.get(i).setGraphic(ulkeBayraklari.get(i));

        }

        updateGecilenUlkelerButtons();

    }

    public void updateGecilenUlkelerButtons(){

        ArrayList<String> gecilenUlkeler = OYS.oyuncu.getGecilenUlkeler();

        for (int i = 0; i < gecilenUlkeler.size(); i++) {
            int index = OYS.ulkelerListesi.indexOf(new Ulke(gecilenUlkeler.get(i)));
            gecilenUlkeButonlari.get(index).setDisable(false);
        }
    }

}