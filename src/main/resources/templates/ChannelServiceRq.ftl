{
  "data": [
    {
      "type": "channel",
      "id": "${body.getHeader().getId()}",
      "attributes": {
		"clientDocumentType": "${body.getClientDocumentType()}",
        "clientDocument": "${body.getClientDocument()}",
        "businessDocumentType": "${body.getBusinessDocumentType()}",
        "businessDocument": "${body.getBusinessDocument()}"
		}
	  },
      {
      "type": "Transaction",
      "id": "${body.getMessageId()}",
      "attributes": {
        "transactionDate": "${body.getTransactionDate()}",
        "clientIp": "${body.getClientIp()}",
        "channelId": "${body.getChannelId()}",
        "consumerId": "${body.getConsumerId()}"
      }
    }
  ]
}