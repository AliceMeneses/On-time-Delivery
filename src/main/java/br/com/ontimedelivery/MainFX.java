package br.com.ontimedelivery;

import java.io.IOException;

import br.com.ontimedelivery.controller.MensagemErroController;
import br.com.ontimedelivery.model.Usuario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFX extends Application{

	private static Scene loginScene;
	private static Scene pedidoScene;
	private static Scene signUpScene;
	private static Scene sucessoPedidoScene;
	private static Scene mensagemErroScene;
	private static Stage stageMensagemErro;
	
	private static MensagemErroController mensagemErroController;
	
	private static Stage stage;
	
	@Override
	public void start(Stage primariaStage) throws IOException {
		stage = primariaStage;
		
		Parent fxmlLogin = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));               
        loginScene = new Scene(fxmlLogin);
        
        Parent fxmlSignUp = FXMLLoader.load(getClass().getResource("/view/SignUp.fxml"));               
        signUpScene = new Scene(fxmlSignUp);
        
        Parent fxmlPedido = FXMLLoader.load(getClass().getResource("/view/Pedido.fxml"));       
        pedidoScene = new Scene(fxmlPedido);
        
        Parent fxmlSucessoPedido = FXMLLoader.load(getClass().getResource("/view/SucessoPedido.fxml"));       
        sucessoPedidoScene = new Scene(fxmlSucessoPedido);
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MensagemErro.fxml"));
        Parent fxmlMensagemErro = fxmlLoader.load();
        mensagemErroController = fxmlLoader.<MensagemErroController>getController();
        
        mensagemErroScene = new Scene(fxmlMensagemErro);
        
        stage.setScene(loginScene);
        stage.show();
		
	}
	
	public static void mudarParaPedidoScene(Usuario usuario) {
		if(usuario != null) {
			pedidoScene.setUserData(usuario);
		}
		stage.setScene(pedidoScene);		
	}
	
	public static void mudarParaLoginScene() {
		stage.setScene(loginScene);
	}
	
	public static void mudarParaSignUpScene() {
		stage.setScene(signUpScene);
	}
	
	public static void mudarParaSucessoPedidoScene() {
		
		stage.setScene(sucessoPedidoScene);
	}
	
	public static void mostrarMensagemErro(String mensagemErro) {
		
		stageMensagemErro = new Stage();
		
		mensagemErroController.setMensagemErro(mensagemErro);
		stageMensagemErro.setScene(mensagemErroScene);		
		stageMensagemErro.show();
	}
	
	public static void fecharMensagemErro() {
		
		stageMensagemErro.close();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
