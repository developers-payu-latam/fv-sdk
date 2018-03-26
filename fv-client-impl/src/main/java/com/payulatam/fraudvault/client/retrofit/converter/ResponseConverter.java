/**
 * PayU Latam - Copyright (c) 2013 - 2018
 * http://www.payu.com.co
 * Date: 26 de ene. de 2018
 */
package com.payulatam.fraudvault.client.retrofit.converter;

import java.util.ArrayList;
import java.util.List;

import com.payulatam.fraudvault.model.response.*;
import com.payulatam.fraudvault.model.response.FraudvaultPosvalidationResponse.FraudvaultPosvalidationResponseBuilder;
import com.payulatam.fraudvault.model.response.FraudvaultPrevalidationResponse.FraudvaultPrevalidationResponseBuilder;
import com.payulatam.fraudvault.model.response.FraudvaultStateQueryResponse.FraudvaultStateQueryResponseBuilder;
import com.payulatam.fraudvault.model.response.FraudvaultStateUpdateResponse.FraudvaultStateUpdateResponseBuilder;
import com.payulatam.fraudvault.model.response.soap.*;

/**
 * Utility class for convert the response data mapped with the XML response structure into simpler
 * objects
 * for returning to the Fraudvault service client.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com" >Claudia Jimena Rodriguez</a>
 */
public final class ResponseConverter {

	private ResponseConverter() {}

	/**
	 * Converts from a Fraudvault prevalidation response mapped with the XML response structure into
	 * simpler object with the Fraudvault prevalidation data.
	 * 
	 * @param response the Fraudvault prevalidation response mapped with the XML response structure.
	 * @return the Fraudvault prevalidation data.
	 */
	public static FraudvaultPrevalidationResponse
			getFraudvaultPrevalidation(PrevalidationResponseSoapWrapper response) {

		FraudvaultPrevalidationResponseBuilder prevalidationBuilder = FraudvaultPrevalidationResponse
				.builder();
		EvaluationSoapWrapper evaluation = response.getEvaluation();
		if (evaluation != null) {
			prevalidationBuilder.decisionIdValue(evaluation.getDecision());
			prevalidationBuilder.decision(PrevalidationDecision.getById(evaluation.getDecision()));
			EvaluationDetailSoapWrapper detail = evaluation.getDetail();
			if (detail != null) {
				prevalidationBuilder.actions(getActions(detail.getActions()))
						.actionsKeysValues(detail.getActions())
						.alerts(getAlerts(detail.getAlerts()))
						.alertsKeysValues(detail.getAlerts())
						.errorMessage(detail.getErrorMessage())
						.evaluationTime(detail.getEvaluationTime())
						.ipAddressLocation(detail.getIpAddressLocation())
						.ipProxy(detail.isIpProxy()).ispName(detail.getIspName())
						.issuerBank(detail.getIssuerBank())
						.similarTransactionsNumber(detail.getSimilarTransactionsNumber())
						.transactionId(detail.getTransactionId()).triggeredRules(detail.getRules())
						.validatedWithCreditBureau(detail.isValidatedWithCreditBureau());
				prevalidationBuilder.errorCodeIdValue(detail.getErrorCode());
				if (detail.getErrorCode() != null) {
					prevalidationBuilder.errorCode(ErrorCode.getById(detail.getErrorCode()));
				}

				ControlListsInformation controlListInformation = detail
						.getControlListsInformation();
				if (controlListInformation != null) {
					prevalidationBuilder
							.blackListsMatching(controlListInformation.getBlackListsMatching())
							.whiteListsMatching(controlListInformation.getWhiteListsMatching())
							.temporaryListsMatching(
									controlListInformation.getTemporaryListsMatching());
				}
				HeuristicAnalysis heuristicAnalysis = detail.getHeuristicAnalysis();
				if (heuristicAnalysis != null) {
					prevalidationBuilder
							.neuronalNetworkGeneralScore(heuristicAnalysis.getGeneralScore())
							.neuronalNetworkEmailScore(heuristicAnalysis.getEmailScore());
				}
			}
		}
		FraudvaultPrevalidationResponse prevalidation = prevalidationBuilder.build();
		prevalidation.setGeneralErrorCodeValue(response.getGeneralErrorCode());
		if (response.getGeneralErrorCode() != null) {
			prevalidation.setGeneralErrorCode(GeneralErrorCode.getById(response.getGeneralErrorCode()));
		}
		prevalidation.setGeneralErrorMessage(response.getGeneralErrorMessage());
		prevalidation.setResponseDate(response.getResponseDate());
		prevalidation.setGeneralAnswerCodeValue(response.getGeneralAnswerCode());
		if (response.getGeneralAnswerCode() != null) {
			prevalidation.setGeneralAnswerCode(GeneralAnswerCode.getById(response.getGeneralAnswerCode()));
		}
		return prevalidation;
	}

	/**
	 * Gets a list of Action from the actions strings list.
	 * 
	 * @param actions the strings of the actions.
	 * @return list of Action values.
	 */
	private static List<Action> getActions(List<String> actions) {

		List<Action> actionsList = new ArrayList<>();
		if (actions != null) {
			for (String action : actions) {
				actionsList.add(Action.getByKey(action));
			}
		}
		return actionsList;
	}
	
	/**
	 * Gets a list of Alert from the alerts strings list.
	 * 
	 * @param alerts the strings of the alerts.
	 * @return list of Alert values.
	 */
	private static List<Alert> getAlerts(List<String> alerts) {

		List<Alert> alertsList = new ArrayList<>();
		if (alerts != null) {
			for (String alert : alerts) {
				alertsList.add(Alert.getByKey(alert));
			}
		}
		return alertsList;
	}

	/**
	 * Converts from a Fraudvault posvalidation response mapped with the XML response structure into
	 * simpler object with the Fraudvault posvalidation data.
	 * 
	 * @param response the Fraudvault prevalidation response mapped with the XML response structure.
	 * @return the Fraudvault posvalidation data.
	 */
	public static FraudvaultPosvalidationResponse
			getFraudvaultPosvalidation(PosvalidationResponsesSoapWrapper response) {

		FraudvaultPosvalidationResponseBuilder posvalidationBuilder = FraudvaultPosvalidationResponse
				.builder();
		EvaluationSoapWrapper evaluation = response.getEvaluation();
		if (evaluation != null) {
			posvalidationBuilder.decisionIdValue(evaluation.getDecision());
			posvalidationBuilder.decision(PosvalidationDecision.getById(evaluation.getDecision()));
			EvaluationDetailSoapWrapper detail = evaluation.getDetail();
			if (detail != null) {
				posvalidationBuilder.actions(getActions(detail.getActions()))
						.actionsKeysValues(detail.getActions())
						.errorMessage(detail.getErrorMessage())
						.evaluationTime(detail.getEvaluationTime())
						.similarTransactionsNumber(detail.getSimilarTransactionsNumber())
						.transactionId(detail.getTransactionId()).triggeredRules(detail.getRules());
				posvalidationBuilder.errorCodeIdValue(detail.getErrorCode());
				if (detail.getErrorCode() != null) {
					posvalidationBuilder.errorCode(ErrorCode.getById(detail.getErrorCode()));
				}
			}
		}
		FraudvaultPosvalidationResponse posvalidation = posvalidationBuilder.build();
		posvalidation.setGeneralErrorCodeValue(response.getGeneralErrorCode());
		if (response.getGeneralErrorCode() != null) {
			posvalidation.setGeneralErrorCode(GeneralErrorCode.getById(response.getGeneralErrorCode()));
		}
		posvalidation.setGeneralErrorMessage(response.getGeneralErrorMessage());
		posvalidation.setResponseDate(response.getResponseDate());
		posvalidation.setGeneralAnswerCodeValue(response.getGeneralAnswerCode());
		if (response.getGeneralAnswerCode() != null) {
			posvalidation.setGeneralAnswerCode(GeneralAnswerCode.getById(response.getGeneralAnswerCode()));
		}
		return posvalidation;
	}

	/**
	 * Converts from a Fraudvault State Query response mapped with the XML response structure into
	 * simpler object with the Fraudvault State Query data.
	 * 
	 * @param response the Fraudvault State Query response mapped with the XML response structure.
	 * @return the Fraudvault State Query data.
	 */
	public static FraudvaultStateQueryResponse
			getFraudvaultStateQuery(QueryStateResponseSoapWrapper response) {

		FraudvaultStateQueryResponseBuilder queryStateBuilder = FraudvaultStateQueryResponse
				.builder();
		StateOperationResponseContentSoapWrapper queryResponseContent = response
				.getQueryStateResponseContent();
		if (queryResponseContent != null) {
			queryStateBuilder.transactionId(queryResponseContent.getTransactionId());
			queryStateBuilder.stateIdValue(queryResponseContent.getState());
			queryStateBuilder.state(TransactionState.getById(queryResponseContent.getState()));
			if (queryResponseContent.getAnswerCode() != null) {
				queryStateBuilder.answerCode(GeneralAnswerCode.getById(queryResponseContent.getAnswerCode()));
			}
			queryStateBuilder.errorMessage(queryResponseContent.getErrorMessage());
		}
		FraudvaultStateQueryResponse queryState = queryStateBuilder.build();
		queryState.setGeneralErrorCodeValue(response.getGeneralErrorCode());
		if (response.getGeneralErrorCode() != null) {
			queryState.setGeneralErrorCode(GeneralErrorCode.getById(response.getGeneralErrorCode()));
		}
		queryState.setGeneralErrorMessage(response.getGeneralErrorMessage());
		queryState.setResponseDate(response.getResponseDate());
		queryState.setGeneralAnswerCodeValue(response.getGeneralAnswerCode());
		if (response.getGeneralAnswerCode() != null) {
			queryState.setGeneralAnswerCode(GeneralAnswerCode.getById(response.getGeneralAnswerCode()));
		}
		return queryState;
	}

	/**
	 * Converts from a Fraudvault update state response mapped with the XML response structure into
	 * simpler object with the Fraudvault update state data.
	 * 
	 * @param response the Fraudvault update state response mapped with the XML response structure.
	 * @return the Fraudvault update state data.
	 */
	public static FraudvaultStateUpdateResponse
			getFraudvaultStateUpdate(UpdateStateResponseSoapWrapper response) {

		FraudvaultStateUpdateResponseBuilder updateStateBuilder = FraudvaultStateUpdateResponse
				.builder();
		StateOperationResponseContentSoapWrapper updateResponseContent = response
				.getUpdateStateResponseContent();
		if (updateResponseContent != null) {
			updateStateBuilder.transactionId(updateResponseContent.getTransactionId());
			if (updateResponseContent.getAnswerCode() != null) {
				updateStateBuilder.answerCode(GeneralAnswerCode.getById(updateResponseContent.getAnswerCode()));
			}
			updateStateBuilder.errorMessage(updateResponseContent.getErrorMessage());
		}
		FraudvaultStateUpdateResponse updateState = updateStateBuilder.build();
		updateState.setGeneralErrorCodeValue(response.getGeneralErrorCode());
		if (response.getGeneralErrorCode() != null) {
			updateState.setGeneralErrorCode(GeneralErrorCode.getById(response.getGeneralErrorCode()));
		}
		updateState.setGeneralErrorMessage(response.getGeneralErrorMessage());
		updateState.setResponseDate(response.getResponseDate());
		updateState.setGeneralAnswerCodeValue(response.getGeneralAnswerCode());
		if (response.getGeneralAnswerCode() != null) {
			updateState.setGeneralAnswerCode(
					GeneralAnswerCode.getById(response.getGeneralAnswerCode()));
		}
		return updateState;
	}

}
