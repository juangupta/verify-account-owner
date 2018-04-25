package co.com.bancolombia.service.verifyaccountowner.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * jsonApiResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-04-11T14:17:45.456Z")

public class JsonApiResponse   {
  @JsonProperty("data")
  private List<VerifyAccountResponse> data;
  
  @JsonProperty("errors")
  private List<JsonApiError> errors;

  public JsonApiResponse data(List<VerifyAccountResponse> data) {
    this.data = data;
    return this;
  }

  public JsonApiResponse addDataItem(VerifyAccountResponse dataItem) {
    this.data.add(dataItem);
    return this;
  }

  /**
   * Get data
   * @return data
  **/

  public List<VerifyAccountResponse> getData() {
    return data;
  }

  public void setData(List<VerifyAccountResponse> data) {
    this.data = data;
  }

  /**
   * Get errors
   * @return errors
  **/
  public List<JsonApiError> getErrors() {
	return errors;
}

  public void setErrors(List<JsonApiError> errors) {
	this.errors = errors;
}

  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    JsonApiResponse jsonApiResponse = (JsonApiResponse) o;
    return Objects.equals(this.data, jsonApiResponse.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class jsonApiResponse {\n");
    
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
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

