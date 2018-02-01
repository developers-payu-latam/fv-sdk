package com.payulatam.fraudvault.model.response.xml;

import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import lombok.Data;

/**
 * Represents the detail of the Fraudvault evaluation result.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
@Root(name = "evaluacion")
@Data
public class FraudvaultEvaluationDetail {

	@Element(name = "transaccion-id", required = false)
	private String transactionId;

	@Path("alertas")
	@ElementList(inline = true, entry = "alerta", required = false)
	private List<String> alerts;

	@Path("acciones")
	@ElementList(inline = true, entry = "accion", required = false)
	private List<String> actions;

	@Element(name = "transacciones-similares", required = false)
	private Integer similarTransactionsNumber;

	@Path("filtros")
	@ElementList(inline = true, entry = "filtro", required = false)
	private List<TriggeredRule> rules;

	@Element(name = "analisis-heuristico", required = false)
	private HeuristicAnalysis heuristicAnalysis;

	@Element(name = "listas-de-control", required =false)
	private ControlListsInformation controlListsInformation;

	@Element(name = "banco-emisor", required = false)
	private IssuerBank issuerBank;
	
	@Element(name = "direccion-ip-proxy", required = false)
	private boolean ipProxy;
	
	@Element(name = "isp", required = false)
	private String ispName;
	
	@Element(name = "ubicacion", required = false)
	private IpAddressLocation ipAddressLocation;
	
	@Element(name = "tiempo-evaluacion", required = false)
	private Integer evaluationTime;

	@Element(name = "codigo-error", required = false)
	private Integer errorCode;

	@Element(name = "mensaje-error", required = false)
	private String errorMessage;

	@Element(name = "validar-con-central-de-riesgo", required = false)
	private boolean validateCentralRisk;

}
