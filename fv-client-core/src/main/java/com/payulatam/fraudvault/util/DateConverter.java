package com.payulatam.fraudvault.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

/**
 * 
 * Converter used in the XML serialization/deserialization of Date type.
 */
public class DateConverter implements Converter<Date> {

	public static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";

	@Override
	public Date read(InputNode node) throws Exception {
		
		String date = node.getValue();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
		
		return sdf.parse(date);
	}

	@Override
	public void write(OutputNode node, Date date) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
		String dateStr = sdf.format(date);
		
		node.setValue(dateStr);
		
	}
	
}
