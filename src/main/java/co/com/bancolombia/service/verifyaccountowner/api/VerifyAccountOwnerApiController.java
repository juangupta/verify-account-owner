package co.com.bancolombia.service.verifyaccountowner.api;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.camel.EndpointInject;
import org.apache.camel.FluentProducerTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupobancolombia.intf.producto.depositos.consultacuentadepositos.v2.CondicionesComercialesCIE;
import com.grupobancolombia.intf.producto.depositos.consultacuentadepositos.v2.ConsultarInformacionExtendidaCuenta;
import com.grupobancolombia.intf.producto.depositos.consultacuentadepositos.v2.IdentificacionCliente;
import com.grupobancolombia.intf.producto.depositos.consultacuentadepositos.v2.InformacionClienteCIE;
import com.grupobancolombia.intf.producto.depositos.consultacuentadepositos.v2.InformacionCuentaCIE;
import com.grupobancolombia.intf.producto.depositos.consultacuentadepositos.v2.InformacionTransaccion;

import co.com.bancolombia.service.verifyaccountowner.model.JsonApiRequest;
import co.com.bancolombia.service.verifyaccountowner.model.JsonApiResponse;
import co.com.bancolombia.service.verifyaccountowner.model.VerifyAccountRequest;
import co.com.bancolombia.service.verifyaccountowner.model.VerifyAccountResponse;
import io.swagger.annotations.ApiParam;

@Controller
public class VerifyAccountOwnerApiController implements VerifyAccountOwnerApi {

    @EndpointInject(uri="direct:verify-account-transaction")
    private FluentProducerTemplate producerTemplate;

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public VerifyAccountOwnerApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<JsonApiResponse> verifyAccountOwner(@ApiParam(value = "" ,required=true )  @Valid @RequestBody JsonApiRequest body) {

    	
         JsonApiResponse response = (JsonApiResponse) producerTemplate.withBody(body).request();
         System.out.println(response);
    	 return new ResponseEntity<JsonApiResponse>(response, HttpStatus.OK); 
//         if (response.getData() != null) {
//        	 return new ResponseEntity<JsonApiResponse>(response, HttpStatus.OK);        	 
//         }
//         else {
//        	 
//        	 return new ResponseEntity<JsonApiResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//         }
        	
    }

}
