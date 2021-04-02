package br.com.ontimedelivery;

import java.io.IOException;

import br.com.ontimedelivery.dao.ConexaoBanco;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	private static Scene loginScene;
	private static Scene pedidoScene;
	
	private static Stage stage;
	
	@Override
	public void start(Stage primariaStage) throws IOException {
		stage = primariaStage;
		
		stage.setOnCloseRequest(event -> ConexaoBanco.desconectar());
		
        Parent fxmlLogin = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));        
        loginScene = new Scene(fxmlLogin);
        
        Parent fxmlTeste = FXMLLoader.load(getClass().getResource("/view/Pedido.fxml"));
        pedidoScene = new Scene(fxmlTeste);
        
        stage.setScene(loginScene);
        stage.show();
		
	}
	
	public static void trocarScene(String scene) {
		
		switch (scene) {
			case "Login":
				stage.setScene(loginScene);
				break;
			case "Pedido":
				stage.setScene(pedidoScene);
				break;
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
