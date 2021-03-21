package br.com.ontimedelivery;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

private static Scene scene;

	
	@Override
	public void start(Stage stage) throws IOException {
		
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
