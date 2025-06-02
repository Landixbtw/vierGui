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

public class PlayerController {

    private boolean player;
    private int input;
    private Board board;
    private Image imageY;
    private Image imageR;

    @FXML
    Button buttonReturn;

    @FXML
    ComboBox<Integer> comboBox;

    @FXML
    GridPane gridBoard;

    @FXML
    Label billboard;

    @FXML
    Label playerBlabel;

    @FXML
    Label playerAlabel;

    @FXML
    Button setButton;

    @FXML
    Button newGame;

    public PlayerController(){
        init();
    }

    @FXML
    public void initialize(){
        comboBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7);
        playerAlabel.setVisible(false);
        gridBoard.setGridLinesVisible(true);

        imageY = new Image(getClass()
                .getResource("yellowCircle.png")
                .toExternalForm());

        imageR = new Image(getClass()
                .getResource("redCircle.png")
                .toExternalForm());

        ImageView ivy = new ImageView(imageY);
        ivy.setFitHeight(50);
        ivy.setFitHeight(50);
        ivy.setPreserveRatio(true);
        playerAlabel.setGraphic(ivy);

        ImageView ivr = new ImageView(imageR);
        ivr.setFitHeight(50);
        ivr.setFitHeight(50);
        ivr.setPreserveRatio(true);
        playerBlabel.setGraphic(ivr);
    }

    @FXML
    public void handleReturnButton() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("splashScreen.fxml"));
        Scene scene = new Scene(root);
        Main.setScene(scene);
    }

    @FXML
    public void handleComboBox(){
        if(comboBox.getValue() != null)
            input = comboBox.getValue() - 1;
    }

     @FXML
    public void handleSetButton() throws IOException{
        if(input != -1){
            if (!board.setValue(input, (player? 'X' : 'O'))) {
                billboard.setText("Full row.");
                return;
            }

            ImageView imgView = new ImageView(player? imageR : imageY);
            imgView.setFitHeight(25);
            imgView.setFitHeight(25);
            imgView.setPreserveRatio(true);
            gridBoard.add(imgView, input, board.getHighestYCoord(input));
            GridPane.setHalignment(imgView, HPos.CENTER);
            GridPane.setValignment(imgView, VPos.CENTER);

            //Prüfen, ob gewonnen wurde
            if(board.checkField(input, board.getHighestYCoord(input))){
                billboard.setText("Player "+ (player? "A" : "B") + " won!");
                comboBox.setVisible(false);
                setButton.setVisible(false);
            }

            player = !player;
            if(player) {
                playerBlabel.setVisible(true);
                playerAlabel.setVisible(false);
            }
            else{
                playerBlabel.setVisible(false);
                playerAlabel.setVisible(true);
            }
        }
    }

    @FXML
    public void handleNewButton(){
        billboard.setText("VS");
        comboBox.setVisible(true);
        comboBox.setValue(null);
        setButton.setVisible(true);
        playerAlabel.setVisible(false);
        playerBlabel.setVisible(true);
        gridBoard.getChildren().removeIf(node -> node instanceof ImageView);
        init();
    }

    private void init(){
        player = true;
        input = -1;
        board = new Board();
    }
}
