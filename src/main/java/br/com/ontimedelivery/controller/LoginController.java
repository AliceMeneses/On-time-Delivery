package br.com.ontimedelivery.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.swing.text.StyledEditorKit.BoldAction;

import br.com.ontimedelivery.dao.ConexaoBanco;
import br.com.ontimedelivery.dao.UsuarioDAO;
import br.com.ontimedelivery.model.Usuario;
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

	private EntityManager entityManager;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		entityManager = ConexaoBanco.getEntityManager();

	}

	@FXML
	private void login(ActionEvent event) {

		Usuario usuario;

		usuario = new Usuario();
		usuario.setEmail(tfEmail.getText());
		usuario.setSenha(tfSenha.getText());

		boolean dadosValidos = validarDados(usuario);

		if(dadosValidos) {
			login(usuario);
		}

	}

	private boolean validarDados(Usuario usuario) {

		boolean senhaContemEspaco = usuario.getSenha().contains(" ");

		if(emailInvalido(usuario.getEmail())) {
			System.out.println("E-mail inválido");

			return false;
		}

		if (senhaContemEspaco) {
			System.out.println("Senha têm espaços em branco");
			return false;
		}

		return true;

	}
	
	private boolean emailInvalido(String email) {
		 String expressao = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,3}$";
         Pattern pattern = Pattern.compile(expressao, Pattern.CASE_INSENSITIVE);
         Matcher matcher = pattern.matcher(email);
         
         return !matcher.matches();
         
	}

	private void login(Usuario usuario) {
		UsuarioDAO usuarioDAO;

		usuarioDAO = new UsuarioDAO(entityManager);

		boolean existeUsuario = usuarioDAO.usuarioCadastrado(usuario);

		if (existeUsuario) {
			System.out.println("Usuario existe");
		} else {
			System.out.println("Usuario não existe");
		}
	}

}
