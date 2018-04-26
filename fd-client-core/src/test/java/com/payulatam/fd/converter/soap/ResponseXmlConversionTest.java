/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 4 de dic. de 2017
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

package com.payulatam.fd.converter.soap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.payulatam.fd.api.client.exception.XmlConversionException;
import com.payulatam.fd.model.response.*;
import com.payulatam.fd.model.response.soap.*;

/**
 * Test for the conversions from XML data to objects with the XML response structure.
 */
public class ResponseXmlConversionTest {
	
	/**
	 * Test the conversion from the xml response to the object representing the prevalidation response.
	 * @throws IOException if an error happens reading the file.
	 * @throws XmlConversionException if the object cannot be deserialized from the XML.
	 */
	@Test
	public void convertPrevalidationResponseContent() throws IOException, XmlConversionException{

		String xmlResponse = cargarXml("responsePrevalidation.xml");
		PrevalidationResponseSoapWrapper response = PrevalidationResponseSoapWrapper.fromXml(xmlResponse);
		Assert.assertEquals(response.getGeneralAnswerCode(), new Integer(1));
		Assert.assertNull(response.getGeneralErrorCode());
		Assert.assertNull(response.getGeneralErrorMessage());
		Assert.assertNotNull(response.getEvaluation());
		Assert.assertEquals(response.getEvaluation().getDecision(), new Integer(1));

		EvaluationDetailSoapWrapper evaluationDetail = response.getEvaluation().getDetail();
		Assert.assertNotNull(evaluationDetail);
		Assert.assertEquals(evaluationDetail.getTransactionId(), "9194802");
		Assert.assertNotNull(evaluationDetail.getActions());
		Assert.assertNotNull(evaluationDetail.getAlerts());
		Assert.assertEquals(evaluationDetail.getActions().size(), 1);
		Assert.assertEquals(evaluationDetail.getAlerts().size(), 6);
		Assert.assertNotNull(evaluationDetail.getSimilarTransactionsNumber());
		Assert.assertEquals(evaluationDetail.getSimilarTransactionsNumber(), new Integer(114));

		validateControlListsInformation(evaluationDetail);
		validateTriggeredRules(evaluationDetail);
		validateHeuristicAnalysis(evaluationDetail);
		validateIssuerBank(evaluationDetail);
		validateIpAddressLocation(evaluationDetail);

		Assert.assertFalse(evaluationDetail.isIpProxy());
		Assert.assertEquals(evaluationDetail.getIspName(), "DoD Network Information Center");

		Integer evaluationTime = evaluationDetail.getEvaluationTime();
		Assert.assertNotNull(evaluationTime);
		Assert.assertEquals(evaluationTime, new Integer(2141));

		Assert.assertNull(evaluationDetail.getErrorCode());
		Assert.assertNull(evaluationDetail.getErrorMessage());
	}
	
	/**
	 * Test the conversion from the xml response to the object representing the transaction state query response.
	 * @throws IOException if an error happens reading the file.
	 * @throws XmlConversionException if the object cannot be deserialized from the XML.
	 */
	@Test
	public void convertQueryStateResponseContent() throws IOException, XmlConversionException{

		String xmlResponse = cargarXml("responseQueryState.xml");
		QueryStateResponseSoapWrapper response = QueryStateResponseSoapWrapper.fromXml(xmlResponse);
		Assert.assertNotNull(response.getResponseDate());
		Assert.assertEquals(response.getGeneralAnswerCode(), new Integer(1));
		Assert.assertNull(response.getGeneralErrorCode());
		Assert.assertNull(response.getGeneralErrorMessage());
		StateOperationResponseContentSoapWrapper responseContent = response
				.getQueryStateResponseContent();
		Assert.assertEquals(responseContent.getTransactionId(), "456602622");
		Assert.assertEquals(responseContent.getState(), new Integer(11));
		Assert.assertEquals(responseContent.getAnswerCode(), new Integer(1));
		Assert.assertNull(responseContent.getErrorMessage());
	}
	
	/**
	 * Test the conversion from the xml response to the object representing the transaction update query response.
	 * @throws IOException if an error happens reading the file.
	 * @throws XmlConversionException if the object cannot be deserialized from the XML. 
	 */
	@Test
	public void convertUpdateStateResponseContent() throws IOException, XmlConversionException{

		String xmlResponse = cargarXml("responseUpdateState.xml");
		UpdateStateResponseSoapWrapper response = UpdateStateResponseSoapWrapper.fromXml(xmlResponse);
		Assert.assertNotNull(response.getResponseDate());
		Assert.assertEquals(response.getGeneralAnswerCode(), new Integer(1));
		Assert.assertNull(response.getGeneralErrorCode());
		Assert.assertNull(response.getGeneralErrorMessage());
		StateOperationResponseContentSoapWrapper responseContent = response
				.getUpdateStateResponseContent();
		Assert.assertEquals(responseContent.getTransactionId(), "456602622");
		Assert.assertEquals(responseContent.getAnswerCode(), new Integer(1));
		Assert.assertNull(responseContent.getErrorMessage());
	}
	
	/**
	 * Test the conversion from the xml response to the object representing the posvalidation response.
	 * @throws IOException 
	 * @throws XmlConversionException 
	 */
	@Test
	public void convertPosvalidationResponseContent() throws IOException, XmlConversionException{

		String xmlResponse = cargarXml("responsePosvalidation.xml");
		PosvalidationResponsesSoapWrapper response = PosvalidationResponsesSoapWrapper
				.fromXml(xmlResponse);
		Assert.assertNotNull(response.getResponseDate());
		Assert.assertEquals(response.getGeneralAnswerCode(), new Integer(1));
		Assert.assertNull(response.getGeneralErrorCode());
		Assert.assertNull(response.getGeneralErrorMessage());

		EvaluationSoapWrapper evaluation = response.getEvaluation();
		Assert.assertNotNull(evaluation);
		Assert.assertEquals(evaluation.getDecision(), new Integer(6));

		EvaluationDetailSoapWrapper evaluationDetail = evaluation.getDetail();
		Assert.assertEquals(evaluationDetail.getTransactionId(), "9194802");
		Assert.assertNull(evaluationDetail.getErrorCode());
		Assert.assertNull(evaluationDetail.getErrorMessage());

		Integer evaluationTime = evaluationDetail.getEvaluationTime();
		Assert.assertNotNull(evaluationTime);
		Assert.assertEquals(evaluationTime, new Integer(2854));
		Assert.assertNotNull(evaluationDetail.getSimilarTransactionsNumber());
		Assert.assertEquals(evaluationDetail.getSimilarTransactionsNumber(), new Integer(114));
		Assert.assertNull(evaluationDetail.getActions());
		Assert.assertNull(evaluationDetail.getRules());
	}
	
	/**
	 * Test the conversion from the xml response with returned error to the object representing the prevalidation response.
	 * @throws IOException if an error happens reading the file.
	 * @throws XmlConversionException if the object cannot be deserialized from the XML.
	 */
	@Test
	public void convertPrevalidationResponseInputMissingTag() throws IOException, XmlConversionException{

		String xmlResponse = cargarXml("responsePreInputMissingTag.xml");
		PosvalidationResponsesSoapWrapper response = PosvalidationResponsesSoapWrapper.fromXml(xmlResponse);
		Assert.assertNotNull(response.getResponseDate());
		Assert.assertEquals(response.getGeneralAnswerCode(), new Integer(2));
		Assert.assertNotNull(response.getGeneralErrorCode());
		Assert.assertEquals(response.getGeneralErrorCode(), new Integer(1005));
		Assert.assertNotNull(response.getGeneralErrorMessage());
		Assert.assertNull(response.getEvaluation());
	}
	
	/**
	 * Test the conversion from the xml response with returned error to the object representing the transaction state query response.
	 * @throws IOException if an error happens reading the file.
	 * @throws XmlConversionException if the object cannot be deserialized from the XML. 
	 */
	@Test
	public void convertQueryStateResponseTrxIdInvalid() throws IOException, XmlConversionException{

		String xmlResponse = cargarXml("responseQueryStateTrxIdInvalid.xml");
		QueryStateResponseSoapWrapper response = QueryStateResponseSoapWrapper.fromXml(xmlResponse);
		Assert.assertNotNull(response.getResponseDate());
		Assert.assertEquals(response.getGeneralAnswerCode(), new Integer(2));
		Assert.assertNotNull(response.getGeneralErrorCode());
		Assert.assertEquals(response.getGeneralErrorCode(), new Integer(1007));
		Assert.assertNotNull(response.getGeneralErrorMessage());

		StateOperationResponseContentSoapWrapper responseContent = response.getQueryStateResponseContent();
		Assert.assertNotNull(responseContent);
		Assert.assertNotNull(responseContent.getAnswerCode());
		Assert.assertEquals(responseContent.getAnswerCode(), new Integer(2));
		Assert.assertNotNull(responseContent.getErrorMessage());
		Assert.assertNotNull(responseContent.getTransactionId());
		Assert.assertEquals(responseContent.getTransactionId(), "456602622111");
		Assert.assertNull(responseContent.getState());
	}

	/**
	 * Validate the data of the IP address location in the evaluation detail.
	 * 
	 * @param evaluationDetail evaluation data.
	 */
	private void validateIpAddressLocation(EvaluationDetailSoapWrapper evaluationDetail) {

		IpAddressLocation ipAddressLocation = evaluationDetail.getIpAddressLocation();
		Assert.assertNotNull(ipAddressLocation);
		Assert.assertEquals(ipAddressLocation.getIpCity(), "Virginia Beach");
		Assert.assertEquals(ipAddressLocation.getIpCountry(), "United States");
		Assert.assertEquals(ipAddressLocation.getIpCountryCode(), "US");
		Assert.assertEquals(ipAddressLocation.getIpRegion(), "VA");
		Assert.assertEquals(ipAddressLocation.getIpTelephoneAreaCode(), "0");
		Assert.assertEquals(ipAddressLocation.getIpZipCode(), "23459");
		Assert.assertEquals(ipAddressLocation.getIpLatitude(), 36.9205);
		Assert.assertEquals(ipAddressLocation.getIpLongitude(), -76.0192);
		Assert.assertEquals(ipAddressLocation.getIpMetroAreaCode(), new Integer(0));
	}

	/**
	 * Validate the data of the issuer bank in the evaluation detail.
	 * 
	 * @param evaluationDetail evaluation data.
	 */
	private void validateIssuerBank(EvaluationDetailSoapWrapper evaluationDetail) {

		IssuerBank issuerBank = evaluationDetail.getIssuerBank();
		Assert.assertNotNull(issuerBank);
		Assert.assertEquals(issuerBank.getBank(), "WELLS FARGO");
		Assert.assertEquals(issuerBank.getCountry(), "US");
		Assert.assertEquals(issuerBank.getPhoneNumber(), "8008693557");
	}

	/**
	 * Validate the data of the heuristic analysis in the evaluation detail.
	 * 
	 * @param evaluationDetail evaluation data.
	 */
	private void validateHeuristicAnalysis(EvaluationDetailSoapWrapper evaluationDetail) {

		HeuristicAnalysis heuristicAnalysis = evaluationDetail.getHeuristicAnalysis();
		Assert.assertNotNull(heuristicAnalysis);
		Assert.assertEquals(heuristicAnalysis.getGeneralScore(), 0.00);
		Assert.assertEquals(heuristicAnalysis.getEmailScore(), 0.04);
	}

	/**
	 * Validate the data of the rules in the evaluation detail.
	 * 
	 * @param evaluationDetail evaluation data.
	 */
	private void validateTriggeredRules(EvaluationDetailSoapWrapper evaluationDetail) {

		List<TriggeredRule> triggeredRules = evaluationDetail.getRules();
		Assert.assertNotNull(triggeredRules);
		Assert.assertEquals(triggeredRules.size(), 2);
		TriggeredRule rule1 = triggeredRules.get(0);
        TriggeredRuleCondition triggeredRuleCondition1=rule1.getTriggeredRuleCondition(0);
		Assert.assertEquals(rule1.getRuleName(), "regla_test [MONITOREAR]");

		Assert.assertEquals(triggeredRuleCondition1.getTransactionFieldName(), "Documento del comprador");
		Assert.assertEquals(triggeredRuleCondition1.getRuleConfiguredValue(), "12345678");
		Assert.assertEquals(triggeredRuleCondition1.getTransactionFieldValue(), "12345678");
		Assert.assertEquals(triggeredRuleCondition1.getOperator(), "IGUAL");

		TriggeredRule rule2 = triggeredRules.get(1);
        TriggeredRuleCondition triggeredRuleCondition2=rule2.getTriggeredRuleCondition(0);
		Assert.assertEquals(rule2.getRuleName(), "det_cedula");
		Assert.assertEquals(triggeredRuleCondition2.getTransactionFieldName(), "Documento Comprador");
		Assert.assertEquals(triggeredRuleCondition2.getRuleConfiguredValue(), "53140140");
		Assert.assertEquals(triggeredRuleCondition2.getTransactionFieldValue(), "53140140");
		Assert.assertEquals(triggeredRuleCondition2.getOperator(), "IGUAL");
	}

	/**
	 * Validate the data of the lists in the evaluation detail.
	 * 
	 * @param evaluationDetail evaluation data.
	 */
	private void validateControlListsInformation(EvaluationDetailSoapWrapper evaluationDetail) {

		ControlListsInformation listsInformation = evaluationDetail.getControlListsInformation();
		Assert.assertNotNull(listsInformation);
		ListMatch whiteListsMatching = listsInformation.getWhiteListsMatching();
		Assert.assertNotNull(whiteListsMatching);
		Assert.assertEquals(whiteListsMatching.isMatch(), false);

		ListMatch blackListsMatching = listsInformation.getBlackListsMatching();
		Assert.assertNotNull(blackListsMatching);
		Assert.assertEquals(blackListsMatching.isMatch(), true);
		Assert.assertEquals(blackListsMatching.getTransactionFieldName(), "correoElectronico");

		ListMatch temporaryListsMatching = listsInformation.getTemporaryListsMatching();
		Assert.assertNotNull(temporaryListsMatching);
		Assert.assertEquals(temporaryListsMatching.isMatch(), false);
	}

	/**
	 * Get the content of a xml file located in src/test/resources path. 
	 * @param nombre name of the file.
	 * @return the string with content of the file.
	 * @throws IOException if an error happens reading the file.
	 */
	private String cargarXml(String nombre) throws IOException {

		String res = "";
		StringBuffer contenidoXML = new StringBuffer();
		String archivo = "./src/test/resources/" + nombre;
		File f = new File(archivo);
		BufferedReader entrada;
		if (f.exists()) {
			entrada = new BufferedReader(new InputStreamReader(new FileInputStream(f), 
					StandardCharsets.UTF_8.name()));
			while ((res = entrada.readLine()) != null) {
				contenidoXML.append(res);
			}
		}
		return contenidoXML.toString();
	}

}
