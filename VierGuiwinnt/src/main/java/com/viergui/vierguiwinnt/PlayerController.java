package com.viergui.vierguiwinnt;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class PlayerController extends GameController{

    @FXML
    Label playerAlabel;

    @FXML
    Label playerBlabel;

    public PlayerController(){
        super();
    }

    @FXML
    public void initialize(){
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
    public void handleSetButton() {
        if(input != -1){
            if(!board.isFull()) {
                if (!board.setValue(input, (player ? 'X' : 'O'))) {
                    billboard.setText("Full row.");
                    return;
                }

                ImageView imgView = new ImageView(player ? getImageR() : getImageY());
                imgView.setFitHeight(25);
                imgView.setFitHeight(25);
                imgView.setPreserveRatio(true);
                gridBoard.add(imgView, input, board.getHighestYCoord(input));
                GridPane.setHalignment(imgView, HPos.CENTER);
                GridPane.setValignment(imgView, VPos.CENTER);

                //Prüfen, ob gewonnen wurde
                if (board.checkField(input, board.getHighestYCoord(input))) {
                    billboard.setText("Player " + (player ? "A" : "B") + " won!");
                    comboBox.setVisible(false);
                    setButton.setVisible(false);
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
    public void handleNewButton(){
        super.handleNewButton();
        playerAlabel.setVisible(false);
        playerBlabel.setVisible(true);
    }
}
