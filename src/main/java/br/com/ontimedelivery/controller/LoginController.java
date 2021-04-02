package br.com.ontimedelivery.controller;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;

import br.com.ontimedelivery.Main;
import br.com.ontimedelivery.dao.ConexaoBanco;
import br.com.ontimedelivery.dao.UsuarioDAO;
import br.com.ontimedelivery.model.Usuario;
import br.com.ontimedelivery.validacao.ValidarCampos;
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
	private void login(ActionEvent event) {	

		boolean dadosValidos = validarDados();

		if(dadosValidos) {
			
			Usuario usuario = new Usuario();
			usuario.setEmail(tfEmail.getText());
			usuario.setSenha(tfSenha.getText());
			
			login(usuario);
		}
		
	}

	private boolean validarDados() {

		ValidarCampos validarCampos = new ValidarCampos();
		boolean camposPreenchidos, emailValido = false;
		
		camposPreenchidos = validarCampos.procurarTextFieldVazio(Arrays.asList(tfEmail, tfSenha));

		if(camposPreenchidos) {
			emailValido = validarCampos.validarEmail(tfEmail);		
		}
		
		return emailValido && camposPreenchidos;
	}

	private void login(Usuario usuario) {
		UsuarioDAO usuarioDAO;
		ValidarCampos validarCampos = new ValidarCampos();
		
		usuarioDAO = new UsuarioDAO(entityManager);

		boolean existeUsuario = usuarioDAO.usuarioCadastrado(usuario);

		if (existeUsuario) {
			validarCampos.validarCamposDoUsuario(tfEmail, tfSenha);
			Main.trocarScene("Pedido");
		} else {
			validarCampos.invalidarCamposDosUsuario(tfEmail, tfSenha);
		}
	}

}
