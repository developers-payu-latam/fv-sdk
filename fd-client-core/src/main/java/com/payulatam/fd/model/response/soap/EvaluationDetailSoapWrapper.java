/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 19 de oct. de 2017
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *    http://www.apache.org/licenses/LICENSE-2.0
 *    
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.payulatam.fd.model.response.soap;

import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import com.payulatam.fd.model.response.*;

import lombok.Data;

/**
 * Represents the evaluation result detail of the fraud detection service.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
@Root(name = "evaluacion")
@Data
public class EvaluationDetailSoapWrapper {

	/** The identifier of the transaction. */
	@Element(name = "transaccion-id", required = false)
	private String transactionId;

	/**
	 * A list of alerts that can be thrown by the fraud detection service. For example:
	 * INTERNATIONAL_CARD: if the credit card is international.
	 * DESCRIPTION_HAS_SUSPICIOUS_WORD: if the description sent in the transaction data contains
	 * any suspicious word.
	 */
	@Path("alertas")
	@ElementList(inline = true, entry = "alerta", required = false)
	private List<String> alerts;

	/**
	 * The actions triggered in the execution of rules. The actions triggered are according
	 * to transaction data that match with the rules profiles configuration in the fraud detection service.
	 */
	@Path("acciones")
	@ElementList(inline = true, entry = "accion", required = false)
	private List<String> actions;

	/** Number of fraudulent transactions that are similar to the current transaction. */
	@Element(name = "transacciones-similares", required = false)
	private Integer similarTransactionsNumber;

	/**
	 * A list with the detail of the triggered rules by the fraud detection service. The detail contains among others:
	 * the name of the rule, the field of the transaction that triggers the rule, the value
	 * configured in the rule.
	 */
	@Path("filtros")
	@ElementList(inline = true, entry = "filtro", required = false)
	private List<TriggeredRule> rules;

	/** The risk scores assigned by the neuronal network. */
	@Element(name = "analisis-heuristico", required = false)
	private HeuristicAnalysis heuristicAnalysis;

	/** The control lists matching with the transaction data.*/
	@Element(name = "listas-de-control", required =false)
	private ControlListsInformation controlListsInformation;

	/** Information of the card issuer bank. */
	@Element(name = "banco-emisor", required = false)
	private IssuerBank issuerBank;
	
	/** Indicates if there is a high risk that the IP address is a proxy. */
	@Element(name = "direccion-ip-proxy", required = false)
	private boolean ipProxy;
	
	/** The name of the detected Internet service provider. */
	@Element(name = "isp", required = false)
	private String ispName;
	
	/** Information of the IP address location. */
	@Element(name = "ubicacion", required = false)
	private IpAddressLocation ipAddressLocation;
	
	/** Time in milliseconds that the fraud detection service takes to evaluate the transaction. */
	@Element(name = "tiempo-evaluacion", required = false)
	private Integer evaluationTime;

	/** A value from 2001 to 2999 returned if there is an error when evaluating a transaction. */
	@Element(name = "codigo-error", required = false)
	private Integer errorCode;

	/** Error message associated with the evaluation of the transaction. */
	@Element(name = "mensaje-error", required = false)
	private String errorMessage;

	@Element(name = "validar-con-central-de-riesgo", required = false)
	private boolean validatedWithCreditBureau;

}
