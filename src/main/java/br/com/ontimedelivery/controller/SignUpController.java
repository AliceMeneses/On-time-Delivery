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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpController {

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

	@FXML
	public void signUp(ActionEvent event) {

		Boolean dadosValidos = validarDados();

		if (dadosValidos) {
			
			EntityManager entityManager = JPAUtil.getEntityManager();
			
			UsuarioDAO usuarioDAO = new UsuarioDAO(entityManager);

			Usuario usuario = new Usuario(tfSingUpNome.getText(), tfSingUpTel.getText(), tfSingUpEmail.getText(),
					pfSingUpSenha.getText());

			Boolean usuarioNaoExiste = !(usuarioDAO.buscarUsuarioCadastrado(usuario));

			if (usuarioNaoExiste) {
				
				entityManager.getTransaction().begin();
				
				usuarioDAO.inserir(usuario);
				
				entityManager.getTransaction().commit();

				ValidarDados.validarCadastroDoUsuario(
						Arrays.asList(tfSingUpNome, tfSingUpEmail, tfSingUpTel, pfSingUpSenha, pfSingUpReSenha));

				limparCampos();
				
				Main.mudarParaPedidoScene(usuario);
			} else {

				ValidarDados.invalidarCadastroDoUsuario(
						Arrays.asList(tfSingUpNome, tfSingUpEmail, tfSingUpTel, pfSingUpSenha, pfSingUpReSenha));
			}

			entityManager.close();

		}

	}
	
	@FXML
	public void voltarParaLogin(ActionEvent event) {
		
		limparCampos();
		
		Main.mudarParaLoginScene();
	}

	private boolean validarDados() {

		boolean textFieldsPreenchidos, emailValido, telefoneValido, senhasIguais;

		emailValido = telefoneValido = senhasIguais = false;

		textFieldsPreenchidos = !(ValidarDados.procurarTextFieldVazio(
				Arrays.asList(tfSingUpNome, tfSingUpEmail, tfSingUpTel, pfSingUpSenha, pfSingUpReSenha)));

		if (textFieldsPreenchidos) {

			emailValido = ValidarDados.validarEmail(tfSingUpEmail);
			telefoneValido = ValidarDados.validarTelefone(tfSingUpTel);
			senhasIguais = ValidarDados.validarSenhaDeCadastro(pfSingUpSenha, pfSingUpReSenha);
		}

		return emailValido && textFieldsPreenchidos && telefoneValido && senhasIguais;
	}
	
	private void limparCampos() {
		
		pfSingUpReSenha.clear();
		pfSingUpSenha.clear();
		tfSingUpEmail.clear();
		tfSingUpNome.clear();
		tfSingUpTel.clear();
	}

}
