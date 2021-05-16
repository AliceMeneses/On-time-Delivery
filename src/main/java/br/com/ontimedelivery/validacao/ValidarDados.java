package br.com.ontimedelivery.validacao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.ontimedelivery.util.EstiloDaValidacao;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

public class ValidarDados {



	public static Boolean procurarTextFieldVazio(List<TextField> textFields) {

		List<TextField> textFieldVazio = new ArrayList<>();

		for (TextField textField : textFields) {

			Tooltip tooltip = new Tooltip();

			Boolean textFieldEstaVazio = textField.getText().trim().equals("");

			if (textFieldEstaVazio) {
				
				EstiloDaValidacao.adicionarCorDaBordaETooltip(textField, tooltip);
				tooltip.setText("Campo obrigatório");

				textFieldVazio.add(textField);
			} else {
				
				EstiloDaValidacao.removerCorDaBordaETooltip(textField, tooltip);
			}

		} 

		return !textFieldVazio.isEmpty();
	}

	public static Boolean procurarTextAreaVazio(TextArea textArea) {

		Tooltip tooltip = new Tooltip();

		Boolean textAreaEstaVazio = textArea.getText().trim().equals("");

		if (textAreaEstaVazio) {

			EstiloDaValidacao.adicionarCorDaBordaETooltip(textArea, tooltip);
			tooltip.setText("Campo obrigatório");

			return true;
		} else {
			EstiloDaValidacao.removerCorDaBordaETooltip(textArea, tooltip);

			return false;
		}
	}
	
	public static Boolean validarDataAgendadaEntrega(DatePicker datePicker) {
		
		Tooltip tooltip = new Tooltip();
		
		LocalDate dataAgendadaEntrega = datePicker.getValue();
		
		if(dataAgendadaEntrega == null) {
			
			tooltip.setText("Campo obrigatório");
			
			EstiloDaValidacao.adicionarCorDaBordaETooltip(datePicker, tooltip);
			
			return false;			
		}
		
		if(dataAgendadaEntrega.isBefore(LocalDate.now())) {
			
			tooltip.setText("Data inválida");
			
			EstiloDaValidacao.adicionarCorDaBordaETooltip(datePicker, tooltip);
			return false;
		}
		
		EstiloDaValidacao.removerCorDaBordaETooltip(datePicker, tooltip);
		
		return true;
	}

	public static Boolean validarCEP(TextField tfCep) {

		String cep = tfCep.getText().trim();
		Tooltip tooltip = new Tooltip();

		if (cep.length() == 8) {

			try {
				
				Integer.parseInt(cep);
				
				EstiloDaValidacao.removerCorDaBordaETooltip(tfCep, tooltip);
				
				return true;
			} catch (NumberFormatException e) {
				
				tooltip.setText("CEP inválido");
				EstiloDaValidacao.adicionarCorDaBordaETooltip(tfCep, tooltip);
				
				return false;
			}
		} else {
			
			tooltip.setText("CEP inválido");
			EstiloDaValidacao.adicionarCorDaBordaETooltip(tfCep, tooltip);
			
			return false;
		}

	}

	public static Boolean validarNumero(TextField tfnumero) {

		Tooltip tooltip = new Tooltip();

		try {

			Integer.parseInt(tfnumero.getText());
			
			EstiloDaValidacao.removerCorDaBordaETooltip(tfnumero, tooltip);
			
			return true;
		} catch (NumberFormatException e) {
			
			tooltip.setText("Número inválido");
			EstiloDaValidacao.adicionarCorDaBordaETooltip(tfnumero, tooltip);
			
			return false;
		}
	}

	public static Boolean validarTelefone(TextField tfTelefone) {
		String telefone = tfTelefone.getText();

		String expressao = "^\\([0-9]{2}\\) [0-9]{4,5}-[0-9]{4}$";
		Pattern pattern = Pattern.compile(expressao);
		Matcher matcher = pattern.matcher(telefone);

		Boolean telefoneValido = matcher.matches();

		if (telefoneValido) {

			EstiloDaValidacao.removerCorDaBordaETooltip(tfTelefone, new Tooltip());
		} else {

			EstiloDaValidacao.adicionarCorDaBordaETooltip(tfTelefone, new Tooltip("Telefone inválido"));
		}

		return telefoneValido;
	}

	public static Boolean validarEmail(TextField tfEmail) {

		String email = tfEmail.getText();
		String expressao = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,3}$";
		Pattern pattern = Pattern.compile(expressao, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);

		Boolean emailValido = matcher.matches();

		if (emailValido) {

			EstiloDaValidacao.removerCorDaBordaETooltip(tfEmail, new Tooltip());
		} else {

			EstiloDaValidacao.adicionarCorDaBordaETooltip(tfEmail, new Tooltip("E-mail inválido"));
		}
		
		return emailValido;
	}

	public static void invalidarTextFieldsDosUsuario(TextField textFieldEmail, TextField textFieldSenha) {

		EstiloDaValidacao.adicionarCorDaBordaETooltip(textFieldEmail, new Tooltip("Usuário não existe"));
		EstiloDaValidacao.adicionarCorDaBordaETooltip(textFieldSenha, new Tooltip("Usuário não existe"));
	}

	public static void validarTextFieldsDoUsuario(TextField textFieldEmail, TextField textFieldSenha) {

		EstiloDaValidacao.removerCorDaBordaETooltip(textFieldEmail, new Tooltip());
		EstiloDaValidacao.removerCorDaBordaETooltip(textFieldSenha, new Tooltip());
	}

	public static void validarCadastroDoUsuario(List<TextField> textFields) {

		for (TextField textField : textFields) {
			
			EstiloDaValidacao.removerCorDaBordaETooltip(textField, new Tooltip());
		}
	}

	public static void invalidarCadastroDoUsuario(List<TextField> textFields) {
		for (TextField textField : textFields) {

			Tooltip tooltip = new Tooltip("Usuário já existe");

			EstiloDaValidacao.adicionarCorDaBordaETooltip(textField, tooltip);
		}
	}

	public static Boolean validarSenhaDeCadastro(PasswordField psSenha, PasswordField psSenhaRepetida) {
		
		String senha = psSenha.getText();
		String senhaRepetida = psSenhaRepetida.getText();
		Tooltip tooltip = new Tooltip();

		if(senha.equals(senhaRepetida)) {

			EstiloDaValidacao.removerCorDaBordaETooltip(psSenha, tooltip);
			EstiloDaValidacao.removerCorDaBordaETooltip(psSenhaRepetida, tooltip);	
			
			return true;			
		} else {
			
			tooltip.setText("Senhas não coincidem");
			EstiloDaValidacao.adicionarCorDaBordaETooltip(psSenha, tooltip);
			EstiloDaValidacao.adicionarCorDaBordaETooltip(psSenhaRepetida, tooltip);
			
			return false;
		}
	}
}
