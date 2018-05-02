package co.com.bancolombia.service.verifyaccountowner.routes;

import org.apache.camel.*;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.processor.validation.PredicateValidationException;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.HashMap;
import java.util.Map;

@RunWith(CamelSpringBootRunner.class)
@SpringBootTest
public class DepositAccountQueryRouteTest {

    private static final String MOCK_RESULT_SPRING_WS="mock:result-springws";
    private static final String MOCK_RESULT_FREE_MARKER="mock:result-free-marker";
    private static final String MOCK_DIRECT="direct:mock-soap-deposit-account-query";

    @Autowired
    private CamelContext camelContext;

    @EndpointInject(uri = MOCK_RESULT_SPRING_WS)
    private MockEndpoint mockEndpointSpringWS;

    @EndpointInject(uri = MOCK_RESULT_FREE_MARKER)
    private MockEndpoint mockEndpointFreeMarker;

    @EndpointInject(uri = MOCK_DIRECT)
    private ProducerTemplate producerTemplate;

    @Before
    public void setup() throws Exception {

        camelContext.getRouteDefinition(DepositAccountQueryRoute.ROUTE_ID)
                .autoStartup(true)
                .adviceWith(camelContext, new AdviceWithRouteBuilder() {
                    @Override
                    public void configure() throws Exception {
                        replaceFromWith(MOCK_DIRECT);

                        interceptSendToEndpoint("freemarker*")
                                .skipSendToOriginalEndpoint()
                                .to(MOCK_RESULT_FREE_MARKER);

                        interceptSendToEndpoint("spring-ws*")
                                .skipSendToOriginalEndpoint()
                                .to(MOCK_RESULT_SPRING_WS);
                    }
                });

        mockEndpointFreeMarker.expectedBodiesReceived(getFreeMarkerExpectedMap());
    }

    @Test(expected = CamelExecutionException.class)
    public void validateBody_IsMapInstace(){
        producerTemplate.requestBody("String as body");
    }

    @Test
    public void validateRequiredField_ProductType() throws InterruptedException {
        Map<String,String> body = new HashMap<>();
        body.put("ProductNumber","");
        body.put("BeneficiaryDocumentType","");
        body.put("BeneficiaryDocument","");

        producerTemplate.requestBody(body);
        mockEndpointFreeMarker.assertIsNotSatisfied();
    }

    @Test
    public void validateRequiredField_ProductNumber() throws InterruptedException {
        Map<String,String> body = new HashMap<>();
        body.put("ProductType","");
        body.put("BeneficiaryDocumentType","");
        body.put("BeneficiaryDocument","");

        producerTemplate.requestBody(body);
        mockEndpointFreeMarker.assertIsNotSatisfied();
    }

    @Test
    public void validateRequiredField_BeneficiaryDocumentType() throws InterruptedException {
        Map<String,String> body = new HashMap<>();
        body.put("ProductType","");
        body.put("ProductNumber","");
        body.put("BeneficiaryDocument","");

        producerTemplate.requestBody(body);
        mockEndpointFreeMarker.assertIsNotSatisfied();
    }

    @Test
    public void validateRequiredField_BeneficiaryDocument() throws InterruptedException {
        Map<String,String> body = new HashMap<>();
        body.put("ProductType","");
        body.put("ProductNumber","");
        body.put("BeneficiaryDocumentType","");

        producerTemplate.requestBody(body);
        mockEndpointFreeMarker.assertIsNotSatisfied();
    }

    @Test
    public void validateAllRequiredFields() throws InterruptedException {
        mockEndpointFreeMarker.expectedMessageCount(3);
        Map<String,String> body = new HashMap<>();
        body.put("ProductType","");
        body.put("ProductNumber","");
        body.put("BeneficiaryDocumentType","");
        body.put("BeneficiaryDocument","");

        producerTemplate.requestBody(body);
        mockEndpointFreeMarker.assertIsSatisfied();
    }


    private Map<String ,String > getFreeMarkerExpectedMap(){
        Map<String,String> expectedMap = new HashMap<>();
        expectedMap.put("ProductType","");
        expectedMap.put("ProductNumber","");
        expectedMap.put("BeneficiaryDocumentType","");
        expectedMap.put("BeneficiaryDocument","");
        return expectedMap;
    }
}
