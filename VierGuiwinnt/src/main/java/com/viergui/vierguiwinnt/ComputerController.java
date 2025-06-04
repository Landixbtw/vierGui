package com.viergui.vierguiwinnt;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import java.io.IOException;

/**
 * This is the controller class of the player vs. computer game mode
 * @author Ole Wortmann,
 * @author Yannik Schuldes, 6008313
 */

public class ComputerController extends GameController{

    private Computer computer;

    @FXML
    private Label playerLabel;

    @FXML
    private Label computerLabel;

    @FXML
    private Label playerImgLabel;

    @FXML
    private Label computerImgLabel;

    public ComputerController(){
        super();
        computer = new Computer(board.getColumn());
    }


    @FXML
    protected void initialize(){
        super.initialize();
        computerImgLabel.setVisible(false);

        ImageView ivy = new ImageView(getImageR());
        ivy.setFitHeight(50);
        ivy.setFitHeight(50);
        ivy.setPreserveRatio(true);
        playerImgLabel.setGraphic(ivy);

        ImageView ivr = new ImageView(getImageY());
        ivr.setFitHeight(50);
        ivr.setFitHeight(50);
        ivr.setPreserveRatio(true);
        computerImgLabel.setGraphic(ivr);
    }

    /*
    * This method handles the checking of the Location the Computer placement and win checking
    * */
    @FXML
    protected void handleSetButton() throws IOException {
        // we need to automatically place after player places
        // if its computer turn we want to disable the button
        // and kinda "skip" the turn so that user cant place double

        if(input != -1) {
            if(!board.isFull()){
                if (!board.setValue(input, ('X'))) {
                    billboard.setText("Full column");
                    return;
                }

                ImageView imgView = setImageLabel(true);
                gridBoard.add(imgView, input, board.getHighestYCoord(input));

                if (board.checkField(input, board.getHighestYCoord(input))) {
                    billboard.setText("Player won!");
                    comboBox.setVisible(false);
                    setButton.setVisible(false);
                    return;
                }

                // here we go from player turn to computer turn
                setButton.setVisible(false);
                playerImgLabel.setVisible(false);
                computerImgLabel.setVisible(true);

                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.7), e -> {

                    //if a row is full, computer should choose again
                    int computerInput;
                    do{
                        computerInput = computer.set();
                    } while(!board.setValue(computerInput, 'O'));


                    ImageView imgViewComputer = setImageLabel(false);
                    gridBoard.add(imgViewComputer, computerInput, board.getHighestYCoord(computerInput));

                    // checking for win
                    if (board.checkField(computerInput, board.getHighestYCoord(computerInput))) {
                        billboard.setText("Computer Won!");
                        comboBox.setVisible(false);
                        setButton.setVisible(false);
                    }
                    else {
                        // Set up next player turn
                        playerImgLabel.setVisible(true);
                        computerImgLabel.setVisible(false);
                        setButton.setVisible(true);
                    }

                }));
                timeline.play();
            }
            else{
                billboard.setText("Board Full");
                comboBox.setVisible(false);
                setButton.setVisible(false);
            }
        }
    }


    @FXML
    protected void handleNewButton(){
        super.handleNewButton();
        playerImgLabel.setVisible(true);
        computerImgLabel.setVisible(false);
    }
}
