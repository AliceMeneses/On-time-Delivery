package br.com.ontimedelivery.validacao;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

public class ValidarDados {

	public void adicionarCorDaBordaETooltip(Node node, Tooltip tooltip) {

		node.setStyle("-fx-border-color: red;");
		tooltip.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0); "
				+ "-fx-font-weight: bold;" + " -fx-padding: 5;" + " -fx-border-width:1; "
				+ "-fx-background-color: #FBEFEF; " + "-fx-text-fill: #cc0033;" + " -fx-border-color:#cc0033;");

		Tooltip.install(node, tooltip);
	}

	public void removerCorDaBordaETooltip(Node node, Tooltip tooltip) {

		node.setStyle(null);
		Tooltip.uninstall(node, tooltip);

	}

	public Boolean procurarTextFieldVazio(List<TextField> textFields) {

		List<TextField> textFieldVazio = new ArrayList<>();

		for (TextField textField : textFields) {

			Tooltip tooltip = new Tooltip();

			Boolean textFieldEstaVazio = textField.getText().trim().equals("");

			if (textFieldEstaVazio) {
				adicionarCorDaBordaETooltip(textField, tooltip);
				tooltip.setText("Campo obrigat�rio");

				textFieldVazio.add(textField);
			} else {
				removerCorDaBordaETooltip(textField, tooltip);
			}

		}

		return textFieldVazio.isEmpty();
	}

	public Boolean procurarTextAreaVazio(TextArea textArea) {

		Tooltip tooltip = new Tooltip();

		Boolean textAreaEstaVazio = textArea.getText().trim().equals("");

		if (textAreaEstaVazio) {

			adicionarCorDaBordaETooltip(textArea, tooltip);
			tooltip.setText("Campo obrigat�rio");

			return false;
		} else {
			removerCorDaBordaETooltip(textArea, tooltip);

			return true;
		}
	}

	public Boolean validarCEP(TextField tfCep) {

		String cep = tfCep.getText().trim();
		Tooltip tooltip = new Tooltip();

		if (cep.length() == 8) {

			
			try {

				Integer.parseInt(cep);
				
				removerCorDaBordaETooltip(tfCep, tooltip);
				
				return true;
			} catch (NumberFormatException e) {
				
				tooltip.setText("CEP inv�lido");
				adicionarCorDaBordaETooltip(tfCep, tooltip);
				return false;
			}
		} else {
			
			tooltip.setText("CEP inv�lido");
			adicionarCorDaBordaETooltip(tfCep, tooltip);
			return false;
		}

	}

	public Boolean validarNumero(TextField tfnumero) {

		Tooltip tooltip = new Tooltip();

		try {

			Integer.parseInt(tfnumero.getText());
			
			removerCorDaBordaETooltip(tfnumero, tooltip);
			return true;
		} catch (NumberFormatException e) {
			
			tooltip.setText("N�mero inv�lido");
			adicionarCorDaBordaETooltip(tfnumero, tooltip);
			return false;
		}
	}

	public Boolean validarTelefone(TextField tfTelefone) {
		String telefone = tfTelefone.getText();

		String expressao = "^\\([0-9]{2}\\) [0-9]{4,5}-[0-9]{4}$";
		Pattern pattern = Pattern.compile(expressao);
		Matcher matcher = pattern.matcher(telefone);

		Boolean telefoneValido = matcher.matches();

		if (telefoneValido) {

			removerCorDaBordaETooltip(tfTelefone, new Tooltip());
		} else {

			adicionarCorDaBordaETooltip(tfTelefone, new Tooltip("Telefone inv�lido"));
		}

		return telefoneValido;
	}

	public Boolean validarEmail(TextField tfEmail) {

		String email = tfEmail.getText();
		String expressao = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,3}$";
		Pattern pattern = Pattern.compile(expressao, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);

		Boolean emailValido = matcher.matches();

		if (emailValido) {

			removerCorDaBordaETooltip(tfEmail, new Tooltip());
		} else {

			adicionarCorDaBordaETooltip(tfEmail, new Tooltip("E-mail inv�lido"));
		}
		return emailValido;

	}

	public void invalidarTextFieldsDosUsuario(TextField textFieldEmail, TextField textFieldSenha) {

		adicionarCorDaBordaETooltip(textFieldEmail, new Tooltip("Usu�rio n�o existe"));
		adicionarCorDaBordaETooltip(textFieldSenha, new Tooltip("Usu�rio n�o existe"));
	}

	public void validarTextFieldsDoUsuario(TextField textFieldEmail, TextField textFieldSenha) {

		removerCorDaBordaETooltip(textFieldEmail, new Tooltip());
		removerCorDaBordaETooltip(textFieldSenha, new Tooltip());

	}

	public void validarCadastroDoUsuario(List<TextField> textFields) {

		for (TextField textField : textFields) {
			
			removerCorDaBordaETooltip(textField, new Tooltip());
		}
	}

	public void invalidarCadastroDoUsuario(List<TextField> textFields) {
		for (TextField textField : textFields) {

			Tooltip tooltip = new Tooltip("Usu�rio j� existe");

			adicionarCorDaBordaETooltip(textField, tooltip);
		}
	}

	public Boolean validarSenhaDeCadastro(PasswordField psSenha, PasswordField psSenhaRepetida) {
		
		String senha = psSenha.getText();
		String senhaRepetida = psSenhaRepetida.getText();
		Tooltip tooltip = new Tooltip();

		if(senha.equals(senhaRepetida)) {

			removerCorDaBordaETooltip(psSenha, tooltip);
			removerCorDaBordaETooltip(psSenhaRepetida, tooltip);		
			
			return true;			
		} else {
			
			tooltip.setText("Senhas n�o coincidem");
			adicionarCorDaBordaETooltip(psSenha, tooltip);
			adicionarCorDaBordaETooltip(psSenhaRepetida, tooltip);

			return false;
		}
	}
}
