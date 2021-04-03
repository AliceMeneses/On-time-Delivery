/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ontimedelivery.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.ontimedelivery.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class SucessoPedidoController implements Initializable {

    @FXML
    private Button btnPedidoRoute;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
	public void fazerNovoPedido(ActionEvent event) {
    	
    	Main.mudarParaPedidoScene(null);
    }
}
