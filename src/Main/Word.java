package Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

 public class Word {

    private String word_target;//từ tiếng anh
    private String word_explain;//giải nghĩa từ
    public Word(String word_target_,String word_explain_)//phương thưc/hàm khởi tạo từ
    {
        word_target=word_target_;
        word_explain=word_explain_;
    }
    public String getWord_target()
    {
        return word_target;
    }//hàm trả lại từ
    public String getWord_explain()
    {
        return word_explain;
    }//hàm trả lại nghĩa từ

}
