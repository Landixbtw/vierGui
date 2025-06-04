package com.viergui.vierguiwinnt;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * This is the controller class of the player vs. player game mode
 * @author Ole Wortmann,
 * @author Yannik Schuldes, 6008313
 */

public class PlayerController extends GameController{

    private boolean player;

    @FXML
    private Label playerAlabel;

    @FXML
    private Label playerBlabel;

    public PlayerController(){
        super();
        player = true;
    }

    @FXML
    protected void initialize(){
        super.initialize();
        playerAlabel.setVisible(false);

        ImageView ivy = new ImageView(getImageY());
        ivy.setFitHeight(50);
        ivy.setFitHeight(50);
        ivy.setPreserveRatio(true);
        playerAlabel.setGraphic(ivy);

        ImageView ivr = new ImageView(getImageR());
        ivr.setFitHeight(50);
        ivr.setFitHeight(50);
        ivr.setPreserveRatio(true);
        playerBlabel.setGraphic(ivr);
    }

    @FXML
    protected void handleSetButton() {
        if(input != -1){
            if(!board.isFull()) {
                if (!board.setValue(input, (player ? 'X' : 'O'))) {
                    billboard.setText("Full column.");
                    return;
                }

                ImageView imgView = setImageLabel(player);
                gridBoard.add(imgView, input, board.getHighestYCoord(input));

                //Prüfen, ob gewonnen wurde
                if (board.checkField(input, board.getHighestYCoord(input))) {

                    String txt = "Player " + (player ? "A" : "B") + " won!";

                    billboard.setText(txt);
                    comboBox.setVisible(false);
                    setButton.setVisible(false);
                    return;
                }

                player = !player;
                if (player) {
                    playerBlabel.setVisible(true);
                    playerAlabel.setVisible(false);
                } else {
                    playerBlabel.setVisible(false);
                    playerAlabel.setVisible(true);
                }
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
        playerAlabel.setVisible(false);
        playerBlabel.setVisible(true);
        player = true;
    }
}
