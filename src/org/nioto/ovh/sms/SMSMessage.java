package org.nioto.ovh.sms;

import java.text.DateFormat;
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
	private String from;
	private List<String> to;
	private String message;
	private int nostop = 1;
	private Date date;
	private String tag;
	private SmsClass classType = SmsClass.CLASS_1;
	private ContentType contentType = ContentType.TEXT_PLAIN;
	
	protected SMSMessage(SMSApi api, String from){
		this.api = api;
		this.from = from;
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
		map.put( "from", this.from);
		if ( this.to.size() >1) {
			StringBuilder sb = new StringBuilder( this.to.get(0));
			for (int i = 1; i< this.to.size(); i ++) {
				sb.append( ',').append( this.to.get(i) );
			}
			map.put("to", sb.toString() );
		}
		map.put("message", this.message);
		map.put("noStop", this.nostop);
		if ( this.date !=null) {
			String tmp = new SimpleDateFormat( Constants.DATE_FORMAT).format( this.date);
			map.put("deferred", tmp);
		}
		map.put("class", this.classType.getValue());
		if( this.tag != null ) {
			map.put("tag", this.tag);
		}
		map.put("contenType", this.contentType);		
		return map;		
	}
	
	
	public void send()
	throws SMSException {
		
	}
	
}
