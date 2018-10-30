package Control ;
import Main.Dictionary;
import Main.DictionaryManagement;
import Main.VoiceOver;
import GoogleTranslate.GoogleTTS_Translate;
import com.darkprograms.speech.translator.GoogleTranslate;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;

public class ControlMain extends Dictionary implements Initializable {
    @FXML
    private ComboBox<String> ChangeLang;//tùy chọn dịch nghĩa google dịch
    private ObservableList<String> setLanguage=FXCollections.observableArrayList("VI","FR","RU","KO","JA");
    public MenuBar menuBar;//thanh công cụ
    @FXML
    private TextArea textArea ;//danh sách hiển thị nghĩa của từ
    @FXML
    private TextField searchField,addField_Target,addField_Explain;//modify ;//các index nhập ký tự
    @FXML
    Button speaker;//nút phát âm bằng giọng nói
    Button speaker1;//phát âm thông qua google dịch
    public ListView listView = new ListView();//danh sách từ theo dạng list


    public void clicked (MouseEvent e){//click chuột vào từ tiếng anh để hiện ra nghĩa
        try
        {
            searchField.setText(listView.getSelectionModel().getSelectedItem().toString());
            textArea.setText(DictionaryManagement.dictionaryLookup(listView.getSelectionModel().getSelectedItem().toString()));
        }catch (NullPointerException e1)
        {
            System.out.println("There is nothing");
        }

    }//click chuột của từ tiếng anh để hiện ra nghĩa

    public void add_word(KeyEvent event)//Chức năng thêm từ năm trong EDIT
    {
        if(event.getCode()==KeyCode.SHIFT )
        {
            String engrisk=addField_Target.getText();
            String meaning=addField_Explain.getText();
            if(engrisk.length()>0 &&meaning.length()>0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("HEY!");
                alert.setHeaderText(null);
                alert.setContentText("You are adding this word " + '"' + engrisk + '"' + " and its meaning " + '"' + meaning + '"');
                alert.showAndWait();
                List<String> newWord = DictionaryManagement.addmoreword(engrisk, meaning);
                DictionaryManagement.dictionaryExportToFile();
                ObservableList<String> data = FXCollections.observableArrayList(newWord);
                listView.setItems(data);
                newWord.clear();
                addField_Explain.clear();
                addField_Target.clear();
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Please!");
                alert.setHeaderText(null);
                alert.setContentText("Please add words correctly.");
                alert.showAndWait();
            }

        }
    }
    public void wordlookup(KeyEvent event)//ham su dung khi danh mot tu//an enter se hien thang nghia tren textArea
    {
        if(event.getCode()==KeyCode.ENTER) {
            String wordLook = searchField.getText();
            textArea.setText(DictionaryManagement.dictionaryLookup(wordLook));
        }
    }
    public void deleteword(KeyEvent event) throws IOException {//xóa từ thông qua nút Del trong Listview

        try
        {   textArea.setText(DictionaryManagement.dictionaryLookup(listView.getSelectionModel().getSelectedItem().toString()));
            String del=listView.getSelectionModel().getSelectedItem().toString();
            if(event.getCode()==KeyCode.DELETE) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("HEY!");
                alert.setHeaderText(null);
                alert.setContentText("This word " + '"' + del + '"' + " will be deleted");
                alert.showAndWait();
                textArea.clear();
                searchField.clear();
                List<String> pull_out = DictionaryManagement.removeWordFromDitionary(del);
                //DictionaryManagement.dictionaryExportToFile();
                ObservableList<String> data = FXCollections.observableArrayList(pull_out);
                Collections.sort(data);
                listView.setItems(data);
                pull_out.clear();
            }

        }catch (Exception e)
        {
            System.out.println(">>>");
        }
    }//xóa từ
    public void close(ActionEvent event) {//ham đóng ứng dụng
        Platform.exit();
        System.exit(0);
    }
    //search từ trên giao diện
    public void inputsearch(KeyEvent event)//hàm tìm kiếm hiển thị danh sách từ
    {
        //textArea.setText(DictionaryManagement.dictionaryLookup(listView.getSelectionModel().getSelectedItem().toString()));
        String look=searchField.getText().toString();
        List<String> s= DictionaryManagement.dictionarySearcher(look);
        ObservableList<String> input = FXCollections.observableArrayList(s);
        listView.setItems(input);
        //listView.scrollTo(look);
        //textArea.clear();
        DictionaryManagement.add_up.clear();
    }
    public void VoicebyInput()//hàm phát âm thông qua lệnh gõ trên thanh search
    {
        VoiceOver.TextToSpeech(searchField.getText());
    }
    public void GGTranslate()//hàm dịch nghĩa bằng GG Translate thông qua thanh tìm kiếm
    {
        String option=ChangeLang.getSelectionModel().getSelectedItem().toLowerCase();
        try
        {
            String translate=GoogleTTS_Translate.google_Translate(option,searchField.getText().toLowerCase());
            textArea.clear();
            textArea.setText(translate);
        }catch (NullPointerException e)
        {
            System.out.println("Done");

        }

        //textArea.setEditable(false);
    }
    public void About(ActionEvent event)//hàm thông tin
    {
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("@Copyright-This Dictionary was written by Nguyen Ngoc Minh & Le Quang Phuoc using Java FX platform, Google Translate API, TTS Voice Over and Database was based on Oxford English Dictionary(35000-40000 words)");
        alert.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        final Tooltip tooltip=new Tooltip();
        tooltip.setText("File: where you can close the application\nEdit: where you can add new words\nAbout: where you can read the Information ");
        menuBar.setTooltip(tooltip);
        final Tooltip tooltip1=new Tooltip();
        tooltip1.setText("You can input the word you are searching for then click on the list\nor type the entire word then press Enter to show the meaning.");
        searchField.setTooltip(tooltip1);
        final Tooltip tooltip2=new Tooltip();
        tooltip2.setText("Speaker for pronouncing");
        speaker.setTooltip(tooltip2);
        final Tooltip tooltip3=new Tooltip();
        tooltip3.setText("This Area will display the meaning/interpretation of the word by text.\nAlso you can edit/modify this as well and confirm the modification by hit ENTER");
        textArea.setTooltip(tooltip3);
        VoiceOver.TextToSpeech("Welcome,Let's get started");
        try{
            DictionaryManagement.InsertFromFile();
        } catch (IOException e){
            e.printStackTrace();
        }
        ChangeLang.setItems(setLanguage);
        ChangeLang.setValue("VI");
        Collections.sort(listWordTarget);
        ObservableList<String> data = FXCollections.observableArrayList(listWordTarget);
        listView.setItems(data);

    }//hàm khởi tạo sẵn khi trong runtime

    public void mod(KeyEvent event)//hàm chỉnh sửa nghĩa từ
    {
        try {
            textArea.setEditable(true);
            if(event.getCode()==KeyCode.ENTER) {
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Warning");
                alert.setHeaderText("You are changing this word");
                alert.showAndWait();
                alert.close();
                String modding=textArea.getText();
                String point=searchField.getText();
                String s=DictionaryManagement.modified(point, modding);
                textArea.setText(s);
                textArea.setEditable(false);
            }
        }catch (Exception e)
        {
            System.out.println("Try Again");
        }

    }
}


