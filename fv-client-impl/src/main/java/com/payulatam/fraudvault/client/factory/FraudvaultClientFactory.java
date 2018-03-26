/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 20 de oct. de 2017
 */
package com.payulatam.fraudvault.client.factory;

import com.payulatam.fraudvault.api.client.FraudvaultClient;
import com.payulatam.fraudvault.api.client.FraudvaultClientConfiguration;
import com.payulatam.fraudvault.client.retrofit.RetrofitFraudvaultClient;

/**
 * Factory to create Fraudvault client instances.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a> 
 */
public class FraudvaultClientFactory {
	
	private FraudvaultClientFactory(){}
	
	/**
	 * Gets an instance of a default Fraudvault client using Retrofit.
	 * 
	 * @param configuration the configuration data for the client.
	 * @return the Fraudvault client.
	 */
	public static FraudvaultClient createDefaultFraudvaultClient(FraudvaultClientConfiguration configuration){
		if(configuration == null){
			 throw new IllegalArgumentException("Fraudvault client configuration must not be null");
		}		
		return new RetrofitFraudvaultClient(configuration);
	}

}
