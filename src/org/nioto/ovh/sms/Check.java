package org.nioto.ovh.sms;

import java.util.List;

public class Check {

	public static void notNull(String name, Object value) 
			throws SMSException {
		if( value == null){
			throw new SMSException( String.format("%1 cannot be null", name));
		}
	}
	
	public static void checkLength(String name, int maxLen, String value)
	throws SMSException {
		if (value != null) {
			if( value.length() >maxLen) {
				throw new SMSException( String.format("%1 must be less than %2 characters", name, maxLen));
			}
		}
	}

	public static void notEmpty(String name, List<String> value) 
	throws SMSException {
		if (value==null || value.size() ==0){
			throw new SMSException( String.format("%1 must not be empty", name));
		}
	}
	public static void notEmpty(String name, String value) 
	throws SMSException {
		if (value==null || value.isEmpty()){
			throw new SMSException( String.format("%1 must not be empty", name));
		}
	}
}
