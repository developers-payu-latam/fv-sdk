/**
 * PayU Latam - Copyright (c) 2013 - 2018
 * http://www.payu.com.co
 * Date: 26 de ene. de 2018
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

package com.payulatam.fd.client.retrofit.converter;

import java.util.ArrayList;
import java.util.List;

import com.payulatam.fd.model.response.*;
import com.payulatam.fd.model.response.FDPosvalidationResponse.FDPosvalidationResponseBuilder;
import com.payulatam.fd.model.response.FDPrevalidationResponse.FDPrevalidationResponseBuilder;
import com.payulatam.fd.model.response.FDStateQueryResponse.FDStateQueryResponseBuilder;
import com.payulatam.fd.model.response.FDStateUpdateResponse.FDStateUpdateResponseBuilder;
import com.payulatam.fd.model.response.soap.*;

/**
 * Utility class for converting the response data mapped with the XML response structure into simpler
 * objects for returning to the fraud detection service client.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com" >Claudia Jimena Rodriguez</a>
 */
public final class ResponseConverter {

	private ResponseConverter() {}

	/**
	 * Converts from a prevalidation response of the fraud detection service mapped with the XML response structure 
	 * into simpler object with the service prevalidation result data.
	 * 
	 * @param response the prevalidation response mapped with the XML response structure.
	 * @return the prevalidation result data.
	 */
	public static FDPrevalidationResponse getFDPrevalidationResponse(PrevalidationResponseSoapWrapper response) {

		FDPrevalidationResponseBuilder prevalidationBuilder = FDPrevalidationResponse.builder();
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
		FDPrevalidationResponse prevalidation = prevalidationBuilder.build();
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
	 * Converts from a posvalidation response of the fraud detection service mapped with the XML response structure 
	 * into simpler object with the service posvalidation result data.
	 * 
	 * @param response the posvalidation response mapped with the XML response structure.
	 * @return the posvalidation result data.
	 */
	public static FDPosvalidationResponse getFDPosvalidationResponse(PosvalidationResponsesSoapWrapper response) {

		FDPosvalidationResponseBuilder posvalidationBuilder = FDPosvalidationResponse
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
		FDPosvalidationResponse posvalidation = posvalidationBuilder.build();
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
	 * Converts from a state query response of the fraud detection service mapped with the XML response structure into
	 * simpler object with the state query result data.
	 * 
	 * @param response the state query response mapped with the XML response structure.
	 * @return the state query result data.
	 */
	public static FDStateQueryResponse getStateQueryResponse(QueryStateResponseSoapWrapper response) {

		FDStateQueryResponseBuilder queryStateBuilder = FDStateQueryResponse
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
		FDStateQueryResponse queryState = queryStateBuilder.build();
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
	 * Converts from a update state response of the fraud detection service mapped with the XML response structure into
	 * simpler object with the update state result data.
	 * 
	 * @param response the update state response mapped with the XML response structure.
	 * @return the update state result data.
	 */
	public static FDStateUpdateResponse getStateUpdateResponse(UpdateStateResponseSoapWrapper response) {

		FDStateUpdateResponseBuilder updateStateBuilder = FDStateUpdateResponse
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
		FDStateUpdateResponse updateState = updateStateBuilder.build();
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
