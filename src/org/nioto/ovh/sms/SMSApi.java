package org.nioto.ovh.sms;

public class SMSApi {

	private String account, login, password, from;
	
	private boolean trustAllCertificates;
	
	public SMSApi(String account, String login, String password, String from)
	throws SMSException {
		this.account = account;
		Check.notNull("account", this.account);
		if( !this.account.matches("sms\\-[a-zA-Z0-9]+\\-[a-zA-Z0-9]+")) {
			throw new SMSException("Format for account is not sms-NIC-XXXXX");
		}
		this.login = login;
		Check.notNull("login", this.login);
		this.password = password;
		Check.notNull("password", this.password);
		Check.notNull("From", from);
		this.from = from;
	}
	
	
	protected String getAccount(){
		return this.account;
	}
	protected String getLogin(){
		return this.login;
	}
	protected String getPassword(){
		return this.password;
	}
	protected String getFrom(){
		return this.from;
	}
	
	public SMSMessage createMessage() 
			throws SMSException {
		return new SMSMessage(this);
	}


	public boolean isTrustAllCertificates() {
		return trustAllCertificates;
	}

/**
 * For certain JDK, some SSL incompatibilites occured.
 * Setting this parameter to true, disable Certificates verification.
 * @param trustAllCertificates
 */
	public void setTrustAllCertificates(boolean trustAllCertificates) {
		this.trustAllCertificates = trustAllCertificates;
	}
}