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
import com.payulatam.fraudvault.model.response.xml.IpAddressLocation;
import com.payulatam.fraudvault.model.response.xml.IssuerBank;
import com.payulatam.fraudvault.model.response.xml.ListMatch;
import com.payulatam.fraudvault.model.response.xml.TriggeredRule;

/**
 * Integration tests of the Retrofit client implementation using the Fraudvault service in the STG endpoint. 
 */
public class RetrofitClientIT {

	private static final Long TEST_CLIENT_ID = 7l;

	private static final String TEST_USER_LOGIN = "test.tech@pagosonline.com";

	private static final String TEST_USER_PASSWORD = "123456";

	private static final String TEST_WS_BASE_URL = "https://pruebas.maf.pagosonline.net/ws";

	/** Class logger. */
	private final Logger logger = Logger.getLogger(getClass());
	
	private String trxId;
	
	private FraudvaultClient fraudvaultClient;

	/**
	 * Initializes some commons fields for the tests.
	 */
	@BeforeSuite
	private void init(){
		trxId = "trx-001" + new Random().nextInt();
		Credentials credentials = Credentials .builder(TEST_CLIENT_ID, TEST_USER_LOGIN, TEST_USER_PASSWORD).build();
		fraudvaultClient = FraudvaultClientFactory.createDefaultFraudvaultClient(
				FraudvaultClientConfiguration.builder(credentials, TEST_WS_BASE_URL).build());
	}
	
	/**
	 * Test the transaction prevalidation with the prevalidate method.
	 * @throws FraudvaultException if an error occurs in the prevalidation process.
	 */
	@Test
	public void testPrevalidate() throws FraudvaultException{
		logger.debug("************* PREVALIDATION TEST IN STG *************");
		FraudvaultPrevalidation response = fraudvaultClient.prevalidate(getTransaction(trxId));
		assertPrevalidationResponse(response);
	}

	/**
	 * Test the transaction prevalidation with the evaluate method.
	 * @throws FraudvaultException if an error occurs in the prevalidation process.
	 */
	@Test
	public void testEvaluate() throws FraudvaultException {
		logger.debug("************* EVALUATE TEST IN STG *************");
		FraudvaultPrevalidation response = fraudvaultClient.evaluate(getTransaction(trxId));
		assertPrevalidationResponse(response);
	}

	/**
	 * Assert the postconditions of a prevalidation response.
	 * @param response
	 */
	private void assertPrevalidationResponse(FraudvaultPrevalidation response){
		Assert.assertNotNull(response);
		printPrevalidationDetail(response);
		Assert.assertEquals(new Integer(1), response.getGeneralAnswerCode());
		Assert.assertNotNull(response.getResponseDate());
		Assert.assertNull(response.getGeneralErrorCode());
		Assert.assertNull(response.getGeneralErrorMessage());
		Assert.assertNotNull(response.getDecision());
		Assert.assertNotNull(response.getTransactionId());
		Assert.assertNotNull(response.getEvaluationTime());
		Assert.assertNull(response.getErrorCode());
		Assert.assertNull(response.getErrorMessage());		
	}
	
	/**
	 * Assert the postconditions of a prevalidation response with invalid transaction data.
	 * @throws FraudvaultException  if an error occurs in the process. 
	 */
	@Test
	private void assertNonExistentTrxPrevalidationResponse() throws FraudvaultException{
		logger.debug("************* PREVALIDATION WITH INVALID DATA TEST IN STG *************");
		FraudvaultPrevalidation response = fraudvaultClient.prevalidate(Transaction.builder().transactionId("non-existent-id").build());
		Assert.assertNotNull(response);
		Assert.assertEquals(new Integer(2), response.getGeneralAnswerCode());
		Assert.assertNotNull(response.getResponseDate());
		Assert.assertNotNull(response.getGeneralErrorCode());
		Assert.assertNotNull(response.getGeneralErrorMessage());
		Assert.assertNull(response.getDecision());	
		printPrevalidationDetail(response);
	}

	/**
	 * Test the transaction posvalidation.
	 * @throws FraudvaultException if an error occurs in the posvalidation process. 
	 */
	@Test(dependsOnMethods = {"testPrevalidate"})
	public void testPostvalidate() throws FraudvaultException {
		logger.debug("************* POSVALIDATION TEST IN STG *************");
		FraudvaultPosvalidation response = fraudvaultClient.posvalidate(trxId);
		Assert.assertNotNull(response);
		Assert.assertEquals(new Integer(1), response.getGeneralAnswerCode());
		Assert.assertNotNull(response.getResponseDate());
		Assert.assertNull(response.getGeneralErrorCode());
		Assert.assertNull(response.getGeneralErrorMessage());
		Assert.assertNotNull(response.getDecision());

		Assert.assertNotNull(response.getTransactionId());
		Assert.assertNotNull(response.getEvaluationTime());
		Assert.assertNull(response.getErrorCode());
		Assert.assertNull(response.getErrorMessage());
		printPosvalidationDetail(response);
	}

	/**
	 * Test the query of the transaction state.
	 * @throws FraudvaultException if an error occurs in the query operation.
	 */
	@Test(dependsOnMethods = {"testPrevalidate"})
	public void testQueryState() throws FraudvaultException {
		logger.debug("************* STATE QUERY TEST IN STG *************");
		FraudvaultStateQuery response = fraudvaultClient.queryTransactionState(trxId);
		Assert.assertNotNull(response);
		Assert.assertEquals(new Integer(1), response.getGeneralAnswerCode());
		Assert.assertNotNull(response.getResponseDate());
		Assert.assertNull(response.getGeneralErrorCode());
		Assert.assertNull(response.getGeneralErrorMessage());
		Assert.assertNotNull(response.getTransactionId());
		Assert.assertNotNull(response.getState());
		Assert.assertNotNull(response.getAnswerCode());
		Assert.assertEquals(new Integer(1), response.getAnswerCode());
		Assert.assertNull(response.getErrorMessage());
		logger.debug("-GeneralAnswerCode: " + response.getGeneralAnswerCode() + " -GeneralErrorCode: "
				+ response.getGeneralErrorCode()
				+ " -GeneralErrorMessage: " + response.getGeneralErrorMessage() + " -TransactionID: "
				+ response.getTransactionId()
				+ " -ResponseDate: " + response.getResponseDate() + " -AnswerCode: "
				+ response.getAnswerCode()
				+ " -ErrorMessage: " + response.getErrorMessage());
	}

	/**
	 * Test the update of the transaction state.
	 * @throws FraudvaultException if an error occurs in the update operation.
	 */
	@Test(dependsOnMethods = {"testPrevalidate"})
	public void testUpdateState() throws FraudvaultException {
		logger.debug("************* UPDATE STATE TEST IN STG *************");
		FraudvaultStateUpdate response = fraudvaultClient.updateTransactionState(trxId, 11L);
		logger.debug("-GeneralAnswerCode: " + response.getGeneralAnswerCode() + " -GeneralErrorCode: "
				+ response.getGeneralErrorCode()
				+ " -GeneralErrorMessage: " + response.getGeneralErrorMessage() + " -TransactionID: "
				+ response.getTransactionId()
				+ " -ResponseDate: " + response.getResponseDate() + " -AnswerCode: "
				+ response.getAnswerCode()
				+ " -ErrorMessage: " + response.getErrorMessage());
		Assert.assertNotNull(response);
		Assert.assertEquals( new Integer(1), response.getGeneralAnswerCode());
		Assert.assertNotNull(response.getResponseDate());
		Assert.assertNull(response.getGeneralErrorCode());
		Assert.assertNull(response.getGeneralErrorMessage());	
		Assert.assertNotNull(response.getTransactionId());
		Assert.assertEquals(response.getTransactionId(), trxId);
		Assert.assertNotNull(response.getAnswerCode());
		Assert.assertEquals(new Integer(1), response.getAnswerCode());
		Assert.assertNull(response.getErrorMessage());
	}
	
	/**
	 * Assert the posconditions of a state update response with invalid transaction data.
	 * 
	 * @throws FraudvaultException  if an error occurs in the process. 
	 */
	@Test
	public void testTrxNonExistentStateUpdate() throws FraudvaultException {
		logger.debug("************* STATE QUERY WITH INVALID ID TEST IN STG *************");
		FraudvaultStateUpdate response = fraudvaultClient.updateTransactionState("trx-non-existID", 11L);
		Assert.assertNotNull(response);
		Assert.assertEquals(new Integer(2),response.getGeneralAnswerCode());
		Assert.assertNotNull(response.getResponseDate());
		Assert.assertNotNull(response.getGeneralErrorCode());
		Assert.assertNotNull(response.getGeneralErrorMessage());	
		Assert.assertNotNull(response.getTransactionId());
		Assert.assertEquals("trx-non-existID", response.getTransactionId());
		Assert.assertNotNull(response.getAnswerCode());
		Assert.assertEquals(new Integer(2), response.getAnswerCode());
		Assert.assertNotNull(response.getErrorMessage());
		logger.debug("-GeneralAnswerCode: " + response.getGeneralAnswerCode() + " -GeneralErrorCode: "
				+ response.getGeneralErrorCode()
				+ " -GeneralErrorMessage: " + response.getGeneralErrorMessage() + " -TransactionID: "
				+ response.getTransactionId()
				+ " -ResponseDate: " + response.getResponseDate() + " -AnswerCode: "
				+ response.getAnswerCode()
				+ " -ErrorMessage: " + response.getErrorMessage());
	}

	/**
	 * Log the data of the prevalidation detail for help with the tracing of the tests.
	 * 
	 * @param response prevalidation response.
	 */
	private void printPrevalidationDetail(FraudvaultPrevalidation response){
		
		logger.debug("-- Transaction ID: " + response.getTransactionId());
		logger.debug("-- Decision: " + response.getDecision());
		logger.debug("-- EvaluationTime: " + response.getEvaluationTime());	
		logger.debug("-- Answer code: " + response.getGeneralAnswerCode());
		logger.debug("-- ErrorCode: " + response.getErrorCode());
		logger.debug("-- ErrorMessage: " + response.getErrorMessage());
		logger.debug("-- GeneralErrorCode: " + response.getGeneralErrorCode());
		logger.debug("-- GeneralErrorMessage: " + response.getGeneralErrorMessage());
		logger.debug("-- ISP: " + response.getIspName());
		logger.debug("-- NeuronalNetworkEmailScore: " + response.getNeuronalNetworkEmailScore());
		logger.debug("-- euronalNetworkGeneralScore: " + response.getNeuronalNetworkGeneralScore());
		logger.debug("-- SimilarTransactionsNumber: " + response.getSimilarTransactionsNumber());
		logger.debug("-- ResponseDate: " + response.getResponseDate());
		
		if (response.getAlerts() != null) {
			for (String fraudvaultAlert : response.getAlerts()) {
				logger.debug("-- alert: " + fraudvaultAlert);
			}
		}
		if (response.getActions() != null) {
			for (String fraudvaultAlert : response.getActions()) {
				logger.debug("-- accion: " + fraudvaultAlert);
			}
		}

		if (response.getTriggeredRules() != null) {
			for (TriggeredRule filter : response.getTriggeredRules()) {
				logger.debug("-- filters: " + filter.getRuleName() + " - " + filter.getFilteredAttributeName() 
				+ " - " + filter.getFilteredValue() + " - " + filter.getRuleConfiguredValue()+ " - " + filter.getOperator());
			}
		}

		ListMatch blackLists = response.getBlackListsMatching();
		if (blackLists != null) {
			logger.debug("-- lista negra: " + blackLists.isMatch() + " - " + blackLists.getParameter());
		}
		
		ListMatch whiteLists = response.getBlackListsMatching();
		if (whiteLists != null) {
			logger.debug("-- lista blanca: " + whiteLists.isMatch() + " - " + whiteLists.getParameter());
		}
		
		ListMatch temporaryLists = response.getTemporaryListsMatching();
		if (temporaryLists != null) {			
			logger.debug("-- lista temporal: " + temporaryLists.isMatch() + " - " + temporaryLists.getParameter());
		}
		
		IssuerBank issuerBank = response.getIssuerBank();
		if(issuerBank != null){
			logger.debug("-- issuer bank: "+ issuerBank.getBank()+ " - " + issuerBank.getCountry() + " - " + issuerBank.getPhoneNumber());
		}
		IpAddressLocation ipAddressLocation = response.getIpAddressLocation();
		if(ipAddressLocation != null){				
			logger.debug(
				"-- ubicación IP: "
						+ ipAddressLocation.getIpCountry() + " - " + ipAddressLocation.getIpCity() + " - "
						+ ipAddressLocation.getIpCountryCode() + " - " + ipAddressLocation.getIpLatitude() + " - " 
						+ ipAddressLocation.getIpLongitude() + " - " + ipAddressLocation.getIpMetroAreaCode() + " - "
						+ ipAddressLocation.getIpRegion() + " - " + ipAddressLocation.getIpTelephoneAreaCode()
						+ " - " + ipAddressLocation.getIpZipCode());

		}
		if(response.getSimilarTransactionsNumber() != null){
			logger.debug("-- numero de similares: " + response.getSimilarTransactionsNumber());
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
		passengers.add(Passenger.builder().idDocumentType(1).idDocumentNumber("53140141")
				.firstName("Claudia").lastName("Rodriguez").email("crp@test.com").build());
		passengers.add(Passenger.builder().idDocumentType(1).idDocumentNumber("53140142")
				.firstName("Pedro").lastName("Gomez").email("pgomez@ymail.com").build());
		List<FlightPath> flightPaths = new ArrayList<>();

		flightPaths.add(FlightPath.builder().flightNumber("AVI123").arrivalDate(new Date())
				.departureDate(new Date()).destination("MEX").origin("BOG").travelClass("A")
				.build());
		flightPaths.add(FlightPath.builder().flightNumber("AVI456").arrivalDate(new Date())
				.departureDate(new Date()).destination("BOG").origin("MEX").travelClass("A")
				.build());

		return Transaction.builder()
				.transactionId(transactionId).referenceCode("000001").sessionId("session-abc-1234")
				.creationDate(new Date()).test(false).countryIso("CO").origin(1100)
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
								.pan("54685300008820584").expirationDate("2021/05")
								.bin("546853").currencyIso("COP")
								.installmentsNumber(2)
								.holder(AccountHolder.builder().name("Mary Martinez")
										.mobilePhoneNumber("3143191919").idDocumentNumber("53140140").gender("F")
										.build())
								.build())
				.deliveryAddress(Address.builder().countryIso("CO").city("Bogota")
						.street("Cra 80 #12-45").build())
				.pnr(Pnr.builder().bookingCode("ABC-PNR")
						.bookingAgent(BookingAgent.builder().id("123")
								.firstName("booking agent name")
								.lastName("booking agent lastname").email("booking@test.com").officePhone("1111111")
								.build())
						.bookingOffice(Office.builder().id("123Office").country("CO").build())
						.sellerOffice(Office.builder().id("456Office").country("CO").build())
						.tripType("RT")
						.passengers(passengers)
						.flightPaths(flightPaths)
						.build())
				.build();
	}
	
	/**
	 * Log the data of the posvalidation detail for help with the tracing of the tests.
	 * 
	 * @param response posvalidation response.
	 */
	private void printPosvalidationDetail(FraudvaultPosvalidation response){
		
		logger.debug("-- TransactionId: " + response.getTransactionId());
		logger.debug("-- Decision: " + response.getDecision());
		logger.debug("-- EvaluationTime: " + response.getEvaluationTime());
		logger.debug("-- AnswerCode: " + response.getGeneralAnswerCode());
		logger.debug("-- GeneralErrorCode: " + response.getGeneralErrorCode());
		logger.debug("-- GeneralErrorMessage: " + response.getGeneralErrorMessage());		
		logger.debug("-- ErrorCode: " + response.getErrorCode());
		logger.debug("-- ErrorMessage: " + response.getErrorMessage());		
		logger.debug("-- SimilarTransactionsNumber: " + response.getSimilarTransactionsNumber());
		logger.debug("-- ResponseDate: " + response.getResponseDate());
		
		if (response.getActions() != null) {
			for (String fraudvaultAlert : response.getActions()) {
				logger.debug("-- accion: " + fraudvaultAlert);
			}
		}
		if (response.getTriggeredRules() != null) {
			for (TriggeredRule filter : response.getTriggeredRules()) {
				logger.debug("-- filters: " + filter.getRuleName() + " - " + filter.getFilteredAttributeName() + " - " + filter.getFilteredValue());
			}
		}
	}

}
