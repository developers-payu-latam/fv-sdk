/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 19 de dic. de 2017
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

package com.payulatam.fd.api.client.exception;


/**
 * Represents an exception related with the process of serialization/deserialization of XML data.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
public class XmlConversionException extends Exception {

	private static final long serialVersionUID = -9096596739135898377L;

	public XmlConversionException(String msg) {
		super(msg);
	}

	public XmlConversionException(String msg, Throwable e) {
		super(msg, e);
	}

	public XmlConversionException(Throwable e) {
		super(e);
	}

}
