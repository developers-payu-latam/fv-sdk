/**
 * PayU Latam - Copyright (c) 2013 - 2018
 * http://www.payu.com.co
 * Date: 29 de ene. de 2018
 */
package com.payulatam.fraudvault.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.payulatam.fraudvault.client.retrofit.converter.ResponseConverter;
import com.payulatam.fraudvault.model.response.*;
import com.payulatam.fraudvault.model.response.xml.ControlListsInformation;
import com.payulatam.fraudvault.model.response.xml.FraudvaultEvaluation;
import com.payulatam.fraudvault.model.response.xml.FraudvaultEvaluationDetail;
import com.payulatam.fraudvault.model.response.xml.FraudvaultPosvalidationResponse;
import com.payulatam.fraudvault.model.response.xml.FraudvaultPrevalidationResponse;
import com.payulatam.fraudvault.model.response.xml.FraudvaultQueryStateResponse;
import com.payulatam.fraudvault.model.response.xml.FraudvaultStateOperationResponseContent;
import com.payulatam.fraudvault.model.response.xml.FraudvaultUpdateStateResponse;
import com.payulatam.fraudvault.model.response.xml.HeuristicAnalysis;
import com.payulatam.fraudvault.model.response.xml.IpAddressLocation;
import com.payulatam.fraudvault.model.response.xml.IssuerBank;
import com.payulatam.fraudvault.model.response.xml.ListMatch;
import com.payulatam.fraudvault.model.response.xml.TriggeredRule;

/**
 * Test for the conversions from the objects with the XML response structure to Fraudvault response
 * objects.
 */
public class ResponseConverterTest {

	/**
	 * Tests the conversion from a Fraudvault prevalidation response mapped with the XML response 
	 * structure into simpler object with the Fraudvault prevalidation data.
	 */
	@Test
	public void getFraudvaultPrevalidationTest() {

		FraudvaultPrevalidationResponse response = getPrevalidationresponse();
		FraudvaultPrevalidation fvPrevalidation = ResponseConverter.getFraudvaultPrevalidation(response);

		Assert.assertEquals(fvPrevalidation.getGeneralAnswerCode(),response.getGeneralAnswerCode());
		Assert.assertEquals(fvPrevalidation.getGeneralErrorCode(), response.getGeneralErrorCode());
		Assert.assertEquals(fvPrevalidation.getGeneralErrorMessage(),response.getGeneralErrorMessage());
		Assert.assertEquals(fvPrevalidation.getResponseDate(), response.getResponseDate());

		FraudvaultEvaluation evaluation = response.getEvaluation();
		Assert.assertNotNull(evaluation);
		Assert.assertEquals(fvPrevalidation.getDecision(), evaluation.getDecision());

		FraudvaultEvaluationDetail responseDetail = evaluation.getDetail();
		Assert.assertNotNull(responseDetail);
		Assert.assertEquals(fvPrevalidation.getTransactionId(), responseDetail.getTransactionId());
		Assert.assertEquals(fvPrevalidation.getEvaluationTime(),responseDetail.getEvaluationTime());
		Assert.assertEquals(fvPrevalidation.getErrorCode(), responseDetail.getErrorCode());
		Assert.assertEquals(fvPrevalidation.getErrorMessage(), responseDetail.getErrorMessage());
		Assert.assertEquals(fvPrevalidation.getIspName(), responseDetail.getIspName());
		Assert.assertEquals(fvPrevalidation.getSimilarTransactionsNumber(),responseDetail.getSimilarTransactionsNumber());
		Assert.assertEquals(fvPrevalidation.isIpProxy(), responseDetail.isIpProxy());
		Assert.assertEquals(fvPrevalidation.isValidateCentralRisk(),responseDetail.isValidateCentralRisk());

		ListMatch blackLists = fvPrevalidation.getBlackListsMatching();
		Assert.assertNotNull(blackLists);
		Assert.assertEquals(blackLists.isMatch(),
				responseDetail.getControlListsInformation().getBlackListsMatching().isMatch());
		Assert.assertEquals(blackLists.getParameter(),
				responseDetail.getControlListsInformation().getBlackListsMatching().getParameter());
		Assert.assertNull(fvPrevalidation.getTemporaryListsMatching());
		Assert.assertNull(fvPrevalidation.getWhiteListsMatching());

		Assert.assertEquals(fvPrevalidation.getNeuronalNetworkEmailScore(),
				responseDetail.getHeuristicAnalysis().getEmailScore());
		Assert.assertEquals(fvPrevalidation.getNeuronalNetworkGeneralScore(),
				responseDetail.getHeuristicAnalysis().getGeneralScore());

		List<String> actions = fvPrevalidation.getActions();
		Assert.assertNotNull(actions);
		Assert.assertEquals(actions.get(0), responseDetail.getActions().get(0));

		Assert.assertNotNull(fvPrevalidation.getAlerts());
		Assert.assertEquals(fvPrevalidation.getAlerts().get(0), responseDetail.getAlerts().get(0));

		IpAddressLocation ipAddressLocation = fvPrevalidation.getIpAddressLocation();
		Assert.assertNotNull(ipAddressLocation);
		Assert.assertEquals(ipAddressLocation.getIpCountry(),
				responseDetail.getIpAddressLocation().getIpCountry());
		Assert.assertEquals(ipAddressLocation.getIpLatitude(),
				responseDetail.getIpAddressLocation().getIpLatitude());
		Assert.assertEquals(ipAddressLocation.getIpLongitude(),
				responseDetail.getIpAddressLocation().getIpLongitude());

		IssuerBank issuerBank = fvPrevalidation.getIssuerBank();
		Assert.assertNotNull(issuerBank);
		Assert.assertEquals(issuerBank.getBank(), responseDetail.getIssuerBank().getBank());
		Assert.assertEquals(issuerBank.getCountry(), responseDetail.getIssuerBank().getCountry());
		Assert.assertEquals(issuerBank.getPhoneNumber(),
				responseDetail.getIssuerBank().getPhoneNumber());

		Assert.assertNotNull(fvPrevalidation.getTriggeredRules());
		TriggeredRule triggeredRule = fvPrevalidation.getTriggeredRules().get(0);
		TriggeredRule triggeredRuleResponse = responseDetail.getRules().get(0);
		Assert.assertEquals(triggeredRule.getFilteredAttributeName(),
				triggeredRuleResponse.getFilteredAttributeName());
		Assert.assertEquals(triggeredRule.getFilteredValue(),
				triggeredRuleResponse.getFilteredValue());
		Assert.assertEquals(triggeredRule.getOperator(), triggeredRuleResponse.getOperator());
		Assert.assertEquals(triggeredRule.getRuleConfiguredValue(),
				triggeredRuleResponse.getRuleConfiguredValue());
		Assert.assertEquals(triggeredRule.getRuleName(), triggeredRuleResponse.getRuleName());
	}

	/**
	 * Tests the conversion from a Fraudvault posvalidation response mapped with the XML response structure 
	 * into simpler object with the Fraudvault posvalidation data.
	 */
	@Test
	public void getFraudvaultPosvalidationTest() {

		FraudvaultPosvalidationResponse response = getPosvalidationResponse();
		FraudvaultPosvalidation fvPosvalidation = ResponseConverter.getFraudvaultPosvalidation(response);
		Assert.assertEquals(fvPosvalidation.getGeneralAnswerCode(),response.getGeneralAnswerCode());
		Assert.assertEquals(fvPosvalidation.getGeneralErrorCode(), response.getGeneralErrorCode());
		Assert.assertEquals(fvPosvalidation.getGeneralErrorMessage(),response.getGeneralErrorMessage());
		Assert.assertEquals(fvPosvalidation.getResponseDate(), response.getResponseDate());

		FraudvaultEvaluation evaluation = response.getEvaluation();
		Assert.assertNotNull(evaluation);
		Assert.assertEquals(fvPosvalidation.getDecision(), evaluation.getDecision());

		FraudvaultEvaluationDetail responseDetail = evaluation.getDetail();
		Assert.assertNotNull(responseDetail);
		Assert.assertEquals(fvPosvalidation.getTransactionId(), responseDetail.getTransactionId());
		Assert.assertEquals(fvPosvalidation.getEvaluationTime(),
				responseDetail.getEvaluationTime());
		Assert.assertEquals(fvPosvalidation.getErrorCode(), responseDetail.getErrorCode());
		Assert.assertEquals(fvPosvalidation.getErrorMessage(), responseDetail.getErrorMessage());
		Assert.assertEquals(fvPosvalidation.getSimilarTransactionsNumber(),
				responseDetail.getSimilarTransactionsNumber());

		List<String> actions = fvPosvalidation.getActions();
		Assert.assertNotNull(actions);
		Assert.assertEquals(actions.get(0), responseDetail.getActions().get(0));

		Assert.assertNotNull(fvPosvalidation.getTriggeredRules());
		TriggeredRule triggeredRule = fvPosvalidation.getTriggeredRules().get(0);
		TriggeredRule triggeredRuleResponse = responseDetail.getRules().get(0);
		Assert.assertEquals(triggeredRule.getFilteredAttributeName(),
				triggeredRuleResponse.getFilteredAttributeName());
		Assert.assertEquals(triggeredRule.getFilteredValue(),
				triggeredRuleResponse.getFilteredValue());
		Assert.assertEquals(triggeredRule.getOperator(), triggeredRuleResponse.getOperator());
		Assert.assertEquals(triggeredRule.getRuleConfiguredValue(),
				triggeredRuleResponse.getRuleConfiguredValue());
		Assert.assertEquals(triggeredRule.getRuleName(), triggeredRuleResponse.getRuleName());
	}

	/**
	 * Tests the conversion from a Fraudvault State Query response mapped with the XML response structure 
	 * into simpler object with the Fraudvault State Query data.
	 */
	@Test
	public void getFraudvaultQueryStateTest() {

		FraudvaultQueryStateResponse response = new FraudvaultQueryStateResponse();
		response.setGeneralAnswerCode(2);
		response.setGeneralErrorCode(1001);
		response.setGeneralErrorMessage("msg-error");
		FraudvaultStateOperationResponseContent queryStateResponseContent = new FraudvaultStateOperationResponseContent();
		queryStateResponseContent.setAnswerCode(2);
		queryStateResponseContent.setErrorMessage("error-msg");
		queryStateResponseContent.setState(8);
		queryStateResponseContent.setTransactionId("trx-test-query");
		response.setQueryStateResponseContent(queryStateResponseContent);
		response.setResponseDate(new Date());

		FraudvaultStateQuery fvQueryState = ResponseConverter.getFraudvaultStateQuery(response);
		Assert.assertEquals(fvQueryState.getGeneralAnswerCode(), response.getGeneralAnswerCode());
		Assert.assertEquals(fvQueryState.getGeneralErrorCode(), response.getGeneralErrorCode());
		Assert.assertEquals(fvQueryState.getGeneralErrorMessage(),
				response.getGeneralErrorMessage());
		Assert.assertEquals(fvQueryState.getResponseDate(), response.getResponseDate());
		Assert.assertEquals(fvQueryState.getTransactionId(),
				queryStateResponseContent.getTransactionId());
		Assert.assertEquals(fvQueryState.getErrorMessage(),
				queryStateResponseContent.getErrorMessage());
		Assert.assertEquals(fvQueryState.getState(), queryStateResponseContent.getState());
		Assert.assertEquals(fvQueryState.getAnswerCode(),
				queryStateResponseContent.getAnswerCode());
	}

	/**
	 * Tests the conversion from a Fraudvault update state response mapped with the XML response structure 
	 * into simpler object with the Fraudvault update state data.
	 */
	@Test
	public void getFraudvaultUpdateStateTest() {

		FraudvaultUpdateStateResponse response = new FraudvaultUpdateStateResponse();
		response.setGeneralAnswerCode(2);
		response.setGeneralErrorCode(1001);
		response.setGeneralErrorMessage("msg-error");
		response.setResponseDate(new Date());
		FraudvaultStateOperationResponseContent updateStateResponseContent = new FraudvaultStateOperationResponseContent();
		updateStateResponseContent.setAnswerCode(2);
		updateStateResponseContent.setErrorMessage("error-msg");
		updateStateResponseContent.setState(8);
		updateStateResponseContent.setTransactionId("trx-test-update");
		response.setUpdateStateResponseContent(updateStateResponseContent);

		FraudvaultStateUpdate fvUpdateState = ResponseConverter.getFraudvaultStateUpdate(response);
		Assert.assertEquals(fvUpdateState.getGeneralAnswerCode(), response.getGeneralAnswerCode());
		Assert.assertEquals(fvUpdateState.getGeneralErrorCode(), response.getGeneralErrorCode());
		Assert.assertEquals(fvUpdateState.getGeneralErrorMessage(),
				response.getGeneralErrorMessage());
		Assert.assertEquals(fvUpdateState.getResponseDate(), response.getResponseDate());
		Assert.assertEquals(fvUpdateState.getTransactionId(),
				updateStateResponseContent.getTransactionId());
		Assert.assertEquals(fvUpdateState.getErrorMessage(),
				updateStateResponseContent.getErrorMessage());
		Assert.assertEquals(fvUpdateState.getAnswerCode(),
				updateStateResponseContent.getAnswerCode());
	}

	/**
	 * Gets a <code>FraudvaultPosvalidationResponse</code> with data for testing.
	 * @return the <code>FraudvaultPosvalidationResponse</code> with data for testing.
	 */
	private FraudvaultPosvalidationResponse getPosvalidationResponse() {

		FraudvaultPosvalidationResponse response = new FraudvaultPosvalidationResponse();
		response.setGeneralAnswerCode(1);
		FraudvaultEvaluation evaluation = new FraudvaultEvaluation();
		evaluation.setDecision(5);
		FraudvaultEvaluationDetail detail = new FraudvaultEvaluationDetail();
		detail.setTransactionId("trx-test-pos");
		List<String> actions = new ArrayList<>();
		actions.add("REVERSAR");
		detail.setActions(actions);
		detail.setErrorCode(2001);
		detail.setErrorMessage("error");
		detail.setEvaluationTime(7);

		List<TriggeredRule> rules = new ArrayList<>();
		TriggeredRule rule = new TriggeredRule();
		rule.setFilteredAttributeName("documento_comprador");
		rule.setFilteredValue("20444444");
		rule.setRuleName("rule_test_post");
		rule.setRuleConfiguredValue("20444444");
		rule.setOperator("IGUAL");
		rules.add(rule);
		detail.setRules(rules);

		detail.setSimilarTransactionsNumber(3);
		evaluation.setDetail(detail);
		response.setEvaluation(evaluation);
		response.setGeneralErrorCode(null);
		response.setGeneralErrorMessage(null);
		response.setResponseDate(new Date());
		return response;
	}

	/**
	 * Gets a <code>FraudvaultPrevalidationResponse</code> with data for testing.
	 * @return the <code>FraudvaultPrevalidationResponse</code> with data for testing.
	 */
	private FraudvaultPrevalidationResponse getPrevalidationresponse() {

		FraudvaultPrevalidationResponse response = new FraudvaultPrevalidationResponse();
		response.setGeneralAnswerCode(1);
		FraudvaultEvaluation evaluation = new FraudvaultEvaluation();
		evaluation.setDecision(2);
		FraudvaultEvaluationDetail detail = new FraudvaultEvaluationDetail();
		List<String> actions = new ArrayList<>();
		actions.add("RECHAZAR");
		detail.setActions(actions);
		List<String> alerts = new ArrayList<>();
		alerts.add("IP_INTERNACIONAL");
		detail.setAlerts(alerts);
		ControlListsInformation controlListsInformation = new ControlListsInformation();
		ListMatch blackListsMatching = new ListMatch();
		blackListsMatching.setMatch(true);
		blackListsMatching.setParameter("correo_electronico");
		controlListsInformation.setBlackListsMatching(blackListsMatching);
		detail.setControlListsInformation(controlListsInformation);
		detail.setErrorCode(null);
		detail.setErrorMessage(null);
		detail.setEvaluationTime(10);
		HeuristicAnalysis heuristicAnalysis = new HeuristicAnalysis();
		heuristicAnalysis.setGeneralScore(0.6);
		heuristicAnalysis.setEmailScore(0.2);
		detail.setHeuristicAnalysis(heuristicAnalysis);
		IpAddressLocation ipAddressLocation = new IpAddressLocation();
		ipAddressLocation.setIpCountryCode("CO");
		ipAddressLocation.setIpLatitude(19.36);
		ipAddressLocation.setIpLongitude(20.20);
		detail.setIpAddressLocation(ipAddressLocation);
		detail.setIpProxy(false);
		detail.setIspName("ISP test");
		IssuerBank issuerBank = new IssuerBank();
		issuerBank.setBank("Banco test");
		issuerBank.setCountry("CO");
		detail.setIssuerBank(issuerBank);
		List<TriggeredRule> rules = new ArrayList<>();
		TriggeredRule rule = new TriggeredRule();
		rule.setFilteredAttributeName("telefono_oficina");
		rule.setFilteredValue("4111111");
		rule.setRuleName("rule_test");
		rule.setRuleConfiguredValue("4111111");
		rule.setOperator("IGUAL");
		rules.add(rule);
		detail.setRules(rules);
		detail.setSimilarTransactionsNumber(7);
		detail.setTransactionId("trx-id-test");
		detail.setValidateCentralRisk(true);
		evaluation.setDetail(detail);
		response.setEvaluation(evaluation);
		response.setGeneralErrorCode(null);
		response.setGeneralErrorMessage(null);
		response.setResponseDate(new Date());
		return response;
	}

}
