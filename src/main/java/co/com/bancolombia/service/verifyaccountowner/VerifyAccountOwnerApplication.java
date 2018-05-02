package co.com.bancolombia.service.verifyaccountowner;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.camel.model.dataformat.JaxbDataFormat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.context.MessageContext;

@SpringBootApplication
public class VerifyAccountOwnerApplication {

	public static void main(String[] args) {
		SpringApplication.run(VerifyAccountOwnerApplication.class, args);
	}
}

@Configuration
class Config{

	/**
	 * JaxbContext for DataPower SOAP http://grupobancolombia.com/intf/Producto/Depositos/ConsultaCuentaDepositos/V1.0
	 * @return Jaxb2Marshaller
	 */
	@Bean
	public Jaxb2Marshaller jaxb2Marshaller(){
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setContextPaths("com.grupobancolombia.ents.common.genericexception.v2",
				"com.grupobancolombia.ents.soi.coreextensions.v2",
				"com.grupobancolombia.ents.soi.messageformat.v2",
				"com.grupobancolombia.intf.producto.depositos.consultacuentadepositos.v2");
		return jaxb2Marshaller;
	}

	/**
	 * Apache Camel JaxbContext for Datapower SOAP http://grupobancolombia.com/intf/Producto/Depositos/ConsultaCuentaDepositos/V1.0
	 * @implNote this bean is used by the Apache Camel Spring-ws component
	 * @return JaxbDataFormat
	 */
	@Bean
	public JaxbDataFormat jaxbDataFormat(){
		JaxbDataFormat format = new JaxbDataFormat();
		format.setContextPath(jaxb2Marshaller().getContextPath());
		format.setPrettyPrint(true);
		return format;
	}

	/**
	 * Spring SOAP Template
	 * @implNote this bean is used by the Apache Camel Spring-ws component
	 * @return WebServiceTemplate
	 */
	@Bean
	public WebServiceTemplate webServiceTemplate(){
		trust();
		ClientInterceptor[] interceptors = new ClientInterceptor[] {new LogHttpHeaderClientInterceptor()};
		WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
		webServiceTemplate.setMarshaller(jaxb2Marshaller());
		webServiceTemplate.setUnmarshaller(jaxb2Marshaller());
		webServiceTemplate.setInterceptors(interceptors);
		return webServiceTemplate;
	}

	public void trust(){
		try {
			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
				}

				public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
				}
			} };
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());

			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			HostnameVerifier allHostsValid = new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			};
			SSLContext.setDefault(sc);
			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
			System.out.println("All Certificates Have Been Trusted Successfully.");
		} catch (KeyManagementException ex) {
			ex.printStackTrace();
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
	}

	class LogHttpHeaderClientInterceptor implements ClientInterceptor {

	    @Override
	    public boolean handleRequest(MessageContext messageContext) throws WebServiceClientException {
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        try {
	            messageContext.getRequest().writeTo(out);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        System.out.println(new String(out.toByteArray()));
	        return true;
	    }

	    @Override
	    public boolean handleResponse(MessageContext messageContext) throws WebServiceClientException {
	        return false;
	    }

	    @Override
	    public boolean handleFault(MessageContext messageContext) throws WebServiceClientException {
	        return false;
	    }

	    @Override
	    public void afterCompletion(MessageContext messageContext, Exception ex) throws WebServiceClientException {

	    }
	}

}
