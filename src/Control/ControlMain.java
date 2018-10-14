
package Control ;
import Main.Dictionary;
import Main.DictionaryManagement;
import Main.Word;
import com.sun.javafx.scene.EnteredExitedHandler;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;


public class ControlMain extends Dictionary implements Initializable {

    private static final String url ="dictionaries.txt";


    @FXML
    TextArea textArea ;
    @FXML
    TextField searchField,addField,removeField ;

    @FXML
    ListView listView = new ListView();
    //click chuột của từ tiếng anh để hiện ra nghĩa
    public void clicked (MouseEvent e){
        textArea.setText(DictionaryManagement.dictionaryLookup(listView.getSelectionModel().getSelectedItem().toString()));
    }
    public void deleteword(KeyEvent event) throws IOException {
        String del=removeField.getText();
        if(event.getCode()==KeyCode.ENTER)
        {
            DictionaryManagement.removeWordFromDitionary(del);

        }
        DictionaryManagement.dictionaryExportToFile();
        Collections.sort(listWordTarget);
        ObservableList <String> data = FXCollections.observableArrayList(listWordTarget);
        listView.setItems(data);
    }
    public void addword(MouseEvent event)
    {

    }
    public void close(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }
    //search từ trên giao diện
    public void inputsearch(KeyEvent event)
    {
                String se=searchField.getText().toString();
                List<String> s= DictionaryManagement.dictionarySearcher(se);
                Collections.sort(s);
                ObservableList<String> input = FXCollections.observableArrayList(s);
                listView.setItems(input);
                DictionaryManagement.addup.clear();

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try{
            DictionaryManagement.InsertFromFile();
        } catch (IOException e){
            e.printStackTrace();
        }
        Collections.sort(listWordTarget);
        ObservableList <String> data = FXCollections.observableArrayList(listWordTarget);
        listView.setItems(data);
    }

    }


