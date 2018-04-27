package co.com.bancolombia.service.verifyaccountowner.beans;

import java.util.HashMap;
import java.util.Map;

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

		return attributes;
	}

	public Map<String, Object> updateAttributesChannelService(ClientJsonApiResponse channelServiceResponse) {
		
		ClientValidateChannelResponse clientValidateChannelResponse = new ClientValidateChannelResponse();
		clientValidateChannelResponse = (ClientValidateChannelResponse) channelServiceResponse.getData().get(0);
		attributes.put("IsActive", clientValidateChannelResponse.getAttributes().isActive());
		return attributes;
	}
}
