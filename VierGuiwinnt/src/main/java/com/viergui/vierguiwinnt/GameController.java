package com.viergui.vierguiwinnt;

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

import java.io.IOException;

public abstract class GameController {

    protected boolean player;
    protected Board board;
    protected int input;
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
    Button setButton;

    @FXML
    Button newGame;

    public GameController() {
        init();
    }

    @FXML
    public void initialize() {
        comboBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7);
        gridBoard.setGridLinesVisible(true);

        imageY = new Image(getClass()
                .getResource("yellowCircle.png")
                .toExternalForm());

        imageR = new Image(getClass()
                .getResource("redCircle.png")
                .toExternalForm());
    }

    @FXML
    public void handleReturnButton() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("splashScreen.fxml"));
        Scene scene = new Scene(root);
        Main.setScene(scene);
    }

    @FXML
    public void handleComboBox() {
        if (comboBox.getValue() != null)
            input = comboBox.getValue() - 1;
    }

    @FXML
    public abstract void handleSetButton() throws IOException;

    @FXML
    public void handleNewButton() {
        billboard.setText("VS");
        comboBox.setVisible(true);
        comboBox.setValue(null);
        setButton.setVisible(true);
        gridBoard.getChildren().removeIf(node -> node instanceof ImageView);
        init();
    }

    protected ImageView setImageLabel(boolean player) {
        ImageView imgView = new ImageView(player ? getImageR() : getImageY());
        imgView.setFitHeight(25);
        imgView.setFitHeight(25);
        imgView.setPreserveRatio(true);
        GridPane.setHalignment(imgView, HPos.CENTER);
        GridPane.setValignment(imgView, VPos.CENTER);
        return imgView;
    }

    private void init() {
        player = true;
        input = -1;
        board = new Board();
    }

    public Image getImageY() {
        return imageY;
    }

    public Image getImageR() {
        return imageR;
    }

}
