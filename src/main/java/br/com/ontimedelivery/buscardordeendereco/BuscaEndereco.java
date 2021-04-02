package br.com.ontimedelivery.buscardordeendereco;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.jr.ob.JSON;

import br.com.ontimedelivery.model.Endereco;

public class BuscaEndereco {
	
	public Endereco procurarPor(String cep) {
		
		String urlParaChamada = "http://viacep.com.br/ws/" + cep.trim() + "/json/";
	
		try {
	        URL url = new URL(urlParaChamada);
	        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
	
	        if (conexao.getResponseCode() != 200) {
	            System.out.println("Tente novamente mais tarde");
	        }
	        
			InputStream in = new BufferedInputStream(conexao.getInputStream());

			Endereco endereco = JSON.std.beanFrom(Endereco.class, in);

	        return endereco;
		} catch (IOException ex) {
			return null;

		}

	}
}
