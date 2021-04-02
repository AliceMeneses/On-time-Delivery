/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ontimedelivery.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;

import br.com.ontimedelivery.dao.ConexaoBanco;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpController implements Initializable {
    
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
    
    private EntityManager entityManager;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	entityManager = ConexaoBanco.getEntityManager();
    } 
    
    
    
    
    
}
