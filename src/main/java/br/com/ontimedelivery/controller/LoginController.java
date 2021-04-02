package br.com.ontimedelivery.controller;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;

import br.com.ontimedelivery.Main;
import br.com.ontimedelivery.dao.ConexaoBanco;
import br.com.ontimedelivery.dao.UsuarioDAO;
import br.com.ontimedelivery.model.Usuario;
import br.com.ontimedelivery.validacao.ValidarDados;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController implements Initializable {
	@FXML
	private TextField tfEmail;
	@FXML
	private TextField tfSenha;
	@FXML
	private Button btnLogin;
    @FXML
    private Button btnSingUpRote;
        
	private EntityManager entityManager;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		entityManager = ConexaoBanco.getEntityManager();

	}
	
	@FXML
	private void signUp(ActionEvent event) {
		Main.mudarParaSignUpScene();
	}

	@FXML
	private void login(ActionEvent event) {	

		boolean dadosValidos = validarDados();

		if(dadosValidos) {
			
			Usuario usuario = new Usuario();
			usuario.setEmail(tfEmail.getText());
			usuario.setSenha(tfSenha.getText());
			
			UsuarioDAO usuarioDAO;
			ValidarDados validarDados = new ValidarDados();
			
			usuarioDAO = new UsuarioDAO(entityManager);

			usuario = usuarioDAO.buscarUsuario(usuario);

			if (usuario != null) {
				validarDados.validarTextFieldsDoUsuario(tfEmail, tfSenha);
				Main.mudarParaPedidoScene(usuario);
			} else {
				validarDados.invalidarTextFieldsDosUsuario(tfEmail, tfSenha);
			}
		}
		
	}

	private boolean validarDados() {

		ValidarDados validarDados = new ValidarDados();
		boolean textFieldsPreenchidos, emailValido = false;
		
		textFieldsPreenchidos = validarDados.procurarTextFieldVazio(Arrays.asList(tfEmail, tfSenha));

		if(textFieldsPreenchidos) {
			emailValido = validarDados.validarEmail(tfEmail);		
		}
		
		return emailValido && textFieldsPreenchidos;
	}

}
