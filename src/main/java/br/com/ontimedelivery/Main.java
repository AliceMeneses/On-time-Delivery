package br.com.ontimedelivery;

import java.io.IOException;

import br.com.ontimedelivery.dao.ConexaoBanco;
import br.com.ontimedelivery.model.Usuario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	private static Scene loginScene;
	private static Scene pedidoScene;
	private static Scene signUpScene;
	
	private static Stage stage;
	
	@Override
	public void start(Stage primariaStage) throws IOException {
		stage = primariaStage;
		
		stage.setOnCloseRequest(event -> ConexaoBanco.desconectar());
		
		Parent fxmlLogin = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));               
        loginScene = new Scene(fxmlLogin);
        
        Parent fxmlSignUp = FXMLLoader.load(getClass().getResource("/view/SignUp.fxml"));               
        signUpScene = new Scene(fxmlSignUp);
        
        Parent fxmlPedido = FXMLLoader.load(getClass().getResource("/view/Pedido.fxml"));       
        pedidoScene = new Scene(fxmlPedido);
        
        stage.setScene(loginScene);
        stage.show();
		
	}
	
	public static void mudarParaPedidoScene(Usuario usuario) {

		pedidoScene.setUserData(usuario);
		stage.setScene(pedidoScene);		
	}
	
	public static void mudarParaLoginScene() {
		stage.setScene(loginScene);
	}
	
	public static void mudarParaSignUpScene() {
		stage.setScene(signUpScene);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
