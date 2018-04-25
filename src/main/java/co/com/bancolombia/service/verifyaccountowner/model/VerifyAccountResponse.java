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
 * VerifyAccountResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-04-11T14:17:45.456Z")

public class VerifyAccountResponse   {
  @JsonProperty("header")
  private Header header = null;

  @JsonProperty("transactionDate")
  private String transactionDate = null;

  @JsonProperty("messageId")
  private String messageId = null;

  @JsonProperty("ownerAccountName")
  private String ownerAccountName = null;

  public VerifyAccountResponse header(Header header) {
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

  public VerifyAccountResponse transactionDate(String transactionDate) {
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

  public VerifyAccountResponse messageId(String messageId) {
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

  public VerifyAccountResponse ownerAccountName(String ownerAccountName) {
    this.ownerAccountName = ownerAccountName;
    return this;
  }

  /**
   * Get ownerAccountName
   * @return ownerAccountName
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getOwnerAccountName() {
    return ownerAccountName;
  }

  public void setOwnerAccountName(String ownerAccountName) {
    this.ownerAccountName = ownerAccountName;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VerifyAccountResponse verifyAccountResponse = (VerifyAccountResponse) o;
    return Objects.equals(this.header, verifyAccountResponse.header) &&
        Objects.equals(this.transactionDate, verifyAccountResponse.transactionDate) &&
        Objects.equals(this.messageId, verifyAccountResponse.messageId) &&
        Objects.equals(this.ownerAccountName, verifyAccountResponse.ownerAccountName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(header, transactionDate, messageId, ownerAccountName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VerifyAccountResponse {\n");
    
    sb.append("    header: ").append(toIndentedString(header)).append("\n");
    sb.append("    transactionDate: ").append(toIndentedString(transactionDate)).append("\n");
    sb.append("    messageId: ").append(toIndentedString(messageId)).append("\n");
    sb.append("    ownerAccountName: ").append(toIndentedString(ownerAccountName)).append("\n");
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

