package br.com.ontimedelivery.controller;

import br.com.ontimedelivery.MainFX;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class MensagemErroController {
	
	@FXML
	private Label lbMensagemErro;
	
	@FXML
	private Button btnOkay;

	@FXML
	private void fechar(ActionEvent event) {	
		MainFX.fecharMensagemErro();
	}
	
	public void setMensagemErro(String mensagemErro) {
		
		lbMensagemErro.setText(mensagemErro);
	}
}
