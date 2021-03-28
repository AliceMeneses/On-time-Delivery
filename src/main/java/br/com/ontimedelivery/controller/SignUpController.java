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
import javafx.scene.control.PasswordField;


/**
 * FXML Controller class
 *
 * @author dell
 */
public class SignUpController implements Initializable {
    
     @FXML
    private PasswordField inputSingUpNome;

    @FXML
    private Button btnSingUp;

    @FXML
    private PasswordField inputSingUpEmail;

    @FXML
    private PasswordField inputSingUpTel;

    @FXML
    private PasswordField inputSingUpSenha;

    @FXML
    private PasswordField inputSingUpReSenha;

    @FXML
    private Button btnLoginRote;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
