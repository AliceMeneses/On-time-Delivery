package br.com.ontimedelivery.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;

import br.com.ontimedelivery.buscardordeendereco.BuscaEndereco;
import br.com.ontimedelivery.dao.ConexaoBanco;
import br.com.ontimedelivery.dao.PedidoDAO;
import br.com.ontimedelivery.model.Endereco;
import br.com.ontimedelivery.model.Pedido;
import br.com.ontimedelivery.model.PesoPedido;
import br.com.ontimedelivery.model.TipoVeiculo;
import br.com.ontimedelivery.validacao.ValidarCampos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class PedidoController implements Initializable {

	private EntityManager entityManager;

	@FXML
	private TextField inputCepRetirada;

	@FXML
	private TextField inputLogradouroRetirada;

	@FXML 
	private TextField inputComplementoRetirada;
	
	@FXML
	private TextField inputNumeroRetirada;

	@FXML
	private TextField inputBairroRetirada;

	@FXML
	private TextField inputLocalidadeRetirada;
	
	@FXML
	private TextField inputUfRetirada;
	
	@FXML
	private TextField tfEnderecoDeEntregaCEP;

	@FXML
	private TextField inputLogradouroEntrega;

	@FXML
	private TextField inputComplementoEntrega;

	@FXML
	private TextField inputNumeroEntrega;
	
	@FXML
	private TextField inputBairroEntrega;

	@FXML
	private TextField inputLocalidadeEntrega;
	
	@FXML
	private TextField inputUfEntrega;
	
	@FXML
    private TextArea dsPedido;

	@FXML
	private CheckBox checkServicoCarregamento;
	
	@FXML
	private RadioButton radioBtnCarro, radioBtnMoto, radioBtnVan;
	
    @FXML
    private ToggleGroup veiculo;
    
    @FXML
    private ToggleGroup peso;
    
	@FXML
	private RadioButton radioBtn25kg, radioBtn50kg, radioBtn100kg, radioBtn150kg, radioBtn200kg;
	
    @FXML
    private Button btnFazerPedido;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		entityManager = ConexaoBanco.getEntityManager();
			
		tfEnderecoDeEntregaCEP.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
			
			boolean inputsEnderecoEntregaVazios = inputLogradouroEntrega.getText().isEmpty() && inputBairroEntrega.getText().isEmpty()
					&& inputLocalidadeEntrega.getText().isEmpty() &&	inputUfEntrega.getText().isEmpty();
			
			boolean inputCepNaoPreenchido = !tfEnderecoDeEntregaCEP.getText().isEmpty();
			
			if (!isNowFocused && inputsEnderecoEntregaVazios && inputCepNaoPreenchido) {
		    	
		    	Endereco enderecoEntrega = buscaEndereco(tfEnderecoDeEntregaCEP.getText());
		    	
		    	if(enderecoEntrega != null) {
		    		setInputsEnderecoEntrega(enderecoEntrega);		    
		    	}
		    }
		});		
			
		inputCepRetirada.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
			
			boolean camposEnderecoRetiradaVazios = inputLogradouroRetirada.getText().isEmpty() && inputBairroRetirada.getText().isEmpty()
					&& inputLocalidadeRetirada.getText().isEmpty() && inputUfRetirada.getText().isEmpty();
			
			boolean inputCepNaoPreenchido = !inputCepRetirada.getText().isEmpty();
			
			if (!isNowFocused && camposEnderecoRetiradaVazios && inputCepNaoPreenchido) {
		    	
		    	Endereco enderecoRetirada= buscaEndereco(inputCepRetirada.getText());
		    	
		    	if(enderecoRetirada != null) {
		    		setCamposEnderecoRetirada(enderecoRetirada);
		    	}
		    }
		});
		
	
	}
	

	public void setInputsEnderecoEntrega(Endereco enderecoEntrega) {
		inputLogradouroEntrega.setText(enderecoEntrega.getLogradouro());
		inputBairroEntrega.setText(enderecoEntrega.getBairro());
		inputLocalidadeEntrega.setText(enderecoEntrega.getLocalidade());
		inputUfEntrega.setText(enderecoEntrega.getUF());

	}
	
	public void setCamposEnderecoRetirada(Endereco enderecoRetirada) {
		inputLogradouroRetirada.setText(enderecoRetirada.getLogradouro());
		inputBairroRetirada.setText(enderecoRetirada.getBairro());
		inputLocalidadeRetirada.setText(enderecoRetirada.getLocalidade());
		inputUfRetirada.setText(enderecoRetirada.getUF());

	}
	
	@FXML
	public void fazerPedido(ActionEvent event) {
		
		boolean dadosValidos = validarDados();
		
		if(dadosValidos) {
			PedidoDAO pedidoDAO;
	
			Pedido pedido = criaPedido();
	
			pedidoDAO = new PedidoDAO(entityManager);
			
			pedidoDAO.inserir(pedido);
		}

	}

	public Pedido criaPedido() {

		TipoVeiculo tipoTransporte;
		PesoPedido pesoPedido;
		BuscaEndereco buscaCEP = new BuscaEndereco();

		Endereco enderecoDeEntrega = buscaCEP.procurarPor(tfEnderecoDeEntregaCEP.getText());
		Endereco enderecoDeRetirada = buscaCEP.procurarPor(inputCepRetirada.getText());

		if (radioBtnCarro.isSelected()) {
			tipoTransporte = TipoVeiculo.CARRO;
		} else if (radioBtnMoto.isSelected()) {
			tipoTransporte = TipoVeiculo.MOTO;
		} else {
			tipoTransporte = TipoVeiculo.VAN;
		}
	
		if (radioBtn25kg.isSelected()) {
			pesoPedido = PesoPedido.ATE25KG;
		} else if (radioBtn50kg.isSelected()) {
			pesoPedido = PesoPedido.ATE50KG;
		} else if (radioBtn100kg.isSelected()) {
			pesoPedido = PesoPedido.ATE100KG;
		} else if (radioBtn150kg.isSelected()) {
			pesoPedido = PesoPedido.ATE150KG;
		} else {
			pesoPedido = PesoPedido.ATE200KG;
		}

		Pedido pedido = new Pedido(enderecoDeEntrega, enderecoDeRetirada, new BigDecimal(10.50), LocalDateTime.now(),
				LocalDate.of(2021, Month.APRIL, 17), pesoPedido, tipoTransporte);

		return pedido;
	}
	
	public Endereco buscaEndereco(String cep) {

		BuscaEndereco buscaEndereco = new BuscaEndereco();

		return buscaEndereco.procurarPor(cep);

	}
	
	private boolean validarDados() {

		ValidarCampos validarCampos = new ValidarCampos();
		
		boolean textFieldsPreenchidos, transportePreenchido, pesoPreenchido, CEPDoEnderecoEntregaValido, 
		CEPDoEnderecoRetiradaValido, numeroDoEnderecoEntregaValido, numeroDoEnderecoRetiradaValido;
		
		textFieldsPreenchidos = transportePreenchido = pesoPreenchido = CEPDoEnderecoEntregaValido = 
		CEPDoEnderecoRetiradaValido = numeroDoEnderecoEntregaValido = numeroDoEnderecoRetiradaValido = false;
		
		textFieldsPreenchidos = validarCampos.procurarTextFieldVazio(Arrays.asList(inputLogradouroRetirada,
				inputComplementoRetirada, inputBairroRetirada, inputLocalidadeRetirada,
				inputCepRetirada, tfEnderecoDeEntregaCEP, inputLogradouroEntrega, 
				inputComplementoEntrega,	inputBairroEntrega, inputLocalidadeEntrega,
				inputUfEntrega, inputUfRetirada));

		transportePreenchido = validarCampos.procurarRadioButtonsVazios(Arrays.asList(radioBtnCarro, radioBtnCarro, radioBtnVan));
		pesoPreenchido = validarCampos.procurarRadioButtonsVazios(Arrays.asList(radioBtn25kg, radioBtn50kg, radioBtn100kg, radioBtn150kg, radioBtn200kg));
		
		boolean camposPreenchidos = textFieldsPreenchidos && transportePreenchido && pesoPreenchido;
		
		if(camposPreenchidos) {
			
			CEPDoEnderecoEntregaValido = validarCampos.validarCEP(tfEnderecoDeEntregaCEP.getText());
			CEPDoEnderecoRetiradaValido = validarCampos.validarCEP(inputCepRetirada.getText());
			
			numeroDoEnderecoEntregaValido = validarCampos.validarNumero(inputComplementoEntrega.getText());
			numeroDoEnderecoRetiradaValido = validarCampos.validarNumero(inputComplementoRetirada.getText());
		}
		
		boolean CEPsValidos = CEPDoEnderecoEntregaValido && CEPDoEnderecoRetiradaValido;
		boolean numerosValidos =  numeroDoEnderecoEntregaValido && numeroDoEnderecoRetiradaValido;
		
		return camposPreenchidos && CEPsValidos && numerosValidos;
	}

}
