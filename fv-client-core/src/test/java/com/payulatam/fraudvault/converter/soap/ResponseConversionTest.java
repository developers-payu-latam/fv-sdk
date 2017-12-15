/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 4 de dic. de 2017
 */
package com.payulatam.fraudvault.converter.soap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.payulatam.fraudvault.model.response.ControlListsInformation;
import com.payulatam.fraudvault.model.response.FraudvaultEvaluation;
import com.payulatam.fraudvault.model.response.FraudvaultEvaluationDetail;
import com.payulatam.fraudvault.model.response.FraudvaultPosvalidationResponse;
import com.payulatam.fraudvault.model.response.FraudvaultPrevalidationResponse;
import com.payulatam.fraudvault.model.response.FraudvaultQueryStateResponse;
import com.payulatam.fraudvault.model.response.FraudvaultStateOperationResponseContent;
import com.payulatam.fraudvault.model.response.FraudvaultUpdateStateResponse;
import com.payulatam.fraudvault.model.response.HeuristicAnalysis;
import com.payulatam.fraudvault.model.response.IpAddressLocation;
import com.payulatam.fraudvault.model.response.IssuerBank;
import com.payulatam.fraudvault.model.response.ListMatch;
import com.payulatam.fraudvault.model.response.TriggeredRule;

/**
 * Test for the conversions from XML data to Fraudvault reponse objects.
 */
public class ResponseConversionTest {
	
	
	/** Class logger. */
	private final Logger logger = Logger.getLogger(getClass());
	
	/**
	 * Test the conversion from the xml response to the object representing the Fraudvault prevalidation response
	 */
	@Test
	public void convertPrevalidationResponseContent() {

		try {
			String xmlResponse = cargarXml("responsePrevalidation.xml");
			FraudvaultPrevalidationResponse response = FraudvaultPrevalidationResponse.fromXml(xmlResponse);
			Assert.assertEquals(response.getAnswerCode(), new Integer(1));
			Assert.assertNull(response.getErrorCode());
			Assert.assertNull(response.getErrorMessage());
			Assert.assertNotNull(response.getEvaluation());
			Assert.assertEquals(response.getEvaluation().getDecision(), new Integer(1));

			FraudvaultEvaluationDetail evaluationDetail = response.getEvaluation().getDetail();
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

		} catch (Exception e) {
			logger.error("Unexpected error testing the convertion of response content", e);
		}
	}
	
	/**
	 * Test the conversion from the xml response to the object representing the Fraudvault transaction state query response.
	 */
	@Test
	public void convertQueryStateResponseContent() {

		try {
			String xmlResponse = cargarXml("responseQueryState.xml");
			FraudvaultQueryStateResponse response = FraudvaultQueryStateResponse.fromXml(xmlResponse);
			Assert.assertNotNull(response.getDate());
			Assert.assertEquals(response.getAnswerCode(), new Integer(1));
			Assert.assertNull(response.getErrorCode());
			Assert.assertNull(response.getErrorMessage());
			FraudvaultStateOperationResponseContent responseContent = response.getQueryStateResponseContent();
			Assert.assertEquals(responseContent.getTransactionId(), "456602622");
			Assert.assertEquals(responseContent.getState(), new Integer(11));
			Assert.assertEquals(responseContent.getAnswerCode(), new Integer(1));
			Assert.assertNull(responseContent.getErrorMessage());
		} catch (Exception e) {
			logger.error("Unexpected error testing the convertion of response content", e);
		}
	}
	
	/**
	 * Test the conversion from the xml response to the object representing the Fraudvault transaction update query response.
	 */
	@Test
	public void convertUpdateStateResponseContent() {

		try {
			String xmlResponse = cargarXml("responseUpdateState.xml");
			FraudvaultUpdateStateResponse response = FraudvaultUpdateStateResponse.fromXml(xmlResponse);
			Assert.assertNotNull(response.getDate());
			Assert.assertEquals(response.getAnswerCode(), new Integer(1));
			Assert.assertNull(response.getErrorCode());
			Assert.assertNull(response.getErrorMessage());
			FraudvaultStateOperationResponseContent responseContent = response.getUpdateStateResponseContent();
			Assert.assertEquals(responseContent.getTransactionId(), "456602622");			
			Assert.assertEquals(responseContent.getAnswerCode(), new Integer(1));
			Assert.assertNull(responseContent.getErrorMessage());
		} catch (Exception e) {
			logger.error("Unexpected error testing the convertion of response content", e);
		}
	}
	
	/**
	 * Test the conversion from the xml response to the object representing the Fraudvault posvalidation response.
	 */
	@Test
	public void convertPosvalidationResponseContent(){
		try {
			String xmlResponse = cargarXml("responsePosvalidation.xml");
			FraudvaultPosvalidationResponse response = FraudvaultPosvalidationResponse.fromXml(xmlResponse);
			Assert.assertNotNull(response.getDate());
			Assert.assertEquals(response.getAnswerCode(), new Integer(1));
			Assert.assertNull(response.getErrorCode());
			Assert.assertNull(response.getErrorMessage());
			
			FraudvaultEvaluation evaluation = response.getEvaluation();
			Assert.assertNotNull(evaluation);
			Assert.assertEquals(evaluation.getDecision(), new Integer(6));
			
			FraudvaultEvaluationDetail evaluationDetail = evaluation.getDetail();
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
			
		} catch (Exception e) {
			logger.error("Unexpected error testing the convertion of response content", e);
		}
	}
	
	/**
	 * Test the conversion from the xml response with returned error to the object representing the Fraudvault prevalidation response.
	 */
	@Test
	public void convertPrevalidationResponseInputMissingTag(){
		String xmlResponse;
		try {
			xmlResponse = cargarXml("responsePreInputMissingTag.xml");
			FraudvaultPosvalidationResponse response = FraudvaultPosvalidationResponse.fromXml(xmlResponse);
			Assert.assertNotNull(response.getDate());
			Assert.assertEquals(response.getAnswerCode(), new Integer(2));
			Assert.assertNotNull(response.getErrorCode());
			Assert.assertEquals(response.getErrorCode(), new Integer(1005));
			Assert.assertNotNull(response.getErrorMessage());
			Assert.assertNull(response.getEvaluation());
		} catch (Exception e) {
			logger.error("Unexpected error testing convertPrevalidationResponseInputMissingTag", e);
		}		
	}
	
	/**
	 * Test the conversion from the xml response with returned error to the object representing the Fraudvault transaction state query response.
	 */
	@Test
	public void convertQueryStateResponseTrxIdInvalid(){
		String xmlResponse;
		try {
			xmlResponse = cargarXml("responseQueryStateTrxIdInvalid.xml");
			FraudvaultQueryStateResponse response = FraudvaultQueryStateResponse.fromXml(xmlResponse);
			Assert.assertNotNull(response.getDate());
			Assert.assertEquals(response.getAnswerCode(), new Integer(2));
			Assert.assertNotNull(response.getErrorCode());
			Assert.assertEquals(response.getErrorCode(), new Integer(1007));
			Assert.assertNotNull(response.getErrorMessage());			

			FraudvaultStateOperationResponseContent responseContent = response.getQueryStateResponseContent();
			Assert.assertNotNull(responseContent);			
			Assert.assertNotNull(responseContent.getAnswerCode());
			Assert.assertEquals(responseContent.getAnswerCode(), new Integer(2));			
			Assert.assertNotNull(responseContent.getErrorMessage());
			Assert.assertNotNull(responseContent.getTransactionId());
			Assert.assertEquals(responseContent.getTransactionId(), "456602622111");
			Assert.assertNull(responseContent.getState());
			
		} catch (Exception e) {
			logger.error("Unexpected error testing convertPrevalidationResponseInputMissingTag", e);
		}		
	}

	/**
	 * Validate the data of the IP address location in the evaluation detail.
	 * 
	 * @param evaluationDetail evaluation data.
	 */
	private void validateIpAddressLocation(FraudvaultEvaluationDetail evaluationDetail) {

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
	private void validateIssuerBank(FraudvaultEvaluationDetail evaluationDetail) {

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
	private void validateHeuristicAnalysis(FraudvaultEvaluationDetail evaluationDetail) {

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
	private void validateTriggeredRules(FraudvaultEvaluationDetail evaluationDetail) {

		List<TriggeredRule> triggeredRules = evaluationDetail.getRules();
		Assert.assertNotNull(triggeredRules);
		Assert.assertEquals(triggeredRules.size(), 2);
		TriggeredRule rule1 = triggeredRules.get(0);
		Assert.assertEquals(rule1.getRuleName(), "regla_test [MONITOREAR]");
		Assert.assertEquals(rule1.getFilteredAttributeName(), "Documento del comprador");
		Assert.assertEquals(rule1.getRuleConfiguredValue(), "12345678");
		Assert.assertEquals(rule1.getFilteredValue(), "12345678");
		Assert.assertEquals(rule1.getOperator(), "IGUAL");

		TriggeredRule rule2 = triggeredRules.get(1);
		Assert.assertEquals(rule2.getRuleName(), "det_cedula");
		Assert.assertEquals(rule2.getFilteredAttributeName(), "Documento Comprador");
		Assert.assertEquals(rule2.getRuleConfiguredValue(), "53140140");
		Assert.assertEquals(rule2.getFilteredValue(), "53140140");
		Assert.assertEquals(rule2.getOperator(), "IGUAL");
	}

	/**
	 * Validate the data of the lists in the evaluation detail.
	 * 
	 * @param evaluationDetail evaluation data.
	 */
	private void validateControlListsInformation(FraudvaultEvaluationDetail evaluationDetail) {

		ControlListsInformation listsInformation = evaluationDetail.getControlListsInformation();
		Assert.assertNotNull(listsInformation);
		ListMatch whiteListsMatching = listsInformation.getWhiteListsMatching();
		Assert.assertNotNull(whiteListsMatching);
		Assert.assertEquals(whiteListsMatching.isMatch(), false);

		ListMatch blackListsMatching = listsInformation.getBlackListsMatching();
		Assert.assertNotNull(blackListsMatching);
		Assert.assertEquals(blackListsMatching.isMatch(), true);
		Assert.assertEquals(blackListsMatching.getParameter(), "correoElectronico");

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
			entrada = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF-8"));
			while ((res = entrada.readLine()) != null) {
				contenidoXML.append(res);
			}
		}
		return contenidoXML.toString();
	}

}
