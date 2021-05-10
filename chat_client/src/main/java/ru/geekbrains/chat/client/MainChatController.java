package ru.geekbrains.chat.client;


import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MainChatController {

    public TextField inputField;
    public TextArea chatArea;
    public ListView onlineUsers;
    public Button buttonSendMessage;
    public Button buttonExit;

    private static HostServices hostServices ;

    public static HostServices getHostServices() {
        return hostServices ;
    }


    public void mockAction(ActionEvent actionEvent) {
        System.out.println("Mockkkk");
    }

    public void exit(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void exitWindow(ActionEvent actionEvent) {
        Stage stage = (Stage) buttonExit.getScene().getWindow();
        stage.close();
    }

    public void showAbout(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/newScene.fxml"));
        Parent root = loader.load();

        Stage newStageAbout = new Stage();
        Scene scene = new Scene(root, 600, 600);


        newStageAbout.setScene(scene);
        newStageAbout.setResizable(false);
        newStageAbout.setTitle("About");
        newStageAbout.setOnHidden(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                System.out.println("Close 'About' Window!");
            }
        });
        newStageAbout.show();
            }



    public void showHelp(ActionEvent actionEvent) throws URISyntaxException, IOException {
        Desktop desktop = Desktop.getDesktop();
        desktop.browse(new URI("https://vk.com/bezvozmezdnopomoshh"));

    }

    //Почему не работает?(((
    public void showMySite(ActionEvent actionEvent) throws URISyntaxException, IOException {
        final Hyperlink hyperlink = new Hyperlink("https://gb.ru/");
        hyperlink.setOnAction(t -> {
           getHostServices().showDocument(hyperlink.getText());
        });
    }


    public void sendMessage(ActionEvent actionEvent) {
        appendTextFromTF();
        System.out.println("SendMessage");
    }

    private void appendTextFromTF() {
       String msg = inputField.getText();
       if(msg.isEmpty()) return;
       chatArea.appendText("ME: " + msg + System.lineSeparator());

       inputField.clear();
    }
}
