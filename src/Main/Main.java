//Từ điển do Nguyễn Ngọc Minh và Lê Quang Phước làm
package Main;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.imageio.IIOException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{//hàm hiển thị giao diện trong runtime
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/Dictionaryfx.fxml"));
        primaryStage.setTitle("Dictionary");
        Scene scene=new Scene(root);
        String css=Main.class.getResource("../Style/Style.css").toExternalForm();
        Image image=new Image("/Style/icons8-dictionary-64.png");
        primaryStage.getIcons().add(image);
        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void showExceptionDialog(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Exception Dialog");
        alert.setHeaderText("An error occurred:");

        String content = "Error: ";
        if (null != e) {
            content += e.toString() + "\n\n";
        }

        alert.setContentText(content);

        Exception ex = new Exception(e);

        //Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);

        String exceptionText = sw.toString();

        //Set up TextArea
        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);


        textArea.setPrefHeight(600);
        textArea.setPrefWidth(800);


        //Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(textArea);


        alert.showAndWait();
    }
    public static void main(String[] args) throws IIOException {//hàm chạy chương chình

        launch(args);
    }
}