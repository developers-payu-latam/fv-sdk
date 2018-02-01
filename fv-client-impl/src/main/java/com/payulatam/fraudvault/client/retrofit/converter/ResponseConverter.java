/**
 * PayU Latam - Copyright (c) 2013 - 2018
 * http://www.payu.com.co
 * Date: 26 de ene. de 2018
 */
package com.payulatam.fraudvault.client.retrofit.converter;

import com.payulatam.fraudvault.model.response.*;
import com.payulatam.fraudvault.model.response.FraudvaultPosvalidation.FraudvaultPosvalidationBuilder;
import com.payulatam.fraudvault.model.response.FraudvaultPrevalidation.FraudvaultPrevalidationBuilder;
import com.payulatam.fraudvault.model.response.FraudvaultStateQuery.FraudvaultStateQueryBuilder;
import com.payulatam.fraudvault.model.response.FraudvaultStateUpdate.FraudvaultStateUpdateBuilder;
import com.payulatam.fraudvault.model.response.xml.ControlListsInformation;
import com.payulatam.fraudvault.model.response.xml.FraudvaultEvaluation;
import com.payulatam.fraudvault.model.response.xml.FraudvaultEvaluationDetail;
import com.payulatam.fraudvault.model.response.xml.FraudvaultPosvalidationResponse;
import com.payulatam.fraudvault.model.response.xml.FraudvaultPrevalidationResponse;
import com.payulatam.fraudvault.model.response.xml.FraudvaultQueryStateResponse;
import com.payulatam.fraudvault.model.response.xml.FraudvaultStateOperationResponseContent;
import com.payulatam.fraudvault.model.response.xml.FraudvaultUpdateStateResponse;
import com.payulatam.fraudvault.model.response.xml.HeuristicAnalysis;

/**
 * Utility class for convert the response data mapped with the XML response structure into simpler objects 
 * for returning to the Fraudvault service client.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com" >Claudia Jimena Rodriguez</a>
 */
public final class ResponseConverter {
	
	private ResponseConverter(){}

	/**
	 * Converts from a Fraudvault prevalidation response mapped with the XML response structure into
	 * simpler object with the Fraudvault prevalidation data.
	 * 
	 * @param response the Fraudvault prevalidation response mapped with the XML response structure.
	 * @return the Fraudvault prevalidation data.
	 */
	public static FraudvaultPrevalidation getFraudvaultPrevalidation(FraudvaultPrevalidationResponse response) {

		FraudvaultPrevalidationBuilder prevalidationBuilder = FraudvaultPrevalidation.builder();
		FraudvaultEvaluation evaluation = response.getEvaluation();
		if (evaluation != null) {
			prevalidationBuilder.decision(evaluation.getDecision());
			FraudvaultEvaluationDetail detail = evaluation.getDetail();
			if (detail != null) {
				prevalidationBuilder.actions(detail.getActions()).alerts(detail.getAlerts())
						.errorCode(detail.getErrorCode()).errorMessage(detail.getErrorMessage())
						.evaluationTime(detail.getEvaluationTime())
						.ipAddressLocation(detail.getIpAddressLocation())
						.ipProxy(detail.isIpProxy()).ispName(detail.getIspName())
						.issuerBank(detail.getIssuerBank())
						.similarTransactionsNumber(detail.getSimilarTransactionsNumber())
						.transactionId(detail.getTransactionId()).triggeredRules(detail.getRules())
						.validateCentralRisk(detail.isValidateCentralRisk());

				ControlListsInformation controlListInformation = detail.getControlListsInformation();
				if (controlListInformation != null) {
					prevalidationBuilder
							.blackListsMatching(controlListInformation.getBlackListsMatching())
							.whiteListsMatching(controlListInformation.getWhiteListsMatching())
							.temporaryListsMatching(controlListInformation.getTemporaryListsMatching());
				}
				HeuristicAnalysis heuristicAnalysis = detail.getHeuristicAnalysis();
				if (heuristicAnalysis != null) {
					prevalidationBuilder
							.neuronalNetworkGeneralScore(heuristicAnalysis.getGeneralScore())
							.neuronalNetworkEmailScore(heuristicAnalysis.getEmailScore());
				}
			}
		}
		FraudvaultPrevalidation prevalidation = prevalidationBuilder.build();
		prevalidation.setGeneralErrorCode(response.getGeneralErrorCode());
		prevalidation.setGeneralErrorMessage(response.getGeneralErrorMessage());
		prevalidation.setResponseDate(response.getResponseDate());
		prevalidation.setGeneralAnswerCode(response.getGeneralAnswerCode());
		return prevalidation;
	}

	/**
	 * Converts from a Fraudvault posvalidation response mapped with the XML response structure into
	 * simpler object with the Fraudvault posvalidation data.
	 * 
	 * @param response the Fraudvault prevalidation response mapped with the XML response structure.
	 * @return the Fraudvault posvalidation data.
	 */
	public static FraudvaultPosvalidation getFraudvaultPosvalidation(FraudvaultPosvalidationResponse response) {

		FraudvaultPosvalidationBuilder posvalidationBuilder = FraudvaultPosvalidation.builder();
		FraudvaultEvaluation evaluation = response.getEvaluation();
		if (evaluation != null) {
			posvalidationBuilder.decision(evaluation.getDecision());
			FraudvaultEvaluationDetail detail = evaluation.getDetail();
			if (detail != null) {
				posvalidationBuilder.actions(detail.getActions())
						.errorCode(detail.getErrorCode()).errorMessage(detail.getErrorMessage())
						.evaluationTime(detail.getEvaluationTime())
						.similarTransactionsNumber(detail.getSimilarTransactionsNumber())
						.transactionId(detail.getTransactionId()).triggeredRules(detail.getRules());
			}
		}
		FraudvaultPosvalidation posvalidation = posvalidationBuilder.build();
		posvalidation.setGeneralErrorCode(response.getGeneralErrorCode());
		posvalidation.setGeneralErrorMessage(response.getGeneralErrorMessage());
		posvalidation.setResponseDate(response.getResponseDate());
		posvalidation.setGeneralAnswerCode(response.getGeneralAnswerCode());
		return posvalidation;
	}

	/**
	 * Converts from a Fraudvault State Query response mapped with the XML response structure into
	 * simpler object with the Fraudvault State Query data.
	 * 
	 * @param response the Fraudvault State Query response mapped with the XML response structure.
	 * @return the Fraudvault State Query data.
	 */
	public static FraudvaultStateQuery getFraudvaultStateQuery(FraudvaultQueryStateResponse response) {
		
		FraudvaultStateQueryBuilder queryStateBuilder = FraudvaultStateQuery.builder();		
		FraudvaultStateOperationResponseContent queryResponseContent = response.getQueryStateResponseContent();
		if(queryResponseContent != null){
			queryStateBuilder.transactionId(queryResponseContent.getTransactionId());
			queryStateBuilder.state(queryResponseContent.getState());
			queryStateBuilder.answerCode(queryResponseContent.getAnswerCode());
			queryStateBuilder.errorMessage(queryResponseContent.getErrorMessage());
		}
		FraudvaultStateQuery queryState = queryStateBuilder.build();
		queryState.setGeneralErrorCode(response.getGeneralErrorCode());
		queryState.setGeneralErrorMessage(response.getGeneralErrorMessage());
		queryState.setResponseDate(response.getResponseDate());
		queryState.setGeneralAnswerCode(response.getGeneralAnswerCode());
		return queryState;
	}
	
	/**
	 * Converts from a Fraudvault update state response mapped with the XML response structure into
	 * simpler object with the Fraudvault update state data.
	 * 
	 * @param response the Fraudvault update state response mapped with the XML response structure.
	 * @return the Fraudvault update state data.
	 */
	public static FraudvaultStateUpdate getFraudvaultStateUpdate(FraudvaultUpdateStateResponse response) {

		FraudvaultStateUpdateBuilder updateStateBuilder = FraudvaultStateUpdate.builder();		
		FraudvaultStateOperationResponseContent updateResponseContent = response.getUpdateStateResponseContent();
		if(updateResponseContent != null){
			updateStateBuilder.transactionId(updateResponseContent.getTransactionId());			
			updateStateBuilder.answerCode(updateResponseContent.getAnswerCode());
			updateStateBuilder.errorMessage(updateResponseContent.getErrorMessage());
		}
		FraudvaultStateUpdate updateState = updateStateBuilder.build();
		updateState.setGeneralErrorCode(response.getGeneralErrorCode());
		updateState.setGeneralErrorMessage(response.getGeneralErrorMessage());
		updateState.setResponseDate(response.getResponseDate());
		updateState.setGeneralAnswerCode(response.getGeneralAnswerCode());
		return updateState;
	}
	


}
