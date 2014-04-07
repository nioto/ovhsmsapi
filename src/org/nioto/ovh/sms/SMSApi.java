package org.nioto.ovh.sms;

public class SMSApi {

	private String account, login, password;
	
	public SMSApi(String account, String login, String password)
	throws SMSException {
		this.account = account;
		Check.notNull("account", this.account);
		if( !this.account.startsWith("sms-nic-")) {
			throw new SMSException("account must start with sms-nic-");
		}
		this.login = login;
		Check.notNull("login", this.login);
		this.password = password;
		Check.notNull("password", this.password);
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
}