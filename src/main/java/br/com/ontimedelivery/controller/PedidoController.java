/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ontimedelivery.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class PedidoController implements Initializable {

    @FXML
    private RadioButton radioBtnCarro;

    @FXML
    private ToggleGroup veiculo;

    @FXML
    private RadioButton radioBtnMoto;

    @FXML
    private RadioButton radioBtnVan;

    @FXML
    private RadioButton radioBtn25kg;

    @FXML
    private ToggleGroup peso;

    @FXML
    private RadioButton radioBtn50kg;

    @FXML
    private RadioButton radioBtn100kg;

    @FXML
    private RadioButton radioBtn150kg;

    @FXML
    private TextArea dsPedido;

    @FXML
    private CheckBox checkServicoCarregamento;

    @FXML
    private TextField inputCepRetirada;

    @FXML
    private TextField inputRuaRetirada;

    @FXML
    private TextField inputBairroRetirada;

    @FXML
    private TextField inputCidadeRetirada;

    @FXML
    private TextField inputUfRetirada;

    @FXML
    private TextField inputComplementoRetirada;

    @FXML
    private TextField inputCepEntrega;

    @FXML
    private TextField inputRuaEntrega;

    @FXML
    private TextField inputUfEntrega;

    @FXML
    private TextField inputComplementoEntrega;

    @FXML
    private Button btnFazerPedido;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 

}
