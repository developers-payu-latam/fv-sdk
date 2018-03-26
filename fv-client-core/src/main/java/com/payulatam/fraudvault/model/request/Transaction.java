package com.payulatam.fraudvault.model.request;

import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.Date;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.convert.Convert;
import org.simpleframework.xml.core.Persister;

import com.payulatam.fraudvault.api.client.exception.XmlConversionException;
import com.payulatam.fraudvault.util.DateConverter;

import lombok.Builder;
import lombok.Data;

/**
 * Encapsulates the data of a transaction that is evaluated by Fraudvault.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
@Root(name = "validar-pago")
@Data
@Builder
public class Transaction {

	/** The identifier of the transaction. */
	@Element(name = "transaccion-id", required = false)
	private String transactionId;

	/** The internal reference code of the transaction used by the client. */
	@Element(name = "referencia", required = false)
	private String referenceCode;

	/** The session identifier used for the device fingerprinting. */
	@Element(name = "session-id", required = false)
	private String sessionId;

	/** The date and time of creation of the transaction. */
	@Element(name = "fecha-creacion", required = false)
	@Convert(DateConverter.class)
	private Date creationDate;

	/** Determines whether the transaction is a test or not. */
	@Element(name = "prueba", required = false)
	private Boolean test;

	/** The ISO 3166 code of the country of the transaction. */
	@Element(name = "pais", required = false)
	private String countryIso;

	/** The origin code of the transaction, the possible values may vary according to the client. */
	@Element(name = "origen", required = false)
	private Integer origin;

	/** Indicates whether the transaction is related to a payment made in a single click. */
	@Element(name = "pago-en-un-solo-clic", required = false)
	private String oneClickPayment;

	/** The merchant seller data. */
	@Element(name = "usuario-vendedor", required = false)
	private Seller sellerUser;

	/** The buyer user data. */
	@Element(name = "usuario-comprador", required = false)
	private Buyer buyerUser;

	/** The description of the payment. */
	@Element(name = "descripcion", required = false)
	private String description;

	/** The amount of the payment. */
	@Element(name = "valor", required = false)
	private BigDecimal value;

	@Element(name = "iva", required = false)
	private BigDecimal tax;

	@Element(name = "base-devolucion-iva", required = false)
	private BigDecimal taxReturnBase;

	@Element(name = "valor-adicional", required = false)
	private BigDecimal additionalValue;

	@Element(name = "tarifa-administrativa", required = false)
	private BigDecimal administrativeRate;

	@Element(name = "iva-tarifa-administrativa", required = false)
	private BigDecimal administrativeRateTax;

	@Element(name = "base-dev-iva-tarifa-administrativa", required = false)
	private BigDecimal administrativeRateTaxReturnBase;
	
	/** First field of extra information. */
	@Element(name = "extra1", required = false)
	private String extra1;

	/** Second field of extra information. */
	@Element(name = "extra2", required = false)
	private String extra2;

	/** Third field of extra information. */
	@Element(name = "extra3", required = false)
	private String extra3;

	/** The delivery address data. */
	@Element(name = "direccion-entrega", required = false)
	private Address deliveryAddress;

	/** The payment information. */
	@Element(name = "informacion-pago", required = false)
	private PaymentInformation paymentInformation;

	@Element(name = "pnr", required = false)
	private Pnr pnr;

	@Element(name = "fecha-ultima-transaccion", required = false)
	@Convert(DateConverter.class)
	private Date lastTransactionDate;
	
	private static Serializer serializer = new Persister(new AnnotationStrategy());

	/**
	 * Serializes the this {@code Transaction} object to XML data.
	 * 
	 * @return the XML data from the {@code Transaction}.
	 * @throws XmlConversionException if the object cannot be serialized.
	 */
	public String toXml() throws XmlConversionException {
		try {			
			Writer writer = new StringWriter();
			serializer.write(this, writer);
			return writer.toString();
		} catch (Exception e) {
			throw new XmlConversionException("Exception serilizing the Transaction object to XML", e);
		}

	}

}
