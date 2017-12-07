package com.payulatam.fraudvault.client.it;

/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 20 de oct. de 2017
 */

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.payulatam.fraudvault.api.client.FraudvaultClient;
import com.payulatam.fraudvault.api.client.FraudvaultClientConfiguration;
import com.payulatam.fraudvault.api.client.exception.FraudvaultException;
import com.payulatam.fraudvault.client.factory.FraudvaultClientFactory;
import com.payulatam.fraudvault.model.request.*;
import com.payulatam.fraudvault.model.response.*;

/**
 * Integration tests of the Retrofit client implementation using the Fraudvault service in the STG endpoint. 
 */
public class RetrofitClientTest {

	private static final Long TEST_CLIENT_ID = 7l;

	private static final String TEST_USER_LOGIN = "test.tech@pagosonline.com";

	private static final String TEST_USER_PASSWORD = "123456";

	private static final String TEST_WS_BASE_URL = "https://pruebas.maf.pagosonline.net/ws";

	/** Class logger. */
	private final Logger logger = Logger.getLogger(getClass());
	
	private String trxId;

	/**
	 * Gets a client for the Fraudvault service in the STG endpoint.
	 * 
	 * @return the test service client.
	 */
	private FraudvaultClient getClientFraudavaultStgEndpoint() {

		Credentials credentials = Credentials
				.builder(TEST_CLIENT_ID, TEST_USER_LOGIN, TEST_USER_PASSWORD).build();
		FraudvaultClientConfiguration configuration = FraudvaultClientConfiguration
				.builder(credentials, TEST_WS_BASE_URL).build();
		return FraudvaultClientFactory.createFraudvaultClient(configuration);
	}
	
	@BeforeSuite
	private void init(){
		trxId = "trx-001" + new Random().nextInt();
	}
	
	/**
	 * Test the transaction prevalidation with teh prevalidate method.
	 */
	@Test
	public void testPrevalidate() {
		logger.debug("---- Prevalidation test in STG");
		try {
			FraudvaultPrevalidationResponse response = getClientFraudavaultStgEndpoint().prevalidate(getTransaction(trxId));
			assertPrevalidationResponse(response);

		} catch (FraudvaultException e) {
			logger.debug("Unexpected error testing prevalidation in STG");
		}
	}

	/**
	 * Test the transaction prevalidation with the evaluate method.
	 */
	@Test
	public void testEvaluate() {
		logger.debug("---- Evaluate test in STG");
		try {
			FraudvaultPrevalidationResponse response = getClientFraudavaultStgEndpoint().evaluate(getTransaction(trxId));
			assertPrevalidationResponse(response);

		} catch (FraudvaultException e) {
			logger.debug("Unexpected error testing prevalidation in STG");
		}
	}

	/**
	 * Assert the postconditions of a prevalidation response.
	 * @param response
	 */
	private void assertPrevalidationResponse(FraudvaultPrevalidationResponse response){	
		Assert.assertNotNull(response);
		Assert.assertEquals(response.getAnswerCode(), new Integer(1));
		Assert.assertNotNull(response.getDate());
		Assert.assertNull(response.getErrorCode());
		Assert.assertNull(response.getErrorMessage());

		FraudvaultEvaluation evaluation = response.getEvaluation();
		Assert.assertNotNull(evaluation);
		Assert.assertNotNull(evaluation.getDecision());
					
		FraudvaultEvaluationDetail evaluationDetail = evaluation.getDetail();
		Assert.assertNotNull(evaluationDetail);
		
		Assert.assertNotNull(evaluationDetail.getTransactionId());
		Assert.assertNotNull(evaluationDetail.getEvaluationTime());
		Assert.assertNull(evaluationDetail.getErrorCode());
		Assert.assertNull(evaluationDetail.getErrorMessage());
		printEvaluationDetail(evaluationDetail);
	}

	/**
	 * Test the transaction posvalidation.
	 */
	@Test(dependsOnMethods = {"testPrevalidate"})
	public void testPostvalidate() {
		logger.debug("---- Posvalidation test in STG");
		try {
			FraudvaultPosvalidationResponse response = getClientFraudavaultStgEndpoint().posvalidate(trxId);
			Assert.assertNotNull(response);
			Assert.assertEquals(response.getAnswerCode(), new Integer(1));
			Assert.assertNotNull(response.getDate());
			Assert.assertNull(response.getErrorCode());
			Assert.assertNull(response.getErrorMessage());

			FraudvaultEvaluation evaluation = response.getEvaluation();
			Assert.assertNotNull(evaluation);
			Assert.assertNotNull(evaluation.getDecision());
						
			FraudvaultEvaluationDetail evaluationDetail = evaluation.getDetail();
			Assert.assertNotNull(evaluationDetail);
			
			Assert.assertNotNull(evaluationDetail.getTransactionId());
			Assert.assertNotNull(evaluationDetail.getEvaluationTime());
			Assert.assertNull(evaluationDetail.getErrorCode());
			Assert.assertNull(evaluationDetail.getErrorMessage());
			printEvaluationDetail(evaluationDetail);

		} catch (FraudvaultException e) {
			logger.debug("Unexpected error testing prevalidation in STG");
		}
	}

	/**
	 * Test the query of the transaction state.
	 */
	@Test(dependsOnMethods = {"testPrevalidate"})
	public void testQueryState() {
		logger.debug("---- Query transaction state test in STG");
		try {
			FraudvaultQueryStateResponse response = getClientFraudavaultStgEndpoint().queryTransactionState(trxId);
			
			Assert.assertNotNull(response);
			Assert.assertEquals(response.getAnswerCode(), new Integer(1));
			Assert.assertNotNull(response.getDate());
			Assert.assertNull(response.getErrorCode());
			Assert.assertNull(response.getErrorMessage());
			FraudvaultStateOperationResponseContent responseContent = response.getQueryStateResponseContent();			
			Assert.assertNotNull(responseContent.getTransactionId());
			Assert.assertNotNull(responseContent.getState());
			Assert.assertNotNull(responseContent.getAnswerCode());
			Assert.assertEquals(responseContent.getAnswerCode(), new Integer(1));
			Assert.assertNull(responseContent.getErrorMessage());

		}  catch (FraudvaultException e) {
			logger.debug("Unexpected error testing the query of transaction state in STG");
		}
	}

	/**
	 * Test the update of the transaction state.
	 */
	@Test(dependsOnMethods = {"testPrevalidate"})
	public void testUpdateState() {
		logger.debug("---- Update transaction state test in STG");
		try {

			FraudvaultUpdateStateResponse response = getClientFraudavaultStgEndpoint().updateTransactionState(trxId, 11L);
			Assert.assertNotNull(response);
			Assert.assertEquals(response.getAnswerCode(), new Integer(1));
			Assert.assertNotNull(response.getDate());
			Assert.assertNull(response.getErrorCode());
			Assert.assertNull(response.getErrorMessage());
			FraudvaultStateOperationResponseContent responseContent = response.getUpdateStateResponseContent();		
			Assert.assertNotNull(responseContent.getTransactionId());
			Assert.assertEquals(responseContent.getTransactionId(), trxId);
			Assert.assertNotNull(responseContent.getAnswerCode());
			Assert.assertEquals(responseContent.getAnswerCode(), new Integer(1));
			Assert.assertNull(responseContent.getErrorMessage());

		}  catch (FraudvaultException e) {
			logger.debug("Unexpected error testing the query of transaction state in STG");
		}
	}

	/**
	 * Log the data of the evaluation detail for help with the tracing of the tests.
	 * 
	 * @param evaluationDetail
	 */
	private void printEvaluationDetail(FraudvaultEvaluationDetail evaluationDetail){
		
		if (evaluationDetail.getAlerts() != null) {
			for (String fraudvaultAlert : evaluationDetail.getAlerts()) {
				logger.debug("-- alert: " + fraudvaultAlert);
			}
		}
		if (evaluationDetail.getActions() != null) {
			for (String fraudvaultAlert : evaluationDetail.getActions()) {
				logger.debug("-- accion: " + fraudvaultAlert);
			}
		}

		if (evaluationDetail.getRules() != null) {
			for (TriggeredRule filter : evaluationDetail.getRules()) {
				logger.debug("-- filters: " + filter.getRuleName() + " - " + filter.getFilteredAttributeName() + " - " + filter.getFilteredValue());
			}
		}

		ControlListsInformation controlLists = evaluationDetail.getControlListsInformation();
		if (controlLists != null) {
			logger.debug("-- lista negra: " + controlLists.getBlackListsMatching().isMatch() + " - " + controlLists.getBlackListsMatching().getParameter());
			logger.debug("-- lista blanca: " + controlLists.getWhiteListsMatching().isMatch() + " - " + controlLists.getWhiteListsMatching().getParameter());
			logger.debug("-- lista temporal: " + controlLists.getTemporaryListsMatching().isMatch() + " - " + controlLists.getTemporaryListsMatching().getParameter());
		}
		
		if(evaluationDetail.getIssuerBank() != null){
			logger.debug("-- issuer bank: "+ evaluationDetail.getIssuerBank().getBank()+ " - " + evaluationDetail.getIssuerBank().getCountry() + " - " + evaluationDetail.getIssuerBank().getPhoneNumber());
		}
		
		if(evaluationDetail.getIpAddressLocation() != null){				
			logger.debug(
				"-- ubicación IP: "
						+ evaluationDetail.getIpAddressLocation().getIpCountry() + " - " + evaluationDetail.getIpAddressLocation().getIpCity() + " - "
						+ evaluationDetail.getIpAddressLocation().getIpCountryCode() + " - " + evaluationDetail.getIpAddressLocation().getIpLatitude() + " - " 
						+ evaluationDetail.getIpAddressLocation().getIpLongitude() + " - " + evaluationDetail.getIpAddressLocation().getIpMetroAreaCode() + " - "
						+ evaluationDetail.getIpAddressLocation().getIpRegion() + " - " + evaluationDetail.getIpAddressLocation().getIpTelephoneAreaCode()
						+ " - " + evaluationDetail.getIpAddressLocation().getIpZipCode());

		}
		if(evaluationDetail.getSimilarTransactionsNumber() != null){
			logger.debug("-- numero de similares: " + evaluationDetail.getSimilarTransactionsNumber());
		}
	}

	/**
	 * Get a Fraudvault transaction object with data for the tests.
	 * 
	 * @param transactionId Id to be setted to the transaction.
	 * @return the transaction object.
	 */
	private Transaction getTransaction(String transactionId) {

		List<Passenger> passengers = new ArrayList<>();
		passengers.add(Passenger.builder().documentType(1).documentNumber("53140140")
				.firstName("Claudia").lastName("Rodriguez").email("crp@test.com").build());
		passengers.add(Passenger.builder().documentType(1).documentNumber("53140141")
				.firstName("Pedro").lastName("Gomez").email("pgomez@test.com").build());
		List<FlightPath> flightPaths = new ArrayList<>();

		flightPaths.add(FlightPath.builder().flightNumber("AVI123").arrivalDate(new Date())
				.departureDate(new Date()).destination("MEX").origin("BOG").travelClass("A")
				.build());
		flightPaths.add(FlightPath.builder().flightNumber("AVI456").arrivalDate(new Date())
				.departureDate(new Date()).destination("BOG").origin("MEX").travelClass("A")
				.build());

		List<RetailItem> items = new ArrayList<>();
		items.add(RetailItem.builder().quantity(1).unitPrice(new BigDecimal(5000)).product(
				RetailProduct.builder().code("prd12").name("The book").brand("brand book").build())
				.build());

		return Transaction.builder()
				.transactionId(transactionId).referenceCode("000001").sessionId("session-abc-1234")
				.creationDate(new Date()).test(true).countryIso("CO").origin(1100)
				.description("desc").value(new BigDecimal(180000)).extra1("extra1")
				.sellerUser(Seller.builder().id("777").clasificationCode("clas").build())
				.buyerUser(Buyer.builder().id("53140140").names("Mary")
						.surnames("Martinez").email("marym@test.com")
						.footPrint(Footprint.builder().ipAddress("192.5.5.6")
								.userAgent(
										"Mozilla/5.0 (Macintosh; Intel Mac) Gecko/20100101 Firefox/42.0")
								.deviceSignature("cookie-signature1111").build())
						.build())
				.paymentInformation(
						PaymentInformation.builder().paymentMethodType(1).paymentMethod(11)
								.pan("54685300008820584").expirationDate("2021/02")
								.bin("546853").currencyIso("COP")
								.installmentsNumber(2)
								.holder(AccountHolder.builder().name("Mary Martinez")
										.mobilePhoneNumber("3143191919").documentNumber("53140140")
										.build())
								.build())
				.deliveryAddress(Address.builder().countryIso("CO").city("Bogota")
						.street("Cra 80 #12-45").build())
				.pnr(Pnr.builder().bookingCode("ABC-PNR")
						.bookingAgent(BookingAgent.builder().id("123")
								.firstName("booking agent name")
								.lastName("booking agent lastname").email("booking@test.com")
								.build())
						.bookingOffice(Office.builder().id("123Office").country("CO").build())
						.tripType("RT")
						.passengers(passengers)
						.flightPaths(flightPaths)
						.build())
				.retail(Retail.builder().items(items).build())
				.build();
	}

}
