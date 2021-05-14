package br.com.ontimedelivery.api;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class BuscaDistancia {

	public String medirDistancia(String enderecoOrigem, String enderecoDestino) {

		String urlDistanceMatrix = "https://maps.googleapis.com/maps/api/distancematrix/json?" 
				+ "origins=" + enderecoOrigem
				+ "&destinations=" + enderecoDestino
				+ "&key=AIzaSyAXW4JQPiNoi0uuCCBebbC8peIGl8kXMTs";

		try {
			URL url = new URL(urlDistanceMatrix);
			HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

			System.out.println(urlDistanceMatrix);
			System.out.println(conexao.getResponseCode());

			InputStream in = new BufferedInputStream(conexao.getInputStream());

			String textoConvertido = IOUtils.toString(in, StandardCharsets.UTF_8.name());
			JsonObject jsonObject = new Gson().fromJson(textoConvertido, JsonObject.class);

			String distancia = jsonObject.getAsJsonArray("rows").get(0).getAsJsonObject().getAsJsonArray("elements")
					.get(0).getAsJsonObject().get("distance").getAsJsonObject().get("text").getAsString();

			return distancia;
		} catch (IOException ex) {
			
			return null;
		}

	}
}
