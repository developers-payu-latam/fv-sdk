/**
 * PayU Latam - Copyright (c) 2013 - 2018
 * http://www.payu.com.co
 * Date: 29 de ene. de 2018
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

package com.payulatam.fd.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.payulatam.fd.client.retrofit.converter.ResponseConverter;
import com.payulatam.fd.model.response.*;
import com.payulatam.fd.model.response.soap.EvaluationDetailSoapWrapper;
import com.payulatam.fd.model.response.soap.EvaluationSoapWrapper;
import com.payulatam.fd.model.response.soap.PosvalidationResponsesSoapWrapper;
import com.payulatam.fd.model.response.soap.PrevalidationResponseSoapWrapper;
import com.payulatam.fd.model.response.soap.QueryStateResponseSoapWrapper;
import com.payulatam.fd.model.response.soap.StateOperationResponseContentSoapWrapper;
import com.payulatam.fd.model.response.soap.UpdateStateResponseSoapWrapper;

/**
 * Test for the conversions from the objects with the XML response structure to simpler objects with the result data.
 */
public class ResponseConverterTest {

	/**
	 * Tests the conversion from a prevalidation response mapped with the XML response 
	 * structure into simpler object with the prevalidation result data.
	 */
	@Test
	public void getFDPrevalidationResponseTest() {

		PrevalidationResponseSoapWrapper response = getPrevalidationresponse();
		FDPrevalidationResponse fdPrevalidationResponse = ResponseConverter.getFDPrevalidationResponse(response);

		Assert.assertEquals(fdPrevalidationResponse.getGeneralAnswerCode(), GeneralAnswerCode.getById(response.getGeneralAnswerCode()));
		Assert.assertEquals(fdPrevalidationResponse.getGeneralErrorCode(), null);
		Assert.assertEquals(fdPrevalidationResponse.getGeneralErrorMessage(),response.getGeneralErrorMessage());
		Assert.assertEquals(fdPrevalidationResponse.getResponseDate(), response.getResponseDate());

		EvaluationSoapWrapper evaluation = response.getEvaluation();
		Assert.assertNotNull(evaluation);
		Assert.assertEquals(fdPrevalidationResponse.getDecision(), PrevalidationDecision.getById(evaluation.getDecision()));

		EvaluationDetailSoapWrapper responseDetail = evaluation.getDetail();
		Assert.assertNotNull(responseDetail);
		Assert.assertEquals(fdPrevalidationResponse.getTransactionId(), responseDetail.getTransactionId());
		Assert.assertEquals(fdPrevalidationResponse.getEvaluationTime(),responseDetail.getEvaluationTime());
		Assert.assertEquals(fdPrevalidationResponse.getErrorCodeIdValue(), responseDetail.getErrorCode());
		Assert.assertEquals(fdPrevalidationResponse.getErrorCode(), ErrorCode.getById(responseDetail.getErrorCode()));
		Assert.assertEquals(fdPrevalidationResponse.getErrorMessage(), responseDetail.getErrorMessage());
		Assert.assertEquals(fdPrevalidationResponse.getIspName(), responseDetail.getIspName());
		Assert.assertEquals(fdPrevalidationResponse.getSimilarTransactionsNumber(),responseDetail.getSimilarTransactionsNumber());
		Assert.assertEquals(fdPrevalidationResponse.isIpProxy(), responseDetail.isIpProxy());
		Assert.assertEquals(fdPrevalidationResponse.isValidatedWithCreditBureau(), responseDetail.isValidatedWithCreditBureau());

		ListMatch blackLists = fdPrevalidationResponse.getBlackListsMatching();
		Assert.assertNotNull(blackLists);
		Assert.assertEquals(blackLists.isMatch(),
				responseDetail.getControlListsInformation().getBlackListsMatching().isMatch());
		Assert.assertEquals(blackLists.getTransactionFieldName(),
				responseDetail.getControlListsInformation().getBlackListsMatching().getTransactionFieldName());
		Assert.assertNull(fdPrevalidationResponse.getTemporaryListsMatching());
		Assert.assertNull(fdPrevalidationResponse.getWhiteListsMatching());

		Assert.assertEquals(fdPrevalidationResponse.getNeuronalNetworkEmailScore(),
				responseDetail.getHeuristicAnalysis().getEmailScore());
		Assert.assertEquals(fdPrevalidationResponse.getNeuronalNetworkGeneralScore(),
				responseDetail.getHeuristicAnalysis().getGeneralScore());

		List<Action> actions = fdPrevalidationResponse.getActions();
		Assert.assertNotNull(actions);
		Assert.assertEquals(actions.get(0), Action.getByKey(responseDetail.getActions().get(0)));
		Assert.assertNotNull(fdPrevalidationResponse.getActionsKeysValues());
		Assert.assertEquals(fdPrevalidationResponse.getActionsKeysValues().get(0), responseDetail.getActions().get(0));

		Assert.assertNotNull(fdPrevalidationResponse.getAlerts());		
		Assert.assertEquals(fdPrevalidationResponse.getAlerts().get(0), Alert.getByKey(responseDetail.getAlerts().get(0)));
		Assert.assertNotNull(fdPrevalidationResponse.getAlertsKeysValues());
		Assert.assertEquals(fdPrevalidationResponse.getAlertsKeysValues().get(0), responseDetail.getAlerts().get(0));

		IpAddressLocation ipAddressLocation = fdPrevalidationResponse.getIpAddressLocation();
		Assert.assertNotNull(ipAddressLocation);
		Assert.assertEquals(ipAddressLocation.getIpCountry(),
				responseDetail.getIpAddressLocation().getIpCountry());
		Assert.assertEquals(ipAddressLocation.getIpLatitude(),
				responseDetail.getIpAddressLocation().getIpLatitude());
		Assert.assertEquals(ipAddressLocation.getIpLongitude(),
				responseDetail.getIpAddressLocation().getIpLongitude());

		IssuerBank issuerBank = fdPrevalidationResponse.getIssuerBank();
		Assert.assertNotNull(issuerBank);
		Assert.assertEquals(issuerBank.getBank(), responseDetail.getIssuerBank().getBank());
		Assert.assertEquals(issuerBank.getCountry(), responseDetail.getIssuerBank().getCountry());
		Assert.assertEquals(issuerBank.getPhoneNumber(),
				responseDetail.getIssuerBank().getPhoneNumber());

		Assert.assertNotNull(fdPrevalidationResponse.getTriggeredRules());
		TriggeredRule triggeredRule = fdPrevalidationResponse.getTriggeredRules().get(0);
		TriggeredRule triggeredRuleResponse = responseDetail.getRules().get(0);
		TriggeredRuleCondition triggeredRuleCondition=triggeredRuleResponse.getTriggeredRuleCondition(0);
		Assert.assertEquals(triggeredRuleCondition.getTransactionFieldName(),
				triggeredRuleCondition.getTransactionFieldName());
		Assert.assertEquals(triggeredRuleCondition.getTransactionFieldValue(),
				triggeredRuleCondition.getTransactionFieldValue());
		Assert.assertEquals(triggeredRuleCondition.getOperator(), triggeredRuleCondition.getOperator());
		Assert.assertEquals(triggeredRuleCondition.getRuleConfiguredValue(),
				triggeredRuleCondition.getRuleConfiguredValue());
		Assert.assertEquals(triggeredRule.getRuleName(), triggeredRuleResponse.getRuleName());
	}

	/**
	 * Tests the conversion from a posvalidation response mapped with the XML response structure 
	 * into simpler object with the posvalidation result data.
	 */
	@Test
	public void getFDPosvalidationResponseTest() {

		PosvalidationResponsesSoapWrapper response = getPosvalidationResponse();
		FDPosvalidationResponse fdPosvalidationResponse = ResponseConverter.getFDPosvalidationResponse(response);
		Assert.assertEquals(fdPosvalidationResponse.getGeneralAnswerCode(), GeneralAnswerCode.getById(response.getGeneralAnswerCode()));
		Assert.assertEquals(fdPosvalidationResponse.getGeneralErrorCode(), null);
		Assert.assertEquals(fdPosvalidationResponse.getGeneralErrorMessage(),response.getGeneralErrorMessage());
		Assert.assertEquals(fdPosvalidationResponse.getResponseDate(), response.getResponseDate());

		EvaluationSoapWrapper evaluation = response.getEvaluation();
		Assert.assertNotNull(evaluation);
		Assert.assertEquals(fdPosvalidationResponse.getDecision(), PosvalidationDecision.getById(evaluation.getDecision()));

		EvaluationDetailSoapWrapper responseDetail = evaluation.getDetail();
		Assert.assertNotNull(responseDetail);
		Assert.assertEquals(fdPosvalidationResponse.getTransactionId(), responseDetail.getTransactionId());
		Assert.assertEquals(fdPosvalidationResponse.getEvaluationTime(),
				responseDetail.getEvaluationTime());
		Assert.assertEquals(fdPosvalidationResponse.getErrorCodeIdValue(), responseDetail.getErrorCode());
		Assert.assertEquals(fdPosvalidationResponse.getErrorCode(), ErrorCode.getById(responseDetail.getErrorCode()));
		Assert.assertEquals(fdPosvalidationResponse.getErrorMessage(), responseDetail.getErrorMessage());
		Assert.assertEquals(fdPosvalidationResponse.getSimilarTransactionsNumber(),
				responseDetail.getSimilarTransactionsNumber());

		List<Action> actions = fdPosvalidationResponse.getActions();
		Assert.assertNotNull(actions);
		Assert.assertEquals(actions.get(0), Action.getByKey(responseDetail.getActions().get(0)));
		Assert.assertNotNull(fdPosvalidationResponse.getActionsKeysValues());
		Assert.assertEquals(fdPosvalidationResponse.getActionsKeysValues().get(0), responseDetail.getActions().get(0));

		Assert.assertNotNull(fdPosvalidationResponse.getTriggeredRules());
		TriggeredRule triggeredRule = fdPosvalidationResponse.getTriggeredRules().get(0);
		TriggeredRuleCondition triggeredRuleCondition=triggeredRule.getTriggeredRuleCondition(0);
		TriggeredRule triggeredRuleResponse = responseDetail.getRules().get(0);
		TriggeredRuleCondition triggeredRuleConditionResponse=triggeredRuleResponse.getTriggeredRuleCondition(0);

		Assert.assertEquals(triggeredRuleCondition.getTransactionFieldName(),
				triggeredRuleConditionResponse.getTransactionFieldName());
		Assert.assertEquals(triggeredRuleCondition.getTransactionFieldValue(),
				triggeredRuleConditionResponse.getTransactionFieldValue());
		Assert.assertEquals(triggeredRuleCondition.getOperator(), triggeredRuleConditionResponse.getOperator());
		Assert.assertEquals(triggeredRuleCondition.getRuleConfiguredValue(),
				triggeredRuleConditionResponse.getRuleConfiguredValue());
		Assert.assertEquals(triggeredRule.getRuleName(), triggeredRuleResponse.getRuleName());
	}

	/**
	 * Tests the conversion from a state query response mapped with the XML response structure 
	 * into simpler object with the state query result data.
	 */
	@Test
	public void getQueryStateResponseTest() {

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

		FDStateQueryResponse queryStateResponse = ResponseConverter.getStateQueryResponse(response);
		Assert.assertTrue(queryStateResponse.hasError());
		Assert.assertEquals(queryStateResponse.getGeneralAnswerCode(), GeneralAnswerCode.getById(response.getGeneralAnswerCode()));
		Assert.assertEquals(queryStateResponse.getGeneralErrorCode(), GeneralErrorCode.getById(response.getGeneralErrorCode()));
		Assert.assertEquals(queryStateResponse.getGeneralErrorMessage(),
				response.getGeneralErrorMessage());
		Assert.assertEquals(queryStateResponse.getResponseDate(), response.getResponseDate());
		Assert.assertEquals(queryStateResponse.getTransactionId(),
				queryStateResponseContent.getTransactionId());
		Assert.assertEquals(queryStateResponse.getErrorMessage(),
				queryStateResponseContent.getErrorMessage());
		Assert.assertEquals(queryStateResponse.getState(), TransactionState.getById(queryStateResponseContent.getState()));
		Assert.assertEquals(queryStateResponse.getAnswerCode(),GeneralAnswerCode.getById(queryStateResponseContent.getAnswerCode()));
	}

	/**
	 * Tests the conversion from a update state response mapped with the XML response structure 
	 * into simpler object with the update state result data.
	 */
	@Test
	public void getUpdateStateResponseTest() {

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

		FDStateUpdateResponse updateStateResponse = ResponseConverter.getStateUpdateResponse(response);
		Assert.assertTrue(updateStateResponse.hasError());
		Assert.assertEquals(updateStateResponse.getGeneralAnswerCode(), GeneralAnswerCode.getById(response.getGeneralAnswerCode()));
		Assert.assertEquals(updateStateResponse.getGeneralErrorCode(), GeneralErrorCode.getById(response.getGeneralErrorCode()));
		Assert.assertEquals(updateStateResponse.getGeneralErrorMessage(),
				response.getGeneralErrorMessage());
		Assert.assertEquals(updateStateResponse.getResponseDate(), response.getResponseDate());
		Assert.assertEquals(updateStateResponse.getTransactionId(),
				updateStateResponseContent.getTransactionId());
		Assert.assertEquals(updateStateResponse.getErrorMessage(),
				updateStateResponseContent.getErrorMessage());
		Assert.assertEquals(updateStateResponse.getAnswerCode(), GeneralAnswerCode.getById(updateStateResponseContent.getAnswerCode()));
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
		rule.setRuleName("rule_test_post");
		rule.setNames(new ArrayList<String>(Arrays.asList("documento_comprador")));
		rule.setValues(new ArrayList<String>(Arrays.asList("20444444")));
		rule.setConfigurationValues(new ArrayList<String>(Arrays.asList("20444444")));
		rule.setOperators(new ArrayList<String>(Arrays.asList("IGUAL")));
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
		rule.setNames(new ArrayList<String>(Arrays.asList("telefono_oficina")));
		rule.setValues(new ArrayList<String>(Arrays.asList("4111111")));
		rule.setConfigurationValues(new ArrayList<String>(Arrays.asList("4111111")));
		rule.setOperators(new ArrayList<String>(Arrays.asList("IGUAL")));
		rule.setRuleName("rule_test");
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
