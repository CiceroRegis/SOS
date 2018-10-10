package com.sgi.utils;

import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * 
 * @author Cícero Régis on 28/09/2018
 *
 */
public class EmailUtils {
	/**
	 * Configuracoes google
	 */
	private static final String SERVIDOR_EMAIL = "smtp.gmail.com";
	private static final String PROTOCOLO_EMAIL = "mail.smtp.host";
	private static final String SOCKET_LABEL_PORT = "mail.smtp.socketFactory.port";
	private static final String PORTA =  "587";
	private static final String SOCKET_LABEL_CLASS = "mail.smtp.socketFactory.class";
	private static final String CLASSE = "javax.net.ssl.SSLSocketFactory";
	private static final String AUTH = "mail.smtp.auth";
	private static final String SMTP_PORT = "mail.smtp.port";
	private static final String USUARIO_EMAIL = "testejava2018@gmail.com";
	private static final String SENHA_EMAIL = "Meups32018";
	private static final String ASSUNTO_ESQUECEU_SENHA = "SGI Email para recuperação de senha";
	private static final String GMAIL_STARTTLS = "mail.smtp.starttls.enable";
	/********************************************************** ******************************************************************/
	
	private static final String AUTORIZACAO = "true";
	
	/***
	 * Para validacao do email
	 * @return
	 */
	private static Pattern pattern;
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	
	public static Session configurarEmailGmail(){
		Properties prop = new Properties();
		
		prop.put(AUTH, AUTORIZACAO);
		prop.put(GMAIL_STARTTLS,AUTORIZACAO);
		prop.put(PROTOCOLO_EMAIL, SERVIDOR_EMAIL);
		prop.put(SMTP_PORT, PORTA);
		
		Session session = Session.getInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(USUARIO_EMAIL, SENHA_EMAIL);
			}
		});
		
		session.setDebug(false);
		
		return session;
	}
	
	public static String construirMensagem(String mensagemDesejada){
		String mensagem = new String();
		
		mensagem = "<html>"
				+ "<center>"
				+ "<img src=\"cid:image\">"
				+ "<p><h2><b>ATENÇÃO!</b></h2><h3>Esta é uma mensagem automatica,por favor não responder!<h3/><p>"
				+ "<br/>"
				+ "<br/>"							
				+ mensagemDesejada
				+ "<br/>"
				+ "</center>"
				+ "</html>";
		return mensagem;
	}
	
	public static void enviarEmailGmail(String mensagemDesejada, String para, String assunto){
		
		try {
			
			MimeMessage mensagem = new MimeMessage(configurarEmailGmail());
			
			Address[] toUser = InternetAddress.parse(para);
			
			mensagem.setRecipients(Message.RecipientType.TO, toUser);
			
			mensagem.setSubject(assunto);
		
			////////////////////////////////////////////////////////////
			
			Multipart multipart = new MimeMultipart("related");
			
			MimeBodyPart corpoEmail = new MimeBodyPart();
			//DataSource data = new FileDataSource("/opt/images/logo-agefis.png");
			DataSource data = new FileDataSource("/home/cicinho/eclipse-workspace/SGI/src/main/webapp/resources/sgi/img/logo.png");
			//DataSource data = new FileDataSource("C:/Users/cicer/git/sgp-repo/src/main/webapp/resources/images/logo.png");

			
			corpoEmail.setContent(construirMensagem(mensagemDesejada), "text/html ; charset=UTF-8");
			
			multipart.addBodyPart(corpoEmail);
						
			corpoEmail = new MimeBodyPart();
			
			corpoEmail.setDataHandler(new DataHandler(data));
			
			corpoEmail.setHeader("Content-ID", "<image>");
			
			multipart.addBodyPart(corpoEmail);
			
			mensagem.setContent(multipart);
			
			mensagem.saveChanges();
			
			Transport.send(mensagem);
			
			System.out.println(">>> Já mandou!!");
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
	}
	
	public static boolean validate(String value) {
		pattern = Pattern.compile(EMAIL_PATTERN);
        if(value == null) {
            return false;
        }
        if(!pattern.matcher(value.toString()).matches()) {
           return false;
        }
        return true;
    }
	
	public Map<String, Object> getMetadata() {
		return null;
	}

	public String getValidatorId() {
		return "custom.emailValidator";
	}

}
