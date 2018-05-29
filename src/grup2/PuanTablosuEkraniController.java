package grup2;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static grup2.Main.OYS;
import static grup2.Main.puanTablosuPencere;


public class PuanTablosuEkraniController implements Initializable {

    @FXML
    private Label LabelBaslık;
    @FXML
    private ListView<String> puanTablosuListView ;
//    @FXML
//    private TableColumn TableColumnOyuncuAdi;
//    @FXML
//    private TableColumn TableColumnPuan;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ArrayList<String> listPuan= new ArrayList<>();

        OYS.puanTablosu = new BinarySearchTree();

        for (int i = 0; i < OYS.oyuncuListesi.size(); i++) {

            OYS.puanTablosu.add( OYS.oyuncuListesi.get(i));
        }

        ArrayList<Oyuncu> puanList = OYS.puanTablosuListesiAl();

        puanTablosuListView.setStyle("-fx-font-size: 1.3em ;");
        for(int i=0; i<puanList.size() ; i++ ){

            StringBuilder s = new StringBuilder();
            s.append(String.format((i+1) +"\t"+"%-20s%-20s",puanList.get(i).getToplamPuan(),puanList.get(i).getKullaniciAdi()));
            listPuan.add(s.toString());
        }

        ObservableList<String> data = FXCollections.observableArrayList(listPuan);
        puanTablosuListView.setItems(data);
        puanTablosuListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }

}

