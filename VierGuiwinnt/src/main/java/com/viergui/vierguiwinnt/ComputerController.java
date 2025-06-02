package com.viergui.vierguiwinnt;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.io.IOException;

public class ComputerController extends GameController{

    //public Button setButton;
    //private boolean player;
    //private boolean nextRound;
    //private int input;
    //private Board board;
    //private final int COLUMN = 0;

//    @FXML
//    Button buttonReturn;

//    @FXML
//    ComboBox<Integer> comboBox;

//    @FXML
//    GridPane gridBoard;

//    @FXML
//    Label billboard;

    @FXML
    Label playerLabel;

    @FXML
    Label computerLabel;

    @FXML
    Label playerImgLabel;

    @FXML
    Label computerImgLabel;

    // NOTE: FXML needs a no arguments constructor otherwise it does not run
//    public ComputerController() {
//        // player = player | !player = computer
//        player = true;
//        nextRound = false;
//        input = -1;
//        board = new Board();
//    }

    public ComputerController(){
        super();
    }


//    @FXML
//    public void initialize() {
//        comboBox.getItems().addAll(1,2,3,4,5,6,7);
//        computerLabel.setVisible(false);
//    }

    @FXML
    public void initialize(){
        super.initialize();
        playerImgLabel.setVisible(false);

        ImageView ivy = new ImageView(getImageY());
        ivy.setFitHeight(50);
        ivy.setFitHeight(50);
        ivy.setPreserveRatio(true);
        playerImgLabel.setGraphic(ivy);

        ImageView ivr = new ImageView(getImageR());
        ivr.setFitHeight(50);
        ivr.setFitHeight(50);
        ivr.setPreserveRatio(true);
        computerImgLabel.setGraphic(ivr);
    }

//    @FXML
//    public void handleReturnButton() throws IOException {
//        String fxmlPath = "splashScreen.fxml";
//        try {
//            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
//            Scene scene = new Scene(root);
//            Main.setScene(scene);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

//    @FXML
//    public void handleComboBox() {
//        input = comboBox.getValue() - 1;
//    }


    private ImageView setImageLabel(boolean player) {
        ImageView imgView = new ImageView(player ? getImageR() : getImageY());
        imgView.setFitHeight(25);
        imgView.setFitHeight(25);
        imgView.setPreserveRatio(true);
        GridPane.setHalignment(imgView, HPos.CENTER);
        GridPane.setValignment(imgView, VPos.CENTER);
        return imgView;
    }

    /*
    * This method handles the checking of the Location the Computer placement and win checking
    * */
    @FXML
    public void handleSetButton() throws IOException {
        // we need to automatically place after player places
        Computer computer = new Computer(board.getColumn());
        // if its computer turn we want to disable the button
        // and kinda "skip" the turn so that user cant place double

        if(input != -1 && !board.isFull()) {
            if (!board.setValue(input, (player ? 'X' : 'O'))) {
                billboard.setText("Full row");
                return;
            }

            ImageView imgView = setImageLabel(player);
            gridBoard.add(imgView, input, board.getHighestYCoord(input));

            if (board.checkField(input, board.getHighestYCoord(input))) {
                billboard.setText("Player Won!");
                System.out.println("Player Won!");
                comboBox.setVisible(false);
                setButton.setVisible(false);
                return;
            }

            // here we go from player turn to computer turn
            player = !player;
            // computer turn
            if (!player) {
                setButton.setVisible(false);
                playerImgLabel.setVisible(false);
                computerLabel.setVisible(true);
                computerImgLabel.setVisible(true);
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.7), e -> {
                int computerInput = computer.set();

                if (!board.setValue(computerInput, 'O')) {
                    billboard.setText("Full row");
                    return;
                }

                ImageView imgViewComputer = setImageLabel(player);
                gridBoard.add(imgViewComputer, computerInput, board.getHighestYCoord(computerInput));

                // checking for win player = player | !player = computer
                if (board.checkField(computerInput, board.getHighestYCoord(computerInput))) {
                    billboard.setText("Computer Won!");
                    System.out.println("Computer Won!");
                    comboBox.setVisible(false);
                    setButton.setVisible(false);
                    return;
                } else {
                    // Set up next player turn
                    player = true;
                    playerLabel.setVisible(true);
                    playerImgLabel.setVisible(true);
                    computerImgLabel.setVisible(false);
                    setButton.setVisible(true);
                }
                }));
                timeline.play();
            }
        }
    }


    @FXML
    public void handleNewButton(){
        super.handleNewButton();
        playerLabel.setVisible(false);
        computerLabel.setVisible(true);
    }
}
