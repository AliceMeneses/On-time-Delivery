package br.com.ontimedelivery.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;

import br.com.ontimedelivery.Main;
import br.com.ontimedelivery.api.BuscaDistancia;
import br.com.ontimedelivery.api.BuscaEndereco;
import br.com.ontimedelivery.dao.EnderecoDAO;
import br.com.ontimedelivery.dao.PedidoDAO;
import br.com.ontimedelivery.model.Endereco;
import br.com.ontimedelivery.model.Pedido;
import br.com.ontimedelivery.model.PesoPedido;
import br.com.ontimedelivery.model.TipoVeiculo;
import br.com.ontimedelivery.model.Usuario;
import br.com.ontimedelivery.util.JPAUtil;
import br.com.ontimedelivery.validacao.ValidarDados;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class PedidoController implements Initializable {

	@FXML
	private TextField tfCepRetirada;

	@FXML
	private TextField tfLogradouroRetirada;

	@FXML
	private TextField tfComplementoRetirada;

	@FXML
	private TextField tfNumeroRetirada;

	@FXML
	private TextField tfBairroRetirada;

	@FXML
	private TextField tfLocalidadeRetirada;

	@FXML
	private TextField tfUfRetirada;

	@FXML
	private TextField tfCepEntrega;

	@FXML
	private TextField tfLogradouroEntrega;

	@FXML
	private TextField tfComplementoEntrega;

	@FXML
	private TextField tfNumeroEntrega;

	@FXML
	private TextField tfBairroEntrega;

	@FXML
	private TextField tfLocalidadeEntrega;

	@FXML
	private TextField tfUfEntrega;

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
	
	@FXML
	private Button btnCalcularFrete;
	
	@FXML
	private Label lbFrete;
	
	@FXML
	private DatePicker dtAgendarEntrega;

	@FXML
	private Button btnSair;

	private Usuario usuario;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		EntityManager entityManager = JPAUtil.getEntityManager();

		tfCepEntrega.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {

			Boolean textFieldsEnderecoEntregaVazios = tfLogradouroEntrega.getText().isEmpty()
					&& tfBairroEntrega.getText().isEmpty() && tfLocalidadeEntrega.getText().isEmpty()
					&& tfUfEntrega.getText().isEmpty();

			Boolean tfCepNaoPreenchido = !tfCepEntrega.getText().isEmpty();

			if (!isNowFocused && textFieldsEnderecoEntregaVazios && tfCepNaoPreenchido) {

				Endereco enderecoEntrega = buscaEndereco(tfCepEntrega.getText());

				if (enderecoEntrega != null) {
					setTextFieldsEnderecoEntrega(enderecoEntrega);
				}
			}
		});

		tfCepRetirada.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {

			Boolean textFieldsEnderecoRetiradaVazios = tfLogradouroRetirada.getText().isEmpty()
					&& tfBairroRetirada.getText().isEmpty() && tfLocalidadeRetirada.getText().isEmpty()
					&& tfUfRetirada.getText().isEmpty();

			Boolean tfCepNaoPreenchido = !tfCepRetirada.getText().isEmpty();

			if (!isNowFocused && textFieldsEnderecoRetiradaVazios && tfCepNaoPreenchido) {

				Endereco enderecoRetirada = buscaEndereco(tfCepRetirada.getText());

				if (enderecoRetirada != null) {
					setTextFieldsEnderecoRetirada(enderecoRetirada);
				}
			}
		});

		entityManager.close();
	}

	private void setTextFieldsEnderecoEntrega(Endereco enderecoEntrega) {

		tfLogradouroEntrega.setText(enderecoEntrega.getLogradouro());
		tfBairroEntrega.setText(enderecoEntrega.getBairro());
		tfLocalidadeEntrega.setText(enderecoEntrega.getLocalidade());
		tfUfEntrega.setText(enderecoEntrega.getUF());
	}

	private void setTextFieldsEnderecoRetirada(Endereco enderecoRetirada) {

		tfLogradouroRetirada.setText(enderecoRetirada.getLogradouro());
		tfBairroRetirada.setText(enderecoRetirada.getBairro());
		tfLocalidadeRetirada.setText(enderecoRetirada.getLocalidade());
		tfUfRetirada.setText(enderecoRetirada.getUF());
	}

	@FXML
	public void sair(ActionEvent event) {
		
		limparCampos();
		
		Main.mudarParaLoginScene();
	}

	@FXML
	public void calcularFrete(ActionEvent event) {

		Boolean dadosValidos = validarDados();

		if (dadosValidos) {
			
			String frete = calcularFrete().toString();
			
			lbFrete.setText("Frete: R$" + frete);
		}

	}
	
	private BigDecimal calcularFrete() {
		BigDecimal precoFrete = BigDecimal.ZERO; 
		BigDecimal precoPorKM = new BigDecimal("3.0");
		
		Endereco enderecoEntrega = criaEnderecoEntrega();
		Endereco enderecoRetirada = criaEnderecoRetirada();
		
		BuscaDistancia buscaDistancia = new BuscaDistancia();
		String distancia = buscaDistancia.medirDistancia(enderecoEntrega.getEnderecoCompleto(), enderecoRetirada.getEnderecoCompleto());

		precoFrete = precoPorKM.multiply(new BigDecimal(distancia));

		return precoFrete;
	}

	@FXML
	public void fazerPedido(ActionEvent event) {

		Boolean dadosValidos = validarDados();
		System.out.println("Dados validos" + dadosValidos);
		if (dadosValidos) {

			EntityManager entityManager = JPAUtil.getEntityManager();

			PedidoDAO pedidoDAO = new PedidoDAO(entityManager);
			EnderecoDAO enderecoDAO = new EnderecoDAO(entityManager);
			Pedido pedido = criaPedido();

			entityManager.getTransaction().begin();

			enderecoDAO.inserir(pedido.getEnderecoEntrega());
			enderecoDAO.inserir(pedido.getEnderecoRetirada());

			pedidoDAO.inserir(pedido);

			entityManager.getTransaction().commit();

			entityManager.close();
						
			limparCampos();
			
			Main.mudarParaSucessoPedidoScene();
		}

	}
	
	private Endereco criaEnderecoEntrega() {
		
		return  new Endereco(tfLogradouroEntrega.getText(), tfNumeroEntrega.getText(),
				tfBairroEntrega.getText(), tfLocalidadeEntrega.getText(), tfUfEntrega.getText(),
				tfCepEntrega.getText());
	}
	
	private Endereco criaEnderecoRetirada() {
		
		return new Endereco(tfLogradouroRetirada.getText(), tfNumeroRetirada.getText(),
				tfBairroRetirada.getText(), tfLocalidadeRetirada.getText(), tfUfRetirada.getText(),
				tfCepRetirada.getText());
	}

	private Pedido criaPedido() {

		usuario = (Usuario) btnFazerPedido.getScene().getUserData();

		TipoVeiculo tipoVeiculo;
		PesoPedido pesoPedido;
		Boolean servicoDeCargaEDescarga = false;

		Endereco enderecoEntrega = criaEnderecoEntrega();

		Endereco enderecoRetirada = criaEnderecoRetirada();

		if (radioBtnCarro.isSelected()) {
			tipoVeiculo = TipoVeiculo.CARRO;
		} else if (radioBtnMoto.isSelected()) {
			tipoVeiculo = TipoVeiculo.MOTO;
		} else {
			tipoVeiculo = TipoVeiculo.VAN;
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

		if (checkServicoCarregamento.selectedProperty().getValue()) {
			servicoDeCargaEDescarga = true;
		}

		Pedido pedido = new Pedido(usuario, enderecoEntrega, enderecoRetirada, calcularFrete(),
				LocalDateTime.now(), dtAgendarEntrega.getValue(), pesoPedido, tipoVeiculo, dsPedido.getText(),
				servicoDeCargaEDescarga);

		return pedido;
	}

	private Endereco buscaEndereco(String cep) {

		BuscaEndereco buscaEndereco = new BuscaEndereco();

		return buscaEndereco.procurarPor(cep);

	}

	private Boolean validarDados() {


		Boolean textFieldsPreenchidos, cepEntregaValido, cepRetiradaValido, numeroEntregaValido, numeroRetiradaValido,
			textAreaPreenchido;

		textFieldsPreenchidos = cepEntregaValido = textAreaPreenchido = cepRetiradaValido = numeroEntregaValido = numeroRetiradaValido = false;

		textFieldsPreenchidos = !(ValidarDados.procurarTextFieldVazio(Arrays.asList(tfLogradouroRetirada,
				tfBairroRetirada, tfLocalidadeRetirada, tfCepRetirada, tfCepEntrega, tfLogradouroEntrega,
				tfBairroEntrega, tfLocalidadeEntrega, tfUfEntrega, tfUfRetirada, tfNumeroEntrega, tfNumeroRetirada)));

		textAreaPreenchido = !(ValidarDados.procurarTextAreaVazio(dsPedido));
		
		Boolean dataValida = ValidarDados.validarDataAgendadaEntrega(dtAgendarEntrega);

		Boolean camposPreenchidos = textFieldsPreenchidos && textAreaPreenchido && dataValida;

		if (camposPreenchidos) {

			cepEntregaValido = ValidarDados.validarCEP(tfCepEntrega);
			cepRetiradaValido = ValidarDados.validarCEP(tfCepRetirada);

			numeroEntregaValido = ValidarDados.validarNumero(tfNumeroEntrega);
			numeroRetiradaValido = ValidarDados.validarNumero(tfNumeroRetirada);
		}

		Boolean CEPsValidos = cepEntregaValido && cepRetiradaValido;

		Boolean numerosValidos = numeroEntregaValido && numeroRetiradaValido;

		return camposPreenchidos && CEPsValidos && numerosValidos;
	}
	
	private void limparCampos() {
		
		tfBairroEntrega.clear();
		tfBairroRetirada.clear();
		tfCepEntrega.clear();
		tfCepRetirada.clear();
		tfComplementoEntrega.clear();
		tfComplementoRetirada.clear();
		tfLocalidadeEntrega.clear();
		tfLocalidadeRetirada.clear();
		tfUfRetirada.clear();
		tfLogradouroEntrega.clear();
		tfLogradouroRetirada.clear();
		tfNumeroEntrega.clear();
		tfNumeroRetirada.clear();
		tfUfEntrega.clear();
		dsPedido.clear();
		checkServicoCarregamento.selectedProperty().setValue(false);
		dtAgendarEntrega.setValue(null);
	}

}
