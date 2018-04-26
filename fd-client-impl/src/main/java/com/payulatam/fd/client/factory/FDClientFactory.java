/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 20 de oct. de 2017
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

package com.payulatam.fd.client.factory;

import com.payulatam.fd.client.retrofit.RetrofitFDClient;
import com.payulatam.fd.api.client.FDClient;
import com.payulatam.fd.api.client.FDClientConfiguration;

/**
 * Factory to create client instances of the fraud detection service.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a> 
 */
public class FDClientFactory {
	
	private FDClientFactory(){}
	
	/**
	 * Gets an instance of a default client using Retrofit.
	 * 
	 * @param configuration the configuration data for the client.
	 * @return the fraud detection service client.
	 */
	public static FDClient createDefaultClient(FDClientConfiguration configuration){
		if(configuration == null){
			 throw new IllegalArgumentException("The client configuration must not be null");
		}		
		return new RetrofitFDClient(configuration);
	}

}
