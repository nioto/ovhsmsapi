package org.nioto.ovh.sms;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nioto.ovh.sms.Constants.ContentType;
import org.nioto.ovh.sms.Constants.SmsClass;

import com.github.kevinsawicki.http.HttpRequest;

public class SMSMessage {

	SMSApi api;
	private List<String> to;
	private String message;
	private boolean addStop = false;
	private Date date;
	private String tag;
	private SmsClass classType = SmsClass.CLASS_1;
	private ContentType contentType = ContentType.TEXT_PLAIN;
	
	protected SMSMessage(SMSApi api){
		this.api = api;
		this.to = new ArrayList<String>();
	}
	
	public void addRecipient(String recipient)
	throws SMSException{
		Check.notNull("recipient", recipient);
		this.to.add(recipient);
	}
	public void setMessage(String msg)
	throws SMSException{
		Check.notNull("message", msg);
		this.message = msg;
	}
	public void setDate(Date d)
	throws SMSException{
		if( d != null  ){
			if  (d.before( new Date())){
			throw new SMSException( "date must be in the futur");
			} else {
				this.date = new Date(d.getTime());
			}
		}
	}
	
	public void setTag(String tag) 
	throws SMSException {
		Check.checkLength( "tag", 20, tag);
		this.tag = tag;
	}
	
	public void setClassType(SmsClass classType) 
	throws SMSException{
		Check.notNull("SMS type", classType);
		this.classType = classType;
	}
	
	public void setContentType(ContentType contentType)
	throws SMSException {
		Check.notNull("Returned Content Type", contentType);
		this.contentType = contentType;
	}
	
	
	public void addStop(boolean b){
		this.addStop = b;
	}
	
	public void checkData() 
			throws SMSException{
		Check.notEmpty("recipients", this.to);
		Check.notEmpty("message", this.message);		
	}

	private Map<String, Object> getData(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("account", this.api.getAccount());
		map.put( "login", this.api.getLogin());
		map.put("password", this.api.getPassword());
		map.put( "from", this.api.getFrom());
		if ( this.to.size() >1) {
			StringBuilder sb = new StringBuilder( this.to.get(0));
			for (int i = 1; i< this.to.size(); i ++) {
				sb.append( ',').append( this.to.get(i) );
			}
			map.put("to", sb.toString() );
		} else {
			map.put("to", this.to.get(0));
		}
		map.put("message", this.message);
		map.put("noStop", (this.addStop? 0:1) );
		if ( this.date !=null) {
			String tmp = new SimpleDateFormat( Constants.DATE_FORMAT).format( this.date);
			map.put("deferred", tmp);
		}
		map.put("class", this.classType.getValue());
		if( this.tag != null ) {
			map.put("tag", this.tag);
		}
		map.put("contentType", this.contentType.getValue());		
		return map;		
	}
	
	/**
	 * Send text message
	 * @return The Body of the response
	 * @throws SMSException if data is malformed
	 */
	public String send()
	throws SMSException {
		checkData();
		HttpRequest req = HttpRequest.get( Constants.URL, getData(), false);
		if( this.api.isTrustAllCertificates()) {
			req.trustAllCerts();
		}
		System.out.println( req.toString());
		return req.body();
	}
}
