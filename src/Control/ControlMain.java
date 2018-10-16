package Control ;
import Main.Dictionary;
import Main.DictionaryManagement;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;


public class ControlMain extends Dictionary implements Initializable {

    private static final String url ="dictionaries.txt";


    @FXML
    TextArea textArea ;//danh sách hiển thị nghĩa của từ
    @FXML
    TextField searchField,addField_Target,addField_Explain ;//các index nhập ký tự

    @FXML
    ListView listView = new ListView();//danh sách từ theo dạng list
    //click chuột của từ tiếng anh để hiện ra nghĩa

    public void clicked (MouseEvent e){//click chuột vào từ tiếng anh để hiện ra nghĩa
        textArea.setText(DictionaryManagement.dictionaryLookup(listView.getSelectionModel().getSelectedItem().toString()));
    }

    public void addup(KeyEvent event)//Chức năng thêm từ năm trong EDIT
    {
        String engrisk=addField_Target.getText()+" ";
        String tiengvet=addField_Explain.getText();
        if(event.getCode()==KeyCode.SHIFT)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("HEY!");
            alert.setHeaderText(null);
            alert.setContentText("You are adding this word "+'"'+engrisk+'"'+" and its meaning "+'"'+tiengvet+'"');
            alert.showAndWait();
            List<String> newWord=DictionaryManagement.addmoreword(engrisk,tiengvet);
            DictionaryManagement.dictionaryExportToFile();
            ObservableList<String> data=FXCollections.observableArrayList(newWord);
            listView.setItems(data);
            newWord.clear();

        }
    }
    public void deleteword(KeyEvent event) throws IOException {//xóa từ thông qua nút Del trong Listview
        textArea.setText(DictionaryManagement.dictionaryLookup(listView.getSelectionModel().getSelectedItem().toString()));
        String del=listView.getSelectionModel().getSelectedItem().toString();
        if(event.getCode()==KeyCode.DELETE)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("HEY!");
            alert.setHeaderText(null);
            alert.setContentText("This word "+'"'+del+'"'+" will be deleted");
            alert.showAndWait();
            List<String> pull_out= DictionaryManagement.removeWordFromDitionary(del);
            DictionaryManagement.dictionaryExportToFile();
            ObservableList <String> data = FXCollections.observableArrayList(pull_out);
            listView.setItems(data);
            pull_out.clear();
        }

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
                DictionaryManagement.add_up.clear();

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


