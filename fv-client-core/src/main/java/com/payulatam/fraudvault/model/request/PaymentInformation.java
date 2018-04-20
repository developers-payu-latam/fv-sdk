/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 17 de nov. de 2017
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

package com.payulatam.fraudvault.model.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import lombok.Builder;
import lombok.Data;

/**
 * Encapsulates the data of the payment.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
@Root
@Data
@Builder
public class PaymentInformation {

	/** The payment method type. */
	private PaymentMethodType paymentMethodType;
	
	/** Identifier of the specific payment method type. **/
	@Element(name = "tipo-medio-pago", required = false)
	private Integer paymentMethodTypeId;

	/** Identifier of the specific payment method. It may vary depend on the country. */
	@Element(name = "medio-pago", required = false)
	private Integer paymentMethod;

	/** Number of the debit/credit card used by the buyer. */
	@Element(name = "pan", required = false)
	private String pan;

	/**
	 * The BIN-Bank Identification Number or IIN-Issuer Identification Number of the card used
	 * by the buyer. It is the 6 first digits of the card number.
	 */
	@Element(name = "bin", required = false)
	private String bin;

	/** Number of the card used by the buyer encrypted in SHA-256. */
	@Element(name = "numero", required = false)
	private String number;

	/** The last 4 digits of the card number used by the buyer. */
	@Element(name = "numero-visible", required = false)
	private String maskedNumber;

	/** Name of the issuing bank of the card used by the buyer. */
	@Element(name = "banco", required = false)
	private String issuerBank;

	/** ISO code 4217 of the currency used in the transaction. For example: USD for US dollar. */
	@Element(name = "moneda", required = false)
	private String currencyIso;

	/**
	 * Expiration date of the card used in the transaction with MM-YY format. For example:10-25 for
	 * October 2025
	 */
	@Element(name = "fecha-expiracion", required = false)
	private String expirationDate;

	/** Security code of the credit card used by the buyer. */
	@Element(name = "codigo-seguridad", required = false)
	private String securityCode;

	/** Number of payment installments. */
	@Element(name = "cuotas", required = false)
	private Integer installmentsNumber;

	/** The account/card holder data. */
	@Element(name = "titular", required = false)
	private AccountHolder holder;

	public static class PaymentInformationBuilder {
		
		public PaymentInformationBuilder paymentMethodType(PaymentMethodType paymentMethodType){
			this.paymentMethodType = paymentMethodType;
			paymentMethodTypeId(paymentMethodType.getId());
			return this;
		}
		
		private PaymentInformationBuilder paymentMethodTypeId(int paymentMethodTypeId){
			this.paymentMethodTypeId = paymentMethodTypeId;
			return this;
		}

		/**
		 * Expiration date of the card used in the transaction.
		 * 
		 * @param cardExpirationMonth month of the card expiration date with MM format. For Example:
		 *            02 for February and 11 for November
		 * @param cardExpirationYear year of the card expiration date with YY format. For Example:
		 *            23 for 2023
		 * @return the PaymentInformationBuilder
		 */
		public PaymentInformationBuilder expirationDate(String cardExpirationMonth, String cardExpirationYear) {
			if (cardExpirationMonth != null  && !cardExpirationMonth.isEmpty() &&
					cardExpirationYear != null && !cardExpirationYear.isEmpty()) {
				if (validateInputsExpirationDate(cardExpirationMonth, cardExpirationYear)) {
					int yearNumber = Integer.parseInt(cardExpirationYear);
					if (yearNumber > 2000) {
						yearNumber = yearNumber - 2000;
					}
					String y =  (yearNumber > 9) ? String.valueOf(yearNumber): "0" + yearNumber;
					this.expirationDate = cardExpirationMonth + "-" + y;					
				}
				else {
					throw new IllegalArgumentException("Invalid values for the card expiration month"
									+ " or " + "card expiration year");
				}
			}
			return this;
		}

		/**
		 * Validates if the card expiration month and year are valid.
		 * 
		 * @param cardExpirationMonth month of the card expiration date.
		 * @param cardExpirationYear year of the card expiration date
		 * @return if the inputs are valid.
		 */
		private static boolean validateInputsExpirationDate(String cardExpirationMonth,
				String cardExpirationYear) {
			boolean validInputs = true;
			try {
				int month = Integer.parseInt(cardExpirationMonth);
				int year = Integer.parseInt(cardExpirationYear);
				if (month < 0 || month > 12 || year < 0) {
					validInputs = false;
				}
			} catch (NumberFormatException e) {
				validInputs = false;
			}
			return validInputs;
		}

	}

}
