package co.com.bancolombia.service.verifyaccountowner.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * VerifyAccountRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-04-11T14:17:45.456Z")

public class VerifyAccountRequest   {
  @JsonProperty("header")
  private Header header = null;

  @JsonProperty("productType")
  private String productType = null;

  @JsonProperty("productNumber")
  private String productNumber = null;

  @JsonProperty("beneficiaryDocumentType")
  private String beneficiaryDocumentType = null;

  @JsonProperty("beneficiaryDocument")
  private String beneficiaryDocument = null;

  @JsonProperty("transactionDate")
  private String transactionDate = null;

  @JsonProperty("clientIp")
  private String clientIp = null;

  @JsonProperty("channelId")
  private String channelId = null;

  @JsonProperty("consumerId")
  private String consumerId = null;

  @JsonProperty("messageId")
  private String messageId = null;

  @JsonProperty("otp")
  private String otp = null;

  @JsonProperty("businessDocumentType")
  private String businessDocumentType = null;

  @JsonProperty("businessDocument")
  private String businessDocument = null;

  @JsonProperty("clientDocumentType")
  private String clientDocumentType = null;

  @JsonProperty("clientDocument")
  private String clientDocument = null;

  public VerifyAccountRequest header(Header header) {
    this.header = header;
    return this;
  }

  /**
   * Get header
   * @return header
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public Header getHeader() {
    return header;
  }

  public void setHeader(Header header) {
    this.header = header;
  }

  public VerifyAccountRequest productType(String productType) {
    this.productType = productType;
    return this;
  }

  /**
   * Get productType
   * @return productType
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getProductType() {
    return productType;
  }

  public void setProductType(String productType) {
    this.productType = productType;
  }

  public VerifyAccountRequest productNumber(String productNumber) {
    this.productNumber = productNumber;
    return this;
  }

  /**
   * Get productNumber
   * @return productNumber
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getProductNumber() {
    return productNumber;
  }

  public void setProductNumber(String productNumber) {
    this.productNumber = productNumber;
  }

  public VerifyAccountRequest beneficiaryDocumentType(String beneficiaryDocumentType) {
    this.beneficiaryDocumentType = beneficiaryDocumentType;
    return this;
  }

  /**
   * Get beneficiaryDocumentType
   * @return beneficiaryDocumentType
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getBeneficiaryDocumentType() {
    return beneficiaryDocumentType;
  }

  public void setBeneficiaryDocumentType(String beneficiaryDocumentType) {
    this.beneficiaryDocumentType = beneficiaryDocumentType;
  }

  public VerifyAccountRequest beneficiaryDocument(String beneficiaryDocument) {
    this.beneficiaryDocument = beneficiaryDocument;
    return this;
  }

  /**
   * Get beneficiaryDocument
   * @return beneficiaryDocument
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getBeneficiaryDocument() {
    return beneficiaryDocument;
  }

  public void setBeneficiaryDocument(String beneficiaryDocument) {
    this.beneficiaryDocument = beneficiaryDocument;
  }

  public VerifyAccountRequest transactionDate(String transactionDate) {
    this.transactionDate = transactionDate;
    return this;
  }

  /**
   * Get transactionDate
   * @return transactionDate
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getTransactionDate() {
    return transactionDate;
  }

  public void setTransactionDate(String transactionDate) {
    this.transactionDate = transactionDate;
  }

  public VerifyAccountRequest clientIp(String clientIp) {
    this.clientIp = clientIp;
    return this;
  }

  /**
   * Get clientIp
   * @return clientIp
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getClientIp() {
    return clientIp;
  }

  public void setClientIp(String clientIp) {
    this.clientIp = clientIp;
  }

  public VerifyAccountRequest channelId(String channelId) {
    this.channelId = channelId;
    return this;
  }

  /**
   * Get channelId
   * @return channelId
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getChannelId() {
    return channelId;
  }

  public void setChannelId(String channelId) {
    this.channelId = channelId;
  }

  public VerifyAccountRequest consumerId(String consumerId) {
    this.consumerId = consumerId;
    return this;
  }

  /**
   * Get consumerId
   * @return consumerId
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getConsumerId() {
    return consumerId;
  }

  public void setConsumerId(String consumerId) {
    this.consumerId = consumerId;
  }

  public VerifyAccountRequest messageId(String messageId) {
    this.messageId = messageId;
    return this;
  }

  /**
   * Get messageId
   * @return messageId
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getMessageId() {
    return messageId;
  }

  public void setMessageId(String messageId) {
    this.messageId = messageId;
  }

  public VerifyAccountRequest otp(String otp) {
    this.otp = otp;
    return this;
  }

  /**
   * Get otp
   * @return otp
  **/
  @ApiModelProperty(value = "")


  public String getOtp() {
    return otp;
  }

  public void setOtp(String otp) {
    this.otp = otp;
  }

  public VerifyAccountRequest businessDocumentType(String businessDocumentType) {
    this.businessDocumentType = businessDocumentType;
    return this;
  }

  /**
   * Get businessDocumentType
   * @return businessDocumentType
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getBusinessDocumentType() {
    return businessDocumentType;
  }

  public void setBusinessDocumentType(String businessDocumentType) {
    this.businessDocumentType = businessDocumentType;
  }

  public VerifyAccountRequest businessDocument(String businessDocument) {
    this.businessDocument = businessDocument;
    return this;
  }

  /**
   * Get businessDocument
   * @return businessDocument
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getBusinessDocument() {
    return businessDocument;
  }

  public void setBusinessDocument(String businessDocument) {
    this.businessDocument = businessDocument;
  }

  public VerifyAccountRequest clientDocumentType(String clientDocumentType) {
    this.clientDocumentType = clientDocumentType;
    return this;
  }

  /**
   * Get clientDocumentType
   * @return clientDocumentType
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getClientDocumentType() {
    return clientDocumentType;
  }

  public void setClientDocumentType(String clientDocumentType) {
    this.clientDocumentType = clientDocumentType;
  }

  public VerifyAccountRequest clientDocument(String clientDocument) {
    this.clientDocument = clientDocument;
    return this;
  }

  /**
   * Get clientDocument
   * @return clientDocument
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getClientDocument() {
    return clientDocument;
  }

  public void setClientDocument(String clientDocument) {
    this.clientDocument = clientDocument;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VerifyAccountRequest verifyAccountRequest = (VerifyAccountRequest) o;
    return Objects.equals(this.header, verifyAccountRequest.header) &&
        Objects.equals(this.productType, verifyAccountRequest.productType) &&
        Objects.equals(this.productNumber, verifyAccountRequest.productNumber) &&
        Objects.equals(this.beneficiaryDocumentType, verifyAccountRequest.beneficiaryDocumentType) &&
        Objects.equals(this.beneficiaryDocument, verifyAccountRequest.beneficiaryDocument) &&
        Objects.equals(this.transactionDate, verifyAccountRequest.transactionDate) &&
        Objects.equals(this.clientIp, verifyAccountRequest.clientIp) &&
        Objects.equals(this.channelId, verifyAccountRequest.channelId) &&
        Objects.equals(this.consumerId, verifyAccountRequest.consumerId) &&
        Objects.equals(this.messageId, verifyAccountRequest.messageId) &&
        Objects.equals(this.otp, verifyAccountRequest.otp) &&
        Objects.equals(this.businessDocumentType, verifyAccountRequest.businessDocumentType) &&
        Objects.equals(this.businessDocument, verifyAccountRequest.businessDocument) &&
        Objects.equals(this.clientDocumentType, verifyAccountRequest.clientDocumentType) &&
        Objects.equals(this.clientDocument, verifyAccountRequest.clientDocument);
  }

  @Override
  public int hashCode() {
    return Objects.hash(header, productType, productNumber, beneficiaryDocumentType, beneficiaryDocument, transactionDate, clientIp, channelId, consumerId, messageId, otp, businessDocumentType, businessDocument, clientDocumentType, clientDocument);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VerifyAccountRequest {\n");
    
    sb.append("    header: ").append(toIndentedString(header)).append("\n");
    sb.append("    productType: ").append(toIndentedString(productType)).append("\n");
    sb.append("    productNumber: ").append(toIndentedString(productNumber)).append("\n");
    sb.append("    beneficiaryDocumentType: ").append(toIndentedString(beneficiaryDocumentType)).append("\n");
    sb.append("    beneficiaryDocument: ").append(toIndentedString(beneficiaryDocument)).append("\n");
    sb.append("    transactionDate: ").append(toIndentedString(transactionDate)).append("\n");
    sb.append("    clientIp: ").append(toIndentedString(clientIp)).append("\n");
    sb.append("    channelId: ").append(toIndentedString(channelId)).append("\n");
    sb.append("    consumerId: ").append(toIndentedString(consumerId)).append("\n");
    sb.append("    messageId: ").append(toIndentedString(messageId)).append("\n");
    sb.append("    otp: ").append(toIndentedString(otp)).append("\n");
    sb.append("    businessDocumentType: ").append(toIndentedString(businessDocumentType)).append("\n");
    sb.append("    businessDocument: ").append(toIndentedString(businessDocument)).append("\n");
    sb.append("    clientDocumentType: ").append(toIndentedString(clientDocumentType)).append("\n");
    sb.append("    clientDocument: ").append(toIndentedString(clientDocument)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

