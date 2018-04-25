package co.com.bancolombia.service.verifyaccountowner.interceptors;

import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.context.MessageContext;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class LogHttpHeaderClientInterceptor implements ClientInterceptor {

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
