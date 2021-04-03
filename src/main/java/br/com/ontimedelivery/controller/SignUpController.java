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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpController implements Initializable {

	@FXML
	private TextField tfSingUpNome;

	@FXML
	private Button btnSingUp;

	@FXML
	private TextField tfSingUpEmail;

	@FXML
	private TextField tfSingUpTel;

	@FXML
	private PasswordField pfSingUpSenha;

	@FXML
	private PasswordField pfSingUpReSenha;

	@FXML
	private Button btnLoginRote;
	
	private EntityManager entityManager;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		entityManager = ConexaoBanco.getEntityManager();
	}

	@FXML
	public void signUp(ActionEvent event) {

		Boolean dadosValidos = validarDados();

		if (dadosValidos) {
			UsuarioDAO usuarioDAO = new UsuarioDAO(entityManager);

			Usuario usuario = new Usuario(tfSingUpNome.getText(), tfSingUpTel.getText(), tfSingUpEmail.getText(),
					pfSingUpSenha.getText());

			ValidarDados validarDados = new ValidarDados();

			Boolean usuarioNaoExiste = !(usuarioDAO.buscarUsuarioCadastrado(usuario));

			if (usuarioNaoExiste) {

				usuarioDAO.inserir(usuario);

				validarDados.validarCadastroDoUsuario(
						Arrays.asList(tfSingUpNome, tfSingUpEmail, tfSingUpTel, pfSingUpSenha, pfSingUpReSenha));

				Main.mudarParaPedidoScene(usuario);
			} else {

				validarDados.invalidarCadastroDoUsuario(
						Arrays.asList(tfSingUpNome, tfSingUpEmail, tfSingUpTel, pfSingUpSenha, pfSingUpReSenha));
			}
		}

	}
	
	@FXML
	public void voltarParaLogin(ActionEvent event) {
		Main.mudarParaLoginScene();
	}

	private boolean validarDados() {

		ValidarDados validarDados = new ValidarDados();
		boolean textFieldsPreenchidos, emailValido, telefoneValido, senhasIguais;

		emailValido = telefoneValido = senhasIguais = false;

		textFieldsPreenchidos = validarDados.procurarTextFieldVazio(
				Arrays.asList(tfSingUpNome, tfSingUpEmail, tfSingUpTel, pfSingUpSenha, pfSingUpReSenha));

		if (textFieldsPreenchidos) {

			emailValido = validarDados.validarEmail(tfSingUpEmail);
			telefoneValido = validarDados.validarTelefone(tfSingUpTel);
			senhasIguais = validarDados.validarSenhaDeCadastro(pfSingUpSenha, pfSingUpReSenha);
		}

		return emailValido && textFieldsPreenchidos && telefoneValido && senhasIguais;
	}

}
