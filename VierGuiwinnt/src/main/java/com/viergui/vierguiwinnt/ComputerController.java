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

    private boolean player;
    private boolean nextRound;
    private int input;
    private Board board;
    private final int COLUMN = 0;

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

    // NOTE: FXML needs a no arguments constructor otherwise it does not run
    public ComputerController() {
        // player = player | !player = computer
        player = true;
        nextRound = false;
        input = -1;
        board = new Board();
    }


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

    @FXML
    public void handleComboBox() {
        input = comboBox.getValue() - 1;
    }


    /*
    * This method handles the checking of the Location the Computer placement and win checking
    * */
    @FXML
    public void handleSetButton() throws IOException {
        // we need to automatically place after player places
        Computer computer = new Computer(board.getColumn());
        if (!player) input = computer.set(); // this should provide column for computer to place
        // directly put the computer place logic behind this, so it all happens in one move? => Loop for 2
        // but then we need to land at player again so that its not player computer, computer
        // need to modify game method? game2?
        if(input != -1) {
            if(!board.setValue(input, (player ? 'X' : 'O'))) {
                billboard.setText("Full row");
                return;
            }

            billboard.setText("VS");
            gridBoard.add(new Label(player ? "X" : "O"), input, board.getHighestYCoord(input));

            // checking for win player = player | !player = computer
            if(board.checkField(input, board.getHighestYCoord(input))) {
                billboard.setText((player ? "Player Won!" : "Computer Won!"));
                this.handleReturnButton();
            }

            // here we go from player turn to computer turn
            player = !player;
            if(player) {
                playerLabel.setVisible(true);
                computerLabel.setVisible(false);
            } else {
                playerLabel.setVisible(false);
                computerLabel.setVisible(true);
            }
        }
    }
}
