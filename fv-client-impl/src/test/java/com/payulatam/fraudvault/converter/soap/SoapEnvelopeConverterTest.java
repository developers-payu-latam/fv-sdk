/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 30 de nov. de 2017
 */
package com.payulatam.fraudvault.converter.soap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.payulatam.fraudvault.api.client.exception.XmlConversionException;
import com.payulatam.fraudvault.client.retrofit.converter.SoapEnvelopeConverter;
import com.payulatam.fraudvault.client.retrofit.model.soap.request.PosvalidationRequestSoapEnvelope;
import com.payulatam.fraudvault.client.retrofit.model.soap.request.PosvalidationRequestSoapEnvelope.PosvalidationBodyData;
import com.payulatam.fraudvault.client.retrofit.model.soap.request.PrevalidationBodyData;
import com.payulatam.fraudvault.client.retrofit.model.soap.request.PrevalidationRequestSoapEnvelope;
import com.payulatam.fraudvault.client.retrofit.model.soap.request.QueryStateRequestSoapEnvelope;
import com.payulatam.fraudvault.client.retrofit.model.soap.request.QueryStateRequestSoapEnvelope.QueryStateBodyData;
import com.payulatam.fraudvault.client.retrofit.model.soap.request.UpdateStateRequestSoapEnvelope;
import com.payulatam.fraudvault.client.retrofit.model.soap.request.UpdateStateRequestSoapEnvelope.UpdateStateBodyData;
import com.payulatam.fraudvault.model.request.AccountHolder;
import com.payulatam.fraudvault.model.request.Address;
import com.payulatam.fraudvault.model.request.BookingAgent;
import com.payulatam.fraudvault.model.request.Buyer;
import com.payulatam.fraudvault.model.request.Credentials;
import com.payulatam.fraudvault.model.request.FlightPath;
import com.payulatam.fraudvault.model.request.Footprint;
import com.payulatam.fraudvault.model.request.Office;
import com.payulatam.fraudvault.model.request.Passenger;
import com.payulatam.fraudvault.model.request.PaymentInformation;
import com.payulatam.fraudvault.model.request.Pnr;
import com.payulatam.fraudvault.model.request.Retail;
import com.payulatam.fraudvault.model.request.RetailItem;
import com.payulatam.fraudvault.model.request.RetailProduct;
import com.payulatam.fraudvault.model.request.Seller;
import com.payulatam.fraudvault.model.request.Transaction;

/**
 * Test of the soap converter.
 */
public class SoapEnvelopeConverterTest {

	private Credentials credentials;

	private String idTransaction;

	@BeforeSuite
	private void init() {

		credentials = getCredentials();
		idTransaction = "trx-001";
	}

	/**
	 * Test the conversion from the transaction data to the object representing the soap message envelope of the prevalidation request.
	 * Validate the generated xml data of the transaction with the expected xml contained in the file: resources/transaction.xml
	 * @throws IOException if an error happens reading the file.
	 * @throws XmlConversionException If an error happens getting the XML data of the transaction.
	 */
	@Test
	public void testPrevalidationSoapConverter() throws XmlConversionException, IOException {

		Transaction trxToPrevalidate = getTransaction(idTransaction);
		PrevalidationRequestSoapEnvelope prevalidationSoapEnvelope = SoapEnvelopeConverter
					.getPrevalidationSoapEnvelope(trxToPrevalidate, credentials);
		PrevalidationBodyData soapEnvelopeBody = prevalidationSoapEnvelope.getRequestSoapEnvelopeBody();
		Assert.assertEquals(credentials.getClientId(), soapEnvelopeBody.getClientId());
		Assert.assertEquals(credentials.getLogin(), soapEnvelopeBody.getLogin());
		Assert.assertEquals(credentials.getPassword(), soapEnvelopeBody.getPassword());
		String transactionXml = soapEnvelopeBody.getTransaction();
		String expectedXml = cargarXml("transaction.xml");
		Assert.assertEquals(transactionXml.replace("\n", "").replace("\r", "").replace(" ", ""),
					expectedXml.replace("\n", "").replace("\r", "").replace(" ", ""));
	}

	/**
	 * Test the conversion from the transaction data to the object representing the soap message envelope of the posvalidation request.
	 */
	@Test
	public void testPosvalidationSoapConverter() {

		PosvalidationRequestSoapEnvelope posvalidationSoapEnvelope = SoapEnvelopeConverter.getPostvalidationSoapEnvelope(idTransaction, credentials);

		PosvalidationBodyData soapEnvelopeBody = posvalidationSoapEnvelope.getRequestSoapEnvelopeBody();
		Assert.assertEquals(credentials.getClientId(), soapEnvelopeBody.getClientId());
		Assert.assertEquals(credentials.getLogin(), soapEnvelopeBody.getLogin());
		Assert.assertEquals(credentials.getPassword(), soapEnvelopeBody.getPassword());
		Assert.assertEquals(idTransaction, soapEnvelopeBody.getTransactionId());
	}

	/**
	 * Test the conversion from the transaction data to the object representing the soap message envelope of the query state request.
	 */
	@Test
	public void testQueryStateSoapConverter() {

		QueryStateRequestSoapEnvelope queryStateSoapEnvelope = SoapEnvelopeConverter.getQueryStateSoapEnvelope(idTransaction, credentials);

		QueryStateBodyData soapEnvelopeBody = queryStateSoapEnvelope.getQueryStateBodyData();
		Assert.assertEquals(credentials.getClientId(), soapEnvelopeBody.getClientId());
		Assert.assertEquals(credentials.getLogin(), soapEnvelopeBody.getLogin());
		Assert.assertEquals(credentials.getPassword(), soapEnvelopeBody.getPassword());
		Assert.assertEquals(idTransaction, soapEnvelopeBody.getTransactionId());
	}

	/**
	 * Test the conversion from the transaction data to the object representing the soap message envelope of the update state request.
	 */
	@Test
	public void testUpdateStateSoapConverter() {

		Long idNewState = 11L;
		UpdateStateRequestSoapEnvelope updateStateSoapEnvelope = SoapEnvelopeConverter.getUpdateStateSoapEnvelope(idTransaction, idNewState, credentials);

		UpdateStateBodyData soapEnvelopeBody = updateStateSoapEnvelope.getUpdateStateBodyData();
		Assert.assertEquals(credentials.getClientId(), soapEnvelopeBody.getClientId());
		Assert.assertEquals(credentials.getLogin(), soapEnvelopeBody.getLogin());
		Assert.assertEquals(credentials.getPassword(), soapEnvelopeBody.getPassword());
		Assert.assertEquals(idTransaction, soapEnvelopeBody.getTransactionId());
		Assert.assertEquals(idNewState, soapEnvelopeBody.getNewStateId());
	}
	
	/**
	 * Get a Fraudvault authentication credentials object.
	 * 
	 * @return the credentials object.
	 */
	private Credentials getCredentials() {

		return Credentials.builder(2L, "myLogin", "myPassword").build();
	}

	/**
	 * Get a Fraudvault transaction object with data for the tests.
	 * 
	 * @param transactionId Id to be setted to the transaction.
	 * @return the transaction object.
	 */
	private Transaction getTransaction(String transactionId) {

		Date creation = getDate(2017, 10, 28, 17, 0, 0);
		Date path1DepartureDate = getDate(2017, 10, 30, 17, 0, 0);
		Date path1ArrivalDate = getDate(2017, 11, 1, 1, 0, 0);

		Date path2DepartureDate = getDate(2017, 11, 10, 8, 0, 0);
		Date path2ArrivalDate = getDate(2017, 11, 10, 13, 0, 0);

		List<Passenger> passengers = new ArrayList<>();
		passengers.add(Passenger.builder().documentType(1).documentNumber("53140140")
				.firstName("Claudia").lastName("Rodriguez").email("crp@test.com").build());
		passengers.add(Passenger.builder().documentType(1).documentNumber("53140141")
				.firstName("Pedro").lastName("Gomez").email("pgomez@test.com").build());
		List<FlightPath> flightPaths = new ArrayList<>();

		flightPaths.add(FlightPath.builder().flightNumber("AVI123").arrivalDate(path1ArrivalDate)
				.departureDate(path1DepartureDate).destination("MEX").origin("BOG").travelClass("A")
				.build());
		flightPaths.add(FlightPath.builder().flightNumber("AVI456").arrivalDate(path2ArrivalDate)
				.departureDate(path2DepartureDate).destination("BOG").origin("MEX").travelClass("A")
				.build());

		List<RetailItem> items = new ArrayList<>();
		items.add(RetailItem.builder().quantity(1).unitPrice(new BigDecimal(5000)).product(
				RetailProduct.builder().code("prd12").name("The book").brand("brand book").build())
				.build());

		return Transaction.builder()
				.transactionId(transactionId).referenceCode("000001").sessionId("session-abc-1234")
				.creationDate(creation).test(true).countryIso("CO").origin(1100)
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
	
	/**
	 * Gets a date with the sent data.
	 * 
	 * @param year year to set in the date.
	 * @param month month to set in the date.
	 * @param day day to set in the date.
	 * @param hour hour to set in the date.
	 * @param minute minute to set in the date.
	 * @param second second to set in the date.
	 * @return the date with the sent data.
	 */
	private Date getDate(int year, int month, int day, int hour, int minute, int second) {

		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(0);
		cal.set(year, month, day, hour, minute, second);
		return cal.getTime();
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
