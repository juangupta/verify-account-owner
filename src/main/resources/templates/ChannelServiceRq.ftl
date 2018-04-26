{
  "data": [
    {
      "type": "channel",
      "id": "${body['Id']}",
      "attributes": {
		"clientDocumentType": "${body['ClientDocumentType']}",
        "clientDocument": "${body['ClientDocument']}",
        "businessDocumentType": "${body['BusinessDocumentType']}",
        "businessDocument": "${body['BusinessDocument']}"
		}
	  },
      {
      "type": "Transaction",
      "id": "${body['MessageId']}",
      "attributes": {
        "transactionDate": "${body['TransactionDate']}",
        "clientIp": "${body['ClientIp']}",
        "channelId": "${body['ChannelId']}",
        "consumerId": "${body['ConsumerId']}"
      }
    }
  ]
}