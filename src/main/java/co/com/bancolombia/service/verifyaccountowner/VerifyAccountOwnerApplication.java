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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;

import org.springframework.ws.soap.security.support.KeyStoreFactoryBean;
import org.springframework.ws.soap.security.support.TrustManagersFactoryBean;
import org.springframework.ws.soap.security.wss4j2.callback.KeyStoreCallbackHandler;
import org.springframework.ws.soap.security.wss4j2.support.CryptoFactoryBean;


@SpringBootApplication
public class VerifyAccountOwnerApplication {

	public static void main(String[] args) {
		SpringApplication.run(VerifyAccountOwnerApplication.class, args);
	}
}

@Configuration
class Config{
	
	@Value("${client.ssl.trust-store}")
	private Resource trustStore;

//	@Value("${client.ssl.trust-store-password}")
//	private String trustStorePassword;
	
	@Value("${client.ssl.key-alias}")
	private String trustStoreAlias;
	
	@Value("${microservice.ssl.key-store}")
	private Resource keyStore;

//	@Value("${microservice.ssl.key-store-password}")
//	private String keyStorePassword;
	
	@Value("${microservice.ssl.key-alias}")
	private String keyAlias;

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
	 * @throws Exception 
	 */
	@Bean
	public WebServiceTemplate webServiceTemplate() throws Exception{
		//trust();
		//ClientInterceptor[] interceptors = new ClientInterceptor[] {new LogHttpHeaderClientInterceptor()};
		ClientInterceptor[] interceptors = {securityInterceptor()};
		WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
		webServiceTemplate.setMarshaller(jaxb2Marshaller());
		webServiceTemplate.setUnmarshaller(jaxb2Marshaller());
		webServiceTemplate.setInterceptors(interceptors);
		
		

		
		
		return webServiceTemplate;
	}
	
	@Bean
	public KeyStoreCallbackHandler securityCallbackHandler() {
		KeyStoreCallbackHandler callbackHandler = new KeyStoreCallbackHandler();
		//callbackHandler.setPrivateKeyPassword(trustStorePassword);
		//callbackHandler.setPrivateKeyPassword(binding.getTrustStorePassword());
		callbackHandler.setPrivateKeyPassword("desarrollo");
		return callbackHandler;
	}
	
	@Bean
    public Wss4jSecurityInterceptor securityInterceptor() throws Exception {
        Wss4jSecurityInterceptor securityInterceptor = new Wss4jSecurityInterceptor();
        //binding = BluemixExternalConfig.getInstance().getSecretBinding(cloudantBindingPath, cloudantBindingFile,
		//		isBase64, CloudantBinding.class);
        // set security actions
        securityInterceptor.setSecurementActions("Signature Encrypt");

        // sign the request
        securityInterceptor.setSecurementUsername(keyAlias);
        //securityInterceptor.setSecurementPassword(keyStorePassword);
        //securityInterceptor.setSecurementPassword(binding.getKeyStorePassword());
        securityInterceptor.setSecurementPassword("desarrollo");
        securityInterceptor.setSecurementSignatureCrypto(getKeyStoreCryptoFactoryBean().getObject());

        // encrypt the request
        securityInterceptor.setSecurementEncryptionUser(trustStoreAlias);
        securityInterceptor.setSecurementEncryptionCrypto(getCryptoFactoryBean().getObject());
        securityInterceptor.setSecurementSignatureKeyIdentifier("DirectReference");
        
     // sign the response
        securityInterceptor.setValidationActions("Timestamp Signature Encrypt");
        securityInterceptor.setValidationSignatureCrypto(getCryptoFactoryBean().getObject());
        securityInterceptor.setValidationDecryptionCrypto(getKeyStoreCryptoFactoryBean().getObject());      
        securityInterceptor.setValidationCallbackHandler(securityCallbackHandler());
  
        return securityInterceptor;
    }
	
    @Bean
    public CryptoFactoryBean getCryptoFactoryBean() throws IOException {
        CryptoFactoryBean cryptoFactoryBean = new CryptoFactoryBean();
        //cryptoFactoryBean.setKeyStorePassword(trustStorePassword);
        //cryptoFactoryBean.setKeyStorePassword(binding.getTrustStorePassword());
        cryptoFactoryBean.setKeyStorePassword("desarrollo");
        cryptoFactoryBean.setKeyStoreLocation(trustStore);
        return cryptoFactoryBean;
    }
	
	 @Bean
    public CryptoFactoryBean getKeyStoreCryptoFactoryBean() throws IOException {
        CryptoFactoryBean cryptoFactoryBean = new CryptoFactoryBean();
        //cryptoFactoryBean.setKeyStorePassword(keyStorePassword);
        //cryptoFactoryBean.setKeyStorePassword(binding.getKeyStorePassword());
        cryptoFactoryBean.setKeyStorePassword("desarrollo");
        cryptoFactoryBean.setKeyStoreLocation(keyStore);
        return cryptoFactoryBean;
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
