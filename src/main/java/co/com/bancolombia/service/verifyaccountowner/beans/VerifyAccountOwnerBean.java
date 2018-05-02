package co.com.bancolombia.service.verifyaccountowner.beans;

import java.util.HashMap;
import java.util.Map;

import com.grupobancolombia.intf.producto.depositos.consultacuentadepositos.v2.ConsultarInformacionExtendidaCuentaResponse;

import co.com.bancolombia.service.verifyaccountowner.model.JsonApiRequest;
import co.com.bancolombia.service.verifyaccountowner.model.client.ClientJsonApiResponse;
import co.com.bancolombia.service.verifyaccountowner.model.client.ClientValidateChannelResponse;

public class VerifyAccountOwnerBean {

	private Map<String, Object> attributes;

	public VerifyAccountOwnerBean() {

		attributes = new HashMap<>();
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public Map<String, Object> updateAttributesRequestService(JsonApiRequest serviceRequest) {
		attributes.put("BeneficiaryDocument", serviceRequest.getData().get(0).getBeneficiaryDocument());
		// Mapear a dato esperado por DP
		attributes.put("BeneficiaryDocumentType", serviceRequest.getData().get(0).getBeneficiaryDocumentType());
		attributes.put("Id", serviceRequest.getData().get(0).getHeader().getId());
		attributes.put("Type", serviceRequest.getData().get(0).getHeader().getType());
		// Mapear a dato esperado por DP
		attributes.put("ClientDocumentType", serviceRequest.getData().get(0).getClientDocumentType());
		attributes.put("ClientDocument", serviceRequest.getData().get(0).getClientDocument());
		// Mapear a dato esperado por DP
		attributes.put("BusinessDocumentType", serviceRequest.getData().get(0).getBusinessDocumentType());
		attributes.put("BusinessDocument", serviceRequest.getData().get(0).getBusinessDocument());
		attributes.put("MessageId", serviceRequest.getData().get(0).getMessageId());
		attributes.put("TransactionDate", serviceRequest.getData().get(0).getTransactionDate());
		attributes.put("ClientIp", serviceRequest.getData().get(0).getClientIp());
		attributes.put("ChannelId", serviceRequest.getData().get(0).getChannelId());
		attributes.put("ConsumerId", serviceRequest.getData().get(0).getConsumerId());
		attributes.put("ProductNumber", serviceRequest.getData().get(0).getProductNumber());
		// Mapear a dato esperado por DP
		attributes.put("ProductType", serviceRequest.getData().get(0).getProductType());
		attributes.put("Otp", serviceRequest.getData().get(0).getOtp());
		attributes.put("Error", "0000");
		attributes.put("DescError", "No error");

		return attributes;
	}

	public Map<String, Object> validateChannelServiceResponse(Object channelServiceResponseObject, String error, String desc_error) {
		
		if (channelServiceResponseObject instanceof ClientJsonApiResponse && error.equals("0000"))
		{
			ClientJsonApiResponse channelServiceResponse = (ClientJsonApiResponse) channelServiceResponseObject;
			ClientValidateChannelResponse clientValidateChannelResponse = new ClientValidateChannelResponse();
			clientValidateChannelResponse = (ClientValidateChannelResponse) channelServiceResponse.getData().get(0);
			attributes.put("IsActive", clientValidateChannelResponse.getAttributes().isActive());
			if (clientValidateChannelResponse.getAttributes().isActive()) 
			{

				attributes.put("Error", "0000");
				attributes.put("DescError", "No error");
				
			}
			else 
			{

				attributes.put("Error", "0003");
				attributes.put("DescError", "Canal no se encuentra activo");
			}
			
		}
		else
		{
			attributes.put("Error", error);
			attributes.put("DescError", desc_error);
		}
		return attributes;
	}
	
	public Map<String, Object> validateDepositAccountServiceResponse(Object depositAccountServiceResponseObject, String error, String desc_error) {
		
		if (depositAccountServiceResponseObject instanceof ConsultarInformacionExtendidaCuentaResponse && error.equals("0000"))
		{
			ConsultarInformacionExtendidaCuentaResponse depositAccountServiceResponse = (ConsultarInformacionExtendidaCuentaResponse) depositAccountServiceResponseObject;
			attributes.put("RelacionCliente", depositAccountServiceResponse.getInformacionCuenta().getInformacionCliente().getRelacionCliente().getRelacionClienteCuenta());
			attributes.put("NombreTitular", depositAccountServiceResponse.getInformacionCuenta().getInformacionCliente().getIdentificacionTitular().getNombreTitular());
			attributes.put("NumeroIdentificacion", depositAccountServiceResponse.getInformacionCuenta().getInformacionCliente().getIdentificacionTitular().getNumeroIdentificacion());
			attributes.put("TipoIdentificacion", depositAccountServiceResponse.getInformacionCuenta().getInformacionCliente().getIdentificacionTitular().getTipoIdentificacion());					
			attributes.put("NumeroCuenta", depositAccountServiceResponse.getInformacionCuenta().getCondicionesComerciales().getInformacionTransaccion().getNumeroCuenta());
			attributes.put("TipoCuenta", depositAccountServiceResponse.getInformacionCuenta().getCondicionesComerciales().getInformacionTransaccion().getTipoCuenta());
			if (attributes.get("RelacionCliente").equals("T")) 
			{

				attributes.put("Error", "0000");
				attributes.put("DescError", "No error");
				
			}
			else 
			{

				attributes.put("Error", "0002");
				attributes.put("DescError","Cliente no es titular de la cuenta");
			}
		}
		else
		{
			attributes.put("Error", error);
			attributes.put("DescError", desc_error);
		}
		return attributes;
	}
}
