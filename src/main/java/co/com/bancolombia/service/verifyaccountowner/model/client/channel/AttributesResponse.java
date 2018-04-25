package co.com.bancolombia.service.verifyaccountowner.model.client.channel;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * AttributesTransactionResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-04-11T15:35:38.161Z")

public class AttributesResponse   {
  @JsonProperty("transactionDate")
  private String transactionDate = null;

  @JsonProperty("transactionYear")
  private String transactionYear = null;

  @JsonProperty("transactionMonth")
  private String transactionMonth = null;

  @JsonProperty("transactionDay")
  private String transactionDay = null;

  @JsonProperty("transactionTime")
  private String transactionTime = null;

  @JsonProperty("clientIp")
  private String clientIp = null;

  @JsonProperty("channelId")
  private String channelId = null;

  @JsonProperty("consumerId")
  private String consumerId = null;

  @JsonProperty("code")
  private String code = null;

  @JsonProperty("active")
  private Boolean active = null;

  public AttributesResponse transactionDate(String transactionDate) {
    this.transactionDate = transactionDate;
    return this;
  }

  /**
   * Get transactionDate
   * @return transactionDate
  **/
  @ApiModelProperty(value = "")


  public String getTransactionDate() {
    return transactionDate;
  }

  public void setTransactionDate(String transactionDate) {
    this.transactionDate = transactionDate;
  }

  public AttributesResponse transactionYear(String transactionYear) {
    this.transactionYear = transactionYear;
    return this;
  }

  /**
   * Get transactionYear
   * @return transactionYear
  **/
  @ApiModelProperty(value = "")


  public String getTransactionYear() {
    return transactionYear;
  }

  public void setTransactionYear(String transactionYear) {
    this.transactionYear = transactionYear;
  }

  public AttributesResponse transactionMonth(String transactionMonth) {
    this.transactionMonth = transactionMonth;
    return this;
  }

  /**
   * Get transactionMonth
   * @return transactionMonth
  **/
  @ApiModelProperty(value = "")


  public String getTransactionMonth() {
    return transactionMonth;
  }

  public void setTransactionMonth(String transactionMonth) {
    this.transactionMonth = transactionMonth;
  }

  public AttributesResponse transactionDay(String transactionDay) {
    this.transactionDay = transactionDay;
    return this;
  }

  /**
   * Get transactionDay
   * @return transactionDay
  **/
  @ApiModelProperty(value = "")


  public String getTransactionDay() {
    return transactionDay;
  }

  public void setTransactionDay(String transactionDay) {
    this.transactionDay = transactionDay;
  }

  public AttributesResponse transactionTime(String transactionTime) {
    this.transactionTime = transactionTime;
    return this;
  }

  /**
   * Get transactionTime
   * @return transactionTime
  **/
  @ApiModelProperty(value = "")


  public String getTransactionTime() {
    return transactionTime;
  }

  public void setTransactionTime(String transactionTime) {
    this.transactionTime = transactionTime;
  }

  public AttributesResponse clientIp(String clientIp) {
    this.clientIp = clientIp;
    return this;
  }

  /**
   * Get clientIp
   * @return clientIp
  **/
  @ApiModelProperty(value = "")


  public String getClientIp() {
    return clientIp;
  }

  public void setClientIp(String clientIp) {
    this.clientIp = clientIp;
  }

  public AttributesResponse channelId(String channelId) {
    this.channelId = channelId;
    return this;
  }

  /**
   * Get channelId
   * @return channelId
  **/
  @ApiModelProperty(value = "")


  public String getChannelId() {
    return channelId;
  }

  public void setChannelId(String channelId) {
    this.channelId = channelId;
  }

  public AttributesResponse consumerId(String consumerId) {
    this.consumerId = consumerId;
    return this;
  }

  /**
   * Get consumerId
   * @return consumerId
  **/
  @ApiModelProperty(value = "")


  public String getConsumerId() {
    return consumerId;
  }

  public void setConsumerId(String consumerId) {
    this.consumerId = consumerId;
  }

  public AttributesResponse code(String code) {
    this.code = code;
    return this;
  }

  /**
   * Get code
   * @return code
  **/
  @ApiModelProperty(value = "")


  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public AttributesResponse active(Boolean active) {
    this.active = active;
    return this;
  }

  /**
   * Get active
   * @return active
  **/
  @ApiModelProperty(value = "")


  public Boolean isActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AttributesResponse attributesTransactionResponse = (AttributesResponse) o;
    return Objects.equals(this.transactionDate, attributesTransactionResponse.transactionDate) &&
        Objects.equals(this.transactionYear, attributesTransactionResponse.transactionYear) &&
        Objects.equals(this.transactionMonth, attributesTransactionResponse.transactionMonth) &&
        Objects.equals(this.transactionDay, attributesTransactionResponse.transactionDay) &&
        Objects.equals(this.transactionTime, attributesTransactionResponse.transactionTime) &&
        Objects.equals(this.clientIp, attributesTransactionResponse.clientIp) &&
        Objects.equals(this.channelId, attributesTransactionResponse.channelId) &&
        Objects.equals(this.consumerId, attributesTransactionResponse.consumerId) &&
        Objects.equals(this.code, attributesTransactionResponse.code) &&
        Objects.equals(this.active, attributesTransactionResponse.active);
  }

  @Override
  public int hashCode() {
    return Objects.hash(transactionDate, transactionYear, transactionMonth, transactionDay, transactionTime, clientIp, channelId, consumerId, code, active);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AttributesTransactionResponse {\n");
    
    sb.append("    transactionDate: ").append(toIndentedString(transactionDate)).append("\n");
    sb.append("    transactionYear: ").append(toIndentedString(transactionYear)).append("\n");
    sb.append("    transactionMonth: ").append(toIndentedString(transactionMonth)).append("\n");
    sb.append("    transactionDay: ").append(toIndentedString(transactionDay)).append("\n");
    sb.append("    transactionTime: ").append(toIndentedString(transactionTime)).append("\n");
    sb.append("    clientIp: ").append(toIndentedString(clientIp)).append("\n");
    sb.append("    channelId: ").append(toIndentedString(channelId)).append("\n");
    sb.append("    consumerId: ").append(toIndentedString(consumerId)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    active: ").append(toIndentedString(active)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

