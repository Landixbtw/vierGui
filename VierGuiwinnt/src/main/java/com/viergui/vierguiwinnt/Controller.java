package com.viergui.vierguiwinnt;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import java.io.IOException;

/**
 * This is the controller class of the main screen
 * @author Ole Wortmann, 6007350
 * @author Yannik Schuldes, 6008313
 */

public class Controller {

    @FXML
    public void handleNeuButton(){
        System.out.println("works");
    }

    @FXML
    Button buttonPlayer;

    @FXML
    Button buttonComputer;

    @FXML
    public void handlePlayerButton() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("playerGame.fxml"));
        Scene scene = new Scene(root);
        Main.setScene(scene);
    }

    @FXML
    public void handleComputerButton() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("computerGame.fxml"));
        Scene scene = new Scene(root);
        Main.setScene(scene);
    }
}
