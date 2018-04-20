/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 19 de oct. de 2017
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

package com.payulatam.fraudvault.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

/**
 * Converter used in the XML serialization/deserialization of Date type.
 */
public class DateConverter implements Converter<Date> {

	/** The format for apply to the date fields. */
	public static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";

	/**
	 * Method invoked for the deserialization of the Date type.
	 * 
	 * @see org.simpleframework.xml.convert.Converter<T>{@link #read(InputNode)}
	 */
	@Override
	public Date read(InputNode node) throws Exception {
		
		String date = node.getValue();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
		
		return sdf.parse(date);
	}

	/**
	 * Method used to serialize an Date object to XML.
	 * 
	 * @see org.simpleframework.xml.convert.Converter<T>{@link #write(OutputNode, Date)}
	 */
	@Override
	public void write(OutputNode node, Date date) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
		String dateStr = sdf.format(date);
		
		node.setValue(dateStr);		
	}
	
}
