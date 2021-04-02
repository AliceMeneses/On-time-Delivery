package br.com.ontimedelivery.validacao;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.Node;
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
				tooltip.setText("Campo obrigatório");

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
			tooltip.setText("Campo obrigatório");

			return false;
		} else {
			removerCorDaBordaETooltip(textArea, tooltip);

			return true;
		}
	}

	public Boolean validarCEP(String cep) {

		if (cep.length() == 8) {

			try {
				System.out.println("cheguei");
				Integer.parseInt(cep);
				return true;
			} catch (Exception e) {

				return false;
			}
		}

		return false;
	}

	public Boolean validarNumero(String numero) {

		try {

			Integer.parseInt(numero);
			return true;
		} catch (Exception e) {

			return false;
		}
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

			adicionarCorDaBordaETooltip(tfEmail, new Tooltip("E-mail inválido"));
		}
		return emailValido;

	}

	public void invalidarTextFieldsDosUsuario(TextField textFieldEmail, TextField textFieldSenha) {

		adicionarCorDaBordaETooltip(textFieldEmail, new Tooltip("Usuário não existe"));
		adicionarCorDaBordaETooltip(textFieldSenha, new Tooltip("Usuário não existe"));
	}

	public void validarTextFieldsDoUsuario(TextField textFieldEmail, TextField textFieldSenha) {

		removerCorDaBordaETooltip(textFieldEmail, new Tooltip());
		removerCorDaBordaETooltip(textFieldSenha, new Tooltip());

	}
}
