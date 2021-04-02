package br.com.ontimedelivery.validacao;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

public class ValidarCampos {
	
	public void adicionarCorDaBordaETooltip(Node node, Tooltip tooltip) {
		
		node.setStyle("-fx-border-color: red;");
		tooltip.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0); "
				+ "-fx-font-weight: bold;"
				+ " -fx-padding: 5;"
				+ " -fx-border-width:1; "
				+ "-fx-background-color: #FBEFEF; "
				+ "-fx-text-fill: #cc0033;"
				+ " -fx-border-color:#cc0033;");
		
		Tooltip.install(node, tooltip);
	}
	
	public void removerCorDaBordaETooltip(Node node, Tooltip tooltip) {
		
		node.setStyle(null);
		Tooltip.uninstall(node, tooltip);

	}
	
	public boolean procurarTextFieldVazio(List<TextField> textFields) {
		
		List<TextField> textFieldVazio = new ArrayList<>();
		
		for(TextField textField : textFields) {
		
			Tooltip tooltip = new Tooltip();
			
			boolean campoVazio = textField.getText().trim().equals("");

			if(campoVazio) {
				adicionarCorDaBordaETooltip(textField, tooltip);
				tooltip.setText("Campo obrigatório");
				
				textFieldVazio.add(textField);
			} else {
				removerCorDaBordaETooltip(textField, tooltip);
			}	
			
		}
		
		return textFieldVazio.isEmpty();
	}
	
	public boolean procurarRadioButtonsVazios(List<RadioButton> radioButtons) {
		
		Tooltip tooltip = new Tooltip();	

		boolean radioButtonSelecionado = false;
		for (RadioButton radioButton : radioButtons) {
			
			if(radioButton.isSelected()) {
				radioButtonSelecionado = true;
				for(int i = 0; i < radioButtons.size(); i++) {

					removerCorDaBordaETooltip(radioButtons.get(i), tooltip);

				}
				
				return false;
			}
			
			if(!radioButtonSelecionado) {
				for(int i = 0; i < radioButtons.size(); i++) {

					adicionarCorDaBordaETooltip(radioButtons.get(i), tooltip);
				}
								
			}			
		}
		
		return true;
					
	}
	
	public boolean validarCEP(String cep) {
        
		if (cep.length() == 8) {
            
			try {
            	
            	Integer.parseInt(cep);
            	return true;
            } catch(Exception e) {
            	
            	return false;
            }
        }
        
        return false;
	}
	
	public boolean validarNumero(String numero) {
		
		try {
	    	
	    	Integer.parseInt(numero);
	    	return true;
	    } catch(Exception e) {
	    	
	    	return false;
	    }
	}
	
	public boolean validarEmail(TextField tfEmail) {
		
		String email = tfEmail.getText();
		String expressao = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,3}$";
        Pattern pattern = Pattern.compile(expressao, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        
        boolean emailValido = matcher.matches();
        
        if(emailValido) {
        	
        	removerCorDaBordaETooltip(tfEmail, new Tooltip());
        } else {
        	
        	adicionarCorDaBordaETooltip(tfEmail, new Tooltip("E-mail inválido"));
        }
        return emailValido;
        
	}

	public void invalidarCamposDosUsuario(TextField textFieldEmail, TextField textFieldSenha) {
		adicionarCorDaBordaETooltip(textFieldEmail, new Tooltip("Usuário não existe"));
		adicionarCorDaBordaETooltip(textFieldSenha, new Tooltip("Usuário não existe"));
	}

	public void validarCamposDoUsuario(TextField textFieldEmail, TextField textFieldSenha) {
		removerCorDaBordaETooltip(textFieldEmail, new Tooltip());
		removerCorDaBordaETooltip(textFieldSenha, new Tooltip());
		
	}
}
