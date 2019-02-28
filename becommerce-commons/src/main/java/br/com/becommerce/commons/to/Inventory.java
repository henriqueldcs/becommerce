package br.com.becommerce.commons.to;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Inventory
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-02-18T04:49:14.604Z")

public class Inventory   {
  @JsonProperty("productReferenceCode")
  private String productReferenceCode = null;

  @JsonProperty("amount")
  private BigDecimal amount = null;

  public Inventory productReferenceCode(String productReferenceCode) {
    this.productReferenceCode = productReferenceCode;
    return this;
  }

  /**
   * Get productReferenceCode
   * @return productReferenceCode
  **/
  @ApiModelProperty(example = "P123", required = true, value = "")
  @NotNull


  public String getProductReferenceCode() {
    return productReferenceCode;
  }

  public void setProductReferenceCode(String productReferenceCode) {
    this.productReferenceCode = productReferenceCode;
  }

  public Inventory amount(BigDecimal amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Get amount
   * @return amount
  **/
  @ApiModelProperty(example = "7.5", required = true, value = "")
  @NotNull

  @Valid

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Inventory inventory = (Inventory) o;
    return Objects.equals(this.productReferenceCode, inventory.productReferenceCode) &&
        Objects.equals(this.amount, inventory.amount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productReferenceCode, amount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Inventory {\n");
    
    sb.append("    productReferenceCode: ").append(toIndentedString(productReferenceCode)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
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

