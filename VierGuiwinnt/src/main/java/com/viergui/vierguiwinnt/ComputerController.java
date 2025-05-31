package com.viergui.vierguiwinnt;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;

public class ComputerController {

    //private boolean player;
    private boolean computer;
    private boolean nextRound;
    private int input;
    private Board board;

    @FXML
    Button buttonReturn;

    @FXML
    ComboBox<Integer> comboBox;

    @FXML
    GridPane gridBoard;

    @FXML
    Label billboard;

    @FXML
    Label playerLabel;

    @FXML
    Label computerLabel;

    public ComputerController(Board board) {
        computer = true;
        nextRound = false;
        input = -1;
        this.board = board;
    }

    /*
    *Caused by: java.lang.NullPointerException: Cannot invoke "javafx.scene.control.Label.setVisible(boolean)" because "this.playerOlabel" is null
	at com.viergui.vierguiwinnt/com.viergui.vierguiwinnt.PlayerController.initialize(PlayerController.java:50)
	*aused by: javafx.fxml.LoadException:
        /home/ole/Dokumente/Uni/java/PROG2/PROG2/GUI/viergui/out/production/viergui/com/viergui/vierguiwinnt/computerGame.fxml
    * */


    @FXML
    public void initialize() {
        comboBox.getItems().addAll(1,2,3,4,5,6,7);
        computerLabel.setVisible(false);
    }


    @FXML
    public void handleReturnButton() throws IOException {
        String fxmlPath = "splashScreen.fxml";
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            Scene scene = new Scene(root);
            Main.setScene(scene);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*
    * This method handles the checking of the Location the Computer placement and win checking
    * */
    @FXML
    public void handleSetButton() throws IOException {
    }
}
