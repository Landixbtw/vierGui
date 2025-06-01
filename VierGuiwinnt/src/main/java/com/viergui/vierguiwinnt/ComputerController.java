package com.viergui.vierguiwinnt;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.io.IOException;

public class ComputerController {

    public Button setButton;
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
    public void handleSetButton() throws IOException, InterruptedException {
        // we need to automatically place after player places
        Computer computer = new Computer(board.getColumn());
        // if its computer turn we want to disable the button
        // and kinda "skip" the turn so that user cant place double

        if(input != -1) {
            if(!board.setValue(input,(player ? 'X' : 'O'))) {
                billboard.setText("Full row");
                return;
            }

            billboard.setText("VS");
            gridBoard.add(new Label(player ? "X" : "O"), input, board.getHighestYCoord(input));

            if(board.checkField(input, board.getHighestYCoord(input))) {
                billboard.setText("Player Won!");
                this.handleReturnButton();
                return;
            }

            // here we go from player turn to computer turn
            player = !player;
            // computer turn
            if(!player) {
                setButton.setDisable(true);
                playerLabel.setVisible(false);
                computerLabel.setVisible(true);
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.7), e -> {
                    input = computer.set();

                    if(!board.setValue(input,'O')) {
                        billboard.setText("Full row");
                        return;
                    }
                    billboard.setText("VS");
                    gridBoard.add(new Label("O"), input, board.getHighestYCoord(input));

                    // checking for win player = player | !player = computer
                    if(board.checkField(input, board.getHighestYCoord(input))) {
                        billboard.setText("Computer Won!");
                    } else {
                        // Set up next player turn
                        player = true;
                        playerLabel.setVisible(true);
                        computerLabel.setVisible(false);
                        setButton.setDisable(false);
                    }
                }));
                timeline.play();
            }
        }
    }
}
