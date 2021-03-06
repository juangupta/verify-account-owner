/**
 * NOTE: This class is auto generated by the swagger code generator program (2.3.1).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package co.com.bancolombia.service.verifyaccountowner.api;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.bancolombia.service.verifyaccountowner.model.JsonApiRequest;
import co.com.bancolombia.service.verifyaccountowner.model.JsonApiResponse;
import co.com.bancolombia.service.verifyaccountowner.model.JsonApiError;
import co.com.bancolombia.service.verifyaccountowner.model.VerifyAccountResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-04-11T14:17:45.456Z")
@Api(value = "verifyAccountOwner", description = "the verifyAccountOwner API")
public interface VerifyAccountOwnerApi {

    @ApiOperation(value = "Verify Account Owner", nickname = "verifyAccountOwner", notes = "Verify if beneficiary is own of account", response = JsonApiResponse.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Beneficiary is account owner", response = JsonApiResponse.class),
        @ApiResponse(code = 405, message = "Invalid Input. please put correct request", response = JsonApiResponse.class) })
    @RequestMapping(value = "/verifyAccountOwner",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<JsonApiResponse> verifyAccountOwner(@ApiParam(value = "", required = true) @Valid @RequestBody JsonApiRequest body);
}
