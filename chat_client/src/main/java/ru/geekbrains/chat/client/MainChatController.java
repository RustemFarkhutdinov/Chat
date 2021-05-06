package ru.geekbrains.chat.client;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MainChatController {

    public TextField inputField;
    public TextArea chatArea;
    public ListView onlineUsers;
    public Button buttonSendMessage;

    public void mockAction(ActionEvent actionEvent) {
        System.out.println("Mockkkk");
    }

    public void exit(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void showAbout(ActionEvent actionEvent) throws IOException {
        App showAbout = FXMLLoader.load(getClass().getResource("/newScene.fxml"));
        Stage stage = new Stage();


        stage.setScene(new Scene()); //Надо было создать в App классе?
        stage.setResizable(false);
        stage.show();
            }



    public void showHelp(ActionEvent actionEvent) throws URISyntaxException, IOException {
        Desktop desktop = Desktop.getDesktop();
        desktop.browse(new URI("https://vk.com/bezvozmezdnopomoshh"));

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
