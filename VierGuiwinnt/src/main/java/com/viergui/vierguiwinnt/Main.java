package com.viergui.vierguiwinnt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Stage stage = null;

    public static void main(String[] args) {
        launch(args);
    }

    public static void setScene(Scene scene){
        Main.stage.setScene(scene);
    }

    public void start(Stage stage) throws IOException {

        Main.stage = stage;

        Parent root = FXMLLoader.load(getClass().getResource("splashScreen.fxml"));
        Scene scene = new Scene(root);
        Main.stage.setScene(scene);
        Main.stage.show();
    }
}
