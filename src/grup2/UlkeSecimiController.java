package grup2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

import static grup2.Main.OYS;
import static grup2.Main.yarismaEkraniPencere;
import static grup2.Main.ulkeSecimiPencere;

/**
 * Created by Yavuz on 26.04.2018.
 */
public class UlkeSecimiController implements Initializable{

    @FXML
    public Label myLabel;

    @FXML
    public Button ulkeSecimButton;

    @FXML
    private ListView<String> ulkeListView;

    ObservableList<String> list = FXCollections.observableArrayList(OYS.getUlkelerListesiAsString());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ulkeListView.setItems(list);
        ulkeListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public void buttonAction(ActionEvent event){
        String ulkeadi;
        ulkeadi = ulkeListView.getSelectionModel().getSelectedItem();
        //System.out.println(ulke);

        OYS.ulkeSec(ulkeadi);

        OYS.oyuncu.gecilenUlkeEkle(ulkeadi);

        ulkeSecimiPencere.close();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("yarismaEkrani.fxml"));
            yarismaEkraniPencere.setScene(new Scene(root));
            yarismaEkraniPencere.setResizable(false);
            yarismaEkraniPencere.show();
        }catch(Exception e){
            System.out.println(e.toString());
        }

    }
}


/*
    ulkeListView = new ListView<String>();
        ulkeListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        Button b;
        ObservableList<String> obList = FXCollections.observableArrayList();


        for (String s: OYS.ulkeler.keySet()) {

            b= new Button(s);
            obList.add(s);
        }

        ulkeListView.setItems(obList);
*/