package br.com.becommerce.commons.to;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * InventoryProductAction
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-02-18T04:49:14.604Z")

public class InventoryProductAction   {
  /**
   * Gets or Sets action
   */
  public enum ActionEnum {
    INCREMENT("INCREMENT"),
    
    DECREMENT("DECREMENT");

    private String value;

    ActionEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ActionEnum fromValue(String text) {
      for (ActionEnum b : ActionEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("action")
  private ActionEnum action;

  @JsonProperty("value")
  private BigDecimal value;

  @JsonProperty("productReferenceCode")
  private String productReferenceCode;



  public InventoryProductAction action(ActionEnum action) {
    this.action = action;
    return this;
  }

  /**
   * Get action
   * @return action
  **/
  @ApiModelProperty(value = "")


  public ActionEnum getAction() {
    return action;
  }

  public void setAction(ActionEnum action) {
    this.action = action;
  }

  public InventoryProductAction value(BigDecimal value) {
    this.value = value;
    return this;
  }

  /**
   * Get value
   * @return value
   **/
  @ApiModelProperty(example = "1.2", value = "")

  @Valid

  public BigDecimal getValue() {
    return value;
  }

  public void setValue(BigDecimal value) {
    this.value = value;
  }




  public InventoryProductAction productReferenceCode(String productReferenceCode) {
    this.productReferenceCode = productReferenceCode;
    return this;
  }

  /**
   * Get productReferenceCode
   * @return productReferenceCode
   **/
  @ApiModelProperty(example = "P123", value = "")

  @Valid

  public String getProductReferenceCode() {
    return productReferenceCode;
  }

  public void setProductReferenceCode(String productReferenceCode) {
    this.productReferenceCode = productReferenceCode;
  }




  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InventoryProductAction inventoryProductAction = (InventoryProductAction) o;
    return Objects.equals(this.action, inventoryProductAction.action) &&
            Objects.equals(this.productReferenceCode, inventoryProductAction.productReferenceCode) &&
            Objects.equals(this.value, inventoryProductAction.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(action, productReferenceCode, value);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InventoryProductAction {\n");

    sb.append("    action: ").append(toIndentedString(action)).append("\n");
    sb.append("    productReferenceCode: ").append(toIndentedString(productReferenceCode)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
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

