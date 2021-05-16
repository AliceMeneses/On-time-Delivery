package br.com.ontimedelivery.controller;

import java.util.Arrays;

import javax.persistence.EntityManager;

import br.com.ontimedelivery.Main;
import br.com.ontimedelivery.dao.UsuarioDAO;
import br.com.ontimedelivery.model.Usuario;
import br.com.ontimedelivery.util.JPAUtil;
import br.com.ontimedelivery.validacao.ValidarDados;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController {
	@FXML
	private TextField tfEmail;
	@FXML
	private TextField tfSenha;
	@FXML
	private Button btnLogin;
    @FXML
    private Button btnSingUpRote;
    
    private EntityManager entityManager;
	
	@FXML
	private void signUp(ActionEvent event) {
		
		limparCampos();
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
			entityManager = JPAUtil.getEntityManager();
						
			usuarioDAO = new UsuarioDAO(entityManager);

			usuario = usuarioDAO.buscarUsuario(usuario);

			if (usuario != null) {
				ValidarDados.validarTextFieldsDoUsuario(tfEmail, tfSenha);
				limparCampos();
				Main.mudarParaPedidoScene(usuario);
			} else {
				ValidarDados.invalidarTextFieldsDosUsuario(tfEmail, tfSenha);
			}
			
			entityManager.close();
		}		
	}
	
	private void limparCampos() {
		tfEmail.clear();
		tfSenha.clear();
	}

	private boolean validarDados() {

		boolean textFieldsPreenchidos, emailValido = false;
		
		textFieldsPreenchidos = !(ValidarDados.procurarTextFieldVazio(Arrays.asList(tfEmail, tfSenha)));

		if(textFieldsPreenchidos) {
			emailValido = ValidarDados.validarEmail(tfEmail);		
		}
		
		return emailValido && textFieldsPreenchidos;
	}

}
