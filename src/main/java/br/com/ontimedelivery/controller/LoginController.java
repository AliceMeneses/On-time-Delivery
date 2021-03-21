package br.com.ontimedelivery.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController implements Initializable{
	@FXML
    private TextField tfUsuario;
    @FXML
    private TextField tfSenha;
    @FXML
    private Button btnLogin;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}  

    @FXML
    private void login(ActionEvent event) {
        

    }
    
}
