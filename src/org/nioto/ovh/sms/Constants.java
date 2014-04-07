package org.nioto.ovh.sms;

public class Constants {

	public static final String URL="https://www.ovh.com/cgi-bin/sms/http2sms.cgi";
	
	public static final String DATE_FORMAT="hhmmjjMMAAAA";
	
	/**
	 *  Type de classe pour le SMS
	 */
	public static enum SmsClass {
		/**
		 *  SMS affiché mais non sauvegardé
		 */
		CLASS_0(0),
		/**
		 * SMS enregistré dans le téléphone, si le téléphone est plein enregistré dans le SIM. Valeur par défaut
		 */
		CLASS_1(1),
		/**
		 * SMS enregistré dans la SIM
		 */
		CLASS_2(2),
		/**
		 * Le message est transféré sur un équipement externe connecté au mobile (PDA, PC portable…)
		 */
		CLASS_3(3);
		
		private int clazz;
		private SmsClass(int clazz) {
			this.clazz = clazz;
		}
		protected int getSmsClass(){
			return this.clazz;
		}
		public int getValue() {
			return this.clazz;
		}
	}
	
	/**
	 * Type de réponse attendue.
	 * Valeur par défaut : text/plain
	 */
	public static enum ContentType{
		TEXT_XML("text/xml"),
		APPLICATION_XML("application/xml"),
		TEXT_JSON("text/json"),
		APPLICATION_JSON("application/json"),
		TEXT_PLAIN("text/plain"),
		TEXT_HTML("text/html");
		
		private String contentType;
		private ContentType(String s){
			this.contentType = s;
		}
		
		public String getValue(){
			return this.contentType;
		}
	}	
}