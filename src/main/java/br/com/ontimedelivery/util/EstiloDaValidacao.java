package br.com.ontimedelivery.util;

import javafx.scene.Node;
import javafx.scene.control.Tooltip;

public class EstiloDaValidacao {

	public static void adicionarCorDaBordaETooltip(Node node, Tooltip tooltip) {

		node.setStyle("-fx-border-color: red;");
		tooltip.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0); "
				+ "-fx-font-weight: bold;" + " -fx-padding: 5;" + " -fx-border-width:1; "
				+ "-fx-background-color: #FBEFEF; " + "-fx-text-fill: #cc0033;" + " -fx-border-color:#cc0033;");

		Tooltip.install(node, tooltip);
	}

	public static void removerCorDaBordaETooltip(Node node, Tooltip tooltip) {

		node.setStyle(null);
		Tooltip.uninstall(node, tooltip);
	}
}
