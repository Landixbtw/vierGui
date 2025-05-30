package com.viergui.vierguiwinnt;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class PlayerController {

    private boolean player;
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
    Label playerXlabel;

    @FXML
    Label playerOlabel;

    public PlayerController(){
        player = true;
        nextRound = true;
        input = -1;
        board = new Board();
    }

    @FXML
    public void initialize(){
        comboBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7);
        playerOlabel.setVisible(false);
    }

    @FXML
    public void handleReturnButton() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("splashScreen.fxml"));
        Scene scene = new Scene(root);
        Main.setScene(scene);
    }

    @FXML
    public void handleComboBox(){
        input = comboBox.getValue() - 1;
    }

     @FXML
    public void handleSetButton() throws IOException{
        if(input != -1){
            if (!board.setValue(input, (player? 'X' : 'O'))) {
                billboard.setText("Full row.");
                return;
            }

            billboard.setText("VS");
            gridBoard.add(new Label(player? "X" : "O"), input,
                    board.getHighestYCoord(input));

            //Prüfen, ob gewonnen wurde
            if(board.checkField(input, board.getHighestYCoord(input))){
                billboard.setText("Player "+ (player? "A" : "B") + " won!");
                this.handleReturnButton();
            }

            player = !player;
            if(player) {
                playerXlabel.setVisible(true);
                playerOlabel.setVisible(false);
            }
            else{
                playerXlabel.setVisible(false);
                playerOlabel.setVisible(true);
            }
        }
     }
}
