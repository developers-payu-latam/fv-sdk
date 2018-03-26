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
import com.payulatam.fraudvault.model.response.soap.EvaluationSoapWrapper;
import com.payulatam.fraudvault.model.response.soap.EvaluationDetailSoapWrapper;
import com.payulatam.fraudvault.model.response.soap.PosvalidationResponsesSoapWrapper;
import com.payulatam.fraudvault.model.response.soap.PrevalidationResponseSoapWrapper;
import com.payulatam.fraudvault.model.response.soap.QueryStateResponseSoapWrapper;
import com.payulatam.fraudvault.model.response.soap.StateOperationResponseContentSoapWrapper;
import com.payulatam.fraudvault.model.response.soap.UpdateStateResponseSoapWrapper;

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

		PrevalidationResponseSoapWrapper response = getPrevalidationresponse();
		FraudvaultPrevalidationResponse fvPrevalidation = ResponseConverter.getFraudvaultPrevalidation(response);

		Assert.assertEquals(fvPrevalidation.getGeneralAnswerCode(), GeneralAnswerCode.getById(response.getGeneralAnswerCode()));
		Assert.assertEquals(fvPrevalidation.getGeneralErrorCode(), null);
		Assert.assertEquals(fvPrevalidation.getGeneralErrorMessage(),response.getGeneralErrorMessage());
		Assert.assertEquals(fvPrevalidation.getResponseDate(), response.getResponseDate());

		EvaluationSoapWrapper evaluation = response.getEvaluation();
		Assert.assertNotNull(evaluation);
		Assert.assertEquals(fvPrevalidation.getDecision(), PrevalidationDecision.getById(evaluation.getDecision()));

		EvaluationDetailSoapWrapper responseDetail = evaluation.getDetail();
		Assert.assertNotNull(responseDetail);
		Assert.assertEquals(fvPrevalidation.getTransactionId(), responseDetail.getTransactionId());
		Assert.assertEquals(fvPrevalidation.getEvaluationTime(),responseDetail.getEvaluationTime());
		Assert.assertEquals(fvPrevalidation.getErrorCodeIdValue(), responseDetail.getErrorCode());
		Assert.assertEquals(fvPrevalidation.getErrorCode(), ErrorCode.getById(responseDetail.getErrorCode()));
		Assert.assertEquals(fvPrevalidation.getErrorMessage(), responseDetail.getErrorMessage());
		Assert.assertEquals(fvPrevalidation.getIspName(), responseDetail.getIspName());
		Assert.assertEquals(fvPrevalidation.getSimilarTransactionsNumber(),responseDetail.getSimilarTransactionsNumber());
		Assert.assertEquals(fvPrevalidation.isIpProxy(), responseDetail.isIpProxy());
		Assert.assertEquals(fvPrevalidation.isValidatedWithCreditBureau(), responseDetail.isValidatedWithCreditBureau());

		ListMatch blackLists = fvPrevalidation.getBlackListsMatching();
		Assert.assertNotNull(blackLists);
		Assert.assertEquals(blackLists.isMatch(),
				responseDetail.getControlListsInformation().getBlackListsMatching().isMatch());
		Assert.assertEquals(blackLists.getTransactionFieldName(),
				responseDetail.getControlListsInformation().getBlackListsMatching().getTransactionFieldName());
		Assert.assertNull(fvPrevalidation.getTemporaryListsMatching());
		Assert.assertNull(fvPrevalidation.getWhiteListsMatching());

		Assert.assertEquals(fvPrevalidation.getNeuronalNetworkEmailScore(),
				responseDetail.getHeuristicAnalysis().getEmailScore());
		Assert.assertEquals(fvPrevalidation.getNeuronalNetworkGeneralScore(),
				responseDetail.getHeuristicAnalysis().getGeneralScore());

		List<Action> actions = fvPrevalidation.getActions();
		Assert.assertNotNull(actions);
		Assert.assertEquals(actions.get(0), Action.getByKey(responseDetail.getActions().get(0)));
		Assert.assertNotNull(fvPrevalidation.getActionsKeysValues());
		Assert.assertEquals(fvPrevalidation.getActionsKeysValues().get(0), responseDetail.getActions().get(0));

		Assert.assertNotNull(fvPrevalidation.getAlerts());		
		Assert.assertEquals(fvPrevalidation.getAlerts().get(0), Alert.getByKey(responseDetail.getAlerts().get(0)));
		Assert.assertNotNull(fvPrevalidation.getAlertsKeysValues());
		Assert.assertEquals(fvPrevalidation.getAlertsKeysValues().get(0), responseDetail.getAlerts().get(0));

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
		Assert.assertEquals(triggeredRule.getTransactionFieldName(),
				triggeredRuleResponse.getTransactionFieldName());
		Assert.assertEquals(triggeredRule.getTransactionFieldValue(),
				triggeredRuleResponse.getTransactionFieldValue());
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

		PosvalidationResponsesSoapWrapper response = getPosvalidationResponse();
		FraudvaultPosvalidationResponse fvPosvalidation = ResponseConverter.getFraudvaultPosvalidation(response);
		Assert.assertEquals(fvPosvalidation.getGeneralAnswerCode(), GeneralAnswerCode.getById(response.getGeneralAnswerCode()));
		Assert.assertEquals(fvPosvalidation.getGeneralErrorCode(), null);
		Assert.assertEquals(fvPosvalidation.getGeneralErrorMessage(),response.getGeneralErrorMessage());
		Assert.assertEquals(fvPosvalidation.getResponseDate(), response.getResponseDate());

		EvaluationSoapWrapper evaluation = response.getEvaluation();
		Assert.assertNotNull(evaluation);
		Assert.assertEquals(fvPosvalidation.getDecision(), PosvalidationDecision.getById(evaluation.getDecision()));

		EvaluationDetailSoapWrapper responseDetail = evaluation.getDetail();
		Assert.assertNotNull(responseDetail);
		Assert.assertEquals(fvPosvalidation.getTransactionId(), responseDetail.getTransactionId());
		Assert.assertEquals(fvPosvalidation.getEvaluationTime(),
				responseDetail.getEvaluationTime());
		Assert.assertEquals(fvPosvalidation.getErrorCodeIdValue(), responseDetail.getErrorCode());
		Assert.assertEquals(fvPosvalidation.getErrorCode(), ErrorCode.getById(responseDetail.getErrorCode()));
		Assert.assertEquals(fvPosvalidation.getErrorMessage(), responseDetail.getErrorMessage());
		Assert.assertEquals(fvPosvalidation.getSimilarTransactionsNumber(),
				responseDetail.getSimilarTransactionsNumber());

		List<Action> actions = fvPosvalidation.getActions();
		Assert.assertNotNull(actions);
		Assert.assertEquals(actions.get(0), Action.getByKey(responseDetail.getActions().get(0)));
		Assert.assertNotNull(fvPosvalidation.getActionsKeysValues());
		Assert.assertEquals(fvPosvalidation.getActionsKeysValues().get(0), responseDetail.getActions().get(0));

		Assert.assertNotNull(fvPosvalidation.getTriggeredRules());
		TriggeredRule triggeredRule = fvPosvalidation.getTriggeredRules().get(0);
		TriggeredRule triggeredRuleResponse = responseDetail.getRules().get(0);
		Assert.assertEquals(triggeredRule.getTransactionFieldName(),
				triggeredRuleResponse.getTransactionFieldName());
		Assert.assertEquals(triggeredRule.getTransactionFieldValue(),
				triggeredRuleResponse.getTransactionFieldValue());
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

		QueryStateResponseSoapWrapper response = new QueryStateResponseSoapWrapper();
		response.setGeneralAnswerCode(2);
		response.setGeneralErrorCode(1001);
		response.setGeneralErrorMessage("msg-error");
		StateOperationResponseContentSoapWrapper queryStateResponseContent = new StateOperationResponseContentSoapWrapper();
		queryStateResponseContent.setAnswerCode(2);
		queryStateResponseContent.setErrorMessage("error-msg");
		queryStateResponseContent.setState(8);
		queryStateResponseContent.setTransactionId("trx-test-query");
		response.setQueryStateResponseContent(queryStateResponseContent);
		response.setResponseDate(new Date());

		FraudvaultStateQueryResponse fvQueryState = ResponseConverter.getFraudvaultStateQuery(response);
		Assert.assertTrue(fvQueryState.hasError());
		Assert.assertEquals(fvQueryState.getGeneralAnswerCode(), GeneralAnswerCode.getById(response.getGeneralAnswerCode()));
		Assert.assertEquals(fvQueryState.getGeneralErrorCode(), GeneralErrorCode.getById(response.getGeneralErrorCode()));
		Assert.assertEquals(fvQueryState.getGeneralErrorMessage(),
				response.getGeneralErrorMessage());
		Assert.assertEquals(fvQueryState.getResponseDate(), response.getResponseDate());
		Assert.assertEquals(fvQueryState.getTransactionId(),
				queryStateResponseContent.getTransactionId());
		Assert.assertEquals(fvQueryState.getErrorMessage(),
				queryStateResponseContent.getErrorMessage());
		Assert.assertEquals(fvQueryState.getState(), TransactionState.getById(queryStateResponseContent.getState()));
		Assert.assertEquals(fvQueryState.getAnswerCode(),GeneralAnswerCode.getById(queryStateResponseContent.getAnswerCode()));
	}

	/**
	 * Tests the conversion from a Fraudvault update state response mapped with the XML response structure 
	 * into simpler object with the Fraudvault update state data.
	 */
	@Test
	public void getFraudvaultUpdateStateTest() {

		UpdateStateResponseSoapWrapper response = new UpdateStateResponseSoapWrapper();
		response.setGeneralAnswerCode(2);
		response.setGeneralErrorCode(1001);
		response.setGeneralErrorMessage("msg-error");
		response.setResponseDate(new Date());
		StateOperationResponseContentSoapWrapper updateStateResponseContent = new StateOperationResponseContentSoapWrapper();
		updateStateResponseContent.setAnswerCode(2);
		updateStateResponseContent.setErrorMessage("error-msg");
		updateStateResponseContent.setState(8);
		updateStateResponseContent.setTransactionId("trx-test-update");
		response.setUpdateStateResponseContent(updateStateResponseContent);

		FraudvaultStateUpdateResponse fvUpdateState = ResponseConverter.getFraudvaultStateUpdate(response);
		Assert.assertTrue(fvUpdateState.hasError());
		Assert.assertEquals(fvUpdateState.getGeneralAnswerCode(), GeneralAnswerCode.getById(response.getGeneralAnswerCode()));
		Assert.assertEquals(fvUpdateState.getGeneralErrorCode(), GeneralErrorCode.getById(response.getGeneralErrorCode()));
		Assert.assertEquals(fvUpdateState.getGeneralErrorMessage(),
				response.getGeneralErrorMessage());
		Assert.assertEquals(fvUpdateState.getResponseDate(), response.getResponseDate());
		Assert.assertEquals(fvUpdateState.getTransactionId(),
				updateStateResponseContent.getTransactionId());
		Assert.assertEquals(fvUpdateState.getErrorMessage(),
				updateStateResponseContent.getErrorMessage());
		Assert.assertEquals(fvUpdateState.getAnswerCode(), GeneralAnswerCode.getById(updateStateResponseContent.getAnswerCode()));
	}

	/**
	 * Gets a <code>PosvalidationResponsesSoapWrapper</code> with data for testing.
	 * @return the <code>PosvalidationResponsesSoapWrapper</code> with data for testing.
	 */
	private PosvalidationResponsesSoapWrapper getPosvalidationResponse() {

		PosvalidationResponsesSoapWrapper response = new PosvalidationResponsesSoapWrapper();
		response.setGeneralAnswerCode(1);
		EvaluationSoapWrapper evaluation = new EvaluationSoapWrapper();
		evaluation.setDecision(5);
		EvaluationDetailSoapWrapper detail = new EvaluationDetailSoapWrapper();
		detail.setTransactionId("trx-test-pos");
		List<String> actions = new ArrayList<>();
		actions.add("REVERSAR");
		detail.setActions(actions);
		detail.setErrorCode(2001);
		detail.setErrorMessage("error");
		detail.setEvaluationTime(7);

		List<TriggeredRule> rules = new ArrayList<>();
		TriggeredRule rule = new TriggeredRule();
		rule.setTransactionFieldName("documento_comprador");
		rule.setTransactionFieldValue("20444444");
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
	 * Gets a <code>PrevalidationResponseSoapWrapper</code> with data for testing.
	 * @return the <code>PrevalidationResponseSoapWrapper</code> with data for testing.
	 */
	private PrevalidationResponseSoapWrapper getPrevalidationresponse() {

		PrevalidationResponseSoapWrapper response = new PrevalidationResponseSoapWrapper();
		response.setGeneralAnswerCode(1);
		EvaluationSoapWrapper evaluation = new EvaluationSoapWrapper();
		evaluation.setDecision(2);
		EvaluationDetailSoapWrapper detail = new EvaluationDetailSoapWrapper();
		List<String> actions = new ArrayList<>();
		actions.add("RECHAZAR");
		detail.setActions(actions);
		List<String> alerts = new ArrayList<>();
		alerts.add("IP_INTERNACIONAL");
		detail.setAlerts(alerts);
		ControlListsInformation controlListsInformation = new ControlListsInformation();
		ListMatch blackListsMatching = new ListMatch();
		blackListsMatching.setMatch(true);
		blackListsMatching.setTransactionFieldName("correo_electronico");
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
		rule.setTransactionFieldName("telefono_oficina");
		rule.setTransactionFieldValue("4111111");
		rule.setRuleName("rule_test");
		rule.setRuleConfiguredValue("4111111");
		rule.setOperator("IGUAL");
		rules.add(rule);
		detail.setRules(rules);
		detail.setSimilarTransactionsNumber(7);
		detail.setTransactionId("trx-id-test");
		detail.setValidatedWithCreditBureau(true);
		evaluation.setDetail(detail);
		response.setEvaluation(evaluation);
		response.setGeneralErrorCode(null);
		response.setGeneralErrorMessage(null);
		response.setResponseDate(new Date());
		return response;
	}

}
